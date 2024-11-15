package br.com.global.controller;

import br.com.global.dto.CadastroMoradorInputDTO;
import br.com.global.dto.CadastroSindicoInputDTO;
import br.com.global.service.MoradorService;
import br.com.global.service.SindicoService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("moradores")
public class MoradorController {
    private MoradorService moradorService;

    public MoradorController() {
        this.moradorService = new MoradorService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enviarSolicitacaoDeCadastro(CadastroMoradorInputDTO dto){
        try {
            moradorService.enviarSolicitacaoDeCadastro(dto);
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
