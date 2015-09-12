package br.com.cris.client.rcp;

import java.util.List;

import br.com.cris.client.beans.Usuario;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UsuarioDAOAsync {

	void add(Usuario usuario, AsyncCallback<Void> callback);

	void del(Usuario usuario, AsyncCallback<Void> callback);

	void upd(Usuario usuario, AsyncCallback<Void> callback);

	void findById(int codigo, AsyncCallback<Usuario> callback);
	
	void findByLogin(String login, AsyncCallback<Usuario> callback);

	void list(AsyncCallback<List<Usuario>> callback);

}
