package br.com.cris.client.view;

import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

import br.com.cris.client.controlecontas;
import br.com.cris.client.beans.Usuario;
import br.com.cris.client.rcp.UsuarioDAO;

/**
 * Presenter para controlar a tela Conta.
 * Usuario pode mudar senha e preencher outras informacoes do seu cadastro.
 * @author cris
 *
 */
public class ContaPresenter {
	
	private ContaView view;
	
	public ContaPresenter() {
		this.view = new ContaView();
		
		this.view.getButtonOK().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ok();
			}
		});
		
		this.view.getButtonCancelar().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				cancelar();
			}
		});
		
		this.view.refletir(UsuarioDAO.Util.getUsuarioCorrente(), CRUDViewInterface.OBJETO_TELA);
	}
	
	private void ok(){
		if(this.view.getDynamicForm().validate()){
			final Usuario usuario = UsuarioDAO.Util.getUsuarioCorrente();
			this.view.refletir(usuario, CRUDViewInterface.TELA_OBJETO);
			UsuarioDAO.Util.getInstance().upd(usuario, new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					controlecontas.usuarioCorrente(usuario);
					controlecontas.mesAtual();
				}
				
				@Override
				public void onFailure(Throwable caught) {
					SC.say("Erro ao atualizar usuario");
				}
			});
		}
	}
	
	private void cancelar(){
		controlecontas.mesAtual();
	}

	public ContaView getView() {
		return view;
	}

	public void setView(ContaView view) {
		this.view = view;
	}

}
