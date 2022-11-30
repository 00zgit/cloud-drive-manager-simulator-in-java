package br.cefet.sisdocs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefet.sisdocs.model.Cliente;
import br.cefet.sisdocs.model.Folder;

public class FolderDAO {
	public void Adicionar(Folder folder, Cliente cliente) throws SQLException {

		Connection con = ConnectionFactory.getConnection();

		String sql = "INSERT INTO pasta(nome,clienteid) VALUES(?,?)";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, folder.getNome());
		stmt.setInt(2, cliente.getId());

		stmt.execute();
		stmt.close();
		con.close();
	}

	public List<Folder> Listar(Cliente cliente) throws SQLException {
		Connection con = ConnectionFactory.getConnection();

		String sql = "SELECT * FROM pasta WHERE clienteid = ?";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setInt(1, cliente.getId());

		ResultSet rs = stmt.executeQuery();

		Folder folder = null;
		List<Folder> folders = new ArrayList<Folder>();

		while (rs.next()) {
			folder = new Folder();
			folder.setId(rs.getInt("id"));
			folder.setNome(rs.getString("nome"));
			folder.setClienteid(rs.getInt("clienteid"));
			folders.add(folder);
		}
		stmt.close();
		con.close();

		return folders;
	}

	public String ListarUm(int folderId) throws SQLException {

		Connection con = ConnectionFactory.getConnection();

		String sql = "SELECT nome FROM pasta WHERE id = (?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, folderId);

		ResultSet rs = stmt.executeQuery();

		String res = null;

		if (rs.next()) {
			res = rs.getString("nome");
		}

		stmt.close();
		con.close();
		return res;
	}
}
