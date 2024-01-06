package grprs;

import java.io.IOException;
import javafx.application.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.time.LocalDate;
import java.time.format.*;
import java.util.HashMap;

import javafx.collections.*;

public class Main extends Application {
    private DataEntry patientData = new DataEntry();
    private ObservableList<DataEntry> patientHistoryList;

    public Main() {
        patientHistoryList = FXCollections.observableArrayList();
    }

    double W = 1366;
    double H = 721;
    private HBox contentLayout;
    private Button patientHistoryBTN;
    private Button patientRecordBTN;
    private HBox container;

    private VBox primaryStage;
    private HBox processing1;
    private VBox secondaryStage;
    private HBox processing2;
    private VBox tertiaryStage;
    private VBox reviewPanel;
    private StackPane stackStages;
    private StackPane stackStages2;

    private Label phase1, phase2, phase3;

    private TextField firstNameField, middleNameField, lastNameField, dobField, sexField, ppnField,
            ecnField, emailField, provinceField, cityField, addressField, maritalStatusField, occupationField,
            allergiesField, currentMedField;

    private boolean isSelected1, isSelected2;

    private ComboBox<String> sexFieldBox;
    private ComboBox<String> maritalStatusBox;
    private DatePicker datePicker = new DatePicker();

    private Label dataLabel1, dataLabel2, dataLabel4, dataLabel5,
            dataLabel6, dataLabel7, dataLabel8, dataLabel9, dataLabel10,
            dataLabel11, dataLabel12, dataLabel13, dataLabel14, dataLabel15,
            dataLabel16, dataLabel17, dataLabel18, dataLabel19, dataLabel20,
            dataLabel21, dataLabel22;

    private TextField DateOfAdmissionField, HospitalRoomNumField, AttendingPhysicianField,
            DiagnosisField, ExamFindingsField, TreatmentPlanField, ProgressField, DischargeSummaryField;

    private ComboBox<String> RoomNumBox;
    private ComboBox<String> PhysiciaNameBox;

    private TextField[] First_allTextFields = new TextField[15];
    private TextField[] Second_allTextFields = new TextField[7];
    private Button prPhase1Submit;
    private Button prPhase2Submit;
    private Button prPhase3Submit;

    private HashMap<String, Integer> prnMap = new HashMap<>();
    private int counter = 1;
    private String prnHashValue;

    private Label catchData1 = new Label();
    private Label reviewData0 = new Label();

    private Label reviewData1 = new Label(); private Label reviewData2 = new Label();
    private Label reviewData3 = new Label(); private Label reviewData4 = new Label();
    private Label reviewData5 = new Label(); private Label reviewData6 = new Label();
    private Label reviewData7 = new Label(); private Label reviewData8 = new Label();
    private Label reviewData9 = new Label(); private Label reviewData10 = new Label();
    private Label reviewData11 = new Label(); private Label reviewData12 = new Label();
    private Label reviewData13 = new Label(); private Label reviewData14 = new Label();
    private Label reviewData15 = new Label(); private Label reviewData16 = new Label();
    private Label reviewData17 = new Label(); private Label reviewData18 = new Label();
    private Label reviewData19 = new Label(); private Label reviewData20 = new Label();
    private Label reviewData21 = new Label(); private Label reviewData22 = new Label();

    private TextField searchField;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        HBox customTitleBar = CustomTitleBar(stage);
        contentLayout = contentLayout(stage);

        // titlebar icon anf label
        Image appIcon = new Image("/assets/iconPRS.png");
        stage.getIcons().add(appIcon);
        stage.setTitle("OSPA Patient Record System");
        stage.initStyle(StageStyle.UNDECORATED);

        // background image PNG
        Image backgroundImage = new Image("/assets/prsBG_716.png");
        ImageView blurIMG = new ImageView(backgroundImage);
        ImageView bgIMG = new ImageView(backgroundImage);

        // Effects
        GaussianBlur gaussianBlur = new GaussianBlur(100);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(1);
        Blend blend = new Blend();
        blend.setMode(BlendMode.OVERLAY);
        blend.setTopInput(colorAdjust);
        blend.setBottomInput(gaussianBlur);

        // Background CLip
        Rectangle imgClip = new Rectangle(20, 40, W - 40, H - 60);
        imgClip.setArcWidth(80);
        imgClip.setArcHeight(80);
        blurIMG.setClip(imgClip);
        blurIMG.setEffect(blend);

        // Backrgonound Overlay
        Rectangle bgOverlay = new Rectangle(W - 40, H - 60);
        bgOverlay.setFill(Color.rgb(46, 84, 87, 0.5));
        bgOverlay.setTranslateX(0);
        bgOverlay.setTranslateY(12);
        bgOverlay.setArcWidth(80);
        bgOverlay.setArcHeight(80);

        // Main Background Image
        Rectangle bgIMGpanel = new Rectangle(0, 0, W, H);
        bgIMG.setClip(bgIMGpanel);

        // calling all avengers
        StackPane composite = new StackPane(bgIMG, blurIMG, bgOverlay, contentLayout, customTitleBar);

        Scene scene = new Scene(composite);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private HBox processing(Stage stage) {
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER_LEFT);
        container.setSpacing(8);

        for (int i = 0; i < 4; i++) {
            Circle circles = new Circle(2);
            container.getChildren().add(circles);
        }

