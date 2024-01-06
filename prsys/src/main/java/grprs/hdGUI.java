package grprs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// import java.util.HashMap;

public class hdGUI extends Application {
    private Stage primaryStage;
    private Stage secondaryStage;
    private Stage tertiaryStage;

    private ObservableList<DataEntry> patientHistoryList;

    private ObservableList<DataEntry> dataList;
    private TextField firstNameTextField;
    private TextField middleInitialTextField;
    private TextField lastNameTextField;
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
    private TextField DateofAdmission;
    private TextField HospitalRoomNum;
    private TextField AttendingPhysician;
    private TextField Diagnosis;
    private TextField ExamFindings;
    private TextField TreatmentPlan;
    private TextField Progress;
    private TextField DischargeSummary;
    // 24

    public hdGUI() {
        patientHistoryList = FXCollections.observableArrayList();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.secondaryStage = new Stage();
        this.tertiaryStage = new Stage();

        primaryStage.setTitle("First Stage");
        secondaryStage.setTitle("Second Stage");
        tertiaryStage.setTitle("Third Stage");

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

        CheckBox tabaCheckBox = new CheckBox("use tabacco");
        CheckBox alcohoCheckBox = new CheckBox("drink alcohol");

        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> nextButtonClicked());

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
        infoGrid.addRow(10, tabaCheckBox, alcohoCheckBox, nextButton);

        VBox root = new VBox();
        root.getChildren().addAll(dateLabel, infoGrid);

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

