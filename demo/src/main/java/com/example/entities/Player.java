package com.example.entities;

public class Player {
    private String nome;
    private String cor;
    private int velo;
    private int life;
    
    public Player(String nome, String cor, int velo, int life) {
        this.nome = nome;
        this.cor = cor;
        this.velo = velo;
        this.life = life;
    }
    
    public Player() {
        this.nome = null;
        this.cor = null;
        this.velo = 5;
        this.life = 0;
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

    public void imprimirPlayer(){
        System.out.println("Nome "+this.nome);
    }
}
