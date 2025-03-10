package tr.com.kaanhangunay.examples.exception_handler.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class QueueNotReadableException {
  private UUID uuid;
  private String destination;
  private String body;
  private String exception;
  private LocalDateTime createdDate = LocalDateTime.now();

  public QueueNotReadableException() {}

  public QueueNotReadableException(
      UUID uuid, String destination, String body, String exception, LocalDateTime createdDate) {
    this.uuid = uuid;
    this.destination = destination;
    this.body = body;
    this.exception = exception;
    this.createdDate = createdDate;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getException() {
    return exception;
  }

  public void setException(String exception) {
    this.exception = exception;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }
}
