package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.PessoaService;



@WebServlet("/deletePessoa")
public class DeletePessoaServlet extends HttpServlet {

    private PessoaService pessoaService;

    @Override
    public void init() throws ServletException {
        this.pessoaService = new PessoaService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Captura o id da pessoa a ser excluída
        int idPessoa = Integer.parseInt(request.getParameter("idPessoa"));

        // Exclui a pessoa
        pessoaService.deletePessoa(idPessoa);

        // Redireciona para a lista de pessoas
        response.sendRedirect("listarPessoas");
    }
}
