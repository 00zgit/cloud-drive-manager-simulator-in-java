package br.cefet.sisdocs.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefet.sisdocs.dao.FileDAO;

/**
 * Servlet implementation class ServletDeleteFile
 */
@WebServlet("/ServletDeleteFile")
public class ServletDeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeleteFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String name = request.getParameter("fileToDelete");
        
        if(name == null) {
        	request.setAttribute("msg", "[ERROR] Please select a file to delete.");
        }else {
        	FileDAO fileDAO = new FileDAO();
			try {
				fileDAO.Delete(name);
				request.setAttribute("msg", "[SUCCESS] Successfully deleted file "+name);
			}catch(SQLException e) {
				e.printStackTrace();
				request.setAttribute("msg", "[ERROR] An error ocurred");				
			}
        }
		
		
		
		request.getRequestDispatcher("drive.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
