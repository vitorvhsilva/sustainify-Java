package br.com.global.infra.dao;

import br.com.global.domain.model.Morador;
import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioSolicitacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoDAO implements RepositorioSolicitacoes {
    private Connection conexao;

    public SolicitacaoDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public void persistirSolicitacao(Solicitacao solicitacao) {
        String sqlInsert = """
                INSERT INTO TB_SOLICITACAO VALUES (?, ?, ?, ?)
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setLong(1, solicitacao.getIdMorador());
            ps.setLong(2, solicitacao.getIdSindico());
            ps.setString(3, solicitacao.getCepSolicitacao());
            ps.setString(4, solicitacao.getNumResidenciaSolicitacao());
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

    @Override
    public List<Solicitacao> pegarSolicitacoesNaComunidadePorCep(String cep) {
        String sqlSelect = "SELECT * FROM TB_SOLICITACAO WHERE cep_solicitacao = ?";
        List<Solicitacao> solicitacoes = new ArrayList<>();
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setString(1, cep);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Solicitacao solicitacao = new Solicitacao();
                solicitacao.setIdMorador(rs.getLong("id_morador"));
                solicitacao.setIdSindico(rs.getLong("id_sindico"));
                solicitacao.setCepSolicitacao(rs.getString("cep_solicitacao"));
                solicitacao.setNumResidenciaSolicitacao(rs.getString("num_residencia_solicitacao"));
                solicitacoes.add(solicitacao);
            }

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return solicitacoes;
    }
}
