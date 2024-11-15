package br.com.global.service;

import br.com.global.domain.model.Comunidade;

public interface ServicosComunidade {
    void persistirComunidade(Comunidade comunidade);
    Long retornarSindicoPorCep(String cep);
}
