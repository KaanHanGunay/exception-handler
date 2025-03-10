package tr.com.kaanhangunay.examples.exception_handler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tr.com.kaanhangunay.examples.exception_handler.model.QueueException;
import tr.com.kaanhangunay.examples.messages.BaseQueueMessage;
import tr.com.kaanhangunay.examples.models.Destination;
import tr.com.kaanhangunay.examples.models.QueueMessageWrapper;
import tr.com.kaanhangunay.examples.service.MessageSenderService;

@Service
public class ExceptionsRetryService {
  private final ObjectMapper objectMapper;
  private final MessageSenderService messageSenderService;

  public ExceptionsRetryService(
      ObjectMapper objectMapper, MessageSenderService messageSenderService) {
    this.objectMapper = objectMapper;
    this.messageSenderService = messageSenderService;
  }

  @SuppressWarnings("unchecked")
  @Scheduled(cron = "0 * * * * *")
  public <T extends BaseQueueMessage> void retryExceptions() throws JsonProcessingException {
    List<QueueException> queueExceptions =
        ExceptionService.queueExceptions.stream()
            .filter(ex -> !ex.getResolved() && ex.getTryCount() < 5)
            .toList();
    for (QueueException queueException : queueExceptions) {
      T event =
          (T)
              objectMapper.readValue(
                  queueException.getBody(), queueException.getType().getMessageClass());
      QueueMessageWrapper<T> message = new QueueMessageWrapper<>();
      message.setMessageId(queueException.getUuid());
      message.setType(queueException.getType());
      message.setBody(event);
      message.setSource(queueException.getSource());
      message.setRetry(true);
      messageSenderService.to(message, queueException.getDestination());
    }
  }
}
