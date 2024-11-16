package br.com.global.controller;

import br.com.global.domain.model.Premio;
import br.com.global.dto.AtualizarStatusMoradorDTO;
import br.com.global.service.PremioService;
import br.com.global.service.ServicosPremio;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("premios")
public class PremioController {
    private ServicosPremio servicosPremio;

    public PremioController() {
        this.servicosPremio = new PremioService();
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
