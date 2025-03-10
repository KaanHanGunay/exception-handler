package tr.com.kaanhangunay.examples.exception_handler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tr.com.kaanhangunay.examples.exception_handler.model.QueueException;
import tr.com.kaanhangunay.examples.messages.QueueExceptionMessage;
import tr.com.kaanhangunay.examples.models.GenericApplicationEvent;

@Component
public class QueueExceptionHandlerListener {
  private final ObjectMapper objectMapper;

  public QueueExceptionHandlerListener(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @EventListener(
      condition =
          "#message.body instanceof T(tr.com.kaanhangunay.examples.messages.QueueExceptionMessage)")
  public void queueExceptionListener(GenericApplicationEvent<QueueExceptionMessage> message)
      throws JsonProcessingException {
    QueueExceptionMessage event = message.body();
    String convertValue = objectMapper.writeValueAsString(event.getBody());
    QueueException queueException =
        ExceptionService.queueExceptions.stream()
            .filter(
                ex ->
                    ex.getUuid().equals(event.getMessageId())
                        && ex.getDestination().equals(event.getDestination()))
            .findFirst()
            .orElse(null);
    if (queueException == null) {
      QueueException newException = new QueueException();
      newException.setUuid(event.getMessageId());
      newException.setDestination(event.getDestination());
      newException.setBody(convertValue);
      newException.setException(event.getException());
      newException.setResolved(false);
      newException.setType(event.getType());
      newException.setSource(event.getSource());
      ExceptionService.queueExceptions.add(newException);
      queueException = newException;
    }
    queueException.setTryCount(queueException.getTryCount() + 1);

    if (queueException.getTryCount() >= 5) {
      /* Send notification to admin */
    }
  }
}
