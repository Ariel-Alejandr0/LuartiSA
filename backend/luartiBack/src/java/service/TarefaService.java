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

    // Criar uma nova tarefa
    public void createTarefa(Tarefa tarefa) throws SQLException {
        tarefaDAO.createTarefa(tarefa); // Atualizado para usar o método correto do DAO
    }

    // Obter todas as tarefas
    public List<Tarefa> getAllTarefas() throws SQLException {
        return tarefaDAO.getAllTarefas(); // Atualizado para usar o método correto do DAO
    }

    // Atualizar uma tarefa existente
    public void updateTarefa(Tarefa tarefa) throws SQLException {
        tarefaDAO.updateTarefa(tarefa); // Atualizado para usar o método correto do DAO
    }

    // Excluir uma tarefa
    public Tarefa deleteTarefa(int id) throws SQLException {
    // Primeiro, buscamos a tarefa a ser deletada
    Tarefa tarefa = tarefaDAO.findById(id); // Método para buscar a tarefa pelo ID
    
    if (tarefa != null) {
        tarefaDAO.deleteTarefa(id); // Em seguida, excluímos a tarefa
    }
    
    // Retornamos a tarefa (ou null, se ela não foi encontrada)
    return tarefa;
}

    // Encontrar uma tarefa a partit do id
     public Tarefa getTarefaById(int id) {
        return tarefaDAO.findById(id);
    }
}
