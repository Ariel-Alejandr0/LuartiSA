package service;

import dao.PessoaDAO;
import java.util.List;
import model.Pessoa;



public class PessoaService {

    private PessoaDAO pessoaDAO = new PessoaDAO(); // Comunicação com banco de dados

    public void createPessoa(Pessoa pessoa) {
        pessoaDAO.save(pessoa);
    }

    public List<Pessoa> getAllPessoas() {
        return pessoaDAO.findAll();
    }

    public Pessoa getPessoaById(int id) {
        return pessoaDAO.findById(id);
    }

    public void updatePessoa(Pessoa pessoa) {
        pessoaDAO.update(pessoa);
    }

    public void deletePessoa(int id) {
        pessoaDAO.delete(id);
    }
}
