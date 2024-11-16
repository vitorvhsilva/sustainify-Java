package br.com.global.service;

import br.com.global.domain.model.Moradia;
import br.com.global.domain.repository.RepositorioMoradias;
import br.com.global.infra.dao.MoradiaDAO;

public class MoradiaService {
    private RepositorioMoradias repositorioMoradias;

    public MoradiaService() {
        this.repositorioMoradias = new MoradiaDAO();
    }

    public void persistirMoradia(Moradia moradia) {
        repositorioMoradias.persistirMoradia(moradia);
        repositorioMoradias.fecharConexao();
    }

    public Long pegarMoradiaPorMorador(String numResidencia) {
        Long idMoradia = repositorioMoradias.pegarMoradiaPorMorador(numResidencia);
        repositorioMoradias.fecharConexao();
        return idMoradia;
    }
}
