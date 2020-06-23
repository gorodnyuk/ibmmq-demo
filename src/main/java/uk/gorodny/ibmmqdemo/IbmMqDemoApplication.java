package uk.gorodny.ibmmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class IbmMqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbmMqDemoApplication.class, args);
	}

}
