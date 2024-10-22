package servlet;

import model.Tarefa;
import service.TarefaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/tarefas")
public class ListarTarefasServlet extends HttpServlet {

    private TarefaService tarefaService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        this.tarefaService = new TarefaService(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Tarefa> tarefas = tarefaService.getAllTarefas();
            req.setAttribute("tarefas", tarefas);
            req.getRequestDispatcher("/tarefas.jsp").forward(req, resp); // Redireciona para uma JSP que mostra as tarefas
        } catch (SQLException e) {
            throw new ServletException("Erro ao listar tarefas", e);
        }
    }
}
