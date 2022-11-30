package br.cefet.sisdocs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefet.sisdocs.model.File;

public class FileDAO {

	public void Adicionar(File file,int folderId) throws SQLException {
		Connection con = (Connection) ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO arquivo(nome,size,pastaid) VALUES (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, file.getNome());
		stmt.setString(2, file.getSize());
		stmt.setInt(3, folderId);

		stmt.execute();
		stmt.close();
		con.close();
	}

	public List<File> Listar(int folderId) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		
		String sql = "SELECT * FROM arquivo a "
				+ "JOIN pasta p "
				+ "ON (a.pastaid=p.id) "
				+ "JOIN cliente c "
				+ "ON (c.id=p.clienteid) "
				+ "WHERE p.id = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, folderId);
		
		ResultSet rs = stmt.executeQuery();

		File file = null;
		List<File> files = new ArrayList<File>();

		while (rs.next()) {
			file = new File();
			file.setId(rs.getInt("a.id"));
			file.setSize(rs.getString("a.size"));
			file.setNome(rs.getString("a.nome"));
			file.setPastaid(rs.getInt("a.pastaid"));
			files.add(file);
		}
		stmt.close();
		con.close();

		return files;
	}

	public boolean Delete(String name) throws SQLException {
		Connection con = (Connection) ConnectionFactory.getConnection();

		Boolean result = true;
		
		String sql = "DELETE FROM arquivo WHERE nome = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1,name);

		try {
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		stmt.close();
		con.close();

		return result;
	}
}
