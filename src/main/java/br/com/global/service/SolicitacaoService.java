package br.com.global.service;

import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioSolicitacoes;
import br.com.global.infra.dao.SolicitacaoDAO;

import java.util.List;

public class SolicitacaoService{
    private RepositorioSolicitacoes repositorioSolicitacoes;
    private MoradorService moradorService;

    public SolicitacaoService() {
        this.repositorioSolicitacoes = new SolicitacaoDAO();
    }

    public void persistirSolicitacao(Solicitacao solicitacao) {
        repositorioSolicitacoes.persistirSolicitacao(solicitacao);
        repositorioSolicitacoes.fecharConexao();
    }

    public List<Solicitacao> pegarSolicitacoesNaComunidadePorCep(String cep) {
        List<Solicitacao> solicitacoes = repositorioSolicitacoes.pegarSolicitacoesNaComunidadePorCep(cep);
        repositorioSolicitacoes.fecharConexao();
        return solicitacoes;
    }

    public void verificarSeSolicitacaoExiste(Solicitacao solicitacao) {
        Solicitacao solicitacaoPega = repositorioSolicitacoes.verificarSeSolicitacaoExiste(solicitacao);
        if (solicitacaoPega.getNumResidenciaSolicitacao() != null) throw new RuntimeException("Solicitação já existe");
    }

    public void deletarSolicitacao(Long idMorador) {
        repositorioSolicitacoes.deletarSolicitacao(idMorador);
        repositorioSolicitacoes.fecharConexao();
    }
}
