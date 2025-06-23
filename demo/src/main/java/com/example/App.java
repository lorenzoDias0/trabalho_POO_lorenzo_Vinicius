package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

import com.example.entities.Player;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Stage stageGlobal;

    public Player player = new Player();

    @Override
    public void start(Stage stage) throws IOException {
        stageGlobal = stage;
        scene = new Scene(loadFXML("creatPlayer"),Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
        stage.setY(0);
        stage.setX(0);
        stage.setScene(scene);
        stage.show();
    }

    public void setCenas(Scene cena) {
        scene = cena;

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public void setPlayer(Player player1) {
        player = player1;
    }

}