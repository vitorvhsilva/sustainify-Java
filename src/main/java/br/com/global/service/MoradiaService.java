package br.com.global.service;

import br.com.global.domain.model.Moradia;
import br.com.global.domain.model.Premio;
import br.com.global.domain.repository.RepositorioMoradias;
import br.com.global.domain.repository.RepositorioPremios;
import br.com.global.infra.dao.MoradiaDAO;
import br.com.global.infra.dao.PremioDAO;

import java.util.List;

public class MoradiaService {
    private RepositorioMoradias repositorioMoradias;
    private RepositorioPremios repositorioPremios;

    public MoradiaService() {
        this.repositorioMoradias = new MoradiaDAO();
        this.repositorioPremios = new PremioDAO();
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

    public List<Premio> pegarPremiosPorMoradia(Long idMoradia) {
        Long idSindico = repositorioMoradias.pegarSindicoPorMoradia(idMoradia);
        List<Premio> premios = repositorioPremios.pegarPremiosDaComunidade(idSindico);
        repositorioMoradias.fecharConexao();
        return premios;
    }

    public Long pegarSindicoPorMoradia(Long idMoradia) {
        return repositorioMoradias.pegarSindicoPorMoradia(idMoradia);
    }

    public Moradia pegarMoradiaPorMoradia(Long idMoradia){
        return repositorioMoradias.pegarMoradiaPorMoradia(idMoradia);
    }

    public void fecharConexao() {
        repositorioMoradias.fecharConexao();
    }
}