        return container;
    }

    private HBox contentLayout(Stage stage) {
        container = new HBox();

        // Left Panel
        Label hospitalNameTag = new Label("OSPA Leyte");

        Image hisotryIcon = new Image(getClass().getResourceAsStream("/assets/PatientHistoryIcon.png"));
        ImageView myIcon1 = new ImageView(hisotryIcon);
        Image recordIcon = new Image(getClass().getResourceAsStream("/assets/patientRecordIcon.png"));
        ImageView myIcon2 = new ImageView(recordIcon);

        patientHistoryBTN = new Button(" Patient History");
        patientHistoryBTN.setGraphic(myIcon1);
        patientRecordBTN = new Button(" Patient Record");
        patientRecordBTN.setGraphic(myIcon2);

        Platform.runLater(() -> {
            patientRecordBTN.setOnAction(event -> switchContent(patientRecord(stage), patientRecordBTN));
            patientHistoryBTN.setOnAction(event -> switchContent(patientHistory(stage), patientHistoryBTN));

            switchContent(patientRecord(stage), patientRecordBTN);
        });

        VBox label = new VBox(hospitalNameTag);
        VBox menuBarList = new VBox(patientRecordBTN, patientHistoryBTN);
        menuBarList.setSpacing(10);

        VBox myMenuBarContainer = new VBox(label, menuBarList);
        myMenuBarContainer.setAlignment(Pos.TOP_CENTER);
        myMenuBarContainer.getStyleClass().add("MenuListBar");

        // right panel
        HBox contentLayout = new HBox(myMenuBarContainer, container);
        contentLayout.setAlignment(Pos.CENTER);
        contentLayout.getStyleClass().add("mainContentLayout");

        return contentLayout;
    }

    private void switchContent(Node newContent, Button activeButton) {
        HBox container = (HBox) contentLayout.getChildren().get(1);
        container.getChildren().clear();
        container.getChildren().add(newContent);

        patientHistoryBTN.getStyleClass().remove("active");
        patientRecordBTN.getStyleClass().remove("active");

        activeButton.getStyleClass().add("active");
    }

    // patientHistory content
    private HBox patientHistory(Stage stage) {
        Label totalPatient = new Label("Total patient");
        VBox searchContent = searchContent(stage);
        HBox container = new HBox(totalPatient, searchContent);

        container.getStyleClass().add("patientHistoryStyle");
        container.setAlignment(Pos.CENTER);

        return container;
    }

    private VBox searchContent(Stage stage){

        VBox mainLayout = new VBox();
        mainLayout.setPadding(new Insets(10));

        searchField = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchEntries(mainLayout));

        HBox searchBox = new HBox(10, new Label("Search by First Name:"), searchField, searchButton);
        VBox entriesBox = new VBox(10);

        for (DataEntry entry : patientHistoryList) {
            EntryNode entryNode = new EntryNode(entry);
            entriesBox.getChildren().add(entryNode);
        }

        ScrollPane scrollPane = new ScrollPane(entriesBox);

        mainLayout.getChildren().addAll(searchBox, scrollPane);

        return mainLayout;
    } 

    // patientRecord content
    private VBox patientRecord(Stage stage) {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("EEEE, d ")
                .appendPattern("MMMM yyyy")
                .toFormatter();

        String formattedDate = currentDate.format(formatter);

        String[] dateParts = formattedDate.split(" ");

        // Create Text nodes for each part
        Text dayOfWeekText = new Text(dateParts[0] + " ");
        dayOfWeekText.setStyle("-fx-fill: #3E3E3E;");
        dayOfWeekText.getStyleClass().add("dateBoldText");
        Text dayOfMonthText = new Text(dateParts[1] + " ");
        dayOfMonthText.setStyle("-fx-fill: #3E3E3E;");
        dayOfMonthText.getStyleClass().add("dateBoldText");
        Text restOfDateText = new Text(dateParts[2] + " " + dateParts[3]);
        restOfDateText.setStyle("-fx-fill: #3E3E3E;");

        // Create an HBox to hold the Text nodes
        HBox dateBox = new HBox(dayOfWeekText, dayOfMonthText, restOfDateText);
        dateBox.setAlignment(Pos.CENTER);
        dateBox.setId("dateField");

        // adminNumber
        Image adminIcon = new Image(getClass().getResourceAsStream("/assets/adminIcon.png"));
        ImageView aIcon = new ImageView(adminIcon);
        Label adminNumber = new Label("Admin 1  ");
        adminNumber.getStyleClass().add("adminNum");
        adminNumber.setGraphic(aIcon);
        adminNumber.setContentDisplay(ContentDisplay.RIGHT);

        // chatBot icon
        Button chatBot = new Button();
        chatBot.setId("chatBotBTN");
        Image chatBotIcon = new Image(getClass().getResourceAsStream("/assets/chatBotIcon.png"));
        ImageView botIcon = new ImageView(chatBotIcon);
        chatBot.setGraphic(botIcon);

        HBox tpanel_left = new HBox(dateBox);
        tpanel_left.setPrefWidth(1057 / 2);
        tpanel_left.setAlignment(Pos.CENTER_LEFT);
        HBox tpanel_right = new HBox(adminNumber, chatBot);
        tpanel_right.setPrefWidth(1057 / 2);
        tpanel_right.setSpacing(10);
        tpanel_right.setAlignment(Pos.CENTER_RIGHT);

        HBox topPanel = new HBox(tpanel_left, tpanel_right);
        topPanel.setMaxHeight(53);

        // bottom panel
        phase1 = new Label("Patient Information");
        phase1.getStyleClass().add("active");
        phase2 = new Label("Clinical Summary");
        phase3 = new Label("Done");

        processing1 = processing(stage);
        processing2 = processing(stage);

        HBox indicator = new HBox(phase1, processing1, phase2, processing2, phase3);
        indicator.setSpacing(20);
        indicator.setAlignment(Pos.CENTER_LEFT);
        indicator.setId("phasingIndicator");

        // content panels here //
        stackStages = new StackPane();
        primaryStage = primaryStage(stage);
        secondaryStage = secondaryStage(stage);
        tertiaryStage = tertiaryStage(stage);

        stackStages.getChildren().addAll(primaryStage, secondaryStage, tertiaryStage);
        stackStages.getChildren().get(1).setVisible(false);
        stackStages.getChildren().get(2).setVisible(false);

        // img Container //
        Image heroIMG = new Image("/assets/HeroImg.png");
        ImageView displayIMG = new ImageView(heroIMG);
        displayIMG.setTranslateY(-20);

        Circle bgCircle = new Circle(142);
        bgCircle.setTranslateY(10);
        bgCircle.setId("bgCircle");
        StackPane imgContainer = new StackPane(bgCircle, displayIMG);
        imgContainer.setAlignment(Pos.CENTER);
        imgContainer.setId("imgContainer");

        stackStages2 = new StackPane();
        reviewPanel = reviewPanel(stage);

        stackStages2.getChildren().addAll(imgContainer, reviewPanel);
        stackStages2.getChildren().get(1).setVisible(false);

        // inserts the content here
        HBox contentPanel = new HBox(stackStages, stackStages2);
        contentPanel.setId("prMainContent");
        contentPanel.setSpacing(10);

        VBox container = new VBox(topPanel, indicator, contentPanel);
        container.setSpacing(10);
        container.getStyleClass().add("patientRecordStyle");
        container.setAlignment(Pos.TOP_CENTER);

        return container;
    }

    private VBox primaryStage(Stage stage) {

        initializeFields();

        for (TextField textField : First_allTextFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonColor());
        }

        firstNameField.setPromptText("First name");
        middleNameField.setPromptText("Middle name");
        lastNameField.setPromptText("Last name");

        datePicker = new DatePicker();
        datePicker.setId("calendar");

        datePicker.setEditable(false);
        datePicker.setPromptText("MM/DD/YYYY");
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                dobField.setText(newValue.toString());
            }
        });

        sexFieldBox = new ComboBox<>(FXCollections.observableArrayList(
                "Male",
                "Female",
                "Rather not to say"));
        sexFieldBox.setEditable(true);
        sexFieldBox.setPromptText("Specify");
        sexFieldBox.setOnAction(event -> sexField.setText(sexFieldBox.getSelectionModel().getSelectedItem()));

        ppnField.setPromptText("0900 000 0000");

        ppnField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {
                ppnField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            if (newValue.length() > 11) {
                ppnField.setText(oldValue);
            }
        });

        ecnField.setPromptText("0900 000 0000");

        ecnField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ecnField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            // Limit to 11 digits
            if (newValue.length() > 11) {
                ecnField.setText(oldValue);
            }
        });

        emailField.setPromptText("example@outlook.com");
        provinceField.setPromptText("Location");
        cityField.setPromptText("Location");
        addressField.setPromptText("Street address");

        maritalStatusBox = new ComboBox<>(FXCollections.observableArrayList(
                "Single",
                "Married",
                "Widowed",
                "Divorced",
                "Prefer not to answer"));
        maritalStatusBox.setEditable(true);
        maritalStatusBox.setPromptText("Specify");
        maritalStatusBox.setOnAction(
                event -> maritalStatusField.setText(maritalStatusBox.getSelectionModel().getSelectedItem()));

        occupationField.setPromptText("Specify");
        allergiesField.setPromptText("Specify");
        currentMedField.setPromptText("Specify");

        firstNameField.getStyleClass().add("textFields");
        firstNameField.getStyleClass().add("row-3s");
        middleNameField.getStyleClass().add("textFields");
        middleNameField.getStyleClass().add("row-3s");
        lastNameField.getStyleClass().add("textFields");
        lastNameField.getStyleClass().add("row-3s");

        datePicker.getStyleClass().add("textFields");
        datePicker.getStyleClass().add("row-f4");
        sexFieldBox.getStyleClass().add("textFields");
        sexFieldBox.getStyleClass().add("row-f6");
        ppnField.getStyleClass().add("textFields");
        ppnField.getStyleClass().add("row-f7");

        ecnField.getStyleClass().add("textFields");
        ecnField.getStyleClass().add("row-3s");
        emailField.getStyleClass().add("textFields");
        emailField.getStyleClass().add("row-3s");
        provinceField.getStyleClass().add("textFields");
        provinceField.getStyleClass().add("row-3s");

        cityField.getStyleClass().add("textFields");
        cityField.getStyleClass().add("row-f11");
        addressField.getStyleClass().add("textFields");
        addressField.getStyleClass().add("row-f12");
        maritalStatusBox.getStyleClass().add("textFields");
        maritalStatusBox.getStyleClass().add("row-f13");
        occupationField.getStyleClass().add("textFields");
        occupationField.getStyleClass().add("row-f14");

        allergiesField.getStyleClass().add("textFields");
        allergiesField.getStyleClass().add("row-pair2");
        currentMedField.getStyleClass().add("textFields");
        currentMedField.getStyleClass().add("row-pair2");

        Image checkIcon1 = new Image(getClass().getResourceAsStream("/assets/checkIcon.png"));
        Image checkIcon2 = new Image(getClass().getResourceAsStream("/assets/uncheckedIcon.png"));
        ImageView checkIconView = new ImageView(checkIcon2);
        Image checkIcon3 = new Image(getClass().getResourceAsStream("/assets/checkIcon.png"));
        Image checkIcon4 = new Image(getClass().getResourceAsStream("/assets/uncheckedIcon.png"));
        ImageView checkIconView2 = new ImageView(checkIcon4);

        CheckBox checkBox1 = new CheckBox("  Tobacco use  ");
        checkBox1.setId("checkBoxBTN");
        checkBox1.setGraphic(checkIconView);
        checkBox1.setSelected(false);
        reviewData13.textProperty().bind(Bindings.concat(isSelected1));

        checkBox1.setOnAction(event -> {
            isSelected1 = checkBox1.isSelected();
            reviewData13.textProperty().bind(Bindings.concat(isSelected1));

            if (isSelected1) {
                checkIconView.setImage(checkIcon1);
                checkBox1.setStyle("-fx-border-color: #4A838E; -fx-text-fill: #105B6A;");
            } else {
                checkIconView.setImage(checkIcon2);
                checkBox1.setStyle("-fx-border-color: #ADAEAE; -fx-text-fill: #3E3E3E;");
            }
        });

        CheckBox checkBox2 = new CheckBox("  Drink alcohol  ");
        checkBox2.setId("checkBoxBTN");
        checkBox2.setGraphic(checkIconView2);
        checkBox2.setSelected(false);
        reviewData14.textProperty().bind(Bindings.concat(isSelected2));

        checkBox2.setOnAction(event -> {
            isSelected2 = checkBox2.isSelected();
            reviewData14.textProperty().bind(Bindings.concat(isSelected2));

            if (isSelected2) {
                checkIconView2.setImage(checkIcon3);
                checkBox2.setStyle("-fx-border-color: #4A838E; -fx-text-fill: #105B6A;");
            } else {
                checkIconView2.setImage(checkIcon4);
                checkBox2.setStyle("-fx-border-color: #ADAEAE; -fx-text-fill: #3E3E3E;");
            }
        });

        HBox checkBoxContainer = new HBox(checkBox1, checkBox2);
        checkBoxContainer.setId("checkBoxContainer");

        prPhase1Submit = new Button("Next");
        prPhase1Submit.setOnAction(event -> {
            nextButtonClicked();
            validateFields();
            validateComboBox();
        });
        prPhase1Submit.setId("prsBTN");

        GridPane prPanel = new GridPane();
        prPanel.setHgap(20);
        prPanel.setVgap(5);
        GridPane prPanel2 = new GridPane();
        prPanel2.setHgap(20);
        prPanel2.setVgap(5);
        GridPane prPanel3 = new GridPane();
        prPanel3.setHgap(20);
        prPanel3.setVgap(5);
        GridPane prPanel4 = new GridPane();
        prPanel4.setHgap(20);
        prPanel4.setVgap(5);
        GridPane prPanel5 = new GridPane();
        prPanel5.setHgap(20);
        prPanel5.setVgap(5);

        prPanel.addRow(0, dataLabel1);
        prPanel.addRow(1, firstNameField, middleNameField, lastNameField);
        prPanel2.addRow(0, dataLabel2, dataLabel4, dataLabel5);
        prPanel2.addRow(1, datePicker, sexFieldBox, ppnField);
        prPanel3.addRow(0, dataLabel6, dataLabel7, dataLabel8);
        prPanel3.addRow(1, ecnField, emailField, provinceField);
        prPanel4.addRow(0, dataLabel9, dataLabel10, dataLabel11, dataLabel12);
        prPanel4.addRow(1, cityField, addressField, maritalStatusBox, occupationField);
        prPanel5.addRow(0, dataLabel13, dataLabel14);
        prPanel5.addRow(1, allergiesField, currentMedField);
        HBox prPanel6 = new HBox(checkBoxContainer, prPhase1Submit);
        prPanel6.setId("prPanel6");
        prPanel6.setAlignment(Pos.CENTER_RIGHT);
        prPanel6.setSpacing(20);

        VBox firstStage = new VBox(prPanel, prPanel2, prPanel3, prPanel4, prPanel5, prPanel6);
        firstStage.setSpacing(15);
        firstStage.setId("prMainContentPanel");

        return firstStage;
    }

    private void initializeFields() {
        // Initialize all text fields
        firstNameField = new TextField();
        middleNameField = new TextField();
        lastNameField = new TextField();
        dobField = new TextField();
        sexField = new TextField();
        ppnField = new TextField();
        ecnField = new TextField();
        emailField = new TextField();
        provinceField = new TextField();
        cityField = new TextField();
        addressField = new TextField();
        maritalStatusField = new TextField();
        occupationField = new TextField();
        allergiesField = new TextField();
        currentMedField = new TextField();

        StringBinding formattedMiddleNameBinding = Bindings.createStringBinding(() -> {
            String middleName = middleNameField.getText();
            if (!middleName.isEmpty()) {
                String formattedMiddleName = middleName.substring(0, 1).toUpperCase();
                return formattedMiddleName;
            }
            return "";
        }, middleNameField.textProperty());

        catchData1.textProperty().bind(
                Bindings.concat(
                        firstNameField.textProperty(),
                        " ",
                        formattedMiddleNameBinding, ". ",
                        lastNameField.textProperty()));

        reviewData0.textProperty().bind(
                Bindings.concat(
                        firstNameField.textProperty(),
                        " ",
                        formattedMiddleNameBinding, ". ",
                        lastNameField.textProperty()));

        reviewData1.textProperty().bind(Bindings.concat(dobField.textProperty()));
        reviewData2.textProperty().bind(Bindings.concat(sexField.textProperty()));
        reviewData3.textProperty().bind(Bindings.concat(ppnField.textProperty()));
        reviewData4.textProperty().bind(Bindings.concat(ecnField.textProperty()));
        reviewData5.textProperty().bind(Bindings.concat(emailField.textProperty()));
        reviewData6.textProperty().bind(Bindings.concat(provinceField.textProperty()));
        reviewData7.textProperty().bind(Bindings.concat(cityField.textProperty()));
        reviewData8.textProperty().bind(Bindings.concat(addressField.textProperty()));
        reviewData9.textProperty().bind(Bindings.concat(maritalStatusField.textProperty()));
        reviewData10.textProperty().bind(Bindings.concat(occupationField.textProperty()));
        reviewData11.textProperty().bind(Bindings.concat(allergiesField.textProperty()));
        reviewData12.textProperty().bind(Bindings.concat(currentMedField.textProperty()));

        catchData1.setStyle("-fx-font-size: 16px; -fx-margin: 27 0 53 0; -fx-text-fill: #4D4D4D;");

        First_allTextFields = new TextField[] {
                firstNameField, middleNameField, lastNameField, dobField, sexField, ppnField,
                ecnField, emailField, provinceField, cityField, addressField, maritalStatusField, occupationField,
                allergiesField, currentMedField
        };

        dataLabel1 = new Label("Patient's name");
        dataLabel2 = new Label("Date of birth");
        dataLabel4 = new Label("Sex");
        dataLabel5 = new Label("Patient's phone no.");
        dataLabel6 = new Label("Emerg. contact no.");
        dataLabel7 = new Label("Email address");
        dataLabel8 = new Label("Province");
        dataLabel9 = new Label("City");
        dataLabel10 = new Label("Address");
        dataLabel11 = new Label("Marital status");
        dataLabel12 = new Label("Occupation");
        dataLabel13 = new Label("Allergies");
        dataLabel14 = new Label("Current medication");
    }

    private void nextButtonClicked() {
        String firstName = firstNameField.getText();
        String middleInitial = middleNameField.getText();
        String lastName = lastNameField.getText();
        String DateofBirth = dobField.getText();
        String Sex = sexField.getText();
        String PatientNum = ppnField.getText();
        String emerNum = ecnField.getText();
        String emailAdd = emailField.getText();
        String Province = provinceField.getText();
        String City = cityField.getText();
        String Address = addressField.getText();
        String MartialStatus = maritalStatusField.getText();
        String Occupation = occupationField.getText();
        String Allergies = allergiesField.getText();
        String CurrentMed = currentMedField.getText();

        if (firstName.isEmpty() || middleInitial.isEmpty() ||
                lastName.isEmpty() || DateofBirth.isEmpty() ||
                Sex.isEmpty() ||
                PatientNum.isEmpty() || emerNum.isEmpty() ||
                emailAdd.isEmpty() || Province.isEmpty() ||
                City.isEmpty() || Address.isEmpty() ||
                MartialStatus.isEmpty() || Occupation.isEmpty() ||
                Allergies.isEmpty() || CurrentMed.isEmpty()) {
            showAlert("Please fill in all required fields.");
            return;
        }

        if (!isValidPhoneNumber(ppnField.getText()) || !isValidPhoneNumber(ecnField.getText())) {
            return;
        } else {
            patientData.setFirstName(firstNameField.getText());
            patientData.setMiddleInitial(middleNameField.getText());
            patientData.setLastName(lastNameField.getText());
            patientData.setDateOfBirth(dobField.getText());
            patientData.setSex(sexField.getText());
            patientData.setPatientNum(ppnField.getText());
            patientData.setEmerNum(ecnField.getText());
            patientData.setEmailAdd(emailField.getText());
            patientData.setProvince(provinceField.getText());
            patientData.setCity(cityField.getText());
            patientData.setAddress(addressField.getText());
            patientData.setMaritalStatus(maritalStatusField.getText());
            patientData.setOccupation(occupationField.getText());
            patientData.setAllergies(allergiesField.getText());
            patientData.setCurrentMed(currentMedField.getText());
            patientData.setTobaccoUse(isSelected1);
            patientData.setDrinkAlcohol(isSelected2);

            primaryStage.setVisible(false);
            secondaryStage.setVisible(true);
            phase2.setStyle("-fx-border-color: #105B6A; -fx-text-fill: #105B6A;");
            processing1.setId("circle-active");
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{11}");
    }

    private void ppnFieldNumberAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Phone Number");
        alert.setHeaderText(
                "Please check the patient's phone number. The phone number must have 11 digits");
        alert.setContentText("Please enter a valid 11-digit phone number");

        alert.showAndWait();
    }

    private void ecnFieldNumberAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Phone Number");
        alert.setHeaderText("Please check emergency contact number. The phone number must have 11 digits");
        alert.setContentText("Please enter a valid 11-digit phone number");

        alert.showAndWait();
    }

    private void updateButtonColor() {
        // Set button color based on text field values
        boolean allFieldsFilled = true;
        for (TextField textField : First_allTextFields) {
            if (textField.getText().isEmpty()) {
                allFieldsFilled = false;
                break;
            }
        }

        if (!isValidPhoneNumber(ppnField.getText()) || !isValidPhoneNumber(ecnField.getText())) {
            return;
        }

        if (allFieldsFilled) {
            prPhase1Submit.setStyle("-fx-background-color: #105B6A; -fx-text-fill: #FFFFFF; -fx-cursor: hand;");
        } else {
            prPhase1Submit.setStyle("");
        }
    }

    private void validateFields() {
        for (int i = 0; i < First_allTextFields.length; i++) {
            TextField textField = First_allTextFields[i];
            if (textField.getText().isEmpty()) {
                textField.setStyle("-fx-border-color: red; -fx-prompt-text-fill: #FD8A8A;");
            } else {
                textField.setStyle("");
            }
        }

        if (!isValidPhoneNumber(ppnField.getText())) {
            ppnFieldNumberAlert();
            ppnField.setStyle("-fx-border-color: red; -fx-prompt-text-fill: #FD8A8A;");
            return;
        } else {
            ppnField.setStyle("");
        }

        if (!isValidPhoneNumber(ecnField.getText())) {
            ecnFieldNumberAlert();
            ecnField.setStyle("-fx-border-color: red; -fx-prompt-text-fill: #FD8A8A;");
            return;
        } else {
            ecnField.setStyle("");
        }
    }

    private void validateComboBox() {
        if (sexFieldBox.getSelectionModel().isEmpty()) {
            sexFieldBox.setStyle("-fx-border-color: red;");

            sexFieldBox.getEditor().styleProperty().bind(
                    Bindings.when(sexFieldBox.valueProperty().isNull())
                            .then("-fx-control-inner-background: red; -fx-opacity: 0.5;")
                            .otherwise("-fx-base: black"));
        } else {
            sexFieldBox.setStyle("");
        }

        if (maritalStatusBox.getSelectionModel().isEmpty()) {
            maritalStatusBox.setStyle("-fx-border-color: red;");

            maritalStatusBox.getEditor().styleProperty().bind(
                    Bindings.when(maritalStatusBox.valueProperty().isNull())
                            .then("-fx-control-inner-background: red; -fx-opacity: 0.5;")
                            .otherwise("-fx-base: black"));
        } else {
            maritalStatusBox.setStyle("");
        }

        if (datePicker.getValue() == null) {
            datePicker.setStyle("-fx-border-color: red;");

            datePicker.getEditor().styleProperty().bind(
                    Bindings.when(datePicker.valueProperty().isNull())
                            .then("-fx-control-inner-background: red; -fx-opacity: 0.5;")
                            .otherwise("-fx-base: black"));
        } else {
            datePicker.setStyle("");
        }
    }

    private VBox secondaryStage(Stage stage) {
        initializeFields2();

        for (TextField textField : Second_allTextFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonColor2());
        }

        LocalDate currentDate = LocalDate.now();

        DateOfAdmissionField = new TextField();
        DateOfAdmissionField.setId("textFieldRow");
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MM / dd / yyyy"));
        DateOfAdmissionField.setText(formattedDate);
        reviewData15.textProperty().bind(Bindings.concat(DateOfAdmissionField.textProperty()));
        DateOfAdmissionField.setEditable(false);
        DateOfAdmissionField
                .setStyle("-fx-cursor: pointer; -fx-highlight-text-fill: -fx-text-fill; -fx-highlight-fill: white;");

        RoomNumBox = new ComboBox<>(FXCollections.observableArrayList(
                "Room 100",
                "Room 101",
                "Room 102",
                "Room 103",
                "Room 104",
                "Room 105"));
        RoomNumBox.setEditable(true);
        RoomNumBox.setPromptText("Room number");
        RoomNumBox.setOnAction(event -> HospitalRoomNumField.setText(RoomNumBox.getSelectionModel().getSelectedItem()));
        RoomNumBox.getStyleClass().add("row-pair3");

        PhysiciaNameBox = new ComboBox<>(FXCollections.observableArrayList(
                "Dra. Sarah Michaels - Attending Cardiologist",
                "Dr. Neil Robertson - Chief of Surgery",
                "Dra. Jessica Yang - Pediatrician",
                "Dr. John Smith - Emergency Medicine Physician",
                "Dr. Fatima Rahman - Neurologist"));
        PhysiciaNameBox.setEditable(true);
        PhysiciaNameBox.setPromptText("Physician name");
        PhysiciaNameBox.setOnAction(
                event -> AttendingPhysicianField.setText(PhysiciaNameBox.getSelectionModel().getSelectedItem()));
        PhysiciaNameBox.getStyleClass().add("row-pair3");

        DiagnosisField.setId("textFieldRow");
        ExamFindingsField.setId("textFieldRow");
        TreatmentPlanField.setId("textFieldRow");
        ProgressField.setId("textFieldRow");
        DischargeSummaryField.setId("textFieldRow");

        HospitalRoomNumField.setPromptText("Room number");
        AttendingPhysicianField.setPromptText("Physician name");
        DiagnosisField.setPromptText("Initial diagnosis");
        ExamFindingsField.setPromptText("Results of medical exams");
        TreatmentPlanField.setPromptText("Proposed treatment plan");
        ProgressField.setPromptText("Case progress notes");
        DischargeSummaryField.setPromptText("Summary at time of discharge");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> backToFirstStage());
        backButton.setId("backBTN");

        prPhase2Submit = new Button("Next");
        prPhase2Submit.setOnAction(e -> {
            nextButtonClicked2();
            validateFields2();
            validateComboBox2();
        });
        prPhase2Submit.setId("prsBTN");

        GridPane prPanel6 = new GridPane();
        prPanel6.setHgap(20);
        prPanel6.setVgap(5);
        GridPane prPanel7 = new GridPane();
        prPanel7.setHgap(20);
        prPanel7.setVgap(5);
        GridPane prPanel8 = new GridPane();
        prPanel8.setHgap(20);
        prPanel8.setVgap(5);
        GridPane prPanel9 = new GridPane();
        prPanel9.setHgap(20);
        prPanel9.setVgap(5);

        prPanel6.addRow(0, dataLabel15, dataLabel16);
        prPanel6.addRow(1, DateOfAdmissionField, RoomNumBox);
        prPanel7.addRow(0, dataLabel17, dataLabel18);
        prPanel7.addRow(1, PhysiciaNameBox, DiagnosisField);
        prPanel8.addRow(0, dataLabel19, dataLabel20);
        prPanel8.addRow(1, ExamFindingsField, TreatmentPlanField);
        prPanel9.addRow(0, dataLabel21, dataLabel22);
        prPanel9.addRow(1, ProgressField, DischargeSummaryField);

        HBox buttonCon = new HBox(backButton, prPhase2Submit);
        buttonCon.setTranslateY(93);
        buttonCon.setAlignment(Pos.CENTER_RIGHT);
        buttonCon.setSpacing(10);

        VBox secondStage = new VBox(prPanel6, prPanel7, prPanel8, prPanel9, buttonCon);
        secondStage.setSpacing(15);
        secondStage.setId("prMainContentPanel");

        return secondStage;
    }

    private void initializeFields2() {
        DateOfAdmissionField = new TextField();
        HospitalRoomNumField = new TextField();
        AttendingPhysicianField = new TextField();
        DiagnosisField = new TextField();
        ExamFindingsField = new TextField();
        TreatmentPlanField = new TextField();
        ProgressField = new TextField();
        DischargeSummaryField = new TextField();

        reviewData16.textProperty().bind(Bindings.concat(HospitalRoomNumField.textProperty()));
        reviewData17.textProperty().bind(Bindings.concat(AttendingPhysicianField.textProperty()));
        reviewData18.textProperty().bind(Bindings.concat(DiagnosisField.textProperty()));
        reviewData19.textProperty().bind(Bindings.concat(ExamFindingsField.textProperty()));
        reviewData20.textProperty().bind(Bindings.concat(TreatmentPlanField.textProperty()));
        reviewData21.textProperty().bind(Bindings.concat(ProgressField.textProperty()));
        reviewData22.textProperty().bind(Bindings.concat(DischargeSummaryField.textProperty()));

        Second_allTextFields = new TextField[] {
                HospitalRoomNumField,
                AttendingPhysicianField, DiagnosisField,
                ExamFindingsField, TreatmentPlanField,
                ProgressField, DischargeSummaryField
        };

        dataLabel15 = new Label("Date of admission");
        dataLabel16 = new Label("Hospital room no.");
        dataLabel17 = new Label("Attending physician");
        dataLabel18 = new Label("Diagnosis");
        dataLabel19 = new Label("Physical examination findings");
        dataLabel20 = new Label("Treatment plan");
        dataLabel21 = new Label("Progress");
        dataLabel22 = new Label("Discharge summary");
    }

    private void updateButtonColor2() {
        boolean allFieldsFilled = true;
        for (TextField textField : Second_allTextFields) {
            if (textField.getText().isEmpty()) {
                allFieldsFilled = false;
                break;
            }
        }

        if (allFieldsFilled) {
            prPhase2Submit.setStyle("-fx-background-color: #105B6A; -fx-text-fill: #FFFFFF; -fx-cursor: hand;");
        } else {
            prPhase2Submit.setStyle("");
        }
    }

    private void validateFields2() {
        for (int i = 0; i < Second_allTextFields.length; i++) {
            TextField textField = Second_allTextFields[i];
            if (textField.getText().isEmpty()) {
                textField.setStyle("-fx-border-color: red; -fx-prompt-text-fill: #FD8A8A;");
            } else {
                textField.setStyle("");
            }
        }
    }

    private void validateComboBox2() {
        if (RoomNumBox.getSelectionModel().isEmpty()) {
            RoomNumBox.setStyle("-fx-border-color: red;");

            RoomNumBox.getEditor().styleProperty().bind(
                    Bindings.when(RoomNumBox.valueProperty().isNull())
                            .then("-fx-control-inner-background: red; -fx-opacity: 0.5;")
                            .otherwise("-fx-base: black"));
        } else {
            RoomNumBox.setStyle("");
        }

        if (PhysiciaNameBox.getSelectionModel().isEmpty()) {
            PhysiciaNameBox.setStyle("-fx-border-color: red;");

            PhysiciaNameBox.getEditor().styleProperty().bind(
                    Bindings.when(PhysiciaNameBox.valueProperty().isNull())
                            .then("-fx-control-inner-background: red; -fx-opacity: 0.5;")
                            .otherwise("-fx-base: black"));
        } else {
            PhysiciaNameBox.setStyle("");
        }
    }

    private void nextButtonClicked2() {
        String DateOfAdmission = DateOfAdmissionField.getText();
        String HospitalRoomNum = HospitalRoomNumField.getText();
        String AttendingPhysician = AttendingPhysicianField.getText();
        String Diagnosis = DiagnosisField.getText();
        String ExamFindings = ExamFindingsField.getText();
        String TreatmentPlan = TreatmentPlanField.getText();
        String Progress = ProgressField.getText();
        String DischargeSummary = DischargeSummaryField.getText();

        if (DateOfAdmission.isEmpty() || HospitalRoomNum.isEmpty() ||
                AttendingPhysician.isEmpty() ||
                Diagnosis.isEmpty() || ExamFindings.isEmpty() ||
                TreatmentPlan.isEmpty() || Progress.isEmpty() ||
                DischargeSummary.isEmpty()) {
            showAlert("Please fill in all required fields.");
            return;
        } else {
            patientData.setDateOfAdmission(DateOfAdmissionField.getText());
            patientData.setHospitalRoomNum(HospitalRoomNumField.getText());
            patientData.setAttendingPhysician(AttendingPhysicianField.getText());
            patientData.setDiagnosis(DiagnosisField.getText());
            patientData.setExamFindings(ExamFindingsField.getText());
            patientData.setTreatmentPlan(TreatmentPlanField.getText());
            patientData.setProgress(ProgressField.getText());
            patientData.setDischargeSummary(DischargeSummaryField.getText());

            secondaryStage.setVisible(false);
            tertiaryStage.setVisible(true);
            reviewPanel.setVisible(true);
            phase3.setStyle("-fx-border-color: #105B6A; -fx-text-fill: #105B6A;");
            processing2.setId("circle-active");
        }
    }

    private static class DataEntry {
        private String firstName;
        private String middleInitial;
        private String lastName;
        private String DateofBirth;
        private String PRNHashValue;
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
        private boolean tobaccoUse;
        private boolean DrinkAlcohol;

        private String DateOfAdmission;
        private String HospitalRoomNum;
        private String AttendingPhysician;
        private String Diagnosis;
        private String ExamFindings;
        private String TreatmentPlan;
        private String Progress;
        private String DischargeSummary;

        // checkbox
        public void setTobaccoUse(boolean tobaccoUse) {
            this.tobaccoUse = tobaccoUse;
        }

        public boolean isTobaccoUse() {
            return tobaccoUse;
        }

        public void setDrinkAlcohol(boolean DrinkAlcohol) {
            this.DrinkAlcohol = DrinkAlcohol;
        }

        public boolean isDrinkAlcohol() {
            return DrinkAlcohol;
        }

        //

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setMiddleInitial(String middleInitial) {
            this.middleInitial = middleInitial;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setDateOfBirth(String DateofBirth) {
            this.DateofBirth = DateofBirth;
        }

        // HashMap set and get

        public void setPRNHashValue(String PRNHashValue) {
            this.PRNHashValue = PRNHashValue;
        }

        public String getPRNHashValue() {
            return PRNHashValue;
        }

        //

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public void setPatientNum(String PatientNum) {
            this.PatientNum = PatientNum;
        }

        public void setEmerNum(String emerNum) {
            this.emerNum = emerNum;
        }

        public void setEmailAdd(String emailAdd) {
            this.emailAdd = emailAdd;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public void setMaritalStatus(String MartialStatus) {
            this.MartialStatus = MartialStatus;
        }

        public void setOccupation(String Occupation) {
            this.Occupation = Occupation;
        }

        public void setAllergies(String Allergies) {
            this.Allergies = Allergies;
        }

        public void setCurrentMed(String CurrentMed) {
            this.CurrentMed = CurrentMed;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getMiddleInitial() {
            return middleInitial;
        }

        public String getLastName() {
            return lastName;
        }

        public String getDateOfBirth() {
            return DateofBirth;
        }

        public String getSex() {
            return Sex;
        }

        public String getPatientNum() {
            return PatientNum;
        }

        public String getEmerNum() {
            return emerNum;
        }

        public String getEmailAdd() {
            return emailAdd;
        }

        public String getProvince() {
            return Province;
        }

        public String getCity() {
            return City;
        }

        public String getAddress() {
            return Address;
        }

        public String getMaritalStatus() {
            return MartialStatus;
        }

        public String getOccupation() {
            return Occupation;
        }

        public String getAllergies() {
            return Allergies;
        }

        public String getCurrentMed() {
            return CurrentMed;
        }

        // stage 2

        public void setDateOfAdmission(String DateOfAdmission) {
            this.DateOfAdmission = DateOfAdmission;
        }

        public void setHospitalRoomNum(String HospitalRoomNum) {
            this.HospitalRoomNum = HospitalRoomNum;
        }

        public void setAttendingPhysician(String AttendingPhysician) {
            this.AttendingPhysician = AttendingPhysician;
        }

        public void setDiagnosis(String Diagnosis) {
            this.Diagnosis = Diagnosis;
        }

        public void setExamFindings(String ExamFindings) {
            this.ExamFindings = ExamFindings;
        }

        public void setTreatmentPlan(String TreatmentPlan) {
            this.TreatmentPlan = TreatmentPlan;
        }

        public void setProgress(String Progress) {
            this.Progress = Progress;
        }

        public void setDischargeSummary(String DischargeSummary) {
            this.DischargeSummary = DischargeSummary;
        }

        public String getDateOfAdmission() {
            return DateOfAdmission;
        }

        public String getHospitalRoomNum() {
            return HospitalRoomNum;
        }

        public String getAttendingPhysician() {
            return AttendingPhysician;
        }

        public String getDiagnosis() {
            return Diagnosis;
        }

        public String getExamFindings() {
            return ExamFindings;
        }

        public String getTreatmentPlan() {
            return TreatmentPlan;
        }

        public String getProgress() {
            return Progress;
        }

        public String getDischargeSummary() {
            return DischargeSummary;
        }

        @Override
        public String toString() {
            return "First Name: " + getFirstName();
        }
    }

    private VBox tertiaryStage(Stage stage) {

        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MM / dd / yyyy"));

        Label prnValueLabel = new Label("PRN" + String.format("%08d", counter));
        Label dateLabel = new Label("   |   " + formattedDate);
        prnValueLabel.setStyle("-fx-font-weight: 400; -fx-font-size: 14px; -fx-text-fill: #4D4D4D;");
        dateLabel.setStyle("-fx-font-weight: 400; -fx-font-size: 14px; -fx-text-fill: #4D4D4D;");

        HBox PRNnDate = new HBox(prnValueLabel, dateLabel);
        PRNnDate.setAlignment(Pos.CENTER);

        Image finalStageImg = new Image("/assets/finalStage_img.png");
        ImageView displayIMG = new ImageView(finalStageImg);
        displayIMG.setTranslateY(-20);

        Circle bgCircle = new Circle(115);
        bgCircle.setId("bgCircle");

        StackPane imgContainer = new StackPane(bgCircle, displayIMG);
        imgContainer.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> backToSecondStage());
        backButton.setId("backBTN");

        prPhase3Submit = new Button("Add patient");
        prPhase3Submit.setId("prsBTN");
        prPhase3Submit.setStyle("-fx-background-color: #105B6A; -fx-text-fill: #FFFFFF; -fx-cursor: hand;");
        prPhase3Submit.setOnAction(e -> {
            patientData.setPRNHashValue(prnValueLabel.getText());

            addPatientToDataList();
            prnHashValue = "PRN" + String.format("%08d", counter++);
            prnMap.put(prnHashValue, counter);

            prnValueLabel.setText(prnHashValue);
        });

        HBox btnCon = new HBox(backButton, prPhase3Submit);
        btnCon.setAlignment(Pos.CENTER);
        btnCon.setSpacing(10);
        VBox thirdStage = new VBox();
        VBox.setMargin(imgContainer, new Insets(0, 0, 27, 0));
        VBox.setMargin(btnCon, new Insets(53, 0, 0, 0));

        thirdStage.getChildren().addAll(imgContainer, catchData1, PRNnDate, btnCon);
        thirdStage.setAlignment(Pos.BOTTOM_CENTER);
        thirdStage.setId("prMainContentPanel");

        return thirdStage;
    }

    private void addPatientToDataList() {
        DataEntry newPatient = new DataEntry();

        newPatient.setFirstName(patientData.getFirstName());
        newPatient.setMiddleInitial(patientData.getMiddleInitial());
        newPatient.setLastName(patientData.getLastName());

        patientHistoryList.add(newPatient);

        showAlert("Patient information added successfully!");
    }

    private class EntryNode extends HBox {
        private DataEntry dataEntry;

        public EntryNode(DataEntry dataEntry) {
            this.dataEntry = dataEntry;

            Label nameLabel = new Label(dataEntry.getFirstName());
            Button showDataButton = new Button("Show Data");
            showDataButton.setStyle("-fx-padding: 250 0;");
            showDataButton.setOnAction(e -> showData());

            getChildren().addAll(nameLabel, showDataButton);
        }

        private void showData() {
            System.out.println("Specific Information: " + dataEntry);
        }
    }

    private void searchEntries(VBox mainLayout) {
        String searchQuery = searchField.getText().toLowerCase();

        VBox entriesBox = new VBox(10);

        for (DataEntry entry : patientHistoryList) {
            if (entry.getFirstName().toLowerCase().contains(searchQuery)) {
                EntryNode entryNode = new EntryNode(entry);
                entriesBox.getChildren().add(entryNode);
            }
        }

        ScrollPane scrollPane = new ScrollPane(entriesBox);

        // Replace the old entriesBox with the new one in the main layout
        mainLayout.getChildren().remove(1);
        mainLayout.getChildren().add(scrollPane);
    }

    private VBox reviewPanel(Stage stage) {

        Label label1 = new Label("Patient's name");
        Label label2 = new Label("Date of birth");
        Label label3 = new Label("Sex");
        Label label4 = new Label("Patient's phone no.");
        Label label5 = new Label("Emerg. contact no.");
        Label label6 = new Label("Email address");
        Label label7 = new Label("Province");
        Label label8 = new Label("City");
        Label label9 = new Label("Address");
        Label label10 = new Label("Marital status");
        Label label11 = new Label("Occupation");
        Label label12 = new Label("Allergies");
        Label label13 = new Label("Current medication");
        Label label14 = new Label("Tobacco use");
        Label label15 = new Label("Drink alcohol");
        Label label16 = new Label("Date of admission");
        Label label17 = new Label("Hospital room no.");
        Label label18 = new Label("Attending physician");
        Label label19 = new Label("Diagnosis");
        Label label20 = new Label("Physical examination findings");
        Label label21 = new Label("Treatment plan");
        Label label22 = new Label("Progress");
        Label label23 = new Label("Discharge summary");

        Label demographics = new Label("Patient's Demographics and Clinical summary");

        GridPane prPanel = new GridPane();
        prPanel.setVgap(5);

        prPanel.addRow(0, label1); prPanel.addRow(1, reviewData0); prPanel.addRow(2, label2); prPanel.addRow(3, reviewData1);
        prPanel.addRow(4, label3); prPanel.addRow(5, reviewData2); prPanel.addRow(6, label4); prPanel.addRow(7, reviewData3);
        prPanel.addRow(8, label5); prPanel.addRow(9, reviewData4); prPanel.addRow(10, label6); prPanel.addRow(11, reviewData5);
        prPanel.addRow(12, label7); prPanel.addRow(13, reviewData6); prPanel.addRow(14, label8); prPanel.addRow(15, reviewData7);
        prPanel.addRow(16, label9); prPanel.addRow(17, reviewData8); prPanel.addRow(18, label10); prPanel.addRow(19, reviewData9);
        prPanel.addRow(20, label11); prPanel.addRow(21, reviewData10); prPanel.addRow(22, label12); prPanel.addRow(23, reviewData11);
        prPanel.addRow(24, label13); prPanel.addRow(25, reviewData12); prPanel.addRow(26, label14); prPanel.addRow(27, reviewData13);
        prPanel.addRow(28, label15); prPanel.addRow(29, reviewData14); prPanel.addRow(30, label16); prPanel.addRow(31, reviewData15);
        prPanel.addRow(32, label17); prPanel.addRow(33, reviewData16); prPanel.addRow(34, label18); prPanel.addRow(35, reviewData17);
        prPanel.addRow(36, label19); prPanel.addRow(37, reviewData18); prPanel.addRow(38, label20); prPanel.addRow(39, reviewData19);
        prPanel.addRow(40, label21); prPanel.addRow(41, reviewData20); prPanel.addRow(42, label22); prPanel.addRow(43, reviewData21);
        prPanel.addRow(44, label23); prPanel.addRow(45, reviewData22);

        ScrollPane scrollPane = new ScrollPane(prPanel);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setId("reviewScrollPanel");

        VBox reviewDataPanel = new VBox(demographics, scrollPane);
        reviewDataPanel.setSpacing(28);
        reviewDataPanel.setId("reviewPanelStyle");

        return reviewDataPanel;
    }

    private void backToFirstStage() {
        primaryStage.setVisible(true);
        secondaryStage.setVisible(false);
        phase2.setStyle("-fx-border-color: #C5C5C5; -fx-text-fill: #C5C5C5;");
        processing1.setId("circle-deactivate");
    }

    private void backToSecondStage() {
        secondaryStage.setVisible(true);
        tertiaryStage.setVisible(false);
        reviewPanel.setVisible(false);
        phase3.setStyle("-fx-border-color: #C5C5C5; -fx-text-fill: #C5C5C5;");
        processing2.setId("circle-deactivate");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Note");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private HBox CustomTitleBar(Stage stage) {
        Image closeIcon = new Image(getClass().getResourceAsStream("/assets/CloseIcon.png"));
        ImageView close = new ImageView(closeIcon);
        Image minIcon = new Image(getClass().getResourceAsStream("/assets/MinimizeIcon.png"));
        ImageView minimize = new ImageView(minIcon);

        Label titleLabel = new Label("OSPA Patient Record System");
        Button closeButton = new Button();
        closeButton.getStyleClass().add("closeBTN");
        closeButton.setGraphic(close);
        closeButton.setOnAction(event -> stage.close());
        Button minimizeButton = new Button();
        minimizeButton.getStyleClass().add("minBTN");
        minimizeButton.setGraphic(minimize);
        minimizeButton.setOnAction(event -> stage.setIconified(true));

        HBox leftBox = new HBox(titleLabel);
        HBox rightBox = new HBox(minimizeButton, closeButton);
        leftBox.getStyleClass().add("lefty");
        rightBox.getStyleClass().add("righty");

        leftBox.setPrefWidth(W / 2);
        rightBox.setPrefWidth(W / 2);

        HBox customTitleBar = new HBox(leftBox, rightBox);
        customTitleBar.setAlignment(Pos.CENTER);
        customTitleBar.getStyleClass().add("custom-title-bar");

        StackPane.setAlignment(customTitleBar, Pos.TOP_CENTER);

        return customTitleBar;
    }
}