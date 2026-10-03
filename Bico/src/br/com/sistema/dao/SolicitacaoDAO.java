/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.model.Solicitacao;
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
public class SolicitacaoDAO {

    private Connection con;

    public SolicitacaoDAO() {
        try {
            this.con = new br.com.sistema.jdbc.ConnectionFactory().getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no SolicitacaoDAO: " + e);
        }
    }

    public void salvarSolicitacao(Solicitacao obj) {
        try {
            String sql = "INSERT INTO solicitacoes (id_categoria, descricao, data_solicitacao, hora_solicitacao, local_solicitacao, proposta_valor, id_usuario) "
                    + "VALUES ((SELECT id_categoria FROM categorias WHERE nome_categoria = ?), ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getCategoria());
            stmt.setString(2, obj.getDescricao());
            stmt.setString(3, obj.getData_solicitacao());
            stmt.setString(4, obj.getHora_solicitacao());
            stmt.setString(5, obj.getLocal_solicitacao());
            stmt.setFloat(6, obj.getProposta_valor());
            stmt.setInt(7, obj.getId_usuario());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Solicitação salva com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar solicitação no banco: " + erro);
        }
    }

    public List<Solicitacao> listarSolicitacoes(int idUsuarioLogado) {
        try {
            List<Solicitacao> lista = new ArrayList<>();

            String sql = "SELECT s.id_solicitacao, c.nome_categoria AS categoria, s.descricao, "
                    + "s.data_solicitacao, s.hora_solicitacao, s.local_solicitacao, s.proposta_valor, s.id_usuario "
                    + "FROM solicitacoes s "
                    + "JOIN categorias c ON s.id_categoria = c.id_categoria "
                    + "WHERE s.id_usuario = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuarioLogado);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Solicitacao s = new Solicitacao(
                        rs.getInt("id_solicitacao"),
                        rs.getString("categoria"),
                        rs.getString("descricao"),
                        rs.getString("data_solicitacao"),
                        rs.getString("hora_solicitacao"),
                        rs.getString("local_solicitacao"),
                        rs.getFloat("proposta_valor"),
                        rs.getInt("id_usuario")
                );
                lista.add(s);
            }
            stmt.close();
            return lista;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar solicitações: " + erro);
            return null;
        }
    }

    public List<Solicitacao> pesquisarSolicitacoes(int idUsuarioLogado, String busca) {
        try {
            List<Solicitacao> lista = new ArrayList<>();
            String sql = "SELECT s.*, c.nome_categoria AS categoria FROM solicitacoes s "
                    + "JOIN categorias c ON s.id_categoria = c.id_categoria "
                    + "WHERE s.id_usuario = ? AND (c.nome_categoria ILIKE ? OR s.local_solicitacao ILIKE ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuarioLogado);
            stmt.setString(2, busca + "%");
            stmt.setString(3, busca + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Solicitacao s = new Solicitacao(
                        rs.getInt("id_solicitacao"),
                        rs.getString("categoria"),
                        rs.getString("descricao"),
                        rs.getString("data_solicitacao"),
                        rs.getString("hora_solicitacao"),
                        rs.getString("local_solicitacao"),
                        rs.getFloat("proposta_valor"),
                        rs.getInt("id_usuario")
                );
                lista.add(s);
            }
            return lista;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar: " + erro);
            return null;
        }
    }

    public void editarSolicitacao(int idSolicitacao, String novaData, String novaHora, String novoLocal) {
        try {
            String sql = "UPDATE solicitacoes SET data_solicitacao = ?, hora_solicitacao = ?, local_solicitacao = ? WHERE id_solicitacao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, novaData);
            stmt.setString(2, novaHora);
            stmt.setString(3, novoLocal);
            stmt.setInt(4, idSolicitacao);

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Solicitação atualizada com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar solicitação: " + erro);
        }
    }

    public void excluirSolicitacao(int idSolicitacao) {
        try {
            String sql = "DELETE FROM solicitacoes WHERE id_solicitacao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idSolicitacao);

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Solicitação excluída com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir solicitação: " + erro);
        }
    }

    public java.util.List<br.com.sistema.model.Categoria> listarCategorias() {
        try {
            java.util.List<br.com.sistema.model.Categoria> lista = new java.util.ArrayList<>();
            String sql = "SELECT id_categoria, nome_categoria FROM categorias ORDER BY nome_categoria";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                br.com.sistema.model.Categoria cat = new br.com.sistema.model.Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nome_categoria")
                );
                lista.add(cat);
            }
            stmt.close();
            return lista;
        } catch (Exception erro) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao listar categorias: " + erro);
            return null;
        }
    }
}
