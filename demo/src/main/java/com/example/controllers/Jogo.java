package com.example.controllers;

import java.util.ResourceBundle;

import com.example.entities.Ghost;
import com.example.entities.Player;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

import java.net.URL;

public class Jogo implements Initializable {
        public Player player = new Player();
        public Ghost ghost = new Ghost("RED", 1);

        private BooleanProperty wPressed = new SimpleBooleanProperty();
        private BooleanProperty aPressed = new SimpleBooleanProperty();
        private BooleanProperty sPressed = new SimpleBooleanProperty();
        private BooleanProperty dPressed = new SimpleBooleanProperty();

        private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);
        private final int cellSize = 32;

        private int posrecPlayerX = 1;
        private int posrecPlayerY = 1;
        private Rectangle[] vidas = new Rectangle[player.getLife()];
        private final int[][] mapa = {
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
                        { 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
                        { 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
                        { 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
                        { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
                        { 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
                        { 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
                        { 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1 },
                        { 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 9, 9, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 9, 9, 9, 9, 9, 9, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
                        { 12, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 9, 9, 9, 9, 9, 9, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 12 },
                        { 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 9, 4, 9, 9, 9, 9, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
                        { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
                        { 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
                        { 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
                        { 1, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 1 },
                        { 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1 },
                        { 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1 },
                        { 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1 },
                        { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 },
                        { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 },
                        { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
        };

        @FXML
        private Rectangle recPlayer;

        @FXML
        private Rectangle recGhost;

        @FXML
        private Rectangle life;

        @FXML
        private AnchorPane mainDisplay;

        @FXML
        private Label points;

        @FXML
        private VBox gmDisplay;

        private long lastMoveTime = 0;
        private final long moveCooldown = 100_000_000; // 100 ms em nanos

        AnimationTimer timer = new AnimationTimer() {
                @Override
                // aqui é o timer, usado para dar tipo um delay entre os inputs e identificar
                // qual tecla foi clicada e realizar a função correspondende.
                // Ele é mt importando pois, sem ele o metodo de movimentação seria chamada a
                // cada frame ou seja 60 vezes por segundo o que faria com que o recPlayer
                // ficasse
                // extremamente rápido
                public void handle(long now) {
                        if (now - lastMoveTime < moveCooldown) {
                                return;
                        }

                        // move para cima
                        if (wPressed.get()) {
                                moverecPlayer(-1, 0);
                                lastMoveTime = now;
                        }
                        // move para baixo
                        if (sPressed.get()) {
                                moverecPlayer(1, 0);
                                lastMoveTime = now;
                        }
                        // move para esquerda
                        if (aPressed.get()) {
                                moverecPlayer(0, -1);
                                lastMoveTime = now;
                        }
                        // move para direita
                        if (dPressed.get()) {
                                moverecPlayer(0, 1);
                                lastMoveTime = now;
                        }
                }
        };

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                renderMapa(mapa, cellSize);
                // centraliza o recPlayer na grid
                recPlayer.setLayoutX(posrecPlayerY * cellSize);
                recPlayer.setLayoutY(posrecPlayerX * cellSize);

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
                Platform.runLater(() -> mainDisplay.requestFocus());

        }

        private void movementSetup() {
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

        private void moverecPlayer(int movX, int movY) {
                // Calcula próxima posição
                int proxLinha = posrecPlayerX + movX;
                int proxColuna = posrecPlayerY + movY;

                // Verifica limites do mapa
                if (proxLinha < 0 || proxLinha >= mapa.length || proxColuna < 0 || proxColuna >= mapa[0].length) {
                        return;
                }

                int valorCelula = mapa[proxLinha][proxColuna];

                // Verifica se é uma célula acessível (0 = livre, 2 = ponto, 12 = portal)
                if (valorCelula != 0 && valorCelula != 2 && valorCelula != 12) {
                        return;
                }

                // Se for ponto(2), remove ele e aumenta pontuação
                if (valorCelula == 2) {
                        mapa[proxLinha][proxColuna] = 0;

                        Node ponto = mainDisplay.lookup("#" + proxColuna + "," + proxLinha);
                        if (ponto != null) {
                                mainDisplay.getChildren().remove(ponto);
                        }

                        player.setPoints(player.getPoints() + 1);
                        uptadePoints(); // Atualiza texto da pontuação
                }

                // Se for portal(12), teleporta para o lado oposto
                else if (valorCelula == 12) {
                        if (proxColuna > 4) {
                                proxColuna = 0;
                        } else {
                                proxColuna = mapa.length - 4;
                        }
                        // mesma coluna (movimento horizontal pelo portal)
                }

                // Atualiza posição do jogador
                posrecPlayerX = proxLinha;
                posrecPlayerY = proxColuna;

                recPlayer.setLayoutX(posrecPlayerY * cellSize);
                recPlayer.setLayoutY(posrecPlayerX * cellSize);
        }

        private void renderMapa(int[][] mapa, int cellSize) {
                // percorre a matriz criando as parede conforme os numeros 1 organizados na
                // matriz mapa
                for (int y = 0; y < mapa.length; y++) {
                        for (int x = 0; x < mapa[0].length; x++) {
                                // cria um retangulo com a escala de 32x32, representando uma celula da matriz
                                Rectangle cell = new Rectangle(cellSize, cellSize);
                                Circle point = new Circle(4, Color.WHITE);

                                // definindo a posição da parede usando a matriz como referencia
                                cell.setLayoutX(x * cellSize);
                                cell.setLayoutY(y * cellSize);

                                // se nessa posição da matriz for 1 então esse retangulo (cell) precisar ser uma
                                // parede
                                if (mapa[y][x] == 1) {
                                        // definindo a cor da dessa parede (cell)
                                        cell.setFill(Color.BLACK);
                                        cell.setStroke(Color.BLUE);
                                } else if (mapa[y][x] == 4) {
                                        recGhost = new Rectangle(cellSize, cellSize);
                                        recGhost.setId("recGhost");
                                        recGhost.setFill(Color.valueOf(ghost.getCor()));
                                        recGhost.setLayoutX(x * cellSize);
                                        recGhost.setLayoutY((y - 1) * cellSize);
                                        mainDisplay.getChildren().add(recGhost);
                                        recGhost.toFront();
                                } else {
                                        // caso não for uma parede ent apenas pinta de branco esse retangulo (cell) e
                                        // deixa uma borda para dar efeito de tabuleiro
                                        cell.setStyle("-fx-fill: black; ");
                                        if (mapa[y][x] == 2) {
                                                point.setLayoutX(x * cellSize + cellSize / 2.0);
                                                point.setLayoutY(y * cellSize + cellSize / 2.0);
                                                point.setId(x + "," + y);
                                                mainDisplay.getChildren().add(point);
                                        }
                                }
                                // adiciona esse retangulo (cell) na tela
                                mainDisplay.getChildren().add(cell);
                                point.toFront();
                        }
                }
                // cria o recPlayer
                recPlayer = new Rectangle(cellSize, cellSize);
                recPlayer.setId("recPlayer");
                recPlayer.setLayoutX(1 * cellSize);
                recPlayer.setLayoutY(1 * cellSize);
                mainDisplay.getChildren().add(recPlayer);
                renderHud();
        }

        public void setPlayer(Player pl) {
                this.player.setNome(pl.getNome());
                this.player.setCor(pl.getCor());
                recPlayer.setFill(Color.valueOf(player.getCor()));
        }

        private void renderHud() {
                for (int i = 0; i < player.getLife(); i++) {
                        life = new Rectangle(cellSize, cellSize);
                        life.setId("life");
                        life.setStyle("-fx-fill: red;");
                        vidas[i] = life;
                        gmDisplay.getChildren().add(life);
                }
                points = new Label("Pontuação: " + player.getPoints());
                points.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: white;");
                gmDisplay.getChildren().add(points);
        }

        public void uptadePoints() {
                points.setText("Pontuação: " + player.getPoints());
        }

}