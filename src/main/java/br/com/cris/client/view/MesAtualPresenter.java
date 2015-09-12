package br.com.cris.client.view;

import java.util.Date;
import java.util.List;

import br.com.cris.client.controlecontas;
import br.com.cris.client.beans.Lancamento;
import br.com.cris.client.beans.Usuario;
import br.com.cris.client.rcp.LancamentoDAO;
import br.com.cris.client.rcp.UsuarioDAO;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;

/**
 * Presentar para controlar a tela de lancamentos do mes atual.
 * @author cris
 *
 */
public class MesAtualPresenter {
	
	private MesAtualView view;
	
	public MesAtualPresenter() {
		this.view = new MesAtualView();
		
		this.view.getButtonIncluir().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				inc();				
			}
		});
		
		this.view.getButtonExcluir().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				del();				
			}
		});
		
		this.view.getButtonPagar().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				pagar();				
			}
		});
		
		this.view.getButtonLiberar().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				liberar();				
			}
		});
		
		this.view.getComboBoxMes().addChangeHandler(new ChangeHandler() {	
			
			@Override
			public void onChange(ChangeEvent event) {
				povoar();
			}
		});

		Date data = new Date();
		this.view.getComboBoxMes().setSelectedIndex(data.getMonth());
		this.povoar();
	}
	
	/**
	 * Exibe a tela de lancamento para inclusao.
	 */
	private void inc(){
		controlecontas.cadLancamento();
	}
	
	/**
	 * Apaga o lancamento selecionado.
	 * 
	 */
	private void del(){
		final Lancamento lancamento = this.view.getSelectionModel().getSelectedObject();
		if(null!=lancamento){
			if(lancamento.getStatus()==1){
				SC.say("O lancamento nao pode ser excluido, pois o mesmo esta quitado.\nFavor Liberar o lancamento antes");
				return;
			}
			String pergunta = "Deseja excluir o lancamento selecionado?";

			if(lancamento.isPai() || null!=lancamento.getLancamentoPai()){				
				pergunta = "Deseja exluir o lancamento selecionado e as outras parcelas?";
			}

			SC.ask(pergunta, new BooleanCallback() {

				@Override
				public void execute(Boolean value) {
					if(value){
						LancamentoDAO.Util.getInstance().del(lancamento, new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								povoar();
								SC.say("Exclusao efetuada com sucesso");
							}

							@Override
							public void onFailure(Throwable caught) {
								SC.say("Erro ao excluir");				
							}
						});
					}
				}
			});			
		}else{
			this.msgSelecionar();
		}

	}
	
	/**
	 * Muda o status de um lancamento para "PG"
	 */
	private void pagar(){
		Lancamento lancamento = this.view.getSelectionModel().getSelectedObject();
		if(null!=lancamento){
			lancamento.setStatus(1);
			lancamento.setPagamento(new Date());
			LancamentoDAO.Util.getInstance().upd(lancamento, new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					povoar();
				}
				
				@Override
				public void onFailure(Throwable caught) {
					SC.say("Erro ao pagar");				
				}
			});	
		}else{
			this.msgSelecionar();
		}
	}
	
	/**
	 * Muda o status de um lancamento para 0 - A pagar.
	 */
	private void liberar(){
		Lancamento lancamento = this.view.getSelectionModel().getSelectedObject();
		
		if(null!=lancamento){
			if(lancamento.getStatus()==0){
				SC.say("Nao e possivel liberar o lancamento, pois o mesmo ainda nao foi quitado");
				return;
			}
			
			lancamento.setStatus(0);
			LancamentoDAO.Util.getInstance().upd(lancamento, new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					povoar();
					SC.say("Lancamento liberado com sucesso");
				}
				
				@Override
				public void onFailure(Throwable caught) {
					SC.say("Erro ao liberar");				
				}
			});
		}else{
			this.msgSelecionar();
		}
	}

	public MesAtualView getView() {
		return view;
	}

	public void setView(MesAtualView view) {
		this.view = view;
	}
	
	/**
	 * Popula a lista de lancamentos para selecao
	 * @param lancamentos
	 */
	private void povoar(List<Lancamento> lancamentos){
		this.view.getCellTable().setRowData(lancamentos);
	}
	
	/**
	 * Busca no banco de dados lancamentos do usuario corrente cujo vencimento seja no mes selecionado.
	 */
	public void povoar(){
		Date data = new Date();
		data.setMonth(this.view.getComboBoxMes().getSelectedIndex());
				
		Usuario usuario = UsuarioDAO.Util.getUsuarioCorrente();
		LancamentoDAO.Util.getInstance().list(usuario, data, new AsyncCallback<List<Lancamento>>() {
			
			@Override
			public void onSuccess(List<Lancamento> lancamentos) {
				povoar(lancamentos);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				SC.say("Erro retornando lancamentos");
			}
			
		});
	}
	
	/**
	 * Exibe mensagem default para exigir que o usuario selecione um lancamento.
	 */
	private void msgSelecionar(){
		SC.say("Nenhum lancamento foi selecionado!\nFavor selecionar o registro desejado");
	}

}
