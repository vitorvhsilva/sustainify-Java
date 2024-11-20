package br.com.global.service;

import br.com.global.domain.model.Comunidade;
import br.com.global.domain.model.Premio;
import br.com.global.domain.repository.RepositorioPremios;
import br.com.global.infra.dao.ConnectionFactory;
import br.com.global.infra.dao.PremioDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PremioService{
    private Connection conexao;
    private RepositorioPremios repositorioPremios;

    public PremioService() {
        this.conexao = new ConnectionFactory().obterConexao();
        this.repositorioPremios = new PremioDAO(conexao);
    }

    public void criarPremiosNaComunidade(Comunidade comunidade) {
        Premio premio1 = new Premio(comunidade.getIdSindico(), 1, "");
        Premio premio2 = new Premio(comunidade.getIdSindico(), 2, "");
        Premio premio3 = new Premio(comunidade.getIdSindico(), 3, "");

        repositorioPremios.persistirPremio(premio1);
        repositorioPremios.persistirPremio(premio2);
        repositorioPremios.persistirPremio(premio3);

        fecharConexao();
    }

    public void atualizarPremio(Premio premio) {
        repositorioPremios.atualizarPremio(premio);
        fecharConexao();
    }

    public List<Premio> pegarPremiosDaComunidade(Long idSindico) {
        List<Premio> premios = repositorioPremios.pegarPremiosDaComunidade(idSindico);
        fecharConexao();
        return premios;
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
