package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Tarefa;

public class TarefaDAO {

    private Connection connection;

    public TarefaDAO(Connection connection) {
        this.connection = connection;
    }

    // Criar uma nova Tarefa
    public void createTarefa(Tarefa tarefa) throws SQLException {
        String sql = "INSERT INTO tarefa (nomeTarefa, descTarefa, dataCriacao, dataFim, status, idTipoTarefa) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tarefa.getNomeTarefa());
            stmt.setString(2, tarefa.getDescTarefa());
            if (tarefa.getDataCriacao() != null) {
                stmt.setTimestamp(3, new Timestamp(tarefa.getDataCriacao().getTime()));
            } else {
                stmt.setNull(3, Types.TIMESTAMP);
            }
            if (tarefa.getDataFim() != null) {
                stmt.setTimestamp(4, new Timestamp(tarefa.getDataFim().getTime()));
            } else {
                stmt.setNull(4, Types.TIMESTAMP);
            }
            stmt.setString(5, tarefa.getStatus().name()); // Convertendo o enum para String
            stmt.setInt(6, tarefa.getIdTipoTarefa());
            stmt.executeUpdate();

            // Obter o ID gerado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tarefa.setIdTarefa(generatedKeys.getInt(1));
                }
            }
        }
    }

// Atualizar uma Tarefa
    public void updateTarefa(Tarefa tarefa) throws SQLException {
        String sql = "UPDATE tarefa SET nomeTarefa = ?, descTarefa = ?, dataCriacao = ?, dataFim = ?, status = ?, idTipoTarefa = ? WHERE idTarefa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tarefa.getNomeTarefa());
            stmt.setString(2, tarefa.getDescTarefa());
            if (tarefa.getDataCriacao() != null) {
                stmt.setTimestamp(3, new Timestamp(tarefa.getDataCriacao().getTime()));
            } else {
                stmt.setNull(3, Types.TIMESTAMP);
            }
            if (tarefa.getDataFim() != null) {
                stmt.setTimestamp(4, new Timestamp(tarefa.getDataFim().getTime()));
            } else {
                stmt.setNull(4, Types.TIMESTAMP);
            }
            stmt.setString(5, tarefa.getStatus().name()); // Convertendo o enum para String
            stmt.setInt(6, tarefa.getIdTipoTarefa());
            stmt.setInt(7, tarefa.getIdTarefa());
            stmt.executeUpdate();
        }
    }

        public List<Tarefa> getAllTarefa() throws SQLException {
        String sql = "SELECT * FROM tarefa";
        List<Tarefa> tarefas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setIdTarefa(rs.getInt("idTarefa"));
                tarefa.setNomeTarefa("nometarefa");
                tarefa.setDescTarefa("descTarefa");
                tarefa.setIdTipoTarefa(rs.getInt("descIdTipoTarefa"));
                tarefa.setDataCriacao(rs.getDate("dataCriacao"));
                tarefa.setDataFim(rs.getDate("dataFim"));
                tarefa.setStatus(Tarefa.Status.valueOf("status"));
            }
        }
        return tarefas;
    }

    
    public void deleteTarefa(int id) throws SQLException {
        String sql = "DELETE FROM tarefa WHERE id= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
