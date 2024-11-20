package br.com.global.infra.dao;

import br.com.global.domain.model.Sindico;
import br.com.global.domain.repository.RepositorioSindicos;
import br.com.global.dto.LoginDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SindicoDAO implements RepositorioSindicos {
    private Connection conexao;

    public SindicoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Long obterProximoId() {
        Long id = null;
        try {
            String sql = "SELECT tb_sindico_seq.NEXTVAL FROM DUAL";
            PreparedStatement comandoDeGeracao = conexao.prepareStatement(sql);
            ResultSet rs = comandoDeGeracao.executeQuery();
            while(rs.next()) {
                id = rs.getLong(1);
            }
            comandoDeGeracao.close();
            rs.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void persistirSindico(Sindico sindico, Long idSindico) {
        String sqlInsert = """
                INSERT INTO TB_SINDICO VALUES (?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setLong(1, idSindico);
            ps.setString(2, sindico.getNomeSindico());
            ps.setString(3, sindico.getCpfSindico());
            ps.setString(4, sindico.getEmailSindico());
            ps.setString(5, sindico.getSenhaSindico());
            ps.setString(6, sindico.getTelefoneSindico());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sindicoExistePorCpf(String cpf) {
        String sqlSelect = "SELECT * FROM TB_SINDICO WHERE cpf_sindico = ?";
        boolean existe = false;

        try (PreparedStatement statement = conexao.prepareStatement(sqlSelect)) {
            statement.setString(1, cpf);
            try (ResultSet rs = statement.executeQuery()) {
                existe = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return existe;
    }

    public boolean sindicoExistePorEmail(String email) {

        String sqlSelect = "SELECT * FROM TB_SINDICO WHERE email_sindico = ?";
        boolean existe = false;

        try (PreparedStatement statement = conexao.prepareStatement(sqlSelect)) {
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                existe = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return existe;
    }

    @Override
    public Long retornarIdDoSindicoPorCpf(String cpf) {
        String sql = "SELECT * FROM TB_SINDICO WHERE cpf_sindico = ?";
        Long id = null;
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                id = rs.getLong("id_sindico");
            }
            ps.close();
            rs.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public Sindico retornarSindicoPorIdSindico(Long idSindico) {
        String sqlSelect = "SELECT * FROM TB_SINDICO WHERE id_sindico = ?";
        Sindico sindico = new Sindico();
        try {
            PreparedStatement ps = conexao.prepareStatement(sqlSelect);
            ps.setLong(1, idSindico);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                sindico.setNomeSindico(rs.getString("nome_sindico"));
                sindico.setEmailSindico(rs.getString("email_sindico"));
                sindico.setSenhaSindico(rs.getString("senha_sindico"));
                sindico.setCpfSindico(rs.getString("cpf_sindico"));
                sindico.setTelefoneSindico(rs.getString("telefone_sindico"));
            }
            ps.close();
            rs.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return sindico;
    }

    @Override
    public Long fazerLogin(LoginDTO dto) {
        String sqlSelect = "SELECT * FROM TB_SINDICO WHERE email_sindico = ? AND senha_sindico = ?";
        Long idSindico = null;
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setString(1, dto.getEmail());
            statement.setString(2, dto.getSenha());
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("Login não encontrado!");
            }

            idSindico = rs.getLong("id_sindico");

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return idSindico;
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
