package servlet;

import service.PessoaHasTarefaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/pessoaHasTarefa/delete")
public class DeletePessoaHasTarefaServlet extends HttpServlet {

    private PessoaHasTarefaService pessoaHasTarefaService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        this.pessoaHasTarefaService = new PessoaHasTarefaService(connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idPessoa = Integer.parseInt(req.getParameter("idPessoa"));
        int idTarefa = Integer.parseInt(req.getParameter("idTarefa"));

        try {
            pessoaHasTarefaService.deletarPessoaHasTarefa(idPessoa, idTarefa);
            resp.sendRedirect("/pessoaHasTarefa/list");
        } catch (SQLException e) {
            throw new ServletException("Erro ao deletar relação Pessoa-Tarefa", e);
        }
    }
}
