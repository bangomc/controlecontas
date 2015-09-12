package br.com.cris.client.rcp;

import java.util.Date;
import java.util.List;

import br.com.cris.client.beans.Lancamento;
import br.com.cris.client.beans.Usuario;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("LancamentoDAO")
public interface LancamentoDAO extends RemoteService {
	
	public static class Util {
		private static LancamentoDAOAsync instance;
		public static LancamentoDAOAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(LancamentoDAO.class);
			}
			return instance;
		}
	}
	
	public void add(Lancamento lancamento);
	public void add(Lancamento lancamento, Integer parcelas);
	public void del(Lancamento lancamento);
	public void upd(Lancamento lancamento);
	public Lancamento findById(int codigo);
	public List<Lancamento> list();
	public List<Lancamento> list(Usuario usuario, Date mes);
}
