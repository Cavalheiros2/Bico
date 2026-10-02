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
    
    public List<Solicitacao> listarSolicitacoes(int idUsuarioLogado){
        try {
            List<Solicitacao> lista = new ArrayList<>();
            
            String sql = "Select id_solicitacao, categoria, descricao, data_solicitacao, hora_solicitacao, local_solicitacao, regiao, proposta_valor" +
                    "from solicitacoes where id_usuario = ?";
            
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
            return lista ;            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar solicitações: " + erro);
            return null;
        }
    }
    
    public List<Solicitacao> pesquisarSolicitacoes(int idUsuarioLogado, String busca) {
        try {
            List<Solicitacao> lista = new ArrayList<>();
            String sql = "SELECT * FROM solicitacoes WHERE id_usuario = ? AND (categoria ILIKE ? OR local_solicitacao ILIKE ?)";
            
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
}
