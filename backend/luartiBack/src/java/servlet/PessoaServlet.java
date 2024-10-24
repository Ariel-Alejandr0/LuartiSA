package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;
import model.Tarefa;
import service.PessoaService;
import service.TarefaService;

@WebServlet("/pessoa")
public class PessoaServlet extends HttpServlet {

    private Connection connection;
    private PessoaService pessoaService;

    @Override
    public void init() throws ServletException {
        this.pessoaService = new PessoaService();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equalsIgnoreCase(action)) {
            createPessoa(request, response);
        } else if ("update".equalsIgnoreCase(action)) {
            updatePessoa(request, response);
        } else if ("addTarefa".equalsIgnoreCase(action)) {
            addTarefaToPessoa(request, response);
        } else if ("removeTarefa".equalsIgnoreCase(action)) {
            removeTarefaFromPessoa(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equalsIgnoreCase(action)) {
            listPessoas(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            deletePessoa(request, response);
        } else if ("listTarefas".equalsIgnoreCase(action)) {
            listTarefasByPessoa(request, response);
        }
    }

    // Método para criar uma nova Pessoa
    private void createPessoa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        int idade = Integer.parseInt(request.getParameter("idade"));

        String sql = "INSERT INTO pessoa (nome, email, idade) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setInt(3, idade);
            stmt.executeUpdate();
            response.getWriter().println("Pessoa criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao criar Pessoa.");
        }
    }

    // Método para atualizar uma Pessoa
    private void updatePessoa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        int idade = Integer.parseInt(request.getParameter("idade"));

        String sql = "UPDATE pessoa SET nome = ?, email = ?, idade = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setInt(3, idade);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            response.getWriter().println("Pessoa atualizada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao atualizar Pessoa.");
        }
    }

    // Método para deletar uma Pessoa
    private void deletePessoa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        String sql = "DELETE FROM pessoa WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            response.getWriter().println("Pessoa deletada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao deletar Pessoa.");
        }
    }

    // Método para listar todas as Pessoas
    private void listPessoas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("id"));
                pessoa.setNomeCompleto(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoas.add(pessoa);
            }

            // Exibição no response
            for (Pessoa p : pessoas) {
                response.getWriter().println("ID: " + p.getIdPessoa() + ", Nome: " + p.getNomeCompleto() + ", Email: " + p.getEmail());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao listar Pessoas.");
        }
    }

    // Método para adicionar uma tarefa a uma pessoa
    private void addTarefaToPessoa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idPessoa = Integer.parseInt(request.getParameter("idPessoa"));
        int idTarefa = Integer.parseInt(request.getParameter("idTarefa"));

        String sql = "INSERT INTO pessoa_has_tarefa (idPessoa, idTarefa) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            stmt.setInt(2, idTarefa);
            stmt.executeUpdate();
            response.getWriter().println("Tarefa associada à pessoa com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao associar tarefa à pessoa.");
        }
    }

    // Método para remover uma tarefa de uma pessoa
    private void removeTarefaFromPessoa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idPessoa = Integer.parseInt(request.getParameter("idPessoa"));
        int idTarefa = Integer.parseInt(request.getParameter("idTarefa"));

        String sql = "DELETE FROM pessoa_has_tarefa WHERE idPessoa = ? AND idTarefa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            stmt.setInt(2, idTarefa);
            stmt.executeUpdate();
            response.getWriter().println("Tarefa removida da pessoa com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao remover tarefa da pessoa.");
        }
    }

    // Método para listar as tarefas associadas a uma pessoa
    private void listTarefasByPessoa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idPessoa = Integer.parseInt(request.getParameter("idPessoa"));
        String sql = "SELECT t.idTarefa, t.nometarefa, t.descTarefa FROM tarefa t INNER JOIN pessoa_has_tarefa pht ON t.idTarefa = pht.idTarefa WHERE pht.idPessoa = ?";

        List<Tarefa> tarefas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setIdTarefa(rs.getInt("idTarefa"));
                    tarefa.setNomeTarefa(rs.getString("nometarefa"));
                    tarefa.setDescTarefa(rs.getString("descTarefa"));
                    tarefas.add(tarefa);
                }

                // Exibição no response
                for (Tarefa t : tarefas) {
                    response.getWriter().println("ID Tarefa: " + t.getIdTarefa() + ", Nome: " + t.getNomeTarefa() + ", Descrição: " + t.getDescTarefa());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao listar tarefas da pessoa.");
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
