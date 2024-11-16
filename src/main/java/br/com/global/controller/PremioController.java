package br.com.global.controller;

import br.com.global.domain.model.Premio;
import br.com.global.dto.AtualizarStatusMoradorDTO;
import br.com.global.service.PremioService;
import br.com.global.service.ServicosPremio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("premios")
public class PremioController {
    private ServicosPremio servicosPremio;

    public PremioController() {
        this.servicosPremio = new PremioService();
    }

    @GET
    @Path("/{idSindico}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarPremiosDaComunidade(@PathParam("idSindico") Long idSindico){
        try {
            List<Premio> premios = servicosPremio.pegarPremiosDaComunidade(idSindico);
            return Response.status(Response.Status.OK).entity(premios).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response atualizarStatusAtualizacao(Premio premio){
        try {
            servicosPremio.atualizarPremio(premio);
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
