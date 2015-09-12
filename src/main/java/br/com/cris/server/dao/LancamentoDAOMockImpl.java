package br.com.cris.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cris.client.beans.Lancamento;
import br.com.cris.client.beans.Usuario;
import br.com.cris.client.rcp.LancamentoDAO;
import br.com.cris.server.LancamentoUtil;

/**
 * Classe de teste do sistema sem acesso ao banco de dados.
 * @author cris
 *
 */
public class LancamentoDAOMockImpl implements LancamentoDAO{

	private List<Lancamento> listaLancamentos = new ArrayList<Lancamento>();
			
	@Override
	public void add(Lancamento lancamento) {
		lancamento.setCodigo(listaLancamentos.size()+1);
		this.listaLancamentos.add(lancamento);
	}

	@Override
	public void add(Lancamento lancamento, Integer parcelas) {
		if(null!=parcelas&&parcelas>1){			
			List<Lancamento> lista = LancamentoUtil.criarParcelas(lancamento, parcelas);
			for (Lancamento lancamento2 : lista) {
				this.add(lancamento2);
			}
		}else{
			this.add(lancamento);
		}
	}

	@Override
	public void del(Lancamento lancamento) {
		Lancamento remover = this.findById(lancamento.getCodigo());
		this.listaLancamentos.remove(remover);
	}

	@Override
	public void upd(Lancamento lancamento) {
		Lancamento remover = this.findById(lancamento.getCodigo());
		this.del(remover);
		this.add(lancamento);
	}

	@Override
	public Lancamento findById(int codigo) {
		Lancamento retorno = null;
		for(Lancamento lancamento:this.listaLancamentos){
			if(lancamento.getCodigo()==codigo){
				retorno = lancamento;
				break;
			}
		}
		return retorno;
	}

	@Override
	public List<Lancamento> list() {
		return this.listaLancamentos;
	}

	@Override
	public List<Lancamento> list(Usuario usuario, Date mes) {
		List<Lancamento> listaMensal = new ArrayList<Lancamento>();
		for(Lancamento lancamento:this.listaLancamentos){
			if(lancamento.getVencimento().getMonth()==mes.getMonth()){
				listaMensal.add(lancamento);
			}
		}
		return listaMensal;
	}

}
