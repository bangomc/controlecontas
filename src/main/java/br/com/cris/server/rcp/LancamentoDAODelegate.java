package br.com.cris.server.rcp;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.cris.client.beans.Lancamento;
import br.com.cris.client.beans.Usuario;
import br.com.cris.client.rcp.LancamentoDAO;
import br.com.cris.server.ParametrosSistema;
import br.com.cris.server.ParametrosSistema.MODO;
import br.com.cris.server.dao.LancamentoDAOMockImpl;

public class LancamentoDAODelegate extends RemoteServiceServlet implements LancamentoDAO{
	
	private static final long serialVersionUID = -3705121607341878222L;
	private static LancamentoDAO dao;
	
	public LancamentoDAODelegate() {
		criarInstancia();
	}

	public void add(Lancamento lancamento) {
		dao.add(lancamento);
	}

	public void add(Lancamento lancamento, Integer parcelas) {
		dao.add(lancamento, parcelas);
	}

	public void del(Lancamento lancamento) {
		dao.del(lancamento);
	}

	public void upd(Lancamento lancamento) {
		dao.upd(lancamento);
	}

	public Lancamento findById(int codigo) {
		return dao.findById(codigo);
	}

	public List<Lancamento> list() {
		return dao.list();
	}

	public List<Lancamento> list(Usuario usuario, Date mes) {
		return dao.list(usuario, mes);
	}
	
	public static LancamentoDAO getInstancia(){
		criarInstancia();
		return dao;
	}
	
	private static void criarInstancia(){
		if(null==dao){
			if(ParametrosSistema.modo==MODO.TESTE){
				dao = new LancamentoDAOMockImpl();
			}
		}
	}
	

}
