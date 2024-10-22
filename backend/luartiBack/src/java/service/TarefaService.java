package service;

import dao.TarefaDAO;
import model.Tarefa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TarefaService {

    private TarefaDAO tarefaDAO;

    public TarefaService(Connection connection) {
        this.tarefaDAO = new TarefaDAO(connection);
    }

    public void createTarefa(Tarefa tarefa) throws SQLException {
        tarefaDAO.createTarefa(tarefa);
    }

    public List<Tarefa> getAllTarefas() throws SQLException {
        return tarefaDAO.getAllTarefa();
    }

    public void updateTarefa(Tarefa tarefa) throws SQLException {
        tarefaDAO.updateTarefa(tarefa);
    }

    public void deleteTarefa(int id) throws SQLException {
        tarefaDAO.deleteTarefa(id);
    }
}
