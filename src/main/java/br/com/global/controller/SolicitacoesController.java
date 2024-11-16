package br.com.global.controller;

import br.com.global.domain.model.Solicitacao;
import br.com.global.dto.AtualizarStatusSolicitacaoDTO;
import br.com.global.service.SolicitacaoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("solicitacoes")
public class SolicitacoesController {
    private SolicitacaoService solicitacaoService;

    public SolicitacoesController() {
        this.solicitacaoService = new SolicitacaoService();
    }

    @GET
    @Path("/{cep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarSolicitacoesNaComunidadePorCep(@PathParam("cep") String cep){
        try {
            List<Solicitacao> solicitacoes = solicitacaoService.pegarSolicitacoesNaComunidadePorCep(cep);
            return Response.status(Response.Status.OK).entity(solicitacoes).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/morador/{idMorador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarSolicitacoesNaComunidadePorMorador(@PathParam("idMorador") Long idMorador){
        try {
            List<Solicitacao> solicitacoes = solicitacaoService.pegarSolicitacoesNaComunidadePorMorador(idMorador);
            return Response.status(Response.Status.OK).entity(solicitacoes).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idMorador}")
    public Response deletarSolicitacao(@PathParam("idMorador") Long idMorador){
        try {
            solicitacaoService.deletarSolicitacao(idMorador);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarSolicitacao(AtualizarStatusSolicitacaoDTO dto){
        try {
            solicitacaoService.atualizarSolicitacao(dto);
            return Response.status(Response.Status.OK).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

}
