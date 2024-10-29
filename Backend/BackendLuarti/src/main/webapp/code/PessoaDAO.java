package Backend.BackendLuarti.src.main.webapp.code;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                stmt.setString(4, pessoa.getStatus());
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
                        pessoa.setStatus(rs.getString("status"));
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
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
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
            return pessoas;
        }

        // Atualizar uma Pessoa
        public void updatePessoa(Pessoa pessoa) throws SQLException {
            String sql = "UPDATE pessoa SET nomeCompleto = ?, email = ?, senha = ?, status = ?, idSuperior = ? WHERE idPessoa = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, pessoa.getNomeCompleto());
                stmt.setString(2, pessoa.getEmail());
                stmt.setString(3, pessoa.getSenha());
                stmt.setString(4, pessoa.getStatus());
                if (pessoa.getIdSuperior() != 0) {
                    stmt.setInt(5, pessoa.getIdSuperior());
                } else {
                    stmt.setNull(5, Types.INTEGER);
                }
                stmt.setInt(6, pessoa.getIdPessoa());
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

        // Associar uma Tarefa a uma Pessoa
        public void adicionarTarefaAPessoa(int idPessoa, int idTarefa) throws SQLException {
            String sql = "INSERT INTO pessoa_has_tarefa (idPessoa, idTarefa) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idPessoa);
                stmt.setInt(2, idTarefa);
                stmt.executeUpdate();
            }
        }

        // Desassociar uma Tarefa de uma Pessoa
        public void removerTarefaDePessoa(int idPessoa, int idTarefa) throws SQLException {
            String sql = "DELETE FROM pessoa_has_tarefa WHERE idPessoa = ? AND idTarefa = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idPessoa);
                stmt.setInt(2, idTarefa);
                stmt.executeUpdate();
            }
        }

        // Obter todas as Tarefas associadas a uma Pessoa
        public List<Tarefa> getTarefasDePessoa(int idPessoa) throws SQLException {
            String sql = "SELECT t.* FROM tarefa t INNER JOIN pessoa_has_tarefa pht ON t.idTarefa = pht.idTarefa WHERE pht.idPessoa = ?";
            List<Tarefa> tarefas = new ArrayList<>();
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idPessoa);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Tarefa tarefa = new Tarefa();
                        tarefa.setIdTarefa(rs.getInt("idTarefa"));
                        tarefa.setNomeTarefa(rs.getString("nomeTarefa"));
                        tarefa.setDescTarefa(rs.getString("descTarefa"));
                        tarefa.setDataCriacao(rs.getTimestamp("dataCriacao"));
                        tarefa.setDataFim(rs.getTimestamp("dataFim"));
                        tarefa.setStatus(rs.getString("status"));
                        tarefa.setIdTipoTarefa(rs.getInt("idTipoTarefa"));
                        tarefas.add(tarefa);
                    }
                }
            }
            return tarefas;
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

