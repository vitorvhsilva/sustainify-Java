package br.com.global.infra.dao;

import br.com.global.domain.model.Premio;
import br.com.global.domain.model.Sindico;
import br.com.global.domain.repository.RepositorioPremios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PremioDAO implements RepositorioPremios {
    private Connection conexao;

    public PremioDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    @Override
    public void persistirPremio(Premio premio) {
        String sqlInsert = """
                INSERT INTO TB_PREMIO VALUES (?, ?, ?)
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setLong(1, premio.getIdSindico());
            ps.setInt(2, premio.getPosicaoPremio());
            ps.setString(3, premio.getPremio());
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
