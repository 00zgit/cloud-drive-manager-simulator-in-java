package br.cefet.sisdocs.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefet.sisdocs.dao.ClienteDAO;
import br.cefet.sisdocs.model.Cliente;

/**
 * Servlet implementation class ServletCreateUser
 */
@WebServlet("/ServletCreateUser")
public class ServletCreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setLogin(login);
		cliente.setSenha(senha);
		
		// e finalmente criando o cliente com os dados da pasta root...
		ClienteDAO clienteDAO = new ClienteDAO();
		try {
			clienteDAO.Adicionar(cliente);
			request.setAttribute("msg", "Conta criada com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro ao criar conta");
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
