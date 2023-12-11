package grprs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Controller {

    private Main mainApp;

    @FXML
    private void patientHistory(ActionEvent event) throws IOException {
        mainApp.switchView("patientHistory.fxml");
    }

    @FXML
    private void patientRecord(ActionEvent event) throws IOException {
        mainApp.switchView("patientRecord.fxml");
    }

    @FXML
    private void RemovedPatient(ActionEvent event) throws IOException {
        mainApp.switchView("removedPatient.fxml");
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
