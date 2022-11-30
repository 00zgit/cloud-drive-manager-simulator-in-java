package br.cefet.sisdocs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefet.sisdocs.dao.FileDAO;
import br.cefet.sisdocs.dao.FolderDAO;
import br.cefet.sisdocs.model.Cliente;
import br.cefet.sisdocs.model.File;

/**
 * Servlet implementation class ServletListFolder
 */
@WebServlet("/ServletListFolder")
public class ServletListFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListFolder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		
		int folderId = Integer.valueOf(request.getParameter("folderId"));
		
		List<File> files = new ArrayList<File>();
		FileDAO fileDAO = new FileDAO();
		
		try {
			files = fileDAO.Listar(folderId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		FolderDAO pastaDAO = new FolderDAO();
		String nomePasta = null;
		try {
			nomePasta = pastaDAO.ListarUm(folderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!files.isEmpty() && nomePasta != null) {
			request.setAttribute("files", files);
			request.setAttribute("msg", "Successfully displayed content of "+cliente.getLogin()+"/"+nomePasta);
		}
		else {
			request.setAttribute("msg", "[ERROR] Folder is empty or does not exists.");
		}
        
        
		request.setAttribute("location", cliente.getLogin()+"/"+nomePasta);
		request.getRequestDispatcher("drive.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
