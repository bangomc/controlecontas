package br.com.cris.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

/**
 * Tela de login do sistema.
 * @author cris
 *
 */
public class LoginView extends Composite {
	
	private DynamicForm dynamicForm;
	private Button btnOk;

	public LoginView() {
				
		HTML htmlTitulo = new HTML("<h1>Autentica\u00E7\u00E3o</h1>", true);
		
		TextItem textItem = new TextItem("txtLogin", "Login");		
		textItem.setRequired(true);
		textItem.setLength(20);
		textItem.setRequiredMessage("Campo obrigatorio");
						
		PasswordItem passwordItem = new PasswordItem("txtSenha", "Senha");
		passwordItem.setRequired(true);
		passwordItem.setLength(20);
		passwordItem.setRequiredMessage("Campo obrigatorio");
		
		dynamicForm = new DynamicForm();
		dynamicForm.setAutoFocus(true);
		dynamicForm.setFields(new FormItem[] { textItem, passwordItem});
				
		btnOk = new Button("OK");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(htmlTitulo);
		verticalPanel.setCellHorizontalAlignment(htmlTitulo, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(dynamicForm);
		verticalPanel.setCellHorizontalAlignment(dynamicForm, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(btnOk);
		verticalPanel.setCellHorizontalAlignment(btnOk, HasHorizontalAlignment.ALIGN_CENTER);
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(verticalPanel);
				
		initWidget(decoratorPanel);
	}

	public DynamicForm getDynamicForm() {
		return dynamicForm;
	}

	public void setDynamicForm(DynamicForm dynamicForm) {
		this.dynamicForm = dynamicForm;
	}

	public Button getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(Button btnOk) {
		this.btnOk = btnOk;
	}

}
