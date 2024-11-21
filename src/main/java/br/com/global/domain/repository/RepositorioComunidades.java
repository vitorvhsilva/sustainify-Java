package br.com.global.domain.repository;

import br.com.global.domain.model.Comunidade;

public interface RepositorioComunidades {
    Long obterProximoId();
    void persistirComunidade(Comunidade comunidade);
    Long retornarSindicoPorCep(String cep);
    boolean verificarSeComunidadeExistePorCep(String cep);
    Comunidade retornarComunidadePorIdSindico(Long idSindico);
}
