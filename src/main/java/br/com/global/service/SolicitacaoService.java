package br.com.global.service;

import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioSolicitacoes;
import br.com.global.infra.dao.SolicitacaoDAO;

public class SolicitacaoService implements ServicosSolicitacao{
    private RepositorioSolicitacoes repositorioSolicitacoes;

    public SolicitacaoService() {
        this.repositorioSolicitacoes = new SolicitacaoDAO();
    }

    public void persistirSolicitacao(Solicitacao solicitacao) {
        repositorioSolicitacoes.persistirSolicitacao(solicitacao);
        repositorioSolicitacoes.fecharConexao();
    }
}
