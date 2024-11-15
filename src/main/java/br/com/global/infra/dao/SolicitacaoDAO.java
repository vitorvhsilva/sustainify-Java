package br.com.global.infra.dao;

import br.com.global.domain.model.Morador;
import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioSolicitacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
