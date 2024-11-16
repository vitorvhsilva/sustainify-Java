package br.com.global.infra.dao;

import br.com.global.domain.model.Comunidade;
import br.com.global.domain.model.Sindico;
import br.com.global.domain.repository.RepositorioComunidades;
import br.com.global.domain.repository.RepositorioSindicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComunidadeDAO implements RepositorioComunidades {
    private Connection conexao;

    public ComunidadeDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId() {
        Long id = null;
        try {
            String sql = "SELECT tb_comunidade_seq.NEXTVAL FROM DUAL";
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

    public void persistirComunidade(Comunidade comunidade) {
        String sqlInsert = """
                INSERT INTO TB_COMUNIDADE VALUES (?, ?, ?, ?)
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setLong(1, comunidade.getIdSindico());
            ps.setString(2, comunidade.getRuaComunidade());
            ps.setString(3, comunidade.getNumComunidade());
            ps.setString(4, comunidade.getCepComunidade());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long retornarSindicoPorCep(String cep) {
        String sqlSelect = "SELECT * FROM TB_COMUNIDADE WHERE cep_comunidade = ?";
        Long id = null;
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setString(1, cep);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getLong("id_sindico");
            }

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException("Comunidade não existe!");
        }
        return id;
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean verificarSeComunidadeExistePorCep(String cep) {
        String sqlSelect = "SELECT * FROM TB_COMUNIDADE WHERE cep_comunidade = ?";
        boolean existe = false;

        try (PreparedStatement statement = conexao.prepareStatement(sqlSelect)) {
            statement.setString(1, cep);
            try (ResultSet rs = statement.executeQuery()) {
                existe = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return existe;
    }

    @Override
    public Comunidade retornarComunidadePorIdSindico(Long idSindico) {
        String sqlSelect = "SELECT * FROM TB_COMUNIDADE WHERE id_sindico = ?";
        Comunidade comunidade = new Comunidade();
        try {
            PreparedStatement ps = conexao.prepareStatement(sqlSelect);
            ps.setLong(1, idSindico);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                comunidade.setIdSindico(rs.getLong("id_sindico"));
                comunidade.setCepComunidade(rs.getString("cep_comunidade"));
                comunidade.setRuaComunidade(rs.getString("rua_comunidade"));
                comunidade.setNumComunidade(rs.getString("num_comunidade"));
            }
            ps.close();
            rs.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return comunidade;
    }

}
