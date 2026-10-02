/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jgfca
 */
public class ConnectionFactory {
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/bico", "postgres", "123");
        } catch (SQLException erro){
            throw new RuntimeException (erro);
        }
    }
}
