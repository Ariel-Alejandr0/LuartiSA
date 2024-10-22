package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;
import service.PessoaService;


@WebServlet("/createPessoa")
public class CreatePessoaServlet extends HttpServlet {

    private PessoaService pessoaService;

    @Override
    public void init() throws ServletException {
        this.pessoaService = new PessoaService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Captura os dados do formulário
        String nomeCompleto = request.getParameter("nomeCompleto");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String statusStr = request.getParameter("status");
        String papelStr = request.getParameter("papel");

        // Convertendo parâmetros para Enums
        Pessoa.Status status = Pessoa.Status.valueOf(statusStr);
        Pessoa.Papel papel = Pessoa.Papel.valueOf(papelStr);

        // Cria o objeto Pessoa
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNomeCompleto(nomeCompleto);
        novaPessoa.setEmail(email);
        novaPessoa.setSenha(senha);
        novaPessoa.setStatus(status);
        novaPessoa.setPapel(papel);

        // Salva a nova pessoa no banco de dados
        pessoaService.createPessoa(novaPessoa);

        // Redireciona para a lista de pessoas
        response.sendRedirect("listarPessoas");
    }
}
