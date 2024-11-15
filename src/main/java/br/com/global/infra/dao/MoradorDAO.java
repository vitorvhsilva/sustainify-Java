package br.com.global.infra.dao;

import br.com.global.domain.model.Morador;
import br.com.global.domain.model.Sindico;
import br.com.global.domain.repository.RepositorioMoradores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoradorDAO implements RepositorioMoradores {
    private Connection conexao;

    public MoradorDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId() {
        Long id = null;
        try {
            String sql = "SELECT tb_morador_seq.NEXTVAL FROM DUAL";
            PreparedStatement comandoDeGeracao = conexao.prepareStatement(sql);
            ResultSet rs = comandoDeGeracao.executeQuery();
            while(rs.next()) {
                id = rs.getLong(1);
            }
            comandoDeGeracao.close();
            rs.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void persistirMorador(Morador morador, Long idMorador) {
        String sqlInsert = """
                INSERT INTO TB_MORADOR VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setLong(1, idMorador);
            ps.setString(2, morador.getNomeMorador());
            ps.setString(3, morador.getCpfMorador());
            ps.setString(4, morador.getEmailMorador());
            ps.setString(5, morador.getSenhaMorador());
            ps.setString(6, morador.getTelefoneMorador());
            ps.setInt(7, morador.getSolicitacaoAceitaMorador());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizarStatusAtualizacao(Long idMorador) {
        String sqlUpdate = """
                UPDATE TB_MORADOR SET solicitacao_aceita_morador = 1 WHERE id_morador = ?
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlUpdate);
            ps.setLong(1, idMorador);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
