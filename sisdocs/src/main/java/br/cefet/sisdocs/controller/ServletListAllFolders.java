package br.cefet.sisdocs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ServletListAllFolders
 */
@WebServlet("/ServletListAllFolders")
public class ServletListAllFolders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListAllFolders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		
		List<Folder> folders = new ArrayList<Folder>();
		FolderDAO folderDAO = new FolderDAO();
		
		try {
			folders = folderDAO.Listar(cliente);	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(!folders.isEmpty()) {
			request.setAttribute("folderList", folders);
			request.setAttribute("msg",MessageFormat.format("Successfully displayed all folders of {0}",cliente.getLogin()));
		}else {
			request.setAttribute("msg", "Add a folder at 'ADD FOLDER' in order to display.");
		}

		request.setAttribute("location", cliente.getLogin());
		request.getRequestDispatcher("drive.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
