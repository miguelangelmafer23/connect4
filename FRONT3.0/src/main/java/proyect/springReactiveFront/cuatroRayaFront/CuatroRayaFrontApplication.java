package proyect.springReactiveFront.cuatroRayaFront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import proyect.springReactiveFront.cuatroRayaFront.domain.JugadorClient;

@SpringBootApplication
public class CuatroRayaFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuatroRayaFrontApplication.class, args);
	}

}
