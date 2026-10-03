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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jgfca
 */
public class UsuarioDAO {

    private Connection con;

    public UsuarioDAO() {
        try {
            this.con = ConnectionFactory.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no UsuarioDAO: " + e.getMessage());
        }
    }

    public Usuario login(String nome, String senha) {
        String sql = "SELECT id_usuario, nome, senha FROM usuarios WHERE nome = ? AND senha = ?";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Usuario usuario = new Usuario();
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

    public List<Usuario> listarClientes() {
        try {
            List<Usuario> lista = new ArrayList<>();
            String sql = "SELECT id_usuario, nome, apelido, cpf, email, telefone, senha FROM usuarios ORDER BY nome";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("apelido"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    rs.getString("senha")
                );
                lista.add(u);
            }
            stmt.close();
            return lista;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + erro);
            return null;
        }
    }

    public List<Usuario> pesquisarClientes(String busca) {
        try {
            List<Usuario> lista = new ArrayList<>();
            String sql = "SELECT * FROM usuarios WHERE nome ILIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, busca + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("apelido"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    rs.getString("senha")
                );
                lista.add(u);
            }
            stmt.close();
            return lista;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar clientes: " + erro);
            return null;
        }
    }

    public void editarCliente(int idUsuario, String apelido, String email, String telefone) {
        try {
            String sql = "UPDATE usuarios SET apelido = ?, email = ?, telefone = ? WHERE id_usuario = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, apelido);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setInt(4, idUsuario);
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Dados do cliente atualizados com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar cliente: " + erro);
        }
    }

    public void excluirCliente(int idUsuario) {
        try {
            String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + erro);
        }
    }
}
