private VBox menuList (Stage stage){
        Label hospitalNameTag = new Label("OSPA Leyte");

        // icons
        Image hisotryIcon = new Image(getClass().getResourceAsStream("/assets/PatientHistoryIcon.png"));
        ImageView myIcon1 = new ImageView(hisotryIcon);
        Image recordIcon = new Image(getClass().getResourceAsStream("/assets/patientRecordIcon.png"));
        ImageView myIcon2 = new ImageView(recordIcon);
        Image removedIcon = new Image(getClass().getResourceAsStream("/assets/RemovedPatientIcon.png"));
        ImageView myIcon3 = new ImageView(removedIcon);

        Button patientHistoryBTN = new Button(" Patient History");
        patientHistoryBTN.setGraphic(myIcon1);
        Button patientRecordBTN = new Button(" Patient Record");
        patientRecordBTN.setGraphic(myIcon2);
        Button removedPatientBTN = new Button(" Removed Patient");
        removedPatientBTN.setGraphic(myIcon3);

        VBox label = new VBox(hospitalNameTag);
        VBox menuBarList = new VBox(patientHistoryBTN, patientRecordBTN, removedPatientBTN);

        VBox myMenuBarContainer = new VBox(label, menuBarList);
        myMenuBarContainer.setAlignment(Pos.TOP_CENTER);
        myMenuBarContainer.getStyleClass().add("MenuListBar");

        StackPane.setAlignment(myMenuBarContainer, Pos.CENTER_LEFT);

        return myMenuBarContainer;
    }    

private HBox patientRecord(Stage stage){
        // variables
        GridPane myGrid = new GridPane();
        myGrid.getStyleClass().add("gridPanel");
        Button mybtn = new Button("Hello World");
        Label myLabel = new Label("zzz");

        GridPane.setRowIndex(mybtn, 0);
        GridPane.setColumnIndex(mybtn, 1);
        GridPane.setConstraints(myLabel, 2, 0);

        // calling all variables
        myGrid.getChildren().addAll(mybtn, myLabel);

        HBox container = new HBox(myGrid);

        container.getStyleClass().add("patientRecord");
        container.setAlignment(Pos.CENTER);
       
        StackPane.setAlignment(container, Pos.CENTER_LEFT);
       
        return container;
    }

OSPA Doctors    |   Availability    |   No. of appointments
1 Dra. Sarah Michaels   |   Available   |   4   