package servlet;

import service.TarefaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/tarefa/delete")
public class DeleteTarefaServlet extends HttpServlet {

    private TarefaService tarefaService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        this.tarefaService = new TarefaService(connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idTarefa = Integer.parseInt(req.getParameter("idTarefa"));

        try {
            tarefaService.deleteTarefa(idTarefa);
            resp.sendRedirect("/tarefas");
        } catch (SQLException e) {
            throw new ServletException("Erro ao excluir tarefa", e);
        }
    }
}
