package br.com.global.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmissaoOutputDTO {
    @JsonProperty
    private String mes;
    @JsonProperty
    private Double emissao;

    public EmissaoOutputDTO() {
    }

    public EmissaoOutputDTO(String mes, Double emissao) {
        this.mes = mes;
        this.emissao = emissao;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Double getEmissao() {
        return emissao;
    }

    public void setEmissao(Double emissao) {
        this.emissao = emissao;
    }
}
