package br.com.cris.server.rcp;

import java.util.List;

import br.com.cris.client.beans.Usuario;
import br.com.cris.client.rcp.UsuarioDAO;
import br.com.cris.server.ParametrosSistema;
import br.com.cris.server.ParametrosSistema.MODO;
import br.com.cris.server.dao.UsuarioDAOMockImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UsuarioDAODelegate extends RemoteServiceServlet implements UsuarioDAO {

	private static final long serialVersionUID = 3535898953052127616L;	
	private static UsuarioDAO dao;
	
	public UsuarioDAODelegate(){
		criarInstancia();
	}

	public void add(Usuario usuario) {
		dao.add(usuario);
	}

	public void del(Usuario usuario) {
		dao.del(usuario);
	}

	public void upd(Usuario usuario) {
		dao.upd(usuario);
	}

	public Usuario findById(int codigo) {
		return dao.findById(codigo);
	}

	public Usuario findByLogin(String login) {
		return dao.findByLogin(login);
	}

	public List<Usuario> list() {
		return dao.list();
	}
	
	public static UsuarioDAO getInstance(){
		criarInstancia();
		return dao;
	}
	
	private static void criarInstancia(){
		if(null==dao){
			if(ParametrosSistema.modo==MODO.TESTE){
				dao = new UsuarioDAOMockImpl();
			}
		}
	}

}
