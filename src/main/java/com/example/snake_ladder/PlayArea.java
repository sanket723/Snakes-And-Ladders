package com.example.snake_ladder;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import static javafx.scene.input.KeyCode.X;
import static javafx.scene.input.KeyCode.Y;

public class PlayArea {
    @FXML
    Text number,number1;
    @FXML
    Label changeturn;
    @FXML
    ImageView player1,player2;

    int turn = 1;

    HashMap < Pair<Double,Double>, Pair<Double,Double> > snakeLadderMove = new HashMap<>();
    @FXML
    public void roll(MouseEvent e) throws IOException {
        getSnakeLadderCoordinates();
        //System.out.println("Dice is Rolling");
        Random random = new Random();
        int rolling = random.nextInt(6) +1;
        // number.setText("" + rolling);


        if(turn==1){
           Pair<Double,Double> move_coordinate = movement(player1.getTranslateX(),player1.getTranslateY(),rolling);

            player1.setTranslateX(move_coordinate.getKey());
            player1.setTranslateY(move_coordinate.getValue());

            if(snakeLadderMove.containsKey(new Pair<>(move_coordinate.getKey(),move_coordinate.getValue()))){
                player1.setTranslateX(snakeLadderMove.get(new Pair<>(move_coordinate.getKey(),move_coordinate.getValue())).getKey());
                player1.setTranslateY(snakeLadderMove.get(new Pair<>(move_coordinate.getKey(),move_coordinate.getValue())).getValue());
            }

            checkWin(player1.getTranslateX(),player1.getTranslateY());

        }
        else{
            Pair<Double,Double> move_coordinate = movement(player2.getTranslateX(),player2.getTranslateY(),rolling);

            player2.setTranslateX(move_coordinate.getKey());
            player2.setTranslateY(move_coordinate.getValue());

            if(snakeLadderMove.containsKey(new Pair<>(move_coordinate.getKey(),move_coordinate.getValue()))){
                player2.setTranslateX(snakeLadderMove.get(new Pair<>(move_coordinate.getKey(),move_coordinate.getValue())).getKey());
                player2.setTranslateY(snakeLadderMove.get(new Pair<>(move_coordinate.getKey(),move_coordinate.getValue())).getValue());
            }

            checkWin(player2.getTranslateX(),player2.getTranslateY());

        }


        if(rolling!=6) {
            if (turn == 1) {
                turn = 2;
                changeturn.setText("Player 2's turn");
                number.setText(" = " + rolling);
                number1.setText("Red moves " + rolling + " step");
            }
            else {
                turn = 1;
                changeturn.setText("Player 1's turn");
                number.setText(" = " + rolling);
                number1.setText("Green moves " + rolling + " step");
            }
        }
        else{
            if(turn==1){
                number.setText(" = " + rolling);
                number1.setText("Red moves " + rolling + " step [extra turn]");
            }
            else{
                number.setText(" = " + rolling);
                number1.setText("Green moves " + rolling + " step [extra turn]");
            }
        }

    }

        //actual movement
    public Pair<Double,Double> movement(double translateX, double translateY, int rolling) {
        Double moveX = translateX;
        Double moveY = translateY;

        if(moveY%100==0) {
            moveX += rolling * 50;
            if (moveX > 500) {
                moveX = 500 * 2 - moveX + 50;
                moveY = moveY - 50;
            }
        }
        else{
            moveX -= rolling*50;
            if(moveX<50){
                if(moveY==-450) return new Pair<>(translateX,translateY);     //last row condition to not move par 100
                moveX = (-1 * moveX) + 50;
                moveY = moveY - 50;
            }
        }

        return new Pair<>(moveX,moveY);
    }

    void checkWin(Double X, Double Y) throws IOException {
        if(X==50 && Y==-450){
            Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
            winAlert.setTitle("Snake And Ladder");
            winAlert.setHeaderText("Game Result");
            if(turn==1)
                winAlert.setContentText("Player 1 has won the game.");
            else
                winAlert.setContentText("Player 2 has won the game.");

            winAlert.showAndWait();
            //Now loading front page i.e game page again
            GamePage page = new GamePage();
            HelloApplication.root.getChildren().setAll(page.root);
        }

    }
      void getSnakeLadderCoordinates(){
       // snakeLadderMove = new HashMap<>();

        snakeLadderMove.put(new Pair<>(50.0,0.0), new Pair<>(150.0,-150.0));
        snakeLadderMove.put(new Pair<>(200.0,0.0), new Pair<>(350.0,-50.0));
        snakeLadderMove.put(new Pair<>(450.0,0.0), new Pair<>(500.0,-150.0));
        snakeLadderMove.put(new Pair<>(200.0,-50.0), new Pair<>(350.0,0.0));
        snakeLadderMove.put(new Pair<>(50.0,-100.0), new Pair<>(100.0,-200.0));
        snakeLadderMove.put(new Pair<>(400.0,-100.0), new Pair<>(200.0,-400.0));
        snakeLadderMove.put(new Pair<>(500.0,-250.0), new Pair<>(350.0,-300.0));
        snakeLadderMove.put(new Pair<>(350.0,-250.0), new Pair<>(350.0,-150.0));
        snakeLadderMove.put(new Pair<>(100.0,-300.0), new Pair<>(100.0,-50.0));
        snakeLadderMove.put(new Pair<>(200.0,-300.0), new Pair<>(50.0,-250.0));
        snakeLadderMove.put(new Pair<>(500.0,-350.0), new Pair<>(500.0,-450.0));
        snakeLadderMove.put(new Pair<>(50.0,-350.0), new Pair<>(50.0,-450.0));
        snakeLadderMove.put(new Pair<>(350.0,-400.0), new Pair<>(200.0,-100.0));
        snakeLadderMove.put(new Pair<>(400.0,-450.0), new Pair<>(400.0,-350.0));
        snakeLadderMove.put(new Pair<>(300.0,-450.0), new Pair<>(300.0,-350.0));
        snakeLadderMove.put(new Pair<>(150.0,-450.0), new Pair<>(100.0,-350.0));

      }

}
