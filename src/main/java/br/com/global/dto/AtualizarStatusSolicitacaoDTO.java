package br.com.global.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AtualizarStatusSolicitacaoDTO {
    @JsonProperty
    private String numResidenciaSolicitacao;

    public AtualizarStatusSolicitacaoDTO() {
    }

    public AtualizarStatusSolicitacaoDTO(String numResidenciaSolicitacao) {
        this.numResidenciaSolicitacao = numResidenciaSolicitacao;
    }

    public String getNumResidenciaSolicitacao() {
        return numResidenciaSolicitacao;
    }

    public void setNumResidenciaSolicitacao(String numResidenciaSolicitacao) {
        this.numResidenciaSolicitacao = numResidenciaSolicitacao;
    }
}
