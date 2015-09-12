package br.com.cris.client.view;

import br.com.cris.client.controlecontas;
import br.com.cris.client.beans.Usuario;
import br.com.cris.client.rcp.LoginService;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
/**
 * Presenter para controlar a tela de Login do sistema.
 * Permite o login do administrador para adicionar novos usuarios do sistema.
 * @author cris
 *
 */
public class LoginPresenter {
	
	private LoginView view;
	
	public LoginPresenter() {
		view = new LoginView();
		view.getBtnOk().addClickHandler( new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				btnOkActionPerformed();
			}
		});
	}
	
	private void btnOkActionPerformed(){
		if(view.getDynamicForm().validate()){
			final String usuario = view.getDynamicForm().getField("txtLogin").getValue().toString();
			final String senha = view.getDynamicForm().getField("txtSenha").getValue().toString();
			LoginService.Util.getInstance().logar(usuario, senha, new AsyncCallback<Usuario>() {
				
				@Override
				public void onSuccess(Usuario result) {
					
					if(null==result){
						SC.say("Usuario ou senha nao confere.");
						return;
					}
					
					//Administrador pode cadastrar novos usuarios para o sistema
					if("admin".equals(result.getLogin())){
						controlecontas.cadUsuario();
						return;
					}
					
					controlecontas.usuarioCorrente(result);
					controlecontas.mesAtual();
				}
				
				@Override
				public void onFailure(Throwable caught) {
					SC.say("Erro no login");
				}
			});
		}
	}

	public LoginView getView() {
		return view;
	}

	public void setView(LoginView view) {
		this.view = view;
	}

}
