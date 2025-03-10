package tr.com.kaanhangunay.examples.exception_handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ExceptionHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionHandlerApplication.class, args);
	}

}
