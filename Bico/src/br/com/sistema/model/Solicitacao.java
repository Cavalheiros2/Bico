/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.model;

/**
 *
 * @author guiho
 */
public class Solicitacao {
    int id_solicitacao;
    String categoria;
    String descricao; //colocar as datas no padrão DD/MM/AAAA
    String data_solicitacao;
    String hora_solicitacao;
    String local_solicitacao;
    String regiao;
    float proposta_valor;

    public Solicitacao() {
    }

    public Solicitacao(int id_solicitacao, String categoria, String descricao, String data_solicitacao, String hora_solicitacao, String local_solicitacao, String regiao, float proposta_valor) {
        this.id_solicitacao = id_solicitacao;
        this.categoria = categoria;
        this.descricao = descricao;
        this.data_solicitacao = data_solicitacao;
        this.hora_solicitacao = hora_solicitacao;
        this.local_solicitacao = local_solicitacao;
        this.regiao = regiao;
        this.proposta_valor = proposta_valor;
    }

    public int getId_solicitacao() {
        return id_solicitacao;
    }

    public void setId_solicitacao(int id_solicitacao) {
        this.id_solicitacao = id_solicitacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData_solicitacao() {
        return data_solicitacao;
    }

    public void setData_solicitacao(String data_solicitacao) {
        this.data_solicitacao = data_solicitacao;
    }

    public String getHora_solicitacao() {
        return hora_solicitacao;
    }

    public void setHora_solicitacao(String hora_solicitacao) {
        this.hora_solicitacao = hora_solicitacao;
    }

    public String getLocal_solicitacao() {
        return local_solicitacao;
    }

    public void setLocal_solicitacao(String local_solicitacao) {
        this.local_solicitacao = local_solicitacao;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public float getProposta_valor() {
        return proposta_valor;
    }

    public void setProposta_valor(float proposta_valor) {
        this.proposta_valor = proposta_valor;
    }
}
