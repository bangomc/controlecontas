package br.com.cris.client.rcp;

import java.util.List;

import br.com.cris.client.beans.Usuario;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.smartgwt.client.util.SC;

@RemoteServiceRelativePath("UsuarioDAO")
public interface UsuarioDAO extends RemoteService {

	public static class Util {
		private static UsuarioDAOAsync instance;
		public static UsuarioDAOAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(UsuarioDAO.class);
			}
			return instance;
		}
		private static Usuario usuarioCorrente;
		public static Usuario getUsuarioCorrente(){
			if(null==usuarioCorrente){
				SC.say("Sem usuario na sessão");
			}
			return usuarioCorrente;
		}
		public static void setUsuarioCorrente(Usuario usuario){
			usuarioCorrente = usuario;
		}
	}
	
	public void add(Usuario usuario);
	public void del(Usuario usuario);
	public void upd(Usuario usuario);
	public Usuario findById(int codigo);
	public Usuario findByLogin(String login);
	public List<Usuario> list();
}
