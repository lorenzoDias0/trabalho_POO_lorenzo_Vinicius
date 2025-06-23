package com.example.entities;

public class Player {
    private String nome;
    private String cor;
    private int velo;
    private int life;
    private int points;

    public Player(String nome, String cor, int velo, int life, int points) {
        this.nome = nome;
        this.cor = cor;
        this.velo = velo;
        this.life = life;
        this.points = points;
    }

    public Player() {
        this.nome = "Anônimo";
        this.cor = "YELLOW";
        this.velo = 5;
        this.life = 3;
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getVelo() {
        return velo;
    }

    public void setVelo(int velo) {
        this.velo = velo;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void imprimirPlayer() {
        System.out.println("Nome " + this.nome);
    }
}
