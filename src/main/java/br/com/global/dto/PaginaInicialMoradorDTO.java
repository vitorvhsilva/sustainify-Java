package br.com.global.dto;

import br.com.global.domain.model.FormularioMensal;
import br.com.global.domain.model.Morador;
import br.com.global.domain.model.Premio;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaginaInicialMoradorDTO {
    @JsonProperty
    private Morador morador;
    @JsonProperty
    private List<EmissaoOutputDTO> emissaoMensal;
    @JsonProperty
    private List<EmissaoOutputDTO> emissaoAnual;
    @JsonProperty
    private List<FormularioMensal> formulariosRanking;
    @JsonProperty
    private List<Premio> premios;

    public PaginaInicialMoradorDTO(Morador morador, List<EmissaoOutputDTO> emissaoMensal, List<EmissaoOutputDTO> emissaoAnual, List<FormularioMensal> formulariosRanking, List<Premio> premios) {
        this.morador = morador;
        this.emissaoMensal = emissaoMensal;
        this.emissaoAnual = emissaoAnual;
        this.formulariosRanking = formulariosRanking;
        this.premios = premios;
    }

    public PaginaInicialMoradorDTO() {
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public List<EmissaoOutputDTO> getEmissaoMensal() {
        return emissaoMensal;
    }

    public void setEmissaoMensal(List<EmissaoOutputDTO> emissaoMensal) {
        this.emissaoMensal = emissaoMensal;
    }

    public List<EmissaoOutputDTO> getEmissaoAnual() {
        return emissaoAnual;
    }

    public void setEmissaoAnual(List<EmissaoOutputDTO> emissaoAnual) {
        this.emissaoAnual = emissaoAnual;
    }

    public List<FormularioMensal> getFormulariosRanking() {
        return formulariosRanking;
    }

    public void setFormulariosRanking(List<FormularioMensal> formulariosRanking) {
        this.formulariosRanking = formulariosRanking;
    }

    public List<Premio> getPremios() {
        return premios;
    }

    public void setPremios(List<Premio> premios) {
        this.premios = premios;
    }
}
