package tr.com.kaanhangunay.examples.exception_handler.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tr.com.kaanhangunay.examples.exception_handler.model.QueueNotReadableException;
import tr.com.kaanhangunay.examples.messages.QueueNotReadableExceptionMessage;
import tr.com.kaanhangunay.examples.models.GenericApplicationEvent;

@Component
public class QueueNotReadableExceptionHandlerListener {
  @EventListener(
      condition =
          "#message.body instanceof T(tr.com.kaanhangunay.examples.messages.QueueNotReadableExceptionMessage)")
  public void queueNotReadExceptionListener(
      GenericApplicationEvent<QueueNotReadableExceptionMessage> message) {
    QueueNotReadableExceptionMessage event = message.body();
    QueueNotReadableException queueNotReadException = new QueueNotReadableException();
    queueNotReadException.setUuid(message.messageId());
    queueNotReadException.setBody(event.getBody());
    queueNotReadException.setDestination(event.getDestination());
    queueNotReadException.setException(event.getException());
    ExceptionService.queueNotReadableExceptions.add(queueNotReadException);
    /* Send Notification to Admin */
  }
}
