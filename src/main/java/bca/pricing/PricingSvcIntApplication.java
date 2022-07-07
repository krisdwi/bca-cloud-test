package bca.pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import com.bca.aini.EnableAINI;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class })
@ComponentScan({ "bca.pricing" })
@EnableAsync
@EnableAINI
public class PricingSvcIntApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricingSvcIntApplication.class, args);
	}

}
