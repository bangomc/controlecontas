package br.com.cris.client.rcp;


import br.com.cris.client.beans.Usuario;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void logar(String usuario, String senha, AsyncCallback<Usuario> callback);

}
