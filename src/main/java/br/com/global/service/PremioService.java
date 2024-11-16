package br.com.global.service;

import br.com.global.domain.model.Comunidade;
import br.com.global.domain.model.Premio;
import br.com.global.domain.repository.RepositorioPremios;
import br.com.global.infra.dao.PremioDAO;

public class PremioService implements ServicosPremio{
    private RepositorioPremios repositorioPremios;

    public PremioService() {
        this.repositorioPremios = new PremioDAO();
    }

    @Override
    public void criarPremiosNaComunidade(Comunidade comunidade) {
        Premio premio1 = new Premio(comunidade.getIdSindico(), 1, "");
        Premio premio2 = new Premio(comunidade.getIdSindico(), 2, "");
        Premio premio3 = new Premio(comunidade.getIdSindico(), 3, "");

        repositorioPremios.persistirPremio(premio1);
        repositorioPremios.persistirPremio(premio2);
        repositorioPremios.persistirPremio(premio3);

        repositorioPremios.fecharConexao();
    }
}
