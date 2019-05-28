package com.example.pgbarbershop.model;

public class ProfissionaisModel {
    private String nome;
    private Filial filial;
    private String urlImage;


    public String getUrlImage() {
        return urlImage;
    }
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Filial getFilial() {
        return filial;
    }
    public void setFilial(Filial filial) {
        this.filial = filial;
    }
}
