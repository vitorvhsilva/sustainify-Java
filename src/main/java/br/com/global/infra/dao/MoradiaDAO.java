package br.com.global.infra.dao;

import br.com.global.domain.model.Moradia;
import br.com.global.domain.model.Morador;
import br.com.global.domain.repository.RepositorioMoradias;
import br.com.global.domain.repository.RepositorioMoradores;
import br.com.global.dto.LoginDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoradiaDAO implements RepositorioMoradias {
    private Connection conexao;

    public MoradiaDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId() {
        Long id = null;
        try {
            String sql = "SELECT tb_moradia_seq.NEXTVAL FROM DUAL";
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

    @Override
    public void persistirMoradia(Moradia moradia) {
        String sqlInsert = """
                INSERT INTO TB_MORADIA VALUES (?, ?, ?, ?)
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setLong(1, obterProximoId());
            ps.setLong(2, moradia.getIdMorador());
            ps.setLong(3, moradia.getIdSindico());
            ps.setString(4, moradia.getNumMoradia());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long pegarMoradiaPorMorador(String numResidencia) {
        String sqlSelect = "SELECT * FROM TB_MORADIA WHERE num_moradia = ?";
        Long idMoradia = null;
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setString(1, numResidencia);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("Moradia não encontrada!");
            }

            idMoradia = rs.getLong("id_moradia");

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return idMoradia;
    }

    @Override
    public Long pegarSindicoPorMoradia(Long idMoradia) {
        String sqlSelect = "SELECT * FROM TB_MORADIA WHERE id_moradia = ?";
        Long idSindico = null;
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idMoradia);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("Moradia não encontrada!");
            }

            idSindico = rs.getLong("id_sindico");

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return idSindico;
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
