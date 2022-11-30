package br.cefet.sisdocs.controller;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefet.sisdocs.dao.FolderDAO;
import br.cefet.sisdocs.model.Cliente;
import br.cefet.sisdocs.model.Folder;

/**
 * Servlet implementation class ServletCreateDrive
 */
@WebServlet("/ServletAddFolder")
public class ServletAddFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAddFolder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomePasta = request.getParameter("folderName");

		Folder folder = new Folder();
		folder.setNome(nomePasta);

		HttpSession session = request.getSession();
		Cliente cliente = (Cliente) session.getAttribute("cliente");

		FolderDAO pastaDAO = new FolderDAO();
		String msg = null;
		try {
			pastaDAO.Adicionar(folder,cliente);
			msg = MessageFormat.format("[SUCCESS] Successfully added folder {0} at {1}/{2}", nomePasta,cliente.getLogin(),folder.getNome());
			
		} catch (Exception e) {
			e.printStackTrace();
			msg = "[ERROR] Could not add specified folder.";
		}

		request.setAttribute("msg",msg);
		request.getRequestDispatcher("drive.jsp").forward(request, response);
	}
}
