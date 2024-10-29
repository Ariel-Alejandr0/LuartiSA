package Backend.BackendLuarti.src.main.webapp.code;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            stmt.setString(5, tarefa.getStatus());
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

    // Obter uma Tarefa por ID
    public Tarefa getTarefaById(int id) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE idTarefa = ?";
        Tarefa tarefa = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tarefa = new Tarefa();
                    tarefa.setIdTarefa(rs.getInt("idTarefa"));
                    tarefa.setNomeTarefa(rs.getString("nomeTarefa"));
                    tarefa.setDescTarefa(rs.getString("descTarefa"));
                    Timestamp dataCriacaoTs = rs.getTimestamp("dataCriacao");
                    if (dataCriacaoTs != null) {
                        tarefa.setDataCriacao(new java.util.Date(dataCriacaoTs.getTime()));
                    }
                    Timestamp dataFimTs = rs.getTimestamp("dataFim");
                    if (dataFimTs != null) {
                        tarefa.setDataFim(new java.util.Date(dataFimTs.getTime()));
                    }
                    tarefa.setStatus(rs.getString("status"));
                    tarefa.setIdTipoTarefa(rs.getInt("idTipoTarefa"));
                }
            }
        }
        return tarefa;
    }

    // Obter todas as Tarefas
    public List<Tarefa> getAllTarefas() throws SQLException {
        String sql = "SELECT * FROM tarefa";
        List<Tarefa> tarefas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setIdTarefa(rs.getInt("idTarefa"));
                tarefa.setNomeTarefa(rs.getString("nomeTarefa"));
                tarefa.setDescTarefa(rs.getString("descTarefa"));
                Timestamp dataCriacaoTs = rs.getTimestamp("dataCriacao");
                if (dataCriacaoTs != null) {
                    tarefa.setDataCriacao(new java.util.Date(dataCriacaoTs.getTime()));
                }
                Timestamp dataFimTs = rs.getTimestamp("dataFim");
                if (dataFimTs != null) {
                    tarefa.setDataFim(new java.util.Date(dataFimTs.getTime()));
                }
                tarefa.setStatus(rs.getString("status"));
                tarefa.setIdTipoTarefa(rs.getInt("idTipoTarefa"));
                tarefas.add(tarefa);
            }
        }
        return tarefas;
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
            stmt.setString(5, tarefa.getStatus());
            stmt.setInt(6, tarefa.getIdTipoTarefa());
            stmt.setInt(7, tarefa.getIdTarefa());
            stmt.executeUpdate();
        }
    }

    // Deletar uma Tarefa
    public void deleteTarefa(int id) throws SQLException {
        String sql = "DELETE FROM tarefa WHERE idTarefa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Associar uma Pessoa a uma Tarefa
    public void adicionarPessoaATarefa(int idTarefa, int idPessoa) throws SQLException {
        String sql = "INSERT INTO pessoa_has_tarefa (idPessoa, idTarefa) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            stmt.setInt(2, idTarefa);
            stmt.executeUpdate();
        }
    }

    // Desassociar uma Pessoa de uma Tarefa
    public void removerPessoaDeTarefa(int idTarefa, int idPessoa) throws SQLException {
        String sql = "DELETE FROM pessoa_has_tarefa WHERE idPessoa = ? AND idTarefa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            stmt.setInt(2, idTarefa);
            stmt.executeUpdate();
        }
    }

    // Obter todas as Pessoas associadas a uma Tarefa
    public List<Pessoa> getPessoasDeTarefa(int idTarefa) throws SQLException {
        String sql = "SELECT p.* FROM pessoa p INNER JOIN pessoa_has_tarefa pht ON p.idPessoa = pht.idPessoa WHERE pht.idTarefa = ?";
        List<Pessoa> pessoas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idTarefa);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setIdPessoa(rs.getInt("idPessoa"));
                    pessoa.setNomeCompleto(rs.getString("nomeCompleto"));
                    pessoa.setEmail(rs.getString("email"));
                    pessoa.setSenha(rs.getString("senha"));
                    pessoa.setStatus(rs.getString("status"));
                    pessoa.setIdSuperior(rs.getInt("idSuperior"));
                    pessoas.add(pessoa);
                }
            }
        }
        return pessoas;
    }
}
