package br.com.cris.server.rcp;

import br.com.cris.client.beans.Usuario;
import br.com.cris.client.rcp.LoginService;
import br.com.cris.server.ParametrosSistema;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	private static final long serialVersionUID = -7634836529040271995L;

	@Override
	public Usuario logar(String usuario, String senha) {
		Usuario retorno = null;
		if("admin".equals(usuario) && senha.equals(ParametrosSistema.adminPWN)){
			retorno = new Usuario();
			retorno.setLogin("admin");
		}else{
			retorno = UsuarioDAODelegate.getInstance().findByLogin(usuario);
			if(null!=retorno){
				if(!senha.equals(retorno.getSenha())){
					retorno = null;
				}
			}	
		}
		return retorno;
	}
}
