package br.com.global.domain.repository;

import br.com.global.domain.model.FormularioMensal;

public interface RepositorioFormulariosMensal {
    Long obterProximoId();
    void persistirFormularioMensal(FormularioMensal formularioMensal);
    void fecharConexao();
}
