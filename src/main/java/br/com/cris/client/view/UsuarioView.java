package br.com.cris.client.view;


import br.com.cris.client.beans.Usuario;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
/**
 * Tela para cadastro de novos usuarios do sistema pelo administrador.
 * @author cris
 *
 */
public class UsuarioView extends Composite implements CRUDViewInterface {

	private DynamicForm dynamicForm;
	private HorizontalPanelCRUDBotoes crudBotoes;	
	private CellTable<Usuario> cellTable;
	private SingleSelectionModel<Usuario> selectionModel = new SingleSelectionModel<Usuario>();

	public UsuarioView() {
		initComponents();
	}

	private void initComponents(){
		HTML htmlTitulo = new HTML("<h1>Cadastro de Usuario</h1>");
		
		TextItem textItemNome = new TextItem("txtNome", "Nome");
		textItemNome.setRequired(true);
		textItemNome.setLength(20);
		textItemNome.setRequiredMessage("Campo obrigatorio");
		
		TextItem textItemLogin = new TextItem("txtLogin", "Login");		
		textItemLogin.setRequired(true);
		textItemLogin.setLength(20);
		textItemLogin.setRequiredMessage("Campo obrigatorio");
						
		PasswordItem passwordItem = new PasswordItem("txtSenha", "Senha");
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
		dynamicForm.setFields(new FormItem[] { textItemNome, textItemLogin, passwordItem, passwordItemNovamente, textItemEmail});
		
		crudBotoes = new HorizontalPanelCRUDBotoes();
		
		selectionModel.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				Usuario usuarioSelecionado = selectionModel.getSelectedObject();
				refletir(usuarioSelecionado,CRUDViewInterface.OBJETO_TELA);
			}
		});
		
		this.initCellTable();
				
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(htmlTitulo);
		verticalPanel.setCellHorizontalAlignment(htmlTitulo, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(dynamicForm);
		verticalPanel.setCellHorizontalAlignment(dynamicForm, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(crudBotoes);
		verticalPanel.setCellHorizontalAlignment(crudBotoes, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(cellTable);
		verticalPanel.setCellHorizontalAlignment(cellTable, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setCellWidth(cellTable, "100%");
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(verticalPanel);
				
		super.initWidget(decoratorPanel);
	}
	
	/**
	 * Realiza a transferencia de valores de um objeto para componentes na tela e vice-versa
	 * @param usuario
	 * @param crudViewInterface
	 */
	public void refletir(Usuario usuario, String crudViewInterface){
		if(crudViewInterface.equals(CRUDViewInterface.OBJETO_TELA)){
			this.dynamicForm.getField("txtNome").setValue(usuario.getNome());
			this.dynamicForm.getField("txtLogin").setValue(usuario.getLogin());
			this.dynamicForm.getField("txtSenha").setValue(usuario.getSenha());
			this.dynamicForm.getField("txtEmail").setValue(usuario.getEmail());	
		}else{
			String nome = (String) this.dynamicForm.getField("txtNome").getValue();
			String login = (String) this.dynamicForm.getField("txtLogin").getValue();
			String senha = (String) this.dynamicForm.getField("txtSenha").getValue();
			String email = (String) this.dynamicForm.getField("txtEmail").getValue();
			
			usuario.setNome(nome);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setEmail(email);
		}
	}
	
	private void initCellTable(){
		cellTable = new CellTable<Usuario>();
		cellTable.setSelectionModel(selectionModel);
		
		TextColumn<Usuario> txtNome = new TextColumn<Usuario>() {
			
			@Override
			public String getValue(Usuario object) {
				return object.getNome();
			}
		};
		
		TextColumn<Usuario> txtLogin = new TextColumn<Usuario>() {
			
			@Override
			public String getValue(Usuario object) {
				return object.getLogin();
			}
		};
		cellTable.addColumn(txtNome, "Nome");
		cellTable.addColumn(txtLogin, "Login");
	}

	@Override
	public Button getButtonINC() {		
		return this.crudBotoes.getButtonINC();
	}

	@Override
	public Button getButtonDEL() {
		return this.crudBotoes.getButtonDEL();
	}

	@Override
	public Button getButtonUPD() {
		return this.crudBotoes.getButtonUPD();
	}

	@Override
	public Button getButtonGRV() {
		return this.crudBotoes.getButtonGRV();
	}

	public DynamicForm getDynamicForm() {
		return dynamicForm;
	}

	public void setDynamicForm(DynamicForm dynamicForm) {
		this.dynamicForm = dynamicForm;
	}

	public CellTable<Usuario> getCellTable() {
		return cellTable;
	}

	public void setCellTable(CellTable<Usuario> cellTable) {
		this.cellTable = cellTable;
	}

	public SingleSelectionModel<Usuario> getSelectionModel() {
		return selectionModel;
	}

	public void setSelectionModel(SingleSelectionModel<Usuario> selectionModel) {
		this.selectionModel = selectionModel;
	}

}
