package br.com.cris.client.rcp;


import br.com.cris.client.beans.Usuario;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("LoginService")
public interface LoginService extends RemoteService {

	public static class Util {
		private static LoginServiceAsync instance;
		public static LoginServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(LoginService.class);
			}
			return instance;
		}
	}
	
	public Usuario logar(String usuario, String senha);
}
