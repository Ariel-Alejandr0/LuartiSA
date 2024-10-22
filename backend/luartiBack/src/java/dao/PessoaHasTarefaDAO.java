package dao;

import model.PessoaHasTarefa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaHasTarefaDAO {
    private Connection connection;

    // Construtor que inicializa a conexão com o banco de dados
    public PessoaHasTarefaDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para adicionar um novo registro de relação entre Pessoa e Tarefa
    public void adicionar(PessoaHasTarefa pessoaHasTarefa) throws SQLException {
        String sql = "INSERT INTO pessoa_has_tarefa (idPessoa, idTarefa) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pessoaHasTarefa.getIdPessoa());
            pstmt.setInt(2, pessoaHasTarefa.getIdTarefa());
            pstmt.executeUpdate();
        }
    }

    // Método para obter todas as relações entre Pessoa e Tarefa
    public List<PessoaHasTarefa> listarTodos() throws SQLException {
        List<PessoaHasTarefa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoa_has_tarefa";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                PessoaHasTarefa pessoaHasTarefa = new PessoaHasTarefa();
                pessoaHasTarefa.setIdPessoa(rs.getInt("idPessoa"));
                pessoaHasTarefa.setIdTarefa(rs.getInt("idTarefa"));
                lista.add(pessoaHasTarefa);
            }
        }
        return lista;
    }

    // Método para deletar uma relação entre Pessoa e Tarefa
    public void deletar(int idPessoa, int idTarefa) throws SQLException {
        String sql = "DELETE FROM pessoa_has_tarefa WHERE idPessoa = ? AND idTarefa = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idPessoa);
            pstmt.setInt(2, idTarefa);
            pstmt.executeUpdate();
        }
    }

    // Método para verificar se uma relação existe
    public boolean existe(int idPessoa, int idTarefa) throws SQLException {
        String sql = "SELECT COUNT(*) FROM pessoa_has_tarefa WHERE idPessoa = ? AND idTarefa = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idPessoa);
            pstmt.setInt(2, idTarefa);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
