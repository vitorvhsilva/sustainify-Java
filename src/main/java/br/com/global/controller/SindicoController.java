package br.com.global.controller;

import br.com.global.dto.CadastroSindicoInputDTO;
import br.com.global.infra.dao.SindicoDAO;
import br.com.global.service.SindicoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sindicos")
public class SindicoController {
    private SindicoService sindicoService;

    public SindicoController() {
        this.sindicoService = new SindicoService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistirSindicoeComunidade(CadastroSindicoInputDTO dto){
        try {
            sindicoService.persistirSindicoeComunidade(dto);
            return Response.status(Response.Status.CREATED).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

}
