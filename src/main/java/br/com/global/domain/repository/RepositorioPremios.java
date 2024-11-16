package br.com.global.domain.repository;

import br.com.global.domain.model.Premio;
import br.com.global.domain.model.Sindico;

import java.util.List;

public interface RepositorioPremios {
    void persistirPremio(Premio premio);
    void fecharConexao();
    void atualizarPremio(Premio premio);
    List<Premio> pegarPremiosDaComunidade(Long idSindico);
}
