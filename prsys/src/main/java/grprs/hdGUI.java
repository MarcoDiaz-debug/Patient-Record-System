package grprs;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class hdGUI extends Application {

    private ObservableList<DataEntry> dataList;
    private TextField lastNameTextField;
    private TextField firstNameTextField;
    private TextField middleInitialTextField;
    private TextField DateofBirthTextField;
    private TextField MRNTextField;
    private TextField SexTextField;
    private TextField PatientNumTextField;
    private TextField emerNumTextField;
    private TextField emailAddTextField;
    private TextField ProvinceTextField;
    private TextField CityTextField;
    private TextField AddressTextField;
    private TextField MartialStatusTextField;
    private TextField OccupationTextField;
    private TextField AllergiesTextField;
    private TextField CurrentMedTextField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        dataList = FXCollections.observableArrayList();

        Label dateLabel = new Label("Date: " + getCurrentDate());
        Label dateLabel2 = new Label("Patient's Name: ");
        Label DateofBirth = new Label("Date of Birth");
        Label MRN = new Label("MRN");
        Label Sex = new Label("Sex");
        Label PatientNum = new Label("Patient's Phone no.");
        Label emerNum = new Label("Emerg. contact no.");
        Label emailAdd = new Label("Email Address");
        Label Province = new Label("Province");
        Label City = new Label("City");
        Label Address = new Label("Address");
        Label MartialStatus = new Label("Martial Status");
        Label Occupation = new Label("Occupation");
        Label Allergies = new Label("Allergies");
        Label CurrentMed = new Label("Current Medication");

        lastNameTextField = new TextField();
        firstNameTextField = new TextField();
        middleInitialTextField = new TextField();
        DateofBirthTextField = new TextField();
        MRNTextField = new TextField();
        SexTextField = new TextField();
        PatientNumTextField = new TextField();
        emerNumTextField = new TextField();
        emailAddTextField = new TextField();
        ProvinceTextField = new TextField();
        CityTextField = new TextField();
        AddressTextField = new TextField();
        MartialStatusTextField = new TextField();
        OccupationTextField = new TextField();
        AllergiesTextField = new TextField();
        CurrentMedTextField = new TextField();

        GridPane infoGrid = new GridPane();
        infoGrid.setHgap(5);
        infoGrid.setVgap(10);
        infoGrid.addRow(0, dateLabel2);
        infoGrid.addRow(1, lastNameTextField, firstNameTextField, middleInitialTextField);
        infoGrid.addRow(2, DateofBirth, MRN, Sex, PatientNum);
        infoGrid.addRow(3, DateofBirthTextField, MRNTextField, SexTextField, PatientNumTextField);
        infoGrid.addRow(4, emerNum, emailAdd, Province);
        infoGrid.addRow(5, emerNumTextField, emailAddTextField, ProvinceTextField);
        infoGrid.addRow(6, City, Address, MartialStatus, Occupation);
        infoGrid.addRow(7, CityTextField, AddressTextField, MartialStatusTextField, OccupationTextField);
        infoGrid.addRow(8, Allergies, CurrentMed);
        infoGrid.addRow(9, AllergiesTextField, CurrentMedTextField);

        // Create Next button
        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> nextButtonClicked());

        // Create an HBox for the button
        HBox buttonBox = new HBox(nextButton);
        buttonBox.setPadding(new Insets(10));

        // Combine everything in a VBox
        VBox root = new VBox();
        root.getChildren().addAll(dateLabel, infoGrid, buttonBox);

        Scene scene = new Scene(root, 950, 550);

        primaryStage.setTitle("Patient Record (JavaFX)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    private void nextButtonClicked() {
        String lastName = lastNameTextField.getText();
        String firstName = firstNameTextField.getText();
        String middleInitial = middleInitialTextField.getText();
        String DateofBirth = DateofBirthTextField.getText();
        String MRN = MRNTextField.getText();
        String Sex = SexTextField.getText();
        String PatientNum = PatientNumTextField.getText();
        String emerNum = emerNumTextField.getText();
        String emailAdd = emailAddTextField.getText();
        String Province = ProvinceTextField.getText();
        String City = CityTextField.getText();
        String Address = AddressTextField.getText();
        String MartialStatus = MartialStatusTextField.getText();
        String Occupation = OccupationTextField.getText();
        String Allergies = AllergiesTextField.getText();
        String CurrentMed = CurrentMedTextField.getText();

       if (lastName.isEmpty() || firstName.isEmpty() || middleInitial.isEmpty() || DateofBirth.isEmpty() ||
        MRN.isEmpty() || Sex.isEmpty() || PatientNum.isEmpty() || emerNum.isEmpty() || emailAdd.isEmpty() ||
        Province.isEmpty() || City.isEmpty() || Address.isEmpty() || MartialStatus.isEmpty() ||
        Occupation.isEmpty() || Allergies.isEmpty() || CurrentMed.isEmpty()) {
        showAlert("Please fill in all required fields.");
        return;
        }  
            if (middleInitial.length() == 1 && Character.isLetter(middleInitial.charAt(0))) {
                DataEntry newEntry = new DataEntry(lastName, firstName, middleInitial, DateofBirth, MRN, Sex, PatientNum, emerNum,
            emailAdd, Province, City, Address, MartialStatus, Occupation, Allergies, CurrentMed);

              dataList.add(newEntry);
              clearInputFields();
              updateTable();
              showAlert("Information saved successfully!");
            } 
            else {
              showAlert("Please enter a valid single letter for the middle initial.");
            }   
        }

private void updateTable() {
    
    }

private void showAlert(String message) {
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Note");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
    }
    

    private void clearInputFields() {
        lastNameTextField.clear();
        firstNameTextField.clear();
        middleInitialTextField.clear();
        DateofBirthTextField.clear();
        MRNTextField.clear();
        SexTextField.clear();
        PatientNumTextField.clear();
        emerNumTextField.clear();
        emailAddTextField.clear();
        ProvinceTextField.clear();
        CityTextField.clear();
        AddressTextField.clear();
        MartialStatusTextField.clear();
        OccupationTextField.clear();
        AllergiesTextField.clear();
        CurrentMedTextField.clear();
    }

    private static class DataEntry {
        private String lastName;
        private String firstName;
        private String middleInitial;
        private String DateofBirth;
        private String MRN;
        private String Sex;
        private String PatientNum;
        private String emerNum;
        private String emailAdd;
        private String Province;
        private String City;
        private String Address;
        private String MartialStatus;
        private String Occupation;
        private String Allergies;
        private String CurrentMed;

        public DataEntry(String lastName, String firstName, String middleInitial,
                         String DateofBirth, String MRN, String Sex, String PatientNum,
                         String emerNum, String emailAdd, String Province, String City,
                         String Address, String MartialStatus, String Occupation,
                         String Allergies, String CurrentMed) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleInitial = middleInitial;
            this.DateofBirth = DateofBirth;
            this.MRN = MRN;
            this.Sex = Sex;
            this.PatientNum = PatientNum;
            this.emerNum = emerNum;
            this.emailAdd = emailAdd;
            this.Province = Province;
            this.City = City;
            this.Address = Address;
            this.MartialStatus = MartialStatus;
            this.Occupation = Occupation;
            this.Allergies = Allergies;
            this.CurrentMed = CurrentMed;
        }
    }
}

