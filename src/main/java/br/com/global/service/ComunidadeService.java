package br.com.global.service;

import br.com.global.domain.model.Comunidade;
import br.com.global.domain.repository.RepositorioComunidades;
import br.com.global.infra.dao.ComunidadeDAO;

public class ComunidadeService implements ServicosComunidade{
    private RepositorioComunidades repositorioComunidades;

    public ComunidadeService() {
        this.repositorioComunidades = new ComunidadeDAO();
    }

    public void persistirComunidade(Comunidade comunidade) {
        repositorioComunidades.persistirComunidade(comunidade);
        repositorioComunidades.fecharConexao();
    }

    public Long retornarSindicoPorCep(String cep){
        Long idSindico = repositorioComunidades.retornarSindicoPorCep(cep);
        repositorioComunidades.fecharConexao();
        return idSindico;
    }
}
