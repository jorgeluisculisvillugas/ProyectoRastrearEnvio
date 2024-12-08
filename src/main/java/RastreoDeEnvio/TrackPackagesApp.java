package RastreoDeEnvio;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

public class TrackPackagesApp extends Application {

    private TextField slugField;
    private TextField trackingNumberField;
    private TextArea resultArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Rastreador de Paquetes");

        slugField = new TextField();
        slugField.setPromptText("Slug del transportista (e.g., dhl)");

        trackingNumberField = new TextField();
        trackingNumberField.setPromptText("Número de rastreo");

        Button trackButton = new Button("Rastrear Paquete");
        trackButton.setOnAction(event -> trackPackage());

        resultArea = new TextArea();
        resultArea.setEditable(false);

        VBox layout = new VBox(10, slugField, trackingNumberField, trackButton, resultArea);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 400, 300);

        stage.setScene(scene);
        stage.show();
    }

    private void trackPackage() {
        String slug = slugField.getText();
        String trackingNumber = trackingNumberField.getText();

        if (slug.isEmpty() || trackingNumber.isEmpty()) {
            resultArea.setText("Por favor, completa ambos campos.");
            return;
        }

        String apiUrl = "http://localhost:8080/api/track/" + slug + "/" + trackingNumber;

        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.getForObject(apiUrl, String.class);
            resultArea.setText(response);
        } catch (Exception e) {
            resultArea.setText("Error al rastrear el paquete.");
        }
    }
}