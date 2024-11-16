package br.com.global.domain.repository;

import br.com.global.domain.model.FormularioMensal;

import java.util.List;

public interface RepositorioFormulariosMensal {
    Long obterProximoId();
    void persistirFormularioMensal(FormularioMensal formularioMensal);
    void fecharConexao();
    List<FormularioMensal> pegarFormulariosPorMesAnoComunidade(Long idSindico, Integer mes, Integer ano);
    List<FormularioMensal> pegarFormulariosPorAnoMoradia(Long idMoradia, Integer ano);
    List<FormularioMensal> pegarFormulariosPorAnoComunidade(Long idSindico, Integer ano);
    FormularioMensal verificarSeFormularioExiste(FormularioMensal formularioMensal);
}
