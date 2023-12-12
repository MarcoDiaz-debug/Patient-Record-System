package grprs;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class justMain extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Patient & History Management");

        initRootLayout();


        showPatientOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(justMain.class.getResource("/Main.java"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the patient overview into the root layout.
     */
    public void showPatientOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(justMain.class.getResource("patientRecord.fxml"));
            AnchorPane patientOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.getChildren().clear();
            rootLayout.getChildren().add(patientOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHistoryOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(justMain.class.getResource("patientHistory.fxml"));
            AnchorPane historyOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.getChildren().clear();
            rootLayout.getChildren().add(historyOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        launch(args);
    }
}