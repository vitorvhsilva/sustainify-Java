package br.com.global.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Solicitacao {
    @JsonProperty
    private Long idMorador;
    @JsonProperty
    private Long idSindico;
    @JsonProperty
    private String cepSolicitacao;
    @JsonProperty
    private String numResidenciaSolicitacao;

    public Solicitacao() {
    }

    public Solicitacao(Long idMorador, Long idSindico, String cepSolicitacao, String numResidenciaSolicitacao) {
        this.idMorador = idMorador;
        this.idSindico = idSindico;
        this.cepSolicitacao = cepSolicitacao;
        this.numResidenciaSolicitacao = numResidenciaSolicitacao;
    }

    public Long getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(Long idMorador) {
        this.idMorador = idMorador;
    }

    public Long getIdSindico() {
        return idSindico;
    }

    public void setIdSindico(Long idSindico) {
        this.idSindico = idSindico;
    }

    public String getCepSolicitacao() {
        return cepSolicitacao;
    }

    public void setCepSolicitacao(String cepSolicitacao) {
        this.cepSolicitacao = cepSolicitacao;
    }

    public String getNumResidenciaSolicitacao() {
        return numResidenciaSolicitacao;
    }

    public void setNumResidenciaSolicitacao(String numResidenciaSolicitacao) {
        this.numResidenciaSolicitacao = numResidenciaSolicitacao;
    }
}
