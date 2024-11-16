package br.com.global.infra.dao;

import br.com.global.domain.model.Morador;
import br.com.global.domain.model.Sindico;
import br.com.global.domain.repository.RepositorioMoradores;
import br.com.global.dto.LoginDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoradorDAO implements RepositorioMoradores {
    private Connection conexao;

    public MoradorDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId() {
        Long id = null;
        try {
            String sql = "SELECT tb_morador_seq.NEXTVAL FROM DUAL";
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

    public void persistirMorador(Morador morador, Long idMorador) {
        String sqlInsert = """
                INSERT INTO TB_MORADOR VALUES (?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setLong(1, idMorador);
            ps.setString(2, morador.getNomeMorador());
            ps.setString(3, morador.getCpfMorador());
            ps.setString(4, morador.getEmailMorador());
            ps.setString(5, morador.getSenhaMorador());
            ps.setString(6, morador.getTelefoneMorador());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean moradorExistePorCpf(String cpf) {
        String sqlSelect = "SELECT * FROM TB_MORADOR WHERE cpf_morador = ?";
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

    public boolean moradorExistePorEmail(String email) {

        String sqlSelect = "SELECT * FROM TB_MORADOR WHERE email_morador = ?";
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
    public Long fazerLogin(LoginDTO dto) {
        String sqlSelect = "SELECT * FROM TB_MORADOR WHERE email_morador = ? AND senha_morador = ?";
        Long idMorador = null;
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setString(1, dto.getEmail());
            statement.setString(2, dto.getSenha());
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("Login não encontrado!");
            }

            idMorador = rs.getLong("id_morador");

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return idMorador;
    }

    @Override
    public Morador retornarMoradorPorIdMorador(Long idMorador) {
        String sqlSelect = "SELECT * FROM TB_MORADOR WHERE id_morador = ?";
        Morador morador = new Morador();
        try {
            PreparedStatement ps = conexao.prepareStatement(sqlSelect);
            ps.setLong(1, idMorador);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                morador.setNomeMorador(rs.getString("nome_morador"));
                morador.setEmailMorador(rs.getString("email_morador"));
                morador.setSenhaMorador(rs.getString("senha_morador"));
                morador.setCpfMorador(rs.getString("cpf_morador"));
                morador.setTelefoneMorador(rs.getString("telefone_morador"));
            }
            ps.close();
            rs.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return morador;
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
