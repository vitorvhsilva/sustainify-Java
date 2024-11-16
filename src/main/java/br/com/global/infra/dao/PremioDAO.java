package br.com.global.infra.dao;

import br.com.global.domain.model.Premio;
import br.com.global.domain.repository.RepositorioPremios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void atualizarPremio(Premio premio) {
        String sqlUpdate = """
                UPDATE TB_PREMIO SET premio = ? WHERE id_sindico = ? AND posicao_premio = ?
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlUpdate);
            ps.setString(1, premio.getPremio());
            ps.setLong(2, premio.getIdSindico());
            ps.setInt(3, premio.getPosicaoPremio());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Premio> pegarPremiosDaComunidade(Long idSindico) {
        String sqlSelect = "SELECT * FROM TB_PREMIO WHERE id_sindico = ?";
        List<Premio> premios = new ArrayList<>();
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idSindico);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Premio premio = new Premio();
                premio.setIdSindico(idSindico);
                premio.setPosicaoPremio(rs.getInt("posicao_premio"));
                premio.setPremio(rs.getString("premio"));
                premios.add(premio);
            }


            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return premios;
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
