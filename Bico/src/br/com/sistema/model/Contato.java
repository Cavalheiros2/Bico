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
public class Contato {

    int id_contato;
    int id_usuario_principal;
    int id_usuario_contato;
    String apelido_contato;

    public Contato() {
    }

    public Contato(int id_contato, int id_usuario_principal, int id_usuario_contato) {
        this.id_contato = id_contato;
        this.id_usuario_principal = id_usuario_principal;
        this.id_usuario_contato = id_usuario_contato;
        this.apelido_contato = apelido_contato;
    }

    public int getId_contato() {
        return id_contato;
    }

    public void setId_contato(int id_contato) {
        this.id_contato = id_contato;
    }

    public int getId_usuario_principal() {
        return id_usuario_principal;
    }

    public void setId_usuario_principal(int id_usuario_principal) {
        this.id_usuario_principal = id_usuario_principal;
    }

    public int getId_usuario_contato() {
        return id_usuario_contato;
    }

    public void setId_usuario_contato(int id_usuario_contato) {
        this.id_usuario_contato = id_usuario_contato;
    }

    public String getApelido_contato() {
        return apelido_contato;
    }

    public void setApelido_contato(String apelido_contato) {
        this.apelido_contato = apelido_contato;
    }
}
