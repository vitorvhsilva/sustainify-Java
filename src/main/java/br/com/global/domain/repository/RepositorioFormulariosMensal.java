package br.com.global.domain.repository;

import br.com.global.domain.model.FormularioMensal;

import java.util.List;

public interface RepositorioFormulariosMensal {
    Long obterProximoId();
    void persistirFormularioMensal(FormularioMensal formularioMensal);
    void fecharConexao();
    List<FormularioMensal> pegarFormulariosPorMesAnoComunidade(Integer mes, Integer ano);
}
