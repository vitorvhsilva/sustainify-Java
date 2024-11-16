package br.com.global.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CadastroFormularioMensalInputDTO {
    @JsonProperty
    private Long idMoradia;
    @JsonProperty
    private Double valorContaLuzMensal;
    @JsonProperty
    private Double energiaGastaMensal;
    @JsonProperty
    private Double emissaoCarbonoMensal;
    @JsonProperty
    private Integer mesEmitido;
    @JsonProperty
    private Integer anoEmitido;

    public CadastroFormularioMensalInputDTO() {
    }

    public CadastroFormularioMensalInputDTO(Long idMoradia, Double valorContaLuzMensal, Double energiaGastaMensal, Double emissaoCarbonoMensal, Integer mesEmitido, Integer anoEmitido) {
        this.idMoradia = idMoradia;
        this.valorContaLuzMensal = valorContaLuzMensal;
        this.energiaGastaMensal = energiaGastaMensal;
        this.emissaoCarbonoMensal = emissaoCarbonoMensal;
        this.mesEmitido = mesEmitido;
        this.anoEmitido = anoEmitido;
    }

    public Long getIdMoradia() {
        return idMoradia;
    }

    public void setIdMoradia(Long idMoradia) {
        this.idMoradia = idMoradia;
    }

    public Double getValorContaLuzMensal() {
        return valorContaLuzMensal;
    }

    public void setValorContaLuzMensal(Double valorContaLuzMensal) {
        this.valorContaLuzMensal = valorContaLuzMensal;
    }

    public Double getEnergiaGastaMensal() {
        return energiaGastaMensal;
    }

    public void setEnergiaGastaMensal(Double energiaGastaMensal) {
        this.energiaGastaMensal = energiaGastaMensal;
    }

    public Double getEmissaoCarbonoMensal() {
        return emissaoCarbonoMensal;
    }

    public void setEmissaoCarbonoMensal(Double emissaoCarbonoMensal) {
        this.emissaoCarbonoMensal = emissaoCarbonoMensal;
    }

    public Integer getMesEmitido() {
        return mesEmitido;
    }

    public void setMesEmitido(Integer mesEmitido) {
        this.mesEmitido = mesEmitido;
    }

    public Integer getAnoEmitido() {
        return anoEmitido;
    }

    public void setAnoEmitido(Integer anoEmitido) {
        this.anoEmitido = anoEmitido;
    }
}
