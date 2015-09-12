package br.com.cris.client.view;

import br.com.cris.client.beans.Usuario;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
/**
 * Tela de manutencao de usuario
 * @author cris
 *
 */
public class ContaView extends Composite {

	private DynamicForm dynamicForm;
	private Button buttonOK;
	private Button buttonCancelar;

	public ContaView() {
		this.initComponents();
	}

	private void initComponents() {
		HTML htmlTitulo = new HTML("<h1>Meus Dados</h1>");

		TextItem textItemNome = new TextItem("txtNome", "Nome");
		textItemNome.setLength(20);

		PasswordItem passwordItem = new PasswordItem("txtSenha", "Nova Senha");
		passwordItem.setRequired(true);
		passwordItem.setLength(20);
		passwordItem.setRequiredMessage("Campo obrigatorio");

		PasswordItem passwordItemNovamente = new PasswordItem("txtSenhaNovamente", "Senha novamente");
		passwordItemNovamente.setRequired(true);
		passwordItemNovamente.setLength(20);
		passwordItemNovamente.setRequiredMessage("Campo obrigatorio");

		MatchesFieldValidator matchesValidator = new MatchesFieldValidator();  
		matchesValidator.setOtherField("txtSenha");  
		matchesValidator.setErrorMessage("Senha nao confere");          
		passwordItemNovamente.setValidators(matchesValidator);

		TextItem textItemEmail = new TextItem("txtEmail", "Email");
		textItemEmail.setLength(20);

		dynamicForm = new DynamicForm();
		dynamicForm.setAutoFocus(true);
		dynamicForm.setFields(new FormItem[] { textItemNome, passwordItem, passwordItemNovamente, textItemEmail});

		HorizontalPanel horizontalPanelBotoes = this.initPanelBotoes();

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(htmlTitulo);
		verticalPanel.setCellHorizontalAlignment(htmlTitulo, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(dynamicForm);
		verticalPanel.setCellHorizontalAlignment(dynamicForm, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(horizontalPanelBotoes);
		verticalPanel.setCellHorizontalAlignment(horizontalPanelBotoes, HasHorizontalAlignment.ALIGN_CENTER);

		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(verticalPanel);

		super.initWidget(decoratorPanel);
	}

	private HorizontalPanel initPanelBotoes() {
		this.buttonOK = new Button("OK");
		this.buttonCancelar = new Button("Cancelar");

		HorizontalPanel horizontalPanelBotoes = new HorizontalPanel();
		horizontalPanelBotoes.add(this.buttonOK);
		horizontalPanelBotoes.add(this.buttonCancelar);

		return horizontalPanelBotoes;
	}
	
	/**
	 * Realiza a transferencia de valores de um objeto para componentes na tela e vice-versa
	 * @param usuario
	 * @param crudViewInterface
	 */
	public void refletir(Usuario usuario, String crudViewInterface){
		if(crudViewInterface.equals(CRUDViewInterface.OBJETO_TELA)){
			this.dynamicForm.getField("txtNome").setValue(usuario.getNome());
			this.dynamicForm.getField("txtEmail").setValue(usuario.getEmail());	
		}else{
			String nome = (String) this.dynamicForm.getField("txtNome").getValue();
			String senha = (String) this.dynamicForm.getField("txtSenha").getValue();
			String email = (String) this.dynamicForm.getField("txtEmail").getValue();
			
			usuario.setNome(nome);
			usuario.setSenha(senha);
			usuario.setEmail(email);
		}
	}

	public DynamicForm getDynamicForm() {
		return dynamicForm;
	}

	public void setDynamicForm(DynamicForm dynamicForm) {
		this.dynamicForm = dynamicForm;
	}

	public Button getButtonOK() {
		return buttonOK;
	}

	public void setButtonOK(Button buttonOK) {
		this.buttonOK = buttonOK;
	}

	public Button getButtonCancelar() {
		return buttonCancelar;
	}

	public void setButtonCancelar(Button buttonCancelar) {
		this.buttonCancelar = buttonCancelar;
	}

}

