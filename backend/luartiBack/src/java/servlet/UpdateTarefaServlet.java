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

@WebServlet("/tarefa/update")
public class UpdateTarefaServlet extends HttpServlet {

    private TarefaService tarefaService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        this.tarefaService = new TarefaService(connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idTarefa = Integer.parseInt(req.getParameter("idTarefa"));
        String nomeTarefa = req.getParameter("nomeTarefa");
        String descTarefa = req.getParameter("descTarefa");
        String status = req.getParameter("status");
        int idTipoTarefa = Integer.parseInt(req.getParameter("idTipoTarefa"));

        Tarefa tarefa = new Tarefa();
        tarefa.setIdTarefa(idTarefa);
        tarefa.setNomeTarefa(nomeTarefa);
        tarefa.setDescTarefa(descTarefa);
        tarefa.setStatus(Tarefa.Status.valueOf(status.toUpperCase()));
        tarefa.setIdTipoTarefa(idTipoTarefa);

        try {
            tarefaService.updateTarefa(tarefa);
            resp.sendRedirect("/tarefas");
        } catch (SQLException e) {
            throw new ServletException("Erro ao atualizar tarefa", e);
        }
    }
}
