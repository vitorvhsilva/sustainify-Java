package br.com.global.infra.dao;

import br.com.global.domain.model.FormularioMensal;
import br.com.global.domain.repository.RepositorioFormulariosMensal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<FormularioMensal> pegarFormulariosPorMesAnoComunidade(Long idSindico, Integer mes, Integer ano) {
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

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
