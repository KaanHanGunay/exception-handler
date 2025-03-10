package tr.com.kaanhangunay.examples.exception_handler.model;

import java.time.LocalDateTime;
import java.util.UUID;
import tr.com.kaanhangunay.examples.models.QueueMessageType;

public class QueueException {
  private UUID uuid;
  private String destination;
  private int tryCount = 0;
  private String body;
  private String exception;
  private Boolean checkControlStatus;
  private Boolean isResolved;
  private LocalDateTime createdDate = LocalDateTime.now();
  private QueueMessageType type;
  private String source;

  public QueueException() {}

  public QueueException(
      UUID uuid,
      String destination,
      int tryCount,
      String body,
      String exception,
      Boolean checkControlStatus,
      Boolean isResolved,
      LocalDateTime createdDate,
      QueueMessageType type,
      String source) {
    this.uuid = uuid;
    this.destination = destination;
    this.tryCount = tryCount;
    this.body = body;
    this.exception = exception;
    this.checkControlStatus = checkControlStatus;
    this.isResolved = isResolved;
    this.createdDate = createdDate;
    this.type = type;
    this.source = source;
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

  public int getTryCount() {
    return tryCount;
  }

  public void setTryCount(int tryCount) {
    this.tryCount = tryCount;
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

  public Boolean getCheckControlStatus() {
    return checkControlStatus;
  }

  public void setCheckControlStatus(Boolean checkControlStatus) {
    this.checkControlStatus = checkControlStatus;
  }

  public Boolean getResolved() {
    return isResolved;
  }

  public void setResolved(Boolean resolved) {
    isResolved = resolved;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public QueueMessageType getType() {
    return type;
  }

  public void setType(QueueMessageType type) {
    this.type = type;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }
}
