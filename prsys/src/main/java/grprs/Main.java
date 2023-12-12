package grprs;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
    double W = 1366;
    double H = 716;
    private StackPane composite;
    private Controller controller;
    private Stage stage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage stage) throws IOException {
        // navigation bar menu
        VBox myMenuBarContainer = menuList(stage);
        
        // custom titlebar styling
        HBox customTitleBar = CustomTitleBar(stage);

        // titlebar icon anf label
        Image image = new Image("/assets/iconPRS.png");
        stage.getIcons().add(image);
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

        // File Sections
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patientHistory.fxml"));
        Parent root = loader.load();

        controller = loader.getController();
        controller.setMainApp(this);

        StackPane composite = new StackPane(bgIMG, blurIMG, bgOverlay, root, customTitleBar, myMenuBarContainer);

        Scene scene = new Scene(composite, W, H);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public Controller getController() {
        return controller;
    }


    // navigation menu (must be done later)
    private VBox menuList (Stage stage){
        Label hospitalNameTag = new Label("OSPA Leyte");

        // icons
        Image hisotryIcon = new Image(getClass().getResourceAsStream("/assets/PatientHistoryIcon.png"));
        ImageView myIcon1 = new ImageView(hisotryIcon);
        Image recordIcon = new Image(getClass().getResourceAsStream("/assets/patientRecordIcon.png"));
        ImageView myIcon2 = new ImageView(recordIcon);
        Image removedIcon = new Image(getClass().getResourceAsStream("/assets/RemovedPatientIcon.png"));
        ImageView myIcon3 = new ImageView(removedIcon);

        Button patientHistoryBTN = new Button("Patient History");
        Button patientRecordBTN = new Button("Patient Record");
        Button removedPatientBTN = new Button("Removed Patient");

        VBox label = new VBox(hospitalNameTag);
        VBox menuBarList = new VBox(patientHistoryBTN, patientRecordBTN, removedPatientBTN);

        VBox myMenuBarContainer = new VBox(label, menuBarList);
        myMenuBarContainer.setAlignment(Pos.TOP_CENTER);
        myMenuBarContainer.getStyleClass().add("MenuListBar");

        StackPane.setAlignment(myMenuBarContainer, Pos.CENTER_LEFT);

        return myMenuBarContainer;
    }

    void switchView(String fxmlFileName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent newRoot = loader.load();

        // Update the center content of the StackPane
        StackPane.setAlignment(newRoot, Pos.CENTER);
        composite.getChildren().set(composite.getChildren().size() - 1, newRoot);
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