package br.com.global.infra.dao;

import br.com.global.domain.model.FormularioMensal;
import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioFormulariosMensal;
import br.com.global.dto.EmissaoOutputDTO;
import br.com.global.dto.EmissaoOutputSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormularioMensalDAO implements RepositorioFormulariosMensal {
    private Connection conexao;

    public FormularioMensalDAO(Connection conexao) {
        this.conexao = conexao;
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
            INSERT INTO TB_FORMULARIO_MENSAL VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setLong(1, obterProximoId());
            ps.setLong(2, formularioMensal.getIdMoradia());
            ps.setLong(3, formularioMensal.getIdMoradia());
            ps.setDouble(4, formularioMensal.getValorContaLuzMensal());
            ps.setDouble(5, formularioMensal.getEnergiaGastaMensal());
            ps.setDouble(6, formularioMensal.getEmissaoCarbonoMensal());
            ps.setString(7, formularioMensal.getNumResidencia());
            ps.setInt(8, formularioMensal.getMesEmitido());
            ps.setInt(9, formularioMensal.getAnoEmitido());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FormularioMensal> pegarFormulariosPorMesAnoSindicoComunidade(Long idSindico, Integer mes, Integer ano) {
        String sqlSelect = "SELECT * FROM TB_FORMULARIO_MENSAL WHERE id_sindico = ? AND mes_emitido = ? AND ano_emitido = ?";
        List<FormularioMensal> formularios = new ArrayList<>();
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idSindico);
            statement.setInt(2, mes);
            statement.setInt(3, ano);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                FormularioMensal formularioMensal = new FormularioMensal();
                formularioMensal.setIdMoradia(rs.getLong("id_moradia"));
                formularioMensal.setIdSindico(rs.getLong("id_sindico"));
                formularioMensal.setValorContaLuzMensal(rs.getDouble("valor_conta_luz_mensal"));
                formularioMensal.setEnergiaGastaMensal(rs.getDouble("energia_gasta_mensal"));
                formularioMensal.setEmissaoCarbonoMensal(rs.getDouble("emissao_carbono_mensal"));
                formularioMensal.setNumResidencia(rs.getString("num_moradia"));
                formularioMensal.setMesEmitido(rs.getInt("mes_emitido"));
                formularioMensal.setAnoEmitido(rs.getInt("ano_emitido"));
                formularios.add(formularioMensal);
            }


            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return formularios;
    }

    @Override
    public List<EmissaoOutputSQL> pegarEmissoesPorAnoComunidade(Long idSindico, Integer ano) {
        String sqlSelect = """
           SELECT mes_emitido AS mes, SUM(emissao_carbono_mensal) AS emissao
           FROM TB_FORMULARIO_MENSAL WHERE id_sindico = ? AND ano_emitido = ?
           GROUP BY mes_emitido
           """;

        List<EmissaoOutputSQL> emissoes = new ArrayList<>();
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idSindico);
            statement.setInt(2, ano);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                EmissaoOutputSQL emissao = new EmissaoOutputSQL(rs.getInt("mes"), rs.getDouble("emissao"));
                emissoes.add(emissao);
            }


            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return emissoes;
    }

    @Override
    public List<FormularioMensal> pegarFormulariosPorAnoMoradia(Long idMoradia, Integer ano) {
        String sqlSelect = "SELECT * FROM TB_FORMULARIO_MENSAL WHERE id_moradia = ? AND ano_emitido = ?";
        List<FormularioMensal> formularios = new ArrayList<>();
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idMoradia);
            statement.setInt(2, ano);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                FormularioMensal formularioMensal = new FormularioMensal();
                formularioMensal.setIdMoradia(rs.getLong("id_moradia"));
                formularioMensal.setIdSindico(rs.getLong("id_sindico"));
                formularioMensal.setValorContaLuzMensal(rs.getDouble("valor_conta_luz_mensal"));
                formularioMensal.setEnergiaGastaMensal(rs.getDouble("energia_gasta_mensal"));
                formularioMensal.setEmissaoCarbonoMensal(rs.getDouble("emissao_carbono_mensal"));
                formularioMensal.setNumResidencia(rs.getString("num_moradia"));
                formularioMensal.setMesEmitido(rs.getInt("mes_emitido"));
                formularioMensal.setAnoEmitido(rs.getInt("ano_emitido"));
                formularios.add(formularioMensal);
            }


            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return formularios;
    }

    @Override
    public FormularioMensal verificarSeFormularioExiste(FormularioMensal formularioMensal) {
        String sqlSelect = "SELECT * FROM TB_FORMULARIO_MENSAL WHERE mes_emitido = ? AND ano_emitido = ?";
        FormularioMensal formularioMensalPego = new FormularioMensal();
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setInt(1, formularioMensal.getMesEmitido());
            statement.setInt(2, formularioMensal.getAnoEmitido());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                formularioMensalPego.setIdMoradia(rs.getLong("id_moradia"));
                formularioMensalPego.setIdSindico(rs.getLong("id_sindico"));
                formularioMensalPego.setValorContaLuzMensal(rs.getDouble("valor_conta_luz_mensal"));
                formularioMensalPego.setEnergiaGastaMensal(rs.getDouble("energia_gasta_mensal"));
                formularioMensalPego.setEmissaoCarbonoMensal(rs.getDouble("emissao_carbono_mensal"));
                formularioMensalPego.setNumResidencia(rs.getString("num_moradia"));
                formularioMensalPego.setMesEmitido(rs.getInt("mes_emitido"));
                formularioMensalPego.setAnoEmitido(rs.getInt("ano_emitido"));
            }

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return formularioMensalPego;
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
