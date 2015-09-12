package br.com.cris.client.beans;

import java.io.Serializable;
/**
 * Respresenta um usuario do sistema.
 * @author cris
 *
 */
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String login;
	private String nome;
	private String senha;
	private String email;
	
	public Usuario() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}