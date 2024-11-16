package br.com.global.service;

import br.com.global.domain.model.Comunidade;
import br.com.global.domain.model.Premio;

import java.util.List;

public interface ServicosPremio {
    void criarPremiosNaComunidade(Comunidade comunidade);
    void atualizarPremio(Premio premio);
    List<Premio> pegarPremiosDaComunidade(Long idSindico);
}
