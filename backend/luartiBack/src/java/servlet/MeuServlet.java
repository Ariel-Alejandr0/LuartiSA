package servlet;

import model.Pessoa;
import model.Tarefa;
import model.TipoTarefa;
import model.PessoaHasTarefa;
import service.PessoaService;
import service.TarefaService;
import service.TipoTarefaService;
import service.PessoaHasTarefaService;
import com.google.gson.Gson;
import java.io.BufferedReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import utils.Conexao;

public class MeuServlet extends HttpServlet {

    private PessoaService pessoaService;
    private TarefaService tarefaService;
    private TipoTarefaService tipoTarefaService;
    private PessoaHasTarefaService pessoaHasTarefaService;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            connection = Conexao.conectar();
            pessoaService = new PessoaService(connection);
            tarefaService = new TarefaService(connection);
            tipoTarefaService = new TipoTarefaService(connection);
            pessoaHasTarefaService = new PessoaHasTarefaService(connection);
        } catch (ClassNotFoundException e) {
            throw new ServletException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.getWriter().write("Ação não especificada.");
            return; // Retorna se a ação for nula
        }

        try {
            switch (action) {
                case "listPessoa":
                    listPessoa(response);
                    break;
                case "getPessoa":
                    getPessoa(request, response);
                    break;
                case "listTarefa":
                    listTarefa(response);
                    break;
                case "getTarefa":
                    // Verifique se o ID está presente
                    if (request.getParameter("id") == null) {
                        response.getWriter().write("ID da tarefa não especificado.");
                        return;
                    }
                    getTarefa(request, response);
                    break;
                case "listTipoTarefa":
                    listTipoTarefa(response);
                    break;
                case "getTipoTarefa":
                    // Verifique se o ID está presente
                    if (request.getParameter("id") == null) {
                        response.getWriter().write("ID do tipo de tarefa não especificado.");
                        return;
                    }
                    getTipoTarefa(request, response);
                    break;
                case "listPessoaHasTarefa":
                    listPessoaHasTarefa(response);
                    break;
                default:
                    response.getWriter().write("Ação desconhecida.");
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao processar a solicitação GET", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {

                case "login":
                    autenticar(request, response);
                    break;
                case "addPessoa":
                    addPessoa(request, response);
                    break;
                case "updatePessoa":
                    updatePessoa(request, response);
                    break;
                case "deletePessoa":
                    deletePessoa(request, response);
                    break;
                case "addTarefa":
                    addTarefa(request, response);
                    break;
                case "updateTarefa":
                    updateTarefa(request, response);
                    break;
                case "deleteTarefa":
                    deleteTarefa(request, response);
                    break;
                case "addTipoTarefa":
                    addTipoTarefa(request, response);
                    break;
                case "updateTipoTarefa":
                    updateTipoTarefa(request, response);
                    break;
                case "deleteTipoTarefa":
                    deleteTipoTarefa(request, response);
                    break;
                case "addPessoaHasTarefa":
                    addPessoaHasTarefa(request, response);
                    break;
                case "deletePessoaHasTarefa":
                    deletePessoaHasTarefa(request, response);
                    break;

                default:
                    response.getWriter().write("Ação desconhecida.");
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao processar a solicitação POST", e);
        }
    }

    private void autenticar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // Cria um objeto Gson para converter JSON para objeto Java
        Gson gson = new Gson();

        // Define o tipo de resposta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Lê o corpo da requisição JSON
        BufferedReader reader = request.getReader();
        Pessoa p = gson.fromJson(reader, Pessoa.class);

        // Extrai email e senha do objeto Pessoa
        String email = p.getEmail();
        String senha = p.getSenha();

        // Verifica se as credenciais são válidas
        Pessoa pessoa = pessoaService.autenticar(email, senha);

        if (pessoa != null) {
            // Login bem-sucedido, converte o objeto Pessoa para JSON e envia na resposta
            String jsonResponse = gson.toJson(pessoa);
            response.getWriter().write(jsonResponse);
        } else {
            // Login falhou, retorna uma mensagem de erro em JSON
            response.getWriter().write("{\"error\": \"Email ou senha incorretos.\"}");
        }
    }

    // Métodos para Pessoa
    private void listPessoa(HttpServletResponse response) throws SQLException, IOException {
        List<Pessoa> pessoas = pessoaService.getAllPessoas();
        response.getWriter().println(pessoas);
    }

    private void getPessoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Pessoa pessoa = pessoaService.getPessoaById(id);
        response.getWriter().println(pessoa);
    }

    private void addPessoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Pessoa pessoa = new Pessoa();
        // Setar os atributos de Pessoa a partir dos parâmetros da requisição
        pessoaService.createPessoa(pessoa);
        response.getWriter().println("Pessoa adicionada com sucesso.");
    }

    private void updatePessoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Pessoa pessoa = new Pessoa();
        // Setar os atributos de Pessoa a partir dos parâmetros da requisição
        pessoaService.updatePessoa(pessoa);
        response.getWriter().println("Pessoa atualizada com sucesso.");
    }

    private void deletePessoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        pessoaService.deletePessoa(id);
        response.getWriter().println("Pessoa deletada com sucesso.");
    }

    // Métodos para Tarefa
    private void listTarefa(HttpServletResponse response) throws SQLException, IOException {
        List<Tarefa> tarefas = tarefaService.getAllTarefas();
        response.getWriter().println(tarefas);
    }

    private void getTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tarefa tarefa = tarefaService.getTarefaById(id);
        response.getWriter().println(tarefa);
    }

    private void addTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Tarefa tarefa = new Tarefa();
        // Setar os atributos de Tarefa a partir dos parâmetros da requisição
        tarefaService.createTarefa(tarefa);
        response.getWriter().println("Tarefa adicionada com sucesso.");
    }

    private void updateTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Tarefa tarefa = new Tarefa();
        // Setar os atributos de Tarefa a partir dos parâmetros da requisição
        tarefaService.updateTarefa(tarefa);
        response.getWriter().println("Tarefa atualizada com sucesso.");
    }

    private void deleteTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tarefaService.deleteTarefa(id);
        response.getWriter().println("Tarefa deletada com sucesso.");
    }

    // Métodos para TipoTarefa
    private void listTipoTarefa(HttpServletResponse response) throws SQLException, IOException {
        List<TipoTarefa> tipoTarefas = tipoTarefaService.listarTodos();
        response.getWriter().println(tipoTarefas);
    }

    private void getTipoTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        TipoTarefa tipoTarefa = tipoTarefaService.obterTipoTarefaPorId(id);
        response.getWriter().println(tipoTarefa);
    }

    private void addTipoTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        TipoTarefa tipoTarefa = new TipoTarefa();
        // Setar os atributos de TipoTarefa a partir dos parâmetros da requisição
        tipoTarefaService.adicionarTipoTarefa(tipoTarefa);
        response.getWriter().println("Tipo de Tarefa adicionado com sucesso.");
    }

    private void updateTipoTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        TipoTarefa tipoTarefa = new TipoTarefa();
        // Setar os atributos de TipoTarefa a partir dos parâmetros da requisição
        tipoTarefaService.atualizarTipoTarefa(tipoTarefa);
        response.getWriter().println("Tipo de Tarefa atualizado com sucesso.");
    }

    private void deleteTipoTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tipoTarefaService.deletarTipoTarefa(id);
        response.getWriter().println("Tipo de Tarefa deletado com sucesso.");
    }

    // Métodos para PessoaHasTarefa
    private void listPessoaHasTarefa(HttpServletResponse response) throws SQLException, IOException {
        List<PessoaHasTarefa> pessoaHasTarefas = pessoaHasTarefaService.listarTodos();
        response.getWriter().println(pessoaHasTarefas);
    }

    private void addPessoaHasTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        PessoaHasTarefa pessoaHasTarefa = new PessoaHasTarefa();
        // Setar os atributos de PessoaHasTarefa a partir dos parâmetros da requisição
        pessoaHasTarefaService.adicionarPessoaHasTarefa(pessoaHasTarefa);
        response.getWriter().println("Pessoa e Tarefa associadas com sucesso.");
    }

    private void deletePessoaHasTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idPessoa = Integer.parseInt(request.getParameter("idPessoa"));
        int idTarefa = Integer.parseInt(request.getParameter("idTarefa"));
        pessoaHasTarefaService.deletarPessoaHasTarefa(idPessoa, idTarefa);
        response.getWriter().println("Associação entre Pessoa e Tarefa deletada com sucesso.");
    }

    @Override
    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
