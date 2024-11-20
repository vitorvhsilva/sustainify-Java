package br.com.global.service;

import br.com.global.domain.model.Moradia;
import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioMoradias;
import br.com.global.domain.repository.RepositorioSolicitacoes;
import br.com.global.dto.AtualizarStatusSolicitacaoDTO;
import br.com.global.infra.dao.MoradiaDAO;
import br.com.global.infra.dao.SolicitacaoDAO;

import java.util.List;

public class SolicitacaoService{
    private RepositorioSolicitacoes repositorioSolicitacoes;
    private RepositorioMoradias repositorioMoradias;

    public SolicitacaoService() {
        this.repositorioSolicitacoes = new SolicitacaoDAO();
        this.repositorioMoradias = new MoradiaDAO();
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

    public void atualizarSolicitacao(AtualizarStatusSolicitacaoDTO dto) {
        repositorioSolicitacoes.atualizarSolicitacao(dto);

        Moradia moradia = new Moradia(dto.getIdMorador(), dto.getIdSindico() ,dto.getNumMoradia());

        repositorioMoradias.persistirMoradia(moradia);
        repositorioSolicitacoes.fecharConexao();
    }

    public List<Solicitacao> pegarSolicitacoesNaComunidadePorMorador(Long idMorador) {
        List<Solicitacao> solicitacoes = repositorioSolicitacoes.pegarSolicitacoesNaComunidadePorMorador(idMorador);
        repositorioSolicitacoes.fecharConexao();
        return solicitacoes;
    }
}
