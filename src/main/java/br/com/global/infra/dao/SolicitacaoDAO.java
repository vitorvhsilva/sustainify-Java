package br.com.global.infra.dao;

import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioSolicitacoes;
import br.com.global.dto.AtualizarStatusSolicitacaoDTO;

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
                INSERT INTO TB_SOLICITACAO VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setLong(1, solicitacao.getIdMorador());
            ps.setLong(2, solicitacao.getIdSindico());
            ps.setString(3, solicitacao.getNomeMorador());
            ps.setString(4, solicitacao.getCpfMorador());
            ps.setString(5, solicitacao.getCepSolicitacao());
            ps.setString(6, solicitacao.getNumResidenciaSolicitacao());
            ps.setInt(7, solicitacao.getSolicitacaoAceita());
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
                solicitacao.setNomeMorador(rs.getString("nome_morador"));
                solicitacao.setCpfMorador(rs.getString("cpf_morador"));
                solicitacao.setCepSolicitacao(rs.getString("cep_solicitacao"));
                solicitacao.setNumResidenciaSolicitacao(rs.getString("num_residencia_solicitacao"));
                solicitacao.setSolicitacaoAceita(rs.getInt("solicitacao_aceita"));
                solicitacoes.add(solicitacao);
            }

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return solicitacoes;
    }

    @Override
    public Solicitacao verificarSeSolicitacaoExiste(Solicitacao solicitacao) {
        String sqlSelect = "SELECT * FROM TB_SOLICITACAO WHERE num_residencia_solicitacao = ?";
        Solicitacao solicitacaoPega = new Solicitacao();
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setString(1, solicitacao.getNumResidenciaSolicitacao());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                solicitacao.setIdMorador(rs.getLong("id_morador"));
                solicitacao.setIdSindico(rs.getLong("id_sindico"));
                solicitacao.setNomeMorador(rs.getString("nome_morador"));
                solicitacao.setCpfMorador(rs.getString("cpf_morador"));
                solicitacao.setCepSolicitacao(rs.getString("cep_solicitacao"));
                solicitacao.setNumResidenciaSolicitacao(rs.getString("num_residencia_solicitacao"));
                solicitacao.setSolicitacaoAceita(rs.getInt("solicitacao_aceita"));
            }

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return solicitacaoPega;
    }


    @Override
    public void atualizarSolicitacao(AtualizarStatusSolicitacaoDTO dto) {
        String sqlUpdate = """
                UPDATE TB_SOLICITACAO SET solicitacao_aceita = 1 WHERE num_residencia_solicitacao = ?
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlUpdate);
            ps.setString(1, dto.getNumResidenciaSolicitacao());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Solicitacao> pegarSolicitacoesNaComunidadePorMorador(Long idMorador) {
        String sqlSelect = "SELECT * FROM TB_SOLICITACAO WHERE id_morador = ?";
        List<Solicitacao> solicitacoes = new ArrayList<>();
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idMorador);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Solicitacao solicitacao = new Solicitacao();
                solicitacao.setIdMorador(rs.getLong("id_morador"));
                solicitacao.setIdSindico(rs.getLong("id_sindico"));
                solicitacao.setNomeMorador(rs.getString("nome_morador"));
                solicitacao.setCpfMorador(rs.getString("cpf_morador"));
                solicitacao.setCepSolicitacao(rs.getString("cep_solicitacao"));
                solicitacao.setNumResidenciaSolicitacao(rs.getString("num_residencia_solicitacao"));
                solicitacao.setSolicitacaoAceita(rs.getInt("solicitacao_aceita"));
                solicitacoes.add(solicitacao);
            }

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return solicitacoes;
    }

    @Override
    public void deletarSolicitacao(Long idMorador) {
        String sqlDelete = """
                DELETE FROM TB_SOLICITACAO WHERE id_morador = ?
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlDelete);
            ps.setLong(1, idMorador);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
