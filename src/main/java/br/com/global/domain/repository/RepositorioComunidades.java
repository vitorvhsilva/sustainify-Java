package br.com.global.domain.repository;

import br.com.global.domain.model.Comunidade;
import br.com.global.domain.model.Sindico;

public interface RepositorioComunidades {
    Long obterProximoId();
    void persistirComunidade(Comunidade comunidade);
    Long retornarSindicoPorCep(String cep);
    void fecharConexao();
}
