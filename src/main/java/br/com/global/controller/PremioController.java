package br.com.global.controller;

import br.com.global.domain.model.Premio;
import br.com.global.service.PremioService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("premios")
public class PremioController {
    private PremioService premioService;

    public PremioController() {
        this.premioService = new PremioService();
    }

    @GET
    @Path("/{idSindico}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarPremiosDaComunidade(@PathParam("idSindico") Long idSindico){
        try {
            List<Premio> premios = premioService.pegarPremiosDaComunidade(idSindico);
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
    public Response atualizarPremio(Premio premio){
        try {
            premioService.atualizarPremio(premio);
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
