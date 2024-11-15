package br.com.global.service;

import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioSolicitacoes;
import br.com.global.infra.dao.SolicitacaoDAO;

import java.util.List;

public class SolicitacaoService implements ServicosSolicitacao{
    private RepositorioSolicitacoes repositorioSolicitacoes;

    public SolicitacaoService() {
        this.repositorioSolicitacoes = new SolicitacaoDAO();
    }

    public void persistirSolicitacao(Solicitacao solicitacao) {
        repositorioSolicitacoes.persistirSolicitacao(solicitacao);
        repositorioSolicitacoes.fecharConexao();
    }

    @Override
    public List<Solicitacao> pegarSolicitacoesNaComunidadePorCep(String cep) {
        List<Solicitacao> solicitacoes = repositorioSolicitacoes.pegarSolicitacoesNaComunidadePorCep(cep);
        repositorioSolicitacoes.fecharConexao();
        return solicitacoes;
    }
}
