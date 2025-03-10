package tr.com.kaanhangunay.examples.exception_handler.service;

import java.util.ArrayList;
import java.util.List;
import tr.com.kaanhangunay.examples.exception_handler.model.QueueException;
import tr.com.kaanhangunay.examples.exception_handler.model.QueueNotReadableException;

public class ExceptionService {
  public static final List<QueueException> queueExceptions = new ArrayList<>();
  public static final List<QueueNotReadableException> queueNotReadableExceptions =
      new ArrayList<>();
}
