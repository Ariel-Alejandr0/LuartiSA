package servlet;

import model.TipoTarefa;
import service.TipoTarefaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/tipoTarefa/list")
public class ListarTipoTarefaServlet extends HttpServlet {

    private TipoTarefaService tipoTarefaService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        this.tipoTarefaService = new TipoTarefaService(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<TipoTarefa> tipoTarefas = tipoTarefaService.listarTodos();
            req.setAttribute("tipoTarefas", tipoTarefas);
            req.getRequestDispatcher("/tipoTarefas.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Erro ao listar TipoTarefas", e);
        }
    }
}
