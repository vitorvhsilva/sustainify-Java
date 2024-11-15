package br.com.global.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CadastroMoradorInputDTO {
    @JsonProperty
    private String nomeMorador;
    @JsonProperty
    private String cpfMorador;
    @JsonProperty
    private String emailMorador;
    @JsonProperty
    private String senhaMorador;
    @JsonProperty
    private String telefoneMorador;
    @JsonProperty
    private String cepSolicitacao;
    @JsonProperty
    private String numResidenciaSolicitacao;

    public CadastroMoradorInputDTO() {
    }

    public CadastroMoradorInputDTO(String nomeMorador, String cpfMorador, String emailMorador, String senhaMorador, String telefoneMorador, String cepSolicitacao, String numResidenciaSolicitacao) {
        this.nomeMorador = nomeMorador;
        this.cpfMorador = cpfMorador;
        this.emailMorador = emailMorador;
        this.senhaMorador = senhaMorador;
        this.telefoneMorador = telefoneMorador;
        this.cepSolicitacao = cepSolicitacao;
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

    public String getEmailMorador() {
        return emailMorador;
    }

    public void setEmailMorador(String emailMorador) {
        this.emailMorador = emailMorador;
    }

    public String getSenhaMorador() {
        return senhaMorador;
    }

    public void setSenhaMorador(String senhaMorador) {
        this.senhaMorador = senhaMorador;
    }

    public String getTelefoneMorador() {
        return telefoneMorador;
    }

    public void setTelefoneMorador(String telefoneMorador) {
        this.telefoneMorador = telefoneMorador;
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

    @Override
    public String toString() {
        return "CadastroMoradorInputDTO{" +
                "nomeMorador='" + nomeMorador + '\'' +
                ", cpfMorador='" + cpfMorador + '\'' +
                ", emailMorador='" + emailMorador + '\'' +
                ", senhaMorador='" + senhaMorador + '\'' +
                ", telefoneMorador='" + telefoneMorador + '\'' +
                ", cepSolicitacao='" + cepSolicitacao + '\'' +
                ", numResidenciaSolicitacao='" + numResidenciaSolicitacao + '\'' +
                '}';
    }
}
