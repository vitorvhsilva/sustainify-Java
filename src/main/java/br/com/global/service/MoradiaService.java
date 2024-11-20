package br.com.global.service;

import br.com.global.domain.model.Moradia;
import br.com.global.domain.model.Premio;
import br.com.global.domain.repository.RepositorioMoradias;
import br.com.global.domain.repository.RepositorioPremios;
import br.com.global.infra.dao.ConnectionFactory;
import br.com.global.infra.dao.MoradiaDAO;
import br.com.global.infra.dao.PremioDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MoradiaService {
    private Connection conexao;
    private RepositorioMoradias repositorioMoradias;
    private RepositorioPremios repositorioPremios;

    public MoradiaService() {
        this.conexao = new ConnectionFactory().obterConexao();
        this.repositorioMoradias = new MoradiaDAO(conexao);
        this.repositorioPremios = new PremioDAO(conexao);
    }

    public void persistirMoradia(Moradia moradia) {
        repositorioMoradias.persistirMoradia(moradia);
        fecharConexao();
    }

    public Long pegarMoradiaPorMorador(String numResidencia) {
        Long idMoradia = repositorioMoradias.pegarMoradiaPorMorador(numResidencia);
        fecharConexao();
        return idMoradia;
    }

    public List<Premio> pegarPremiosPorMoradia(Long idMoradia) {
        Long idSindico = repositorioMoradias.pegarSindicoPorMoradia(idMoradia);
        List<Premio> premios = repositorioPremios.pegarPremiosDaComunidade(idSindico);
        fecharConexao();
        return premios;
    }

    public Long pegarSindicoPorMoradia(Long idMoradia) {
        return repositorioMoradias.pegarSindicoPorMoradia(idMoradia);
    }

    public Moradia pegarMoradiaPorMoradia(Long idMoradia){
        return repositorioMoradias.pegarMoradiaPorMoradia(idMoradia);
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
