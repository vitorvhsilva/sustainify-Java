package br.com.global.dto;

public class EmissaoOutputSQL {
    private Integer mes;
    private Double emissao;

    public EmissaoOutputSQL() {
    }

    public EmissaoOutputSQL(Integer mes, Double emissao) {
        this.mes = mes;
        this.emissao = emissao;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Double getEmissao() {
        return emissao;
    }

    public void setEmissao(Double emissao) {
        this.emissao = emissao;
    }
}
