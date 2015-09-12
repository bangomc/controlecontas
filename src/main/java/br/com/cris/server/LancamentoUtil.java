package br.com.cris.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cris.client.beans.Lancamento;

public class LancamentoUtil {
		
	/**
	 * Cria uma quantidade de lancamentos iguais ao informado
	 * @param lancamentoOrigem
	 * @return
	 */
	public static List<Lancamento> criarParcelas(Lancamento lancamentoOrigem, Integer parcelas){
		List<Lancamento> retorno = new ArrayList<Lancamento>();
		Date vencimento = lancamentoOrigem.getVencimento();
		lancamentoOrigem.setPai(true);
		int mes = vencimento.getMonth();
		String descricao = lancamentoOrigem.getDescricao();
		
		lancamentoOrigem.setDescricao(descricao + " (1/"+parcelas+")");
		
		int qtd = parcelas - 1;
		int parcela = 1;
		
		for (int i = 0; i < qtd; i++) {
			Date novoVencimento = new Date(vencimento.getTime());
			novoVencimento.setMonth(++mes);
			
			Lancamento novo = new Lancamento();
			novo.setVencimento(novoVencimento);
			novo.setValor(lancamentoOrigem.getValor());
			novo.setDescricao(descricao + " ("+(++parcela)+"/"+parcelas+")");
			novo.setUsuario(lancamentoOrigem.getUsuario());
			novo.setLancamentoPai(lancamentoOrigem);
			
			retorno.add(novo);
		}
		
		return retorno;
	}

}
