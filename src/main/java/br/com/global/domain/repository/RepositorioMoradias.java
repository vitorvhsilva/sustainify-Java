package br.com.global.domain.repository;

import br.com.global.domain.model.Moradia;
import br.com.global.domain.model.Morador;
import br.com.global.dto.LoginDTO;

public interface RepositorioMoradias {
    Long obterProximoId();
    void persistirMoradia(Moradia moradia);
    Long pegarMoradiaPorMorador(String numResidencia);

    Long pegarSindicoPorMoradia(Long idMoradia);

    Moradia pegarMoradiaPorMoradia(Long idMoradia);
}
