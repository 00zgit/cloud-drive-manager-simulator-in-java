package br.cefet.sisdocs.model;

public class File {
	public int id;
	public String nome;
	public String size;
	public int pastaid;
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPastaid() {
		return pastaid;
	}
	public void setPastaid(int pastaid) {
		this.pastaid = pastaid;
	}
	
}
