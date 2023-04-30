package com.tictactoe;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    List<Label> labelList;

    public void initialize() {
        labelList = new ArrayList<>(Arrays.asList(label1, label2, label3, label4, label5, label6, label7, label8, label9));
        labelList.forEach(this::setupText);
    }
    
    @FXML
    void restartGame() {
        Random random = new Random();
        int number = random.nextInt(2);

        isEndOfGame = false;
        message.setMaxWidth(Double.MAX_VALUE);
        message.setAlignment(Pos.CENTER);

        if (number == 0) {
            message.setText("Player X's Turn");
            playerTurn = 'X';
        }
        else {
            message.setText("Player O's Turn");
            playerTurn = 'O';
        }

        labelList.forEach(this::resetText);
    }

    public void resetText(Label label){
        label.setDisable(false);
        label.setText("");
        label.setStyle("-fx-text-fill: white; -fx-background-color: black");
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
            label.setText("X");
            playerTurn = 'O';
            message.setText("Player O's Turn");
        } else{
            label.setText("O");
            playerTurn = 'X';
            message.setText("Player X's Turn");
        }
    }

    public void checkIfGameIsOver(){
        for (int a = 1; a <= 8; a++) {
            String line = switch (a) {
                case 1 -> {
                    drawWinningLine(label1,label2,label3);
                    yield label1.getText() + label2.getText() + label3.getText();
                }
                case 2 -> {
                    drawWinningLine(label4, label5, label6);
                    yield label4.getText() + label5.getText() + label6.getText();
                }
                case 3 -> {
                    drawWinningLine(label7, label8, label9);
                    yield label7.getText() + label8.getText() + label9.getText();
                }

                case 4 -> {
                    drawWinningLine(label1, label5, label9);
                    yield label1.getText() + label5.getText() + label9.getText();
                }
                case 5 -> {
                    drawWinningLine(label3, label5, label7);
                    yield label3.getText() + label5.getText() + label7.getText();
                }
                case 6 -> {
                    drawWinningLine(label1, label4, label7);
                    yield label1.getText() + label4.getText() + label7.getText();
                }

                case 7 -> {
                    drawWinningLine(label2, label5, label8);
                    yield label2.getText() + label5.getText() + label8.getText();
                }

                case 8 -> {
                    drawWinningLine(label3, label6, label9);
                    yield label3.getText() + label6.getText() + label9.getText();
                }
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
    
    private static void drawWinningLine(Label label1, Label label2, Label label3){
        if((label1.getText() + label2.getText() + label3.getText()).equals("XXX") ||
                (label1.getText() + label2.getText() + label3.getText()).equals("OOO")){

            label1.setStyle("-fx-text-fill: black; -fx-background-color: white");
            label2.setStyle("-fx-text-fill: black; -fx-background-color: white");
            label3.setStyle("-fx-text-fill: black; -fx-background-color: white");
        }
    }
}

