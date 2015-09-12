package br.com.cris.client.view;

import java.util.Date;

import br.com.cris.client.controlecontas;
import br.com.cris.client.beans.Lancamento;
import br.com.cris.client.rcp.LancamentoDAO;
import br.com.cris.client.rcp.UsuarioDAO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
/**
 * Presenter para controlar a tela de Lancamentos
 * @author cris
 *
 */
public class LancamentoPresenter {
	
	private LancamentoView view;
	
	public LancamentoPresenter() {
		this.view = new LancamentoView();
		
		this.view.getButtonGravar().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				grv();
			}
		});
		
		this.view.getButtonIncluir().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				inc();
			}
		});
		
		this.view.getButtonCancelar().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				can();
			}
		});
		
	}

	public LancamentoView getView() {
		return view;
	}

	public void setView(LancamentoView view) {
		this.view = view;
	}
	
	/**
	 * Persiste no banco o novo lancamento
	 */
	private void grv(){
		if(this.view.getDynamicForm().validate()){
			DynamicForm dynamicForm = this.view.getDynamicForm();
			DateItem dateItem = (DateItem) dynamicForm.getField("txtVencimento");
			Date vencimento = dateItem.getValueAsDate();
			String descricao = (String) dynamicForm.getField("txtDescricao").getValue();
			FloatItem floatItem = (FloatItem) dynamicForm.getField("txtValor");
						
			float valor = 0;
			if(null!=floatItem.getValue()){
				valor = floatItem.getValueAsFloat();
			}
			
			IntegerItem integerItem = (IntegerItem) dynamicForm.getField("txtParcelas");
			
			Integer parcelas = 0;
			if(null!=integerItem.getValue()){
				parcelas = integerItem.getValueAsInteger();	
			}

			Lancamento lancamento = new Lancamento();
			lancamento.setVencimento(vencimento);
			lancamento.setDescricao(descricao);
			lancamento.setValor(valor);
			lancamento.setUsuario(UsuarioDAO.Util.getUsuarioCorrente());
			
			habilitarComponents(false);
			
			LancamentoDAO.Util.getInstance().add(lancamento, parcelas, new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					SC.say("Inclusao efetuada com sucesso");
					inc();
				}

				@Override
				public void onFailure(Throwable caught) {
					SC.say("Erro ao gravar");				
				}
			});
		}
	}
	
	/**
	 * Inclui um novo lancamento
	 */
	private void inc(){
		habilitarComponents(true);
		this.view.getDynamicForm().resetValues();
		this.view.getDynamicForm().getField("txtVencimento").setValue(new Date());
		this.view.getDynamicForm().getField("txtDescricao").focusInItem();
	}
	
	/**
	 * Retorna para a tela de lancamentos no mes atual
	 */
	private void can(){
		controlecontas.mesAtual();
	}
	
	/**
	 * Habilita ou nao os componentes da tela.
	 * @param enable
	 */
	private void habilitarComponents(boolean enable){
		if(enable){
			this.view.getDynamicForm().enable();
		}else{
			this.view.getDynamicForm().disable();
		}
	}

}
