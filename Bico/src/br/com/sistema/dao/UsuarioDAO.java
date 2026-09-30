/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConnectionFactory;
import br.com.sistema.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jgfca
 */
public class UsuarioDAO {

    public Usuario login(String nome, String senha) {

        String sql = "SELECT id_usuario, nome, senha FROM usuarios WHERE nome = ? AND senha = ?";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Usuario usuario = new Usuario();
                // Mapeamento corrigido conforme a imagem
                usuario.setId_usuario(result.getInt("id_usuario"));
                usuario.setNome(result.getString("nome"));
                usuario.setSenha(result.getString("senha"));
                return usuario;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao realizar login: " + e.getMessage());
        }
        return null;
    }
}
