package com.example.controllers;

import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;

public class Jogo implements Initializable {

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);
    private final int cellSize = 32;

    private int posPlayerX = 1;
    private int posPlayerY = 1;
    private final int[][] mapa = {
    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
};
    @FXML
    private Rectangle player;

    @FXML
    private AnchorPane mainDisplay;

    private long lastMoveTime = 0;
    private final long moveCooldown = 100_000_000; // 100 ms em nanos

    AnimationTimer timer = new AnimationTimer() {
        @Override

        // aqui é o timer, usado para dar tipo um delay entre os inputs e identificar
        // qual tecla foi clicada e realizar a função correspondende.
        // Ele é mt importando pois, sem ele o metodo de movimentação seria chamada a
        // cada frame ou seja 60 vezes por segundo o que faria com que o player ficasse
        // extremamente rápido
        public void handle(long now) {
            if (now - lastMoveTime < moveCooldown) {
                return;
            }

            // move para cima
            if (wPressed.get()) {
                movePlayer(-1, 0);
                lastMoveTime = now;
            }
            // move para baixo
            else if (sPressed.get()) {
                movePlayer(1, 0);
                lastMoveTime = now;
            }
            // move para esquerda
            else if (aPressed.get()) {
                movePlayer(0, -1);
                lastMoveTime = now;
            }
            // move para direita
            else if (dPressed.get()) {
                movePlayer(0, 1);
                lastMoveTime = now;
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        renderMapa(mapa, cellSize);

        // centraliza o player na grid
        player.setLayoutX(posPlayerY * cellSize);
        player.setLayoutY(posPlayerX * cellSize);

        movementSetup();

        // caso alguma, qualquer tecla válida para o move seja presionada ou segurada,
        // ele vai dar start no timer
        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if (!aBoolean) {
                timer.start();
            } else {
                timer.stop();
            }
        }));
        mainDisplay.requestFocus();
    }

    public void movementSetup() {
        // apenas atualiza as variaveis do tipo boolean para True caso a tecla
        // correspondente
        // a ela for pressionada
        mainDisplay.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W)
                wPressed.set(true);
            if (e.getCode() == KeyCode.A)
                aPressed.set(true);
            if (e.getCode() == KeyCode.S)
                sPressed.set(true);
            if (e.getCode() == KeyCode.D)
                dPressed.set(true);
        });

        // apenas atualiza as variaveis do tipo boolean para False caso a tecla
        // correspondente
        // a ela for solta, quando para de ser pressionada
        mainDisplay.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.W)
                wPressed.set(false);
            if (e.getCode() == KeyCode.A)
                aPressed.set(false);
            if (e.getCode() == KeyCode.S)
                sPressed.set(false);
            if (e.getCode() == KeyCode.D)
                dPressed.set(false);
        });
    }

    private void movePlayer(int movX, int movY) {

        // pega a prox posição do player mas, não atualiza ainda
        int proxCol = posPlayerX + movX;
        int proxLin = posPlayerY + movY;

        // faz 3 verificações, verifica se a prox posição esta dentro do limite inferior
        // do mapa e do limite superior para as linhas e colunas, verifica se pode mover
        // (prox posição é uma celula livre, sem parede)
        if (proxCol >= 0 && proxCol < mapa.length &&
                proxLin >= 0 && proxLin < mapa[0].length &&
                mapa[proxCol][proxLin] == 0) {
            // pega aprox posição
            posPlayerX = proxCol;
            posPlayerY = proxLin;

            // atualização a posição atual do player para a sua prox posição
            player.setLayoutX(posPlayerY * cellSize);
            player.setLayoutY(posPlayerX * cellSize);
        }
    }

    public void renderMapa(int[][] mapa, int cellSize) {
        // percorre a matriz criando as parede conforme os numeros 1 organizados na
        // matriz mapa
        for (int y = 0; y < mapa.length; y++) {
            for (int x = 0; x < mapa[0].length; x++) {
                // cria um retangulo com a escala de 32x32, representando uma celula da matriz
                Rectangle cell = new Rectangle(cellSize, cellSize);

                // definindo a posição da parede usando a matriz como referencia
                cell.setLayoutX(x * cellSize);
                cell.setLayoutY(y * cellSize);

                // se nessa posição da matriz for 1 então esse retangulo (cell) precisar ser uma
                // parede
                if (mapa[y][x] == 1) {
                    // definindo a cor da dessa parede (cell)
                    cell.setFill(Color.BLACK);
                    cell.setStroke(Color.BLUE);
                } else {
                    // caso não for uma parede ent apenas pinta de branco esse retangulo (cell) e
                    // deixa uma borda para dar efeito de tabuleiro
                    cell.setStyle("-fx-fill: black; ");
                }
                // adiciona esse retangulo (cell) na tela
                mainDisplay.getChildren().addAll(cell);
            }
        }
        // cria o player
        player = new Rectangle(cellSize, cellSize);
        player.setId("player");
        player.setStyle("-fx-fill: yellow;");
        player.setLayoutX(1 * cellSize);
        player.setLayoutY(1 * cellSize);
        mainDisplay.getChildren().add(player);
    }

}