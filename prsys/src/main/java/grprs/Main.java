package grprs;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class Main extends Application {
    double W = 1366;
    double H = 716;
    private HBox contentLayout;
    private Button patientHistoryBTN;
    private Button patientRecordBTN;
    private HBox container;

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
        bgOverlay.setTranslateY(10);
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
            circles.getStyleClass().add("processCircles");
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
        Image removedIcon = new Image(getClass().getResourceAsStream("/assets/RemovedPatientIcon.png"));
        ImageView myIcon3 = new ImageView(removedIcon);

        patientHistoryBTN = new Button(" Patient History");
        patientHistoryBTN.setGraphic(myIcon1);
        patientRecordBTN = new Button(" Patient Record");
        patientRecordBTN.setGraphic(myIcon2);

        Platform.runLater(() -> {
            patientHistoryBTN.setOnAction(event -> switchContent(patientHistory(stage), patientHistoryBTN));
            patientRecordBTN.setOnAction(event -> switchContent(patientRecord(stage), patientRecordBTN));

            switchContent(patientHistory(stage), patientHistoryBTN);
        });

        VBox label = new VBox(hospitalNameTag);
        VBox menuBarList = new VBox(patientHistoryBTN, patientRecordBTN);
        menuBarList.setSpacing(10);

        VBox myMenuBarContainer = new VBox(label, menuBarList);
        myMenuBarContainer.setAlignment(Pos.TOP_CENTER);
        myMenuBarContainer.getStyleClass().add("MenuListBar");

        // right panel
        HBox contentLayout = new HBox(myMenuBarContainer, container);
        contentLayout.setAlignment(Pos.CENTER_LEFT);
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
        Label l1 = new Label("Test 1");
        HBox container = new HBox(l1);

        container.getStyleClass().add("patientHistoryStyle");
        container.setAlignment(Pos.CENTER);

        return container;
    }

    // patientRecord content
    private VBox patientRecord(Stage stage) {
        // top panel //

        // date
        Text day = new Text("Saturday, 16");
        Color color = Color.web("#3E3E3E");
        day.setFill(color);
        day.setId("dayText");

        // Create a Text node for the "December 2023" part with regular (400) font
        // weight
        Text month = new Text("December 2023");
        month.setId("monthText");

        // Create a Label and add the Text nodes to it
        Label dateLabel = new Label();
        dateLabel.setId("dateField");
        dateLabel.setGraphic(day); // Set the bold text as the graphic
        dateLabel.setText(" " + month.getText());

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

        HBox tpanel_left = new HBox(dateLabel);
        tpanel_left.setPrefWidth(1057 / 2);
        tpanel_left.setAlignment(Pos.CENTER_LEFT);
        HBox tpanel_right = new HBox(adminNumber, chatBot);
        tpanel_right.setPrefWidth(1057 / 2);
        tpanel_right.setSpacing(10);
        tpanel_right.setAlignment(Pos.CENTER_RIGHT);

        HBox topPanel = new HBox(tpanel_left, tpanel_right);
        topPanel.getStyleClass().add("topPanel");

        // bottom panel
        Label phase1 = new Label("Patient Information");
        phase1.getStyleClass().add("active");
        Label phase2 = new Label("Clinical Summary");
        Label phase3 = new Label("Done");

        HBox processing = processing(stage);
        HBox processing2 = processing(stage);

        HBox indicator = new HBox(phase1, processing, phase2, processing2, phase3);
        indicator.setSpacing(20);
        indicator.setAlignment(Pos.CENTER_LEFT);
        indicator.setId("phasingIndicator");

        // content panel here //
        Label dataLabel1 = new Label("Patient's name");
        Label dataLabel2 = new Label("Date of birth");
        Label dataLabel3 = new Label("PRN");
        Label dataLabel4 = new Label("Sex");
        Label dataLabel5 = new Label("Patient's phone no.");
        Label dataLabel6 = new Label("Emerg. contact no.");
        Label dataLabel7 = new Label("Email address");
        Label dataLabel8 = new Label("Province");
        Label dataLabel9 = new Label("City");
        Label dataLabel10 = new Label("Address");
        Label dataLabel11 = new Label("Martial status");
        Label dataLabel12 = new Label("Occupation");
        Label dataLabel13 = new Label("Alergies");
        Label dataLabel14 = new Label("Current medication");

        TextField firstNameField = new TextField();
        TextField middleNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField dobField = new TextField();
        TextField PRNField = new TextField();
        TextField sexField = new TextField();
        TextField ppnField = new TextField();
        TextField ecnField = new TextField();
        TextField emailField = new TextField();
        TextField provinceField = new TextField();
        TextField cityField = new TextField();
        TextField addressField = new TextField();
        TextField maritalStatusField = new TextField();
        TextField occupationField = new TextField();
        TextField allergiesField = new TextField();
        TextField currentMedField = new TextField();

        firstNameField.getStyleClass().add("textFields");
        firstNameField.getStyleClass().add("row-3s");
        middleNameField.getStyleClass().add("textFields");
        middleNameField.getStyleClass().add("row-3s");
        lastNameField.getStyleClass().add("textFields");
        lastNameField.getStyleClass().add("row-3s");

        dobField.getStyleClass().add("textFields");
        dobField.getStyleClass().add("row-f4");
        PRNField.getStyleClass().add("textFields");
        PRNField.getStyleClass().add("row-f5");
        sexField.getStyleClass().add("textFields");
        sexField.getStyleClass().add("row-f6");
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
        maritalStatusField.getStyleClass().add("textFields");
        maritalStatusField.getStyleClass().add("row-pair1");
        occupationField.getStyleClass().add("textFields");
        occupationField.getStyleClass().add("row-pair1");

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

        checkBox1.setOnAction(event -> {
            if (checkBox1.isSelected()) {
                checkIconView.setImage(checkIcon1);
                checkBox1.setStyle("-fx-border-color: #4A838E; -fx-text-fill: #105B6A;");
                System.out.println("Tabacoo use: YES");
            } else {
                checkIconView.setImage(checkIcon2);
                checkBox1.setStyle("-fx-border-color: #ADAEAE; -fx-text-fill: #ADAEAE;");
                System.out.println("Tabacoo use: NO");
            }
        });

        CheckBox checkBox2 = new CheckBox("  Drink alcohol  ");
        checkBox2.setId("checkBoxBTN");
        checkBox2.setGraphic(checkIconView2);

        checkBox2.setOnAction(event -> {
            if (checkBox2.isSelected()) {
                checkIconView2.setImage(checkIcon3);
                checkBox2.setStyle("-fx-border-color: #4A838E; -fx-text-fill: #105B6A;");
                System.out.println("Drink alcohol: YES");
            } else {
                checkIconView2.setImage(checkIcon4);
                checkBox2.setStyle("-fx-border-color: #ADAEAE; -fx-text-fill: #ADAEAE;");
                System.out.println("Drink alcohol: NO");
            }
        });

        HBox checkBoxContainer = new HBox(checkBox1, checkBox2);
        checkBoxContainer.setId("checkBoxContainer");

        Button prPhase1Submit = new Button("Next");
        prPhase1Submit.setId("prsSubmitBTN");

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
        prPanel2.addRow(0, dataLabel2, dataLabel3, dataLabel4, dataLabel5);
        prPanel2.addRow(1, dobField, PRNField, sexField, ppnField);
        prPanel3.addRow(0, dataLabel6, dataLabel7, dataLabel8);
        prPanel3.addRow(1, ecnField, emailField, provinceField);
        prPanel4.addRow(0, dataLabel9, dataLabel10, dataLabel11, dataLabel12);
        prPanel4.addRow(1, cityField, addressField, maritalStatusField, occupationField);
        prPanel5.addRow(0, dataLabel13, dataLabel14);
        prPanel5.addRow(1, allergiesField, currentMedField);
        HBox prPanel6 = new HBox(checkBoxContainer, prPhase1Submit);
        prPanel6.setId("prPanel6");
        prPanel6.setAlignment(Pos.CENTER_RIGHT);
        prPanel6.setSpacing(20);

        VBox gridPaneContainer = new VBox(prPanel, prPanel2, prPanel3, prPanel4, prPanel5, prPanel6);
        gridPaneContainer.setSpacing(15);
        gridPaneContainer.setId("prMainContentPanel");

        // img Container //
        Image heroIMG = new Image("/assets/HeroImg.png");
        ImageView displayIMG = new ImageView(heroIMG);
        Circle bgCircle = new Circle(142);
        bgCircle.setId("heroImgCircle");

        StackPane imgContainer = new StackPane(bgCircle, displayIMG);
        imgContainer.setAlignment(Pos.TOP_CENTER);
        imgContainer.setId("imgContainer");

        // inserts the content here
        HBox contentPanel = new HBox(gridPaneContainer, imgContainer);
        contentPanel.setId("prMainContent");
        contentPanel.setSpacing(10);

        VBox container = new VBox(topPanel, indicator, contentPanel);
        container.setSpacing(10);
        container.getStyleClass().add("patientRecordStyle");
        container.setAlignment(Pos.TOP_CENTER);

        return container;
    }

    // removedPatient content
    private HBox removedPatient(Stage stage) {
        Label l1 = new Label("Test 3");
        HBox container = new HBox(l1);

        container.getStyleClass().add("removedPatientStyle");
        container.setAlignment(Pos.CENTER);

        return container;
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

    public static void main(String[] args) {
        launch(args);
    }
}