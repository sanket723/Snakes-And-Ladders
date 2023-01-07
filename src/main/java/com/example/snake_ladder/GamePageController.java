package com.example.snake_ladder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GamePageController {


    @FXML
    ImageView Play;

    public void play(MouseEvent e) throws IOException {
        AnchorPane play = FXMLLoader.load(getClass().getResource("PlayArea.fxml"));
        HelloApplication.root.getChildren().setAll(play);
    }
}
