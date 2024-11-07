package dao;

import model.TipoTarefa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoTarefaDAO {
    private Connection connection;

    // Construtor que inicializa a conexão com o banco de dados
    public TipoTarefaDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para adicionar um novo TipoTarefa
    public void adicionar(TipoTarefa tipoTarefa) throws SQLException {
        String sql = "INSERT INTO tipotarefa (descTipoTarefa) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, tipoTarefa.getDescTipoTarefa());
            pstmt.executeUpdate();
            
            // Recupera o ID gerado
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tipoTarefa.setIdTipoTarefa(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Método para obter um TipoTarefa pelo ID
    public TipoTarefa obterPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tipotarefa WHERE idTipoTarefa = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                TipoTarefa tipoTarefa = new TipoTarefa();
                tipoTarefa.setIdTipoTarefa(rs.getInt("idTipoTarefa"));
                tipoTarefa.setDescTipoTarefa(rs.getString("descTipotarefa"));
                return tipoTarefa;
            }
        }
        return null; // Retorna null se não encontrar
    }

    // Método para listar todos os TipoTarefas
    public List<TipoTarefa> listarTodos() throws SQLException {
        List<TipoTarefa> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipotarefa";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                TipoTarefa tipoTarefa = new TipoTarefa();
                tipoTarefa.setIdTipoTarefa(rs.getInt("idTipoTarefa"));
                tipoTarefa.setDescTipoTarefa(rs.getString("descTipotarefa"));
                lista.add(tipoTarefa);
            }
        }
        return lista;
    }

    // Método para atualizar um TipoTarefa
    public void atualizar(TipoTarefa tipoTarefa) throws SQLException {
        String sql = "UPDATE tipotarefa SET descTipotarefa = ? WHERE idTipoTarefa = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tipoTarefa.getDescTipoTarefa());
            pstmt.setInt(2, tipoTarefa.getIdTipoTarefa());
            pstmt.executeUpdate();
        }
    }

    // Método para deletar um TipoTarefa
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM tipotarefa WHERE idTipoTarefa = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
