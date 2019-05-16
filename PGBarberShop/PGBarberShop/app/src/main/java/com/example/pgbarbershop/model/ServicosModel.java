package com.example.pgbarbershop.model;

public class ServicosModel {
    private String nome;
    private String descricao;
    private int duracao;
    private String urlImage;

    public ServicosModel(String nome, String descricao, int duracao, String urlImage) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.urlImage = urlImage;
    }
    public ServicosModel() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
