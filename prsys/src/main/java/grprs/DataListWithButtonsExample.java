package grprs;

import java.io.IOException;
import javafx.application.*;
import javafx.beans.binding.Bindings;
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
import javafx.geometry.Pos;
import java.time.LocalDate;
import java.time.format.*;
import javafx.collections.*;

public class DataListWithButtonsExample extends Application {

    private ObservableList<DataEntry> dataList;

    @Override
    public void start(Stage primaryStage) {
        dataList = FXCollections.observableArrayList();

        // Add some sample data
        dataList.add(new DataEntry("John", 25, "Male"));
        dataList.add(new DataEntry("Alice", 30, "Female"));

        // Create a VBox to hold individual entries
        VBox entriesBox = new VBox(10);

        // Populate the VBox with entries and buttons
        for (DataEntry entry : dataList) {
            EntryNode entryNode = new EntryNode(entry);
            entriesBox.getChildren().add(entryNode);
        }

        Scene scene = new Scene(entriesBox, 300, 200);

        primaryStage.setTitle("Data List with Buttons Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class EntryNode extends VBox {
        private DataEntry dataEntry;

        public EntryNode(DataEntry dataEntry) {
            this.dataEntry = dataEntry;

            Label nameLabel = new Label(dataEntry.getName());
            Button showDataButton = new Button("Show Data");
            showDataButton.setOnAction(e -> showData());
            showDataButton.setOnAction(e -> showData());

            getChildren().addAll(nameLabel, showDataButton);
        }

        private void showData() {
            System.out.println("Specific Information: " + dataEntry);
        }
    }

    private static class DataEntry {
        private String name;
        private int age;
        private String gender;

        public DataEntry(String name, int age, String gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "DataEntry{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender='" + gender + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

