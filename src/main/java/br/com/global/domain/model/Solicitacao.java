package br.com.global.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Solicitacao {
    @JsonProperty
    private Long idMorador;
    @JsonProperty
    private Long idSindico;
    @JsonProperty
    private String nomeMorador;
    @JsonProperty
    private String cpfMorador;
    @JsonProperty
    private String cepSolicitacao;
    @JsonProperty
    private String numResidenciaSolicitacao;

    public Solicitacao() {
    }

    public Solicitacao(Long idMorador, Long idSindico, String nomeMorador, String cpfMorador, String cepSolicitacao, String numResidenciaSolicitacao) {
        this.idMorador = idMorador;
        this.idSindico = idSindico;
        this.nomeMorador = nomeMorador;
        this.cpfMorador = cpfMorador;
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

    public String getNomeMorador() {
        return nomeMorador;
    }

    public void setNomeMorador(String nomeMorador) {
        this.nomeMorador = nomeMorador;
    }

    public String getCpfMorador() {
        return cpfMorador;
    }

    public void setCpfMorador(String cpfMorador) {
        this.cpfMorador = cpfMorador;
    }

    @Override
    public String toString() {
        return "Solicitacao{" +
                "idMorador=" + idMorador +
                ", idSindico=" + idSindico +
                ", cepSolicitacao='" + cepSolicitacao + '\'' +
                ", numResidenciaSolicitacao='" + numResidenciaSolicitacao + '\'' +
                '}';
    }
}
