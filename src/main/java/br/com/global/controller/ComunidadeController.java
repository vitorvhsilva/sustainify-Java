package br.com.global.controller;

import br.com.global.domain.model.Comunidade;
import br.com.global.service.ComunidadeService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("comunidades")
public class ComunidadeController {
    private ComunidadeService comunidadeService;

    public ComunidadeController() {
        this.comunidadeService = new ComunidadeService();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarComunidadePorIdSindico(@PathParam("id") Long idSindico){
        try {
            Comunidade comunidade = comunidadeService.retornarComunidadePorIdSindico(idSindico);
            return Response.status(Response.Status.OK).entity(comunidade).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

}
