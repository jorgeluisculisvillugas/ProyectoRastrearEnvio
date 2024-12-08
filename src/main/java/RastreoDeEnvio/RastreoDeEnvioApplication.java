package RastreoDeEnvio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class RastreoDeEnvioApplication {

	public static void main(String[] args) {
		SpringApplication.run(RastreoDeEnvioApplication.class, args);

		// Iniciar JavaFX
        new Thread(() -> Application.launch(TrackPackagesApp.class, args)).start();
	}

}
