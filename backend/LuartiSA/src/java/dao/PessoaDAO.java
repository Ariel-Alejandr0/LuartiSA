package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;
import model.Tarefa;

public class PessoaDAO {

    private Connection connection;

    public PessoaDAO(Connection connection) {
        this.connection = connection;
    }

    // Criar uma nova Pessoa
    public void createPessoa(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoa (nomeCompleto, email, senha, status, idSuperior) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pessoa.getNomeCompleto());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getSenha());
            stmt.setString(4, pessoa.getStatus().name());
            if (pessoa.getIdSuperior() != 0) {
                stmt.setInt(5, pessoa.getIdSuperior());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.executeUpdate();

            // Obter o ID gerado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pessoa.setIdPessoa(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Obter uma Pessoa por ID
    public Pessoa getPessoaById(int id) throws SQLException {
        String sql = "SELECT * FROM pessoa WHERE idPessoa = ?";
        Pessoa pessoa = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pessoa = new Pessoa();
                    pessoa.setIdPessoa(rs.getInt("idPessoa"));
                    pessoa.setNomeCompleto(rs.getString("nomeCompleto"));
                    pessoa.setEmail(rs.getString("email"));
                    pessoa.setSenha(rs.getString("senha"));
                    pessoa.setStatus(Pessoa.Status.valueOf(rs.getString("status"))); // Convertendo String para enum
                    pessoa.setPapel(Pessoa.Papel.valueOf(rs.getString("papel"))); // Convertendo String para enum
                    pessoa.setIdSuperior(rs.getInt("idSuperior"));
                }
            }
        }
        return pessoa;
    }

    // Obter todas as Pessoas
    public List<Pessoa> getAllPessoas() throws SQLException {
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("idPessoa"));
                pessoa.setNomeCompleto(rs.getString("nomeCompleto"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setStatus(Pessoa.Status.valueOf(rs.getString("status"))); // Convertendo String para enum
                pessoa.setPapel(Pessoa.Papel.valueOf(rs.getString("papel"))); // Convertendo String para enum
                pessoa.setIdSuperior(rs.getInt("idSuperior"));
                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }

    // Atualizar uma Pessoa
    public void updatePessoa(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE pessoa SET nomeCompleto = ?, email = ?, senha = ?, status = ?, idSuperior = ?, papel = ? WHERE idPessoa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNomeCompleto());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getSenha());
            stmt.setString(4, pessoa.getStatus().name()); // Usando name() para obter o valor da enum
            if (pessoa.getIdSuperior() != 0) {
                stmt.setInt(5, pessoa.getIdSuperior());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.setString(6, pessoa.getPapel().name()); // Usando name() para obter o valor da enum
            stmt.setInt(7, pessoa.getIdPessoa());
            stmt.executeUpdate();
        }
    }

    // Deletar uma Pessoa
    public void deletePessoa(int id) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE idPessoa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
