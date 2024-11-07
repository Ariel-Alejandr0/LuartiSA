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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    // Log para verificar se a lista está vazia
    if (pessoas.isEmpty()) {
        System.out.println("Nenhuma pessoa encontrada.");
    } else {
        System.out.println("Pessoas encontradas: " + pessoas.size());
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa); // Verifique se o método toString() é chamado
        }
    }

    // Retorne a lista como JSON
    Gson gson = new Gson();
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    // Verifique se a lista não está vazia antes de converter para JSON
    if (!pessoas.isEmpty()) {
        String jsonResponse = gson.toJson(pessoas);
        response.getWriter().println(jsonResponse);
    } else {
        // Retorne um JSON vazio ou uma mensagem padrão se não houver pessoas
        response.getWriter().println(gson.toJson(new ArrayList<Pessoa>()));
    }
    }

private void getPessoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Pessoa pessoa = pessoaService.getPessoaById(id);

    // Converta o objeto Pessoa em JSON
    Gson gson = new Gson();
    String json = gson.toJson(pessoa);

    // Defina o tipo de resposta como JSON
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().println(json);
}


    private void deleteTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    Gson gson = new Gson();
    String json;

    try {
        pessoaHasTarefaService.deletarPessoaHasTarefaTarefa(id); // Deleta as associações da tarefa com pessoas
        
        Tarefa tarefa = tarefaService.deleteTarefa(id); // Deleta a tarefa principal
        
        // Se a tarefa foi deletada com sucesso
        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("message", "Tarefa Deletada Com Sucesso.");
        json = gson.toJson(jsonResponse);

    } catch (SQLException e) {
        // Caso de erro na exclusão, como tarefa não encontrada
        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("message", "Erro ao deletar a tarefa: " + e.getMessage());
        json = gson.toJson(jsonResponse);
    }

    response.getWriter().write(json); // Escreve o JSON no response
}

/*    
        private void deletePessoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Gson gson = new Gson();
        String json;
        
        try{
            pessoaHasTarefaService.deletarPessoaHasTarefaPessoa(id);
            
            Pessoa pessoa = pessoaService.deletePessoa(id);
            
            Map<String, String> jsonResponse = new HashMap<>();
            jsonResponse.put("message", "Pessoa Deletada com Sucesso.");
            json = gson.toJson(jsonResponse);
        }catch(SQLException e){
            Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("message", "Erro ao deletar a pessoa: " + e.getMessage());
        json = gson.toJson(jsonResponse);
        }
         response.getWriter().write(json); // Escreve o JSON no response
    }

*/

    private void addPessoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // Lê o JSON do corpo da requisição
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        
        // Converte o JSON para um objeto Pessoa
        Pessoa pessoa = gson.fromJson(reader, Pessoa.class);

        // Chama o serviço para criar a nova pessoa
        pessoaService.createPessoa(pessoa);

        // Define a resposta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"Pessoa adicionada com sucesso.\"}");
    }
    
        private void addTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            Tarefa tarefa = gson.fromJson(reader, Tarefa.class);
        // Setar os atributos de Tarefa a partir dos parâmetros da requisição
        tarefaService.createTarefa(tarefa);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"Tarefa Adicionada com sucesso\"}");
    }

        
    private void addTipoTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException { 
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            TipoTarefa tipoTarefa = gson.fromJson(reader, TipoTarefa.class);
        
        tipoTarefaService.adicionarTipoTarefa(tipoTarefa);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"Tipo de tarefa adicionado com sucesso\"}");
    }
    
    
    private void addPessoaHasTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
               BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            PessoaHasTarefa pessoaHasTarefa = gson.fromJson(reader, PessoaHasTarefa.class);
        // Setar os atributos de Tarefa a partir dos parâmetros da requisição
        pessoaHasTarefaService.adicionarPessoaHasTarefa(pessoaHasTarefa);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"Pessoa adicionada a uma tarefa com sucesso\"}");
    }

    private void updatePessoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Pessoa pessoa = new Pessoa();
        // Setar os atributos de Pessoa a partir dos parâmetros da requisição
        pessoaService.updatePessoa(pessoa);
        response.getWriter().println("Pessoa atualizada com sucesso.");
    }


    // Métodos para Tarefa
    private void listTarefa(HttpServletResponse response) throws SQLException, IOException {
    List<Tarefa> tarefas = tarefaService.getAllTarefas();

    // Log para verificar se a lista está vazia
    if (tarefas.isEmpty()) {
        System.out.println("Nenhuma tarefa encontrada.");
    } else {
        System.out.println("Tarefas encontradas: " + tarefas.size());
        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa); // Verifique se o método toString() é chamado
        }
    }
    // Retorne a lista como JSON
    Gson gson = new Gson();
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    // Verifique se a lista não está vazia antes de converter para JSON
    if (!tarefas.isEmpty()) {
        String jsonResponse = gson.toJson(tarefas);
        response.getWriter().println(jsonResponse);
    } else {
        // Retorne um JSON vazio ou uma mensagem padrão se não houver pessoas
        response.getWriter().println(gson.toJson(new ArrayList<Pessoa>()));
    }
    }
    
    // Métodos para PessoaHasTarefa
    private void listPessoaHasTarefa(HttpServletResponse response) throws SQLException, IOException {
        List<PessoaHasTarefa> pessoaHasTarefas = pessoaHasTarefaService.listarTodos();
        if(pessoaHasTarefas.isEmpty()){
            System.out.println("Nenhuma relação entre pessoas e tarefas encontrada");
        }else{
            System.out.println("Relações encontradas"+pessoaHasTarefas.size());
            for(PessoaHasTarefa pessoaHasTarefa : pessoaHasTarefas){
                System.out.println(pessoaHasTarefa);
            }
        }
        
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if(!pessoaHasTarefas.isEmpty()){
            String jsonResponse = gson.toJson(pessoaHasTarefas);
            response.getWriter().println(jsonResponse);
        }else{
            response.getWriter().println(gson.toJson(new ArrayList<>()));
        }
    }
    
        // Métodos para TipoTarefa
    private void listTipoTarefa(HttpServletResponse response) throws SQLException, IOException {
        List<TipoTarefa> tipoTarefas = tipoTarefaService.listarTodos();
        
        if(tipoTarefas.isEmpty()){
            System.out.println("Nenhum tipo de tarefa encontrado");
        }else{
            System.out.println("Tarefas encontradas"+tipoTarefas.size());
            for(TipoTarefa tipoTarefa : tipoTarefas){
                System.out.println(tipoTarefa);
            }
        }
        
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if(!tipoTarefas.isEmpty()){
            String jsonResponse = gson.toJson(tipoTarefas);
            response.getWriter().println(jsonResponse);
        }else{
            response.getWriter().println(gson.toJson(gson.toJson(new ArrayList<>())));
        }
    }


    private void getTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Tarefa tarefa = tarefaService.getTarefaById(id);

    // Converta o objeto Pessoa em JSON
    Gson gson = new Gson();
    String json = gson.toJson(tarefa);

    // Defina o tipo de resposta como JSON
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().println(json);
}

        private void getTipoTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        TipoTarefa tipoTarefa = tipoTarefaService.obterTipoTarefaPorId(id);
        
        Gson gson = new Gson();
        String json = gson.toJson(tipoTarefa);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(json);
    }
    


    private void updateTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Tarefa tarefa = new Tarefa();
        // Setar os atributos de Tarefa a partir dos parâmetros da requisição
        tarefaService.updateTarefa(tarefa);
        response.getWriter().println("Tarefa atualizada com sucesso.");
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
