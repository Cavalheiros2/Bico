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
}
