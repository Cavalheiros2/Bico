/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.model.Contato;
import br.com.sistema.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jgfca
 */
public class ContatoDAO {

    private Connection con;

    public ContatoDAO() {
        try {
            this.con = new br.com.sistema.jdbc.ConnectionFactory().getConnection();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao conectar no ContatoDAO: " + e);
        }
    }

    public void salvarContato(Contato obj) {
        try {
            String sql = "INSERT INTO contatos (id_usuario_principal, id_usuario_contato, apelido_contato) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId_usuario_principal());
            stmt.setInt(2, obj.getId_usuario_contato());
            stmt.setString(3, obj.getApelido_contato());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Contato salvo com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar contato: " + erro);
        }
    }

    public List<Usuario> listarSeusContatos(int idUsuarioLogado) {
        try {
            List<Usuario> lista = new ArrayList<>();

            String sql = "SELECT u.id_usuario, u.nome, c.apelido_contato, u.telefone "
                    + "FROM contatos c "
                    + "JOIN usuarios u ON c.id_usuario_contato = u.id_usuario "
                    + "WHERE c.id_usuario_principal = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuarioLogado);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setApelido(rs.getString("apelido_contato"));
                u.setTelefone(rs.getString("telefone"));
                lista.add(u);
            }
            return lista;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar contatos: " + erro);
            return null;
        }
    }

    public List<Usuario> listarOutrosUsuarios(int idUsuarioLogado) {
        try {
            List<Usuario> lista = new ArrayList<>();
            String sql = "SELECT id_usuario, nome FROM usuarios WHERE id_usuario <> ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuarioLogado);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                lista.add(u);
            }
            return lista;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar usuários: " + erro);
            return null;
        }
    }
    // Atualize este método dentro do ContatoDAO.java

    public void excluirContato(int idUsuarioPrincipal, int idUsuarioContato) {
        try {
            String sql = "DELETE FROM contatos WHERE id_usuario_principal = ? AND id_usuario_contato = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idUsuarioPrincipal); // Substitui o 1º ponto de interrogação
            stmt.setInt(2, idUsuarioContato);   // Substitui o 2º ponto de interrogação

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Contato removido com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir contato: " + erro);
        }
    }

    public List<Usuario> pesquisarContatos(int idUsuarioLogado, String busca) {
        try {
            List<Usuario> lista = new ArrayList<>();
            // O SQL busca registros que batam com o texto digitado (Nome ou Apelido)
            String sql = "SELECT u.id_usuario, u.nome, c.apelido_contato, u.telefone "
                    + "FROM contatos c "
                    + "JOIN usuarios u ON c.id_usuario_contato = u.id_usuario "
                    + "WHERE c.id_usuario_principal = ? AND (u.nome ILIKE ? OR c.apelido_contato ILIKE ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuarioLogado);
            stmt.setString(2, busca + "%"); // Começa com o texto digitado
            stmt.setString(3, busca + "%"); // Começa com o texto digitado

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setApelido(rs.getString("apelido_contato"));
                u.setTelefone(rs.getString("telefone"));
                lista.add(u);
            }
            return lista;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar contatos: " + erro);
            return null;
        }
    }

    public void editarApelidoContato(int idUsuarioPrincipal, int idUsuarioContato, String novoApelido) {
        try {
            String sql = "UPDATE contatos SET apelido_contato = ? WHERE id_usuario_principal = ? AND id_usuario_contato = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, novoApelido);
            stmt.setInt(2, idUsuarioPrincipal);
            stmt.setInt(3, idUsuarioContato);

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Apelido do contato atualizado com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar contato no DAO: " + erro);
        }
    }
}
