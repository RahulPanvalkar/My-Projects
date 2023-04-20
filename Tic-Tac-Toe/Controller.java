package com.example.newtictactoe;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    @FXML
    private Label message;

    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;
    @FXML
    private Label label9;

    private char playerTurn = 'X';
    private boolean isEndOfGame = false;
    ArrayList<Label> labelList;

    public void initialize() {
        labelList = new ArrayList<>(Arrays.asList(label1, label2, label3, label4, label5, label6, label7, label8, label9));
        labelList.forEach(label -> {
            setupText(label);
        });
    }

    @FXML
    void restartGame() {
        isEndOfGame = false;
        message.setMaxWidth(Double.MAX_VALUE);
        message.setAlignment(Pos.CENTER);
        playerTurn = 'X';
        labelList.forEach(this::resetText);
    }

    public void resetText(Label label){
        label.setDisable(false);
        label.setText("");
        message.setText("Player "+playerTurn+"'s Turn");
        message.setAlignment(Pos.CENTER);
    }

    @FXML
    public void setupText(Label label) {
        label.setOnMouseClicked(mouseEvent -> {

            if (label.getText().isEmpty() && !isEndOfGame){
                label.setText(String.valueOf(playerTurn));
                setPlayerSymbol(label);
                label.setDisable(true);
                checkIfGameIsOver();
            }
        });
    }

    public void setPlayerSymbol(Label label){
        if(playerTurn == 'X'){
            playerTurn = 'O';
            label.setText("X");
            message.setText("Player O's Turn");
        } else{
            playerTurn = 'X';
            label.setText("O");
            message.setText("Player X's Turn");
        }
    }

    public void checkIfGameIsOver(){
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> label1.getText() + label2.getText() + label3.getText();
                case 1 -> label4.getText() + label5.getText() + label6.getText();
                case 2 -> label7.getText() + label8.getText() + label9.getText();

                case 3 -> label1.getText() + label5.getText() + label9.getText();
                case 4 -> label3.getText() + label5.getText() + label7.getText();
                case 5 -> label1.getText() + label4.getText() + label7.getText();

                case 6 -> label2.getText() + label5.getText() + label8.getText();

                case 7 -> label3.getText() + label6.getText() + label9.getText();
                default -> null;
            };

            //X is winner
            if (line.equals("XXX")) {
                isEndOfGame = true;
                message.setText("Player X Won!");
                return;
            }

            //O is winner
            else if (line.equals("OOO")) {
                isEndOfGame = true;
                message.setText("Player O Won!");
                return;
            }
          
            // Draw
            else if (!(label1.getText().isEmpty() || label2.getText().isEmpty() || label3.getText().isEmpty() ||
                    label4.getText().isEmpty() || label5.getText().isEmpty() || label6.getText().isEmpty() ||
                    label7.getText().isEmpty() || label8.getText().isEmpty() || label9.getText().isEmpty())){

                isEndOfGame = true;
                message.setText("Draw!");
            }
        }
    }
}

