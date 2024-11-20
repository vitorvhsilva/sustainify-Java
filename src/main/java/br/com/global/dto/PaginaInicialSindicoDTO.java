package br.com.global.dto;

import br.com.global.domain.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaginaInicialSindicoDTO {
    @JsonProperty
    private Sindico sindico;
    @JsonProperty
    private Comunidade comunidade;
    @JsonProperty
    private List<Solicitacao> solicitacoes;
    @JsonProperty
    private List<FormularioMensal> formulariosMensal;
    @JsonProperty
    private List<EmissaoOutputDTO> emissoes;
    @JsonProperty
    private List<Premio> premios;

    public PaginaInicialSindicoDTO() {
    }

    public PaginaInicialSindicoDTO(Sindico sindico, Comunidade comunidade, List<Solicitacao> solicitacoes, List<FormularioMensal> formulariosMensal, List<EmissaoOutputDTO> emissoes, List<Premio> premios) {
        this.sindico = sindico;
        this.comunidade = comunidade;
        this.solicitacoes = solicitacoes;
        this.formulariosMensal = formulariosMensal;
        this.emissoes = emissoes;
        this.premios = premios;
    }

    public Sindico getSindico() {
        return sindico;
    }

    public void setSindico(Sindico sindico) {
        this.sindico = sindico;
    }

    public Comunidade getComunidade() {
        return comunidade;
    }

    public void setComunidade(Comunidade comunidade) {
        this.comunidade = comunidade;
    }

    public List<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(List<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    public List<FormularioMensal> getFormulariosMensal() {
        return formulariosMensal;
    }

    public void setFormulariosMensal(List<FormularioMensal> formulariosMensal) {
        this.formulariosMensal = formulariosMensal;
    }

    public List<EmissaoOutputDTO> getEmissoes() {
        return emissoes;
    }

    public void setEmissoes(List<EmissaoOutputDTO> emissoes) {
        this.emissoes = emissoes;
    }

    public List<Premio> getPremios() {
        return premios;
    }

    public void setPremios(List<Premio> premios) {
        this.premios = premios;
    }
}
