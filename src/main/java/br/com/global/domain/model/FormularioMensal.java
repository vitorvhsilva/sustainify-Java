package br.com.global.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormularioMensal {
    @JsonProperty
    private Long idMoradia;
    @JsonProperty
    private Double valorContaLuzMensal;
    @JsonProperty
    private String energiaGastaMensal;
    @JsonProperty
    private Double emissaoCarbonoMensal;
    @JsonProperty
    private String mesEmitido;
    @JsonProperty
    private String anoEmitido;

    public FormularioMensal() {
    }

    public FormularioMensal(Long idMoradia, Double valorContaLuzMensal, String energiaGastaMensal, Double emissaoCarbonoMensal, String mesEmitido, String anoEmitido) {
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

    public String getEnergiaGastaMensal() {
        return energiaGastaMensal;
    }

    public void setEnergiaGastaMensal(String energiaGastaMensal) {
        this.energiaGastaMensal = energiaGastaMensal;
    }

    public Double getEmissaoCarbonoMensal() {
        return emissaoCarbonoMensal;
    }

    public void setEmissaoCarbonoMensal(Double emissaoCarbonoMensal) {
        this.emissaoCarbonoMensal = emissaoCarbonoMensal;
    }

    public String getMesEmitido() {
        return mesEmitido;
    }

    public void setMesEmitido(String mesEmitido) {
        this.mesEmitido = mesEmitido;
    }

    public String getAnoEmitido() {
        return anoEmitido;
    }

    public void setAnoEmitido(String anoEmitido) {
        this.anoEmitido = anoEmitido;
    }
}
