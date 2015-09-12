package br.com.cris.client.rcp;

import java.util.Date;
import java.util.List;

import br.com.cris.client.beans.Lancamento;
import br.com.cris.client.beans.Usuario;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LancamentoDAOAsync {

	void add(Lancamento lancamento, AsyncCallback<Void> callback);

	void del(Lancamento lancamento, AsyncCallback<Void> callback);

	void findById(int codigo, AsyncCallback<Lancamento> callback);

	void list(AsyncCallback<List<Lancamento>> callback);

	void upd(Lancamento lancamento, AsyncCallback<Void> callback);

	void list(Usuario usuario, Date mes, AsyncCallback<List<Lancamento>> callback);

	void add(Lancamento lancamento, Integer parcelas, AsyncCallback<Void> callback);

}
