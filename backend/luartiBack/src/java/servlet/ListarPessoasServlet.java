package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;
import service.PessoaService;


@WebServlet("/listarPessoas")
public class ListarPessoasServlet extends HttpServlet {

    private PessoaService pessoaService;

    @Override
    public void init() throws ServletException {
        this.pessoaService = new PessoaService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtém a lista de pessoas do banco de dados
        List<Pessoa> pessoas = pessoaService.getAllPessoas();

        // Adiciona a lista de pessoas ao request
        request.setAttribute("pessoas", pessoas);

        // Encaminha para a página JSP para exibir a lista
        RequestDispatcher dispatcher = request.getRequestDispatcher("listarPessoas.jsp");
        dispatcher.forward(request, response);
    }
}
