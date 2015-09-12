package br.com.cris.server.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.cris.client.beans.Usuario;
import br.com.cris.client.rcp.UsuarioDAO;

/**
 * Classe para teste do sistema sem acesso ao banco de dados.
 * @author cris
 *
 */
public class UsuarioDAOMockImpl implements UsuarioDAO {
	
	private List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	public UsuarioDAOMockImpl() {
		Usuario u1 = new Usuario();
		u1.setCodigo(1);
		u1.setLogin("cris");
		u1.setNome("Cristhiano");
		u1.setSenha("cris");
		
		Usuario u2 = new Usuario();
		u2.setCodigo(2);
		u2.setLogin("polly");
		u2.setNome("Pollyanna");
		u2.setSenha("teste");
		
		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
	}

	@Override
	public void add(Usuario usuario) {
		this.listaUsuarios.add(usuario);
	}

	@Override
	public void del(Usuario usuario) {
		this.listaUsuarios.remove(usuario);
	}

	@Override
	public void upd(Usuario usuario) {
		Usuario remover = this.findById(usuario.getCodigo());
		this.del(remover);
		this.add(usuario);
	}

	@Override
	public Usuario findById(int codigo) {
		Usuario retorno = null;
		for(Usuario usuario:listaUsuarios){
			if(usuario.getCodigo()==codigo){
				retorno = usuario;
				break;
			}
		}
		return retorno;
	}

	@Override
	public Usuario findByLogin(String login) {
		Usuario retorno = null;
		for(Usuario usuario:listaUsuarios){
			if(usuario.getLogin().equals(login)){
				retorno = usuario;
				break;
			}
		}
		return retorno;
	}

	@Override
	public List<Usuario> list() {
		return this.listaUsuarios;
	}
		
}
