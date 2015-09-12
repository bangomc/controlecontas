package br.com.cris.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
/**
 * Representa os botoes de um CRUD em um painel horizontal.
 * @author cris
 *
 */
public class HorizontalPanelCRUDBotoes extends Composite {
	
	private Button buttonINC;
	private Button buttonDEL;
	private Button buttonUPD;
	private Button buttonGRV;
	
	public HorizontalPanelCRUDBotoes(){
		initComponents();
	}

	private void initComponents() {
		this.buttonINC = new Button("Incluir");
		this.buttonDEL = new Button("Excluir");
		this.buttonUPD = new Button("Editar");
		this.buttonGRV = new Button("Gravar");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(buttonINC);
		horizontalPanel.add(buttonDEL);
		horizontalPanel.add(buttonUPD);
		horizontalPanel.add(buttonGRV);
		
		initWidget(horizontalPanel);
	}

	public Button getButtonINC() {
		return buttonINC;
	}

	public void setButtonINC(Button buttonINC) {
		this.buttonINC = buttonINC;
	}

	public Button getButtonDEL() {
		return buttonDEL;
	}

	public void setButtonDEL(Button buttonDEL) {
		this.buttonDEL = buttonDEL;
	}

	public Button getButtonUPD() {
		return buttonUPD;
	}

	public void setButtonUPD(Button buttonUPD) {
		this.buttonUPD = buttonUPD;
	}

	public Button getButtonGRV() {
		return buttonGRV;
	}

	public void setButtonGRV(Button buttonGRV) {
		this.buttonGRV = buttonGRV;
	}
}
