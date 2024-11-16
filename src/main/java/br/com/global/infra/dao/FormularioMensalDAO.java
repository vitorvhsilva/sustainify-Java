package br.com.global.infra.dao;

import br.com.global.domain.model.FormularioMensal;
import br.com.global.domain.model.Sindico;
import br.com.global.domain.repository.RepositorioFormulariosMensal;
import br.com.global.dto.LoginDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormularioMensalDAO implements RepositorioFormulariosMensal {
    private Connection conexao;

    public FormularioMensalDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId() {
        Long id = null;
        try {
            String sql = "SELECT tb_form_mensal_seq.NEXTVAL FROM DUAL";
            PreparedStatement comandoDeGeracao = conexao.prepareStatement(sql);
            ResultSet rs = comandoDeGeracao.executeQuery();
            while (rs.next()) {
                id = rs.getLong(1);
            }
            comandoDeGeracao.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public void persistirFormularioMensal(FormularioMensal formularioMensal) {
        String sqlInsert = """
            INSERT INTO TB_FORMULARIO_MENSAL VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setLong(1, obterProximoId());
            ps.setLong(2, formularioMensal.getIdMoradia());
            ps.setLong(3, formularioMensal.getIdMoradia());
            ps.setDouble(4, formularioMensal.getValorContaLuzMensal());
            ps.setDouble(5, formularioMensal.getEnergiaGastaMensal());
            ps.setDouble(6, formularioMensal.getEmissaoCarbonoMensal());
            ps.setInt(7, formularioMensal.getAnoEmitido());
            ps.setInt(8, formularioMensal.getMesEmitido());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
