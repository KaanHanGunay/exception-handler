package tr.com.kaanhangunay.examples.exception_handler.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tr.com.kaanhangunay.examples.messages.QueueExceptionResolvedMessage;
import tr.com.kaanhangunay.examples.models.GenericApplicationEvent;

@Component
public class QueueExceptionResolvedListener {
  @EventListener(
      condition =
          "#message.body instanceof T(tr.com.kaanhangunay.examples.messages.QueueExceptionResolvedMessage)")
  public void queueExceptionListener(
      GenericApplicationEvent<QueueExceptionResolvedMessage> message) {
    QueueExceptionResolvedMessage event = message.body();
    ExceptionService.queueExceptions.stream()
        .filter(q -> q.getUuid().equals(event.getMessageId()))
        .findFirst()
        .ifPresent(ExceptionService.queueExceptions::remove);
  }
}
