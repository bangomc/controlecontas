package br.com.cris.client;

import br.com.cris.client.beans.Usuario;
import br.com.cris.client.rcp.UsuarioDAO;
import br.com.cris.client.view.ContaPresenter;
import br.com.cris.client.view.LancamentoPresenter;
import br.com.cris.client.view.LoginPresenter;
import br.com.cris.client.view.MesAtualPresenter;
import br.com.cris.client.view.UsuarioPresenter;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Classe principal que controla a navegacao.
 * @author cris
 *
 */
public class controlecontas implements EntryPoint {

	public void onModuleLoad() {
		LoginPresenter presenter = new LoginPresenter();
		RootPanel.get("conteudo").add(presenter.getView());
	}
	
	public static void cadUsuario(){
		UsuarioPresenter presenter = new UsuarioPresenter();
		RootPanel.get("conteudo").clear();
		RootPanel.get("conteudo").add(presenter.getView());
	}
	
	public static void mesAtual(){
		MesAtualPresenter presenter = new MesAtualPresenter();
		RootPanel.get("conteudo").clear();
		RootPanel.get("conteudo").add(presenter.getView());
	}
	
	public static void updConta(){
		ContaPresenter presenter = new ContaPresenter();
		RootPanel.get("conteudo").clear();
		RootPanel.get("conteudo").add(presenter.getView());
	}
	
	public static void cadLancamento(){
		LancamentoPresenter presenter = new LancamentoPresenter();
		RootPanel.get("conteudo").clear();
		RootPanel.get("conteudo").add(presenter.getView());
	}
	
	public static void usuarioCorrente(Usuario usuario){
		UsuarioDAO.Util.setUsuarioCorrente(usuario);
		Button button = new Button();
		button.setText(usuario.getNome());
		
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				updConta();
			}
		});	
	
		RootPanel.get("topod").clear();
		RootPanel.get("topod").add(button);
	}
}
