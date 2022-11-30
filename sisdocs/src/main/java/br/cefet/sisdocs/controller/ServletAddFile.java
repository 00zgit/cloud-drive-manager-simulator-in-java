package br.cefet.sisdocs.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.servlet.http.Part;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefet.sisdocs.dao.FileDAO;
import br.cefet.sisdocs.model.File;
import br.cefet.sisdocs.model.Globals;

/**
 * Servlet implementation class ServletAddFile
 */

@WebServlet("/ServletAddFile")
@MultipartConfig(fileSizeThreshold = Globals.mb * 1, maxFileSize = Globals.mb * 10, maxRequestSize = Globals.mb * 15)
public class ServletAddFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletAddFile() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int folderId = Integer.valueOf(request.getParameter("folderId"));
		
		File file = new File();
		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		
		long filesize = filePart.getSize();
		String size = Globals.CalcSize(filesize);
		file.setSize(size);
		file.setPastaid(folderId);
		
		if (filePart.getSubmittedFileName() == "") {
			request.setAttribute("msg", "Please select a file");
			request.getRequestDispatcher("drive.jsp").forward(request, response);
		} else {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			file.setNome(fileName);
			
			FileDAO fileDAO = new FileDAO();
			try {
				fileDAO.Adicionar(file,folderId);
				request.setAttribute("msg", "[SUCCESS] File uploaded");
			}catch(SQLException e) {
				e.printStackTrace();
				request.setAttribute("msg", "[ERROR] console stack trace");				
			}
			
			
			
			request.getRequestDispatcher("drive.jsp").forward(request, response);
		}

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
