package com.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("TicTacToe.fxml"));
        Scene scene = new Scene(root, 300, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            onExitButtonClick(stage);
        });
    }

    private void onExitButtonClick(Stage window) {
        Alert alert = new Alert(Alert.AlertType.NONE, "Do you want to Quit?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Exit");
        alert.getDialogPane().setPrefSize(250, 90);

        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            window.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

