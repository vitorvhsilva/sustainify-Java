package br.com.global.service;

import br.com.global.domain.model.*;
import br.com.global.domain.repository.*;
import br.com.global.dto.EmissaoOutputDTO;
import br.com.global.dto.EmissaoOutputSQL;
import br.com.global.dto.PaginaInicialMoradorDTO;
import br.com.global.dto.PaginaInicialSindicoDTO;
import br.com.global.infra.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaginaInicialService {
    private Connection conexao;
    private RepositorioMoradores repositorioMoradores;
    private RepositorioSindicos repositorioSindicos;
    private RepositorioFormulariosMensal repositorioFormulariosMensal;
    private RepositorioMoradias repositorioMoradias;
    private RepositorioPremios repositorioPremios;
    private RepositorioComunidades repositorioComunidades;
    private RepositorioSolicitacoes repositorioSolicitacoes;

    public PaginaInicialService() {
        this.conexao = new ConnectionFactory().obterConexao();
        this.repositorioMoradores = new MoradorDAO(conexao);
        this.repositorioSindicos = new SindicoDAO(conexao);
        this.repositorioFormulariosMensal = new FormularioMensalDAO(conexao);
        this.repositorioMoradias = new MoradiaDAO(conexao);
        this.repositorioComunidades = new ComunidadeDAO(conexao);
        this.repositorioSolicitacoes = new SolicitacaoDAO(conexao);
        this.repositorioPremios = new PremioDAO(conexao);
    }

    public PaginaInicialMoradorDTO trazerDadosParaPaginaInicialMorador(Long idMorador, Long idMoradia, Integer mes, Integer ano) {
        Morador morador = repositorioMoradores.retornarMoradorPorIdMorador(idMorador);

        List<FormularioMensal> formulariosMensal = repositorioFormulariosMensal.pegarFormulariosPorAnoMoradia(idMoradia, ano);
        List<EmissaoOutputDTO> emissoesMensal = formulariosMensal.stream()
                .map(f -> new EmissaoOutputDTO(converterMes(f.getMesEmitido()), f.getEmissaoCarbonoMensal()))
                .toList();

        Long idSindico = repositorioMoradias.pegarSindicoPorMoradia(idMoradia);
        List<EmissaoOutputSQL> emissoesSQL = repositorioFormulariosMensal.pegarEmissoesPorAnoComunidade(idSindico, ano);
        List<EmissaoOutputDTO> emissoesAnual = emissoesSQL.stream()
                .map(e -> new EmissaoOutputDTO(converterMes(e.getMes()), e.getEmissao()))
                .toList();

        List<FormularioMensal> formulariosRanking = repositorioFormulariosMensal.pegarFormulariosPorMesAnoSindicoComunidade(idSindico, mes, ano);

        List<Premio> premios = repositorioPremios.pegarPremiosDaComunidade(idSindico);
        fecharConexao();

        return new PaginaInicialMoradorDTO(morador, emissoesMensal, emissoesAnual, formulariosRanking, premios);

    }

    public PaginaInicialSindicoDTO trazerDadosParaPaginaInicialSindico(Long idSindico, Integer mes, Integer ano) {
        Sindico sindico = repositorioSindicos.retornarSindicoPorIdSindico(idSindico);
        Comunidade comunidade = repositorioComunidades.retornarComunidadePorIdSindico(idSindico);

        List<Solicitacao> solicitacoes = repositorioSolicitacoes.pegarSolicitacoesNaComunidadePorCep(comunidade.getCepComunidade());
        List<FormularioMensal> formulariosMensal = repositorioFormulariosMensal.pegarFormulariosPorMesAnoSindicoComunidade(idSindico, mes, ano);

        List<EmissaoOutputSQL> emissoesSQL = repositorioFormulariosMensal.pegarEmissoesPorAnoComunidade(idSindico, ano);

        List<EmissaoOutputDTO> emissoes = emissoesSQL.stream()
                .map(e -> new EmissaoOutputDTO(converterMes(e.getMes()), e.getEmissao()))
                .toList();

        List<Premio> premios = repositorioPremios.pegarPremiosDaComunidade(idSindico);
        fecharConexao();

        return new PaginaInicialSindicoDTO(sindico, comunidade, solicitacoes, formulariosMensal, emissoes, premios);

    }

    private String converterMes(Integer mesNumero) {
        return switch (mesNumero) {
            case 1 -> "Janeiro";
            case 2 -> "Fevereiro";
            case 3 -> "Março";
            case 4 -> "Abril";
            case 5 -> "Maio";
            case 6 -> "Junho";
            case 7 -> "Julho";
            case 8 -> "Agosto";
            case 9 -> "Setembro";
            case 10 -> "Outubro";
            case 11 -> "Novembro";
            case 12 -> "Dezembro";
            default -> "Mês Desconhecido";
        };
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
