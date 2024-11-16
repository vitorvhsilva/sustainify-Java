package br.com.global.service;

import br.com.global.domain.model.Comunidade;
import br.com.global.domain.repository.RepositorioComunidades;
import br.com.global.infra.dao.ComunidadeDAO;

public class ComunidadeService{
    private RepositorioComunidades repositorioComunidades;

    public ComunidadeService() {
        this.repositorioComunidades = new ComunidadeDAO();
    }

    public void persistirComunidade(Comunidade comunidade) {
        validarComunidade(comunidade);
        repositorioComunidades.persistirComunidade(comunidade);
        repositorioComunidades.fecharConexao();
    }

    public Long retornarSindicoPorCep(String cep){
        Long idSindico = repositorioComunidades.retornarSindicoPorCep(cep);
        repositorioComunidades.fecharConexao();
        return idSindico;
    }

    public Comunidade retornarComunidadePorIdSindico(Long idSindico) {
        Comunidade comunidade = repositorioComunidades.retornarComunidadePorIdSindico(idSindico);
        repositorioComunidades.fecharConexao();
        return comunidade;
    }

    private void validarComunidade(Comunidade comunidade) {
        if (repositorioComunidades.verificarSeComunidadeExistePorCep(comunidade.getCepComunidade())) throw new RuntimeException("Comunidade já existe!");
    }
}
