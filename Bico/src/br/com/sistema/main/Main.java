/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.main;

import br.com.sistema.jdbc.ConnectionFactory;
import br.com.sistema.view.FrmTelaLogin;
import javax.swing.JOptionPane;

/**
 *
 * @author guiho
 */
public class Main {
    public static void main(String[] args) {
        
        try{
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com Sucesso!");
        }
       catch(Exception e){
            JOptionPane.showMessageDialog(null, "nao conectado!");
       }
        
        System.out.println("Olá, mundo!");
          FrmTelaLogin login = new FrmTelaLogin();
          login.setVisible(true);
    }
}