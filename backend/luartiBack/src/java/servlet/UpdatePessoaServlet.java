package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;
import service.PessoaService;


@WebServlet("/updatePessoa")
public class UpdatePessoaServlet extends HttpServlet {

    private PessoaService pessoaService;

    @Override
    public void init() throws ServletException {
        this.pessoaService = new PessoaService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Captura os dados do formulário
        int idPessoa = Integer.parseInt(request.getParameter("idPessoa"));
        String nomeCompleto = request.getParameter("nomeCompleto");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String statusStr = request.getParameter("status");
        String papelStr = request.getParameter("papel");

        // Convertendo parâmetros para Enums
        Pessoa.Status status = Pessoa.Status.valueOf(statusStr);
        Pessoa.Papel papel = Pessoa.Papel.valueOf(papelStr);

        // Atualiza o objeto Pessoa
        Pessoa pessoaAtualizada = pessoaService.getPessoaById(idPessoa);
        pessoaAtualizada.setNomeCompleto(nomeCompleto);
        pessoaAtualizada.setEmail(email);
        pessoaAtualizada.setSenha(senha);
        pessoaAtualizada.setStatus(status);
        pessoaAtualizada.setPapel(papel);

        // Atualiza a pessoa no banco de dados
        pessoaService.updatePessoa(pessoaAtualizada);

        // Redireciona para a lista de pessoas
        response.sendRedirect("listarPessoas");
    }
}
