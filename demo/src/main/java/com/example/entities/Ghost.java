package com.example.entities;

public class Ghost {
    private String cor;
    private int velo;

    public Ghost(String cor, int velo) {
        this.cor = cor;
        this.velo = velo;
    }

    public Ghost() {
        this.cor = null;
        this.velo = 0;
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
}
