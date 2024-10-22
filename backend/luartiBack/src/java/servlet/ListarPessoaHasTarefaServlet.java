package servlet;

import model.PessoaHasTarefa;
import service.PessoaHasTarefaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/pessoaHasTarefa/list")
public class ListarPessoaHasTarefaServlet extends HttpServlet {

    private PessoaHasTarefaService pessoaHasTarefaService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        this.pessoaHasTarefaService = new PessoaHasTarefaService(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<PessoaHasTarefa> relacoes = pessoaHasTarefaService.listarTodos();
            req.setAttribute("relacoes", relacoes);
            req.getRequestDispatcher("/pessoaHasTarefaList.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Erro ao listar relações Pessoa-Tarefa", e);
        }
    }
}
