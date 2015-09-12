package br.com.cris.client.view;


import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
/**
 * Tela de inclusao de lancamentos
 * @author cris
 *
 */
public class LancamentoView extends Composite {
	
	private DynamicForm dynamicForm;
	private Button buttonIncluir;
	private Button buttonGravar;
	private Button buttonCancelar;
	
	public LancamentoView() {
		this.initComponents();
	}

	private void initComponents() {
		HTML htmlTitulo = new HTML("<h1>Lan\u00E7amento</h1>", true);		
		
		DateItem dateItem = new DateItem("txtVencimento", "Vencimento");
		
		TextItem textItem = new TextItem("txtDescricao", "Descri\u00E7\u00E3o");
		textItem.setRequired(true);
		textItem.setRequiredMessage("Campo obrigatorio");
		
		FloatItem floatItem = new FloatItem();
		floatItem.setName("txtValor");
		floatItem.setTitle("Valor");
		floatItem.setRequired(true);
		floatItem.setRequiredMessage("Campo obrigatorio");
		
		IntegerItem integerItem = new IntegerItem();
		integerItem.setName("txtParcelas");
		integerItem.setTitle("Parcelas");
		
		this.dynamicForm = new DynamicForm();
		this.dynamicForm.setAutoFocus(true);
		this.dynamicForm.setFields(new FormItem[] { dateItem, textItem, floatItem, integerItem});
		
		HorizontalPanel horizontalPanelBotoes = this.initPanelBotoes();
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(htmlTitulo);
		verticalPanel.setCellHorizontalAlignment(htmlTitulo, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(this.dynamicForm);
		verticalPanel.setCellHorizontalAlignment(this.dynamicForm, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(horizontalPanelBotoes);
		verticalPanel.setCellHorizontalAlignment(horizontalPanelBotoes, HasHorizontalAlignment.ALIGN_CENTER);
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(verticalPanel);
				
		super.initWidget(decoratorPanel);
		
	}
	
	private HorizontalPanel initPanelBotoes() {
		buttonIncluir = new Button("Incluir");
		buttonGravar = new Button("Gravar");
		buttonCancelar = new Button("Cancelar");
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(buttonIncluir);
		horizontalPanel.add(buttonGravar);
		horizontalPanel.add(buttonCancelar);
		return horizontalPanel;
	}

	public DynamicForm getDynamicForm() {
		return dynamicForm;
	}

	public void setDynamicForm(DynamicForm dynamicForm) {
		this.dynamicForm = dynamicForm;
	}

	public Button getButtonIncluir() {
		return buttonIncluir;
	}

	public void setButtonIncluir(Button buttonIncluir) {
		this.buttonIncluir = buttonIncluir;
	}

	public Button getButtonGravar() {
		return buttonGravar;
	}

	public void setButtonGravar(Button buttonGravar) {
		this.buttonGravar = buttonGravar;
	}

	public Button getButtonCancelar() {
		return buttonCancelar;
	}

	public void setButtonCancelar(Button buttonCancelar) {
		this.buttonCancelar = buttonCancelar;
	}

	
}
