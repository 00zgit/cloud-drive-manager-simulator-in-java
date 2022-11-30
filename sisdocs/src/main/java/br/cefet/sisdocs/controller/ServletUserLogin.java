package br.cefet.sisdocs.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefet.sisdocs.dao.ClienteDAO;
import br.cefet.sisdocs.model.Cliente;

/**
 * Servlet implementation class ServletUserLogin
 */
@WebServlet("/ServletUserLogin")
public class ServletUserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/// Extrair a variável do formulário
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		//Criar cliente
		Cliente cliente = null;
		
		// Verificar login do cliente
		ClienteDAO cltDao = new ClienteDAO();
		try {
			cliente = cltDao.Logar(login,senha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String nextPage = null;
		
		if (cliente!= null) {
			nextPage = "drive.jsp";
			request.setAttribute("location", login);
			
			// Pegando a session e pendurando um cliente
			HttpSession session = request.getSession();
			session.setAttribute("cliente", cliente);
			
			// Criando um cookie
			String name = cliente.getLogin();
			String value = null;
			Cookie cookie = new Cookie(name,value);
			cookie.setDomain("localhost");
			cookie.setPath("/drive.jsp");
			response.addCookie(cookie);
			
		}
		else {
			nextPage ="index.jsp";
			request.setAttribute("msg", "Login inválido");
		}
		
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
