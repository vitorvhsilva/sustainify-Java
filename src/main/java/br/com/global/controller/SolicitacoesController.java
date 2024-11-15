package br.com.global.controller;

import br.com.global.domain.model.Solicitacao;
import br.com.global.dto.CadastroSindicoInputDTO;
import br.com.global.service.ServicosComunidade;
import br.com.global.service.ServicosSolicitacao;
import br.com.global.service.SindicoService;
import br.com.global.service.SolicitacaoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("solicitacoes")
public class SolicitacoesController {
    private ServicosSolicitacao servicosSolicitacao;

    public SolicitacoesController() {
        this.servicosSolicitacao = new SolicitacaoService();
    }

    @GET
    @Path("/{cep}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarSolicitacoesNaComunidadePorCep(@PathParam("cep") String cep){
        try {
            List<Solicitacao> solicitacoes = servicosSolicitacao.pegarSolicitacoesNaComunidadePorCep(cep);
            return Response.status(Response.Status.OK).entity(solicitacoes).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

}
