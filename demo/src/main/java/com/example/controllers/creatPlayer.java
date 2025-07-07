package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.io.IOException;

import com.example.App;
import com.example.entities.Player;

public class creatPlayer {

    private String name = null;
    public Player player = new Player();
    

    @FXML
    private AnchorPane mainDisplay;

    @FXML
    private TextField textBox;

    @FXML
    private Button Submit;

    @FXML
    private ChoiceBox<String> cores;

    @FXML
    private Rectangle visualPlayer; 
    
    

    @FXML
    public void initialize() {
        visualPlayer.setFill(Color.YELLOW);
        cores.getItems().addAll("YELLOW", "BLUE", "GREEN");
        cores.setValue("YELLOW");
        cores.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, novoValor) -> {
            if (novoValor != null) {
                player.setCor(cores.getValue());
                visualPlayer.setFill(Color.valueOf(player.getCor()));
            }
        });
    }

    @FXML
    public void setNome(KeyEvent event) {
        name = textBox.getText();
    }

    @FXML
    public void definindoCor(KeyEvent event) {
        name = textBox.getText();
    }

    @FXML
    public void submit(MouseEvent event) {
        player.setNome(name);
        player.setCor(cores.getValue());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/jogo.fxml"));
            Parent root = loader.load();

            Jogo gameController = loader.getController();
            gameController.setPlayer(player);

           
            
            App.stageGlobal.setScene(new Scene(root));
            App.stageGlobal.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
