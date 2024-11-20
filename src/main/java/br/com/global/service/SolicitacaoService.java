package br.com.global.service;

import br.com.global.domain.model.Moradia;
import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioMoradias;
import br.com.global.domain.repository.RepositorioSolicitacoes;
import br.com.global.dto.AtualizarStatusSolicitacaoDTO;
import br.com.global.infra.dao.ConnectionFactory;
import br.com.global.infra.dao.MoradiaDAO;
import br.com.global.infra.dao.SolicitacaoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SolicitacaoService{
    private Connection conexao;
    private RepositorioSolicitacoes repositorioSolicitacoes;
    private RepositorioMoradias repositorioMoradias;

    public SolicitacaoService() {
        this.conexao = new ConnectionFactory().obterConexao();
        this.repositorioSolicitacoes = new SolicitacaoDAO(conexao);
        this.repositorioMoradias = new MoradiaDAO(conexao);
    }

    public void persistirSolicitacao(Solicitacao solicitacao) {
        repositorioSolicitacoes.persistirSolicitacao(solicitacao);
        fecharConexao();
    }

    public List<Solicitacao> pegarSolicitacoesNaComunidadePorCep(String cep) {
        List<Solicitacao> solicitacoes = repositorioSolicitacoes.pegarSolicitacoesNaComunidadePorCep(cep);
        fecharConexao();
        return solicitacoes;
    }

    public void verificarSeSolicitacaoExiste(Solicitacao solicitacao) {
        Solicitacao solicitacaoPega = repositorioSolicitacoes.verificarSeSolicitacaoExiste(solicitacao);
        if (solicitacaoPega.getNumResidenciaSolicitacao() != null) throw new RuntimeException("Solicitação já existe");
    }

    public void deletarSolicitacao(Long idMorador) {
        repositorioSolicitacoes.deletarSolicitacao(idMorador);
        fecharConexao();
    }

    public void atualizarSolicitacao(AtualizarStatusSolicitacaoDTO dto) {
        repositorioSolicitacoes.atualizarSolicitacao(dto);

        Moradia moradia = new Moradia(dto.getIdMorador(), dto.getIdSindico() ,dto.getNumMoradia());

        repositorioMoradias.persistirMoradia(moradia);
        fecharConexao();
    }

    public List<Solicitacao> pegarSolicitacoesNaComunidadePorMorador(Long idMorador) {
        List<Solicitacao> solicitacoes = repositorioSolicitacoes.pegarSolicitacoesNaComunidadePorMorador(idMorador);
        fecharConexao();
        return solicitacoes;
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
