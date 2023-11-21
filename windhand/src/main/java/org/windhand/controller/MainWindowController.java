package org.windhand.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainWindowController {

    @FXML
    private Button staticButton;
    @FXML
    private Button assistantButton;
    @FXML
    private Button taskButton;
    @FXML
    private Button blockerButton;
    @FXML
    private Button settingButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button aboutUsButton;
    @FXML
    private Button normalWorkButton;
    @FXML
    private Button workButton;
    @FXML
    private ChoiceBox<String> workSpaceChoiceBox;
    private boolean isBlockerOn = true;

    private void switchPage(String fxmlURL, Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../" + fxmlURL));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("WindHand");
        stage.setScene(scene);
        stage.show();
    }

    public void workButtonClick() {
        if (workButton.getStyle().equals("-fx-background-color: #A0A0A0;")) {
            workButton.setStyle("-fx-background-color: #101010;");
            normalWorkButton.setStyle("-fx-background-color: #A0A0A0;");
        } else {
            workButton.setStyle("-fx-background-color: #A0A0A0;");
            normalWorkButton.setStyle("-fx-background-color: #101010;");
        }
        this.isBlockerOn = true;
    }

    public void normalButtonClick() {
        if (normalWorkButton.getStyle().equals("-fx-background-color: #A0A0A0;")) {
            workButton.setStyle("-fx-background-color: #A0A0A0;");
            normalWorkButton.setStyle("-fx-background-color: #101010;");
        } else {
            workButton.setStyle("-fx-background-color: #101010;");
            normalWorkButton.setStyle("-fx-background-color: #A0A0A0;");
        }
        this.isBlockerOn = false;
    }

    public void registrationOrAuthorization(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            switchPage("registration-page.fxml", mouseEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void statisticButtonClick(ActionEvent event) {
        try {
            switchPage("statistic-page.fxml", event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void assistantButtonClick(ActionEvent event) {
        try {
            switchPage("assistant-page.fxml", event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void taskButtonClick(ActionEvent event) {
        try {
            switchPage("task-page.fxml", event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void blockerButtonClick(ActionEvent event) {
        if (this.isBlockerOn) {
            try {
                switchPage("blocker-page.fxml", event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void settingButtonClick(ActionEvent event) {
        try {
            switchPage("setting-page.fxml", event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void helpButtonClick(ActionEvent event) {
        try {
            switchPage("help-page.fxml", event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void aboutUsButtonClick(ActionEvent event) {
        try {
            switchPage("about-us.fxml", event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void chooseWorkSpace(ActionEvent event) {
        initialize();
        String selectedWorkSpace = workSpaceChoiceBox.getValue();
        if ("Створити новий робочий простір".equals(selectedWorkSpace)) {
            // Реагуємо на вибір створення нового робочого простору
            //createNewWorkSpace();
        } else {
            // Обробка вибору існуючої робочої області
        }
    }

    public void initialize() {
        List<String> workSpaces = Arrays.asList("WorkSpace1", "WorkSpace2", "WorkSpace3");

        workSpaceChoiceBox.setItems(FXCollections.observableArrayList(workSpaces));

        workSpaceChoiceBox.getItems().add("Створити новий робочий простір");
    }
}
