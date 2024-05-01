package ai.acintyo.ezykle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(exclude = {JacksonAutoConfiguration.class })
@EnableScheduling
public class AcintyoEzykleApiApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(AcintyoEzykleApiApplication.class, args);
		
	}

}
