package br.com.global.service;

import br.com.global.domain.model.Comunidade;
import br.com.global.domain.repository.RepositorioComunidades;
import br.com.global.infra.dao.ComunidadeDAO;
import br.com.global.infra.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ComunidadeService{
    private Connection conexao;
    private RepositorioComunidades repositorioComunidades;

    public ComunidadeService() {
        this.conexao = new ConnectionFactory().obterConexao();
        this.repositorioComunidades = new ComunidadeDAO(conexao);
    }

    public void persistirComunidade(Comunidade comunidade) {
        validarComunidade(comunidade);
        repositorioComunidades.persistirComunidade(comunidade);
        fecharConexao();
    }

    public Long retornarSindicoPorCep(String cep){
        Long idSindico = repositorioComunidades.retornarSindicoPorCep(cep);
        fecharConexao();
        return idSindico;
    }

    public Comunidade retornarComunidadePorIdSindico(Long idSindico) {
        Comunidade comunidade = repositorioComunidades.retornarComunidadePorIdSindico(idSindico);
        fecharConexao();
        return comunidade;
    }

    private void validarComunidade(Comunidade comunidade) {
        if (repositorioComunidades.verificarSeComunidadeExistePorCep(comunidade.getCepComunidade())) throw new RuntimeException("Comunidade já existe!");
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
