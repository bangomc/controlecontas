package br.com.cris.client.view;

import java.util.List;

import br.com.cris.client.beans.Usuario;
import br.com.cris.client.rcp.UsuarioDAO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;

/**
 * Presenter para controlar a tela de cadastro de usuarios pelo administrador do sistema.
 * @author cris
 *
 */
public class UsuarioPresenter {
	
	private UsuarioView view;
	
	public UsuarioPresenter() {
		this.view = new UsuarioView();
		this.view.getButtonINC().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				inc();
			}
		});
		
		this.view.getButtonDEL().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				del();
			}
		});
		
		this.view.getButtonGRV().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				grv();
			}
		});
		
		this.view.getButtonUPD().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				upd();
			}
		});
		this.povoar();
		this.habilitarComponents(false);
	}

	public UsuarioView getView() {
		return view;
	}

	public void setView(UsuarioView view) {
		this.view = view;
	}

	/**
	 * Inclui um registro 
	 */
	public void inc() {
		this.habilitarComponents(true);
		Usuario usuario = new Usuario();
		this.view.getSelectionModel().setSelected(usuario, true);
	}

	/**
	 * Apaga o registro selecionado
	 */
	public void del() {
		SC.ask("Deseja exluir o usuario?", new BooleanCallback() {
			
			@Override
			public void execute(Boolean value) {
				if(value){
					Usuario usuario = view.getSelectionModel().getSelectedObject();
					UsuarioDAO.Util.getInstance().del(usuario, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							povoar();							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							SC.say("Erro excluindo usuario");
						}
					});
				}
			}
		});		
	}

	/**
	 * Realiza a atualizacao do registro
	 */
	public void upd() {
		this.habilitarComponents(true);
	}

	/**
	 * Persiste o novo usuario ou as modificacoes de um usuario existente.
	 */
	public void grv() {
		if(this.view.getDynamicForm().validate()){
			Usuario usuario = this.view.getSelectionModel().getSelectedObject();
			this.view.refletir(usuario, CRUDViewInterface.TELA_OBJETO);
			if(usuario.getCodigo()>0){
				UsuarioDAO.Util.getInstance().upd(usuario, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						povoar();
					}

					@Override
					public void onFailure(Throwable caught) {
						SC.say("Erro gravando usuario");
					}
				});
			}else{
				UsuarioDAO.Util.getInstance().add(usuario, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						povoar();
					}

					@Override
					public void onFailure(Throwable caught) {
						SC.say("Erro gravando usuario");
					}
				});
			}
			this.habilitarComponents(false);
		}		
	}
	
	/**
	 * Popula a lista de usuarios para selecao 
	 * @param lista
	 */
	private void povoar(List<Usuario> lista){		
		this.view.getCellTable().setRowData(lista);
	}
	
	/**
	 * Busca no banco de dados a lista de usuarios
	 */
	public void povoar(){
		UsuarioDAO.Util.getInstance().list(new AsyncCallback<List<Usuario>>() {
			
			@Override
			public void onSuccess(List<Usuario> result) {
				povoar(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				SC.say("Erro retornando usuarios");				
			}
		});
	}
	
	/**
	 * Habilita ou nao os componentes da tela
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