        if (lastName.isEmpty() || firstName.isEmpty() || DateofBirth.isEmpty() ||
                MRN.isEmpty() || Sex.isEmpty() || PatientNum.isEmpty() || emerNum.isEmpty() || emailAdd.isEmpty() ||
                Province.isEmpty() || City.isEmpty() || Address.isEmpty() || MartialStatus.isEmpty() ||
                Occupation.isEmpty() || Allergies.isEmpty() || CurrentMed.isEmpty()) {
            showAlert("Please fill in all required fields.");
            return;
        } else {
            showSecondStage();
        }
    }

    // Second stage
    private void showSecondStage() {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> backToFirstStage());
        Button nextButton2 = new Button("Next");
        nextButton2.setOnAction(e -> nextButtonClicked2());

        secondaryStage.show();
        primaryStage.hide();

        Label dateofAdmission = new Label("Date of Admission");
        Label hospitalRoomNum = new Label(" Hospital Room no.");
        Label attendingPhysician = new Label("Attending Physician");
        Label diagnosis = new Label("Diagnosis");
        Label examFindings = new Label("Physicl Examination Findings");
        Label treatmentPlan = new Label("Treatment Plan");
        Label progress = new Label("Progress");
        Label dischargeSummary = new Label("Discharge Summary");

        DateofAdmission = new TextField();
        HospitalRoomNum = new TextField();
        AttendingPhysician = new TextField();
        Diagnosis = new TextField();
        ExamFindings = new TextField();
        TreatmentPlan = new TextField();
        Progress = new TextField();
        DischargeSummary = new TextField();

        GridPane infoGrid2 = new GridPane();
        infoGrid2.setHgap(5);
        infoGrid2.setVgap(10);
        infoGrid2.addRow(0, dateofAdmission, hospitalRoomNum);
        infoGrid2.addRow(1, DateofAdmission, HospitalRoomNum);
        infoGrid2.addRow(2, attendingPhysician, diagnosis);
        infoGrid2.addRow(3, AttendingPhysician, Diagnosis);
        infoGrid2.addRow(4, examFindings, treatmentPlan);
        infoGrid2.addRow(5, ExamFindings, TreatmentPlan);
        infoGrid2.addRow(6, progress, dischargeSummary);
        infoGrid2.addRow(7, Progress, DischargeSummary);
        infoGrid2.addRow(8, backButton, nextButton2);

        VBox root2 = new VBox();
        root2.getChildren().addAll(infoGrid2);

        Scene scene = new Scene(root2, 550, 400);

        secondaryStage.setTitle("Patient Record (JavaFX)");
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }

    private void nextButtonClicked2() {
        String DoA = DateofAdmission.getText();
        String HRN = HospitalRoomNum.getText();
        String AP = AttendingPhysician.getText();
        String D = Diagnosis.getText();
        String EF = ExamFindings.getText();
        String TP = TreatmentPlan.getText();
        String P = Progress.getText();
        String DS = DischargeSummary.getText();

        if (DoA.isEmpty() || HRN.isEmpty() || AP.isEmpty() ||
                D.isEmpty() || EF.isEmpty() || TP.isEmpty() || P.isEmpty() || DS.isEmpty()) {
            showAlert("Please fill in all required fields.");
            return;
        } else {
            showThirdStage();
        }
    }

    private void backToFirstStage() {
        secondaryStage.hide();
        primaryStage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Note");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

        private String DateofAdmission;
        private String HospitalRoomNum;
        private String AttendingPhysician;
        private String Diagnosis;
        private String ExamFindings;
        private String TreatmentPlan;
        private String Progress;
        private String DischargeSummary;

        public DataEntry(String lastName, String firstName, String middleInitial,
                String DateofBirth, String MRN, String Sex, String PatientNum,
                String emerNum, String emailAdd, String Province, String City,
                String Address, String MartialStatus, String Occupation,
                String Allergies, String CurrentMed, String DateofAdmission,
                String HospitalRoomNum, String AttendingPhysician, String Diagnosis,
                String ExamFindings, String TreatmentPlan, String Progress,
                String DischargeSummary) {
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
            this.DateofAdmission = DateofAdmission;
            this.HospitalRoomNum = HospitalRoomNum;
            this.AttendingPhysician = AttendingPhysician;
            this.Diagnosis = Diagnosis;
            this.ExamFindings = ExamFindings;
            this.TreatmentPlan = TreatmentPlan;
            this.Progress = Progress;
            this.DischargeSummary = DischargeSummary;
        }

        public String toString() {
            return "LastName: " + lastName + ", FirstName: " + firstName + ", DateOfBirth: " + DateofBirth +
                    ", MRN: " + MRN + ", Sex: " + Sex + ", PatientNum: " + PatientNum +
                    ", EmerNum: " + emerNum + ", EmailAdd: " + emailAdd + ", Province: " + Province +
                    ", City: " + City + ", Address: " + Address + ", MartialStatus: " + MartialStatus +
                    ", Occupation: " + Occupation + ", Allergies: " + Allergies + ", CurrentMed: " + CurrentMed +
                    "\nDateofAdmission: " + DateofAdmission + ", HospitalRoomNum: " + HospitalRoomNum +
                    ", AttendingPhysician: " + AttendingPhysician + ", Diagnosis: " + Diagnosis +
                    ", ExamFindings: " + ExamFindings + ", TreatmentPlan: " + TreatmentPlan +
                    ", Progress: " + Progress + ", DischargeSummary: " + DischargeSummary;
        }
    }

    private void showThirdStage() {
        secondaryStage.hide();

        Label summaryLabel = new Label("Patient's Demographics and Clinical Summary");
        DataEntry currentData = new DataEntry(lastNameTextField.getText(), firstNameTextField.getText(),
                middleInitialTextField.getText(), DateofBirthTextField.getText(), MRNTextField.getText(),
                SexTextField.getText(), PatientNumTextField.getText(), emerNumTextField.getText(),
                emailAddTextField.getText(), ProvinceTextField.getText(), CityTextField.getText(),
                AddressTextField.getText(), MartialStatusTextField.getText(), OccupationTextField.getText(),
                AllergiesTextField.getText(), CurrentMedTextField.getText(), DateofAdmission.getText(),
                HospitalRoomNum.getText(), AttendingPhysician.getText(), Diagnosis.getText(),
                ExamFindings.getText(), TreatmentPlan.getText(), Progress.getText(), DischargeSummary.getText());

        TextArea dataTextArea = new TextArea();
        dataTextArea.setEditable(false);
        dataTextArea.setWrapText(true);

        dataTextArea.appendText("LastName: " + currentData.lastName + "\n");
        dataTextArea.appendText("FirstName: " + currentData.firstName + "\n");
        dataTextArea.appendText("MiddleInitial: " + currentData.middleInitial + "\n");
        dataTextArea.appendText("DateOfBirth: " + currentData.DateofBirth + "\n");
        dataTextArea.appendText("MRN: " + currentData.MRN + "\n");
        dataTextArea.appendText("Sex: " + currentData.Sex + "\n");
        dataTextArea.appendText("PatientNum: " + currentData.PatientNum + "\n");
        dataTextArea.appendText("EmerNum: " + currentData.emerNum + "\n");
        dataTextArea.appendText("EmailAdd: " + currentData.emailAdd + "\n");
        dataTextArea.appendText("Province: " + currentData.Province + "\n");
        dataTextArea.appendText("City: " + currentData.City + "\n");
        dataTextArea.appendText("Address: " + currentData.Address + "\n");
        dataTextArea.appendText("MartialStatus: " + currentData.MartialStatus + "\n");
        dataTextArea.appendText("Occupation: " + currentData.Occupation + "\n");
        dataTextArea.appendText("Allergies: " + currentData.Allergies + "\n");
        dataTextArea.appendText("CurrentMed: " + currentData.CurrentMed + "\n");
        dataTextArea.appendText("DateofAdmission: " + currentData.DateofAdmission + "\n");
        dataTextArea.appendText("HospitalRoomNum: " + currentData.HospitalRoomNum + "\n");
        dataTextArea.appendText("AttendingPhysician: " + currentData.AttendingPhysician + "\n");
        dataTextArea.appendText("Diagnosis: " + currentData.Diagnosis + "\n");
        dataTextArea.appendText("ExamFindings: " + currentData.ExamFindings + "\n");
        dataTextArea.appendText("TreatmentPlan: " + currentData.TreatmentPlan + "\n");
        dataTextArea.appendText("Progress: " + currentData.Progress + "\n");
        dataTextArea.appendText("DischargeSummary: " + currentData.DischargeSummary);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> backToSecondStage());

        Button addPatientButton = new Button("Add Patient");
        addPatientButton.setOnAction(e -> addPatientToDataList(currentData));

        Button historyButton = new Button("Patient History");
        historyButton.setOnAction(e -> showPatientHistoryStage());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(backButton, addPatientButton, historyButton);

        VBox root3 = new VBox();
        root3.getChildren().addAll(summaryLabel, dataTextArea, buttonBox);
        Scene scene = new Scene(root3, 550, 400);

        tertiaryStage.setTitle("Patient Record (JavaFX)");
        tertiaryStage.setScene(scene);
        tertiaryStage.show();
    }

    private void backToSecondStage() {
        tertiaryStage.hide();
        secondaryStage.show();
    }

    private void showPatientHistoryStage() {

        Stage historyStage = new Stage();

        Label historyLabel = new Label("Patient History");

        ListView<DataEntry> historyView = new ListView<>();
        historyView.setItems(patientHistoryList);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> historyStage.close());

        VBox root = new VBox(historyLabel, historyView, closeButton);

        Scene scene = new Scene(root);
        historyStage.setScene(scene);
        historyStage.show();

    }

    private void addPatientToDataList(DataEntry currentData) {
        dataList.add(currentData);
        patientHistoryList.add(currentData);

        showAlert("Patient information added successfully!");

        // tertiaryStage.hide();
    }

}
