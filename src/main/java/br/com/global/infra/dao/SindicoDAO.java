package br.com.global.infra.dao;

import br.com.global.domain.model.Sindico;
import br.com.global.domain.repository.RepositorioSindicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SindicoDAO implements RepositorioSindicos {
    private Connection conexao;

    public SindicoDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId() {
        Long id = null;
        try {
            String sql = "SELECT tb_sindico_seq.NEXTVAL FROM DUAL";
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

    public void persistirSindico(Sindico sindico, Long idSindico) {
        String sqlInsert = """
                INSERT INTO TB_SINDICO VALUES (?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setLong(1, idSindico);
            ps.setString(2, sindico.getNomeSindico());
            ps.setString(3, sindico.getCpfSindico());
            ps.setString(4, sindico.getEmailSindico());
            ps.setString(5, sindico.getSenhaSindico());
            ps.setString(6, sindico.getTelefoneSindico());
            ps.execute();
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
