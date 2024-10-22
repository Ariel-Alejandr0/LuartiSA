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

@WebServlet("/tipoTarefa/update")
public class UpdateTipoTarefaServlet extends HttpServlet {

    private TipoTarefaService tipoTarefaService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        this.tipoTarefaService = new TipoTarefaService(connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idTipoTarefa = Integer.parseInt(req.getParameter("idTipoTarefa"));
        String descTipotarefa = req.getParameter("descTipotarefa");

        TipoTarefa tipoTarefa = new TipoTarefa();
        tipoTarefa.setIdTipoTarefa(idTipoTarefa);
        tipoTarefa.setDescTipotarefa(descTipotarefa);

        try {
            tipoTarefaService.atualizarTipoTarefa(tipoTarefa);
            resp.sendRedirect("/tipoTarefa/list");
        } catch (SQLException e) {
            throw new ServletException("Erro ao atualizar TipoTarefa", e);
        }
    }
}