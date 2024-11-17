package br.com.global.domain.repository;

import br.com.global.domain.model.FormularioMensal;
import br.com.global.dto.EmissaoOutputDTO;
import br.com.global.dto.EmissaoOutputSQL;

import java.util.List;

public interface RepositorioFormulariosMensal {
    Long obterProximoId();
    void persistirFormularioMensal(FormularioMensal formularioMensal);
    void fecharConexao();
    List<FormularioMensal> pegarFormulariosPorMesAnoComunidade(Long idSindico, Integer mes, Integer ano);
    List<FormularioMensal> pegarFormulariosPorAnoMoradia(Long idMoradia, Integer ano);
    FormularioMensal verificarSeFormularioExiste(FormularioMensal formularioMensal);
    List<EmissaoOutputSQL> pegarEmissoesPorAnoComunidade(Long idSindico, Integer ano);
}
