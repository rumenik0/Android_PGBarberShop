package com.example.pgbarbershop.model;

public class Agendamento {
    private Filial filial;
    private Servicos servicos;
    private ProfissionaisModel profissional;
    private String data;
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public Servicos getServicos() {
        return servicos;
    }

    public void setServicos(Servicos servicos) {
        this.servicos = servicos;
    }

    public ProfissionaisModel getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionaisModel profissional) {
        this.profissional = profissional;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
