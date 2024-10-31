package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;
import utils.Conexao;

public class PessoaDAO {

    private Connection connection;

    // Construtor que recebe a conexão como parâmetro
    public PessoaDAO(Connection connection) {
        this.connection = connection; // Armazena a conexão fornecida
    }

    // Método para salvar uma nova pessoa
    public void save(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nomeCompleto, email, senha, status, papel, idSuperior) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNomeCompleto());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getSenha());
            stmt.setString(4, pessoa.getStatus().name());
            stmt.setString(5, pessoa.getPapel().name());
            stmt.setInt(6, pessoa.getIdSuperior());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar pessoa", e);
        }
    }

    // Método para buscar todas as pessoas
    public List<Pessoa> findAll() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("idPessoa"));
                pessoa.setNomeCompleto(rs.getString("nomeCompleto"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setStatus(Pessoa.Status.valueOf(rs.getString("status")));
                pessoa.setPapel(Pessoa.Papel.valueOf(rs.getString("papel")));
                pessoa.setIdSuperior(rs.getInt("idSuperior"));

                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoas", e);
        }

        return pessoas;
    }

    // Método para buscar uma pessoa pelo ID
    public Pessoa findById(int id) {
        String sql = "SELECT * FROM pessoa WHERE idPessoa = ?";
        Pessoa pessoa = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("idPessoa"));
                pessoa.setNomeCompleto(rs.getString("nomeCompleto"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setStatus(Pessoa.Status.valueOf(rs.getString("status")));
                pessoa.setPapel(Pessoa.Papel.valueOf(rs.getString("papel")));
                pessoa.setIdSuperior(rs.getInt("idSuperior"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoa pelo ID", e);
        }

        return pessoa;
    }

    // Método para atualizar uma pessoa existente
    public void update(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nomeCompleto = ?, email = ?, senha = ?, status = ?, papel = ?, idSuperior = ? WHERE idPessoa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNomeCompleto());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getSenha());
            stmt.setString(4, pessoa.getStatus().name());
            stmt.setString(5, pessoa.getPapel().name());
            stmt.setInt(6, pessoa.getIdSuperior());
            stmt.setInt(7, pessoa.getIdPessoa());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pessoa", e);
        }
    }

    // Método para excluir uma pessoa
    public void delete(int id) {
        String sql = "DELETE FROM pessoa WHERE idPessoa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir pessoa", e);
        }
    }
public Pessoa autenticar(String email, String senha) throws SQLException {
    String sql = "SELECT * FROM pessoa WHERE email = ? AND senha = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, email);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setIdPessoa(rs.getInt("idPessoa"));
            pessoa.setNomeCompleto(rs.getString("nomeCompleto"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setIdSuperior(rs.getInt("idSuperior"));
            pessoa.setPapel(Pessoa.Papel.valueOf(rs.getString("papel")));
            pessoa.setStatus(Pessoa.Status.valueOf(rs.getString("status")));
            return pessoa; // Retorne a pessoa autenticada
        }
    }
    return null; // Retorne null se a autenticação falhar
}
}