package br.com.cris.client.view;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import br.com.cris.client.Formatar;
import br.com.cris.client.beans.Lancamento;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;
/**
 * Tela de exibicao de lancamentos do usuario corrente e que tenha vencimento no mes selecionado.
 * @author cris
 *
 */
public class MesAtualView extends Composite{

	private static ListBox comboBoxMes;
	private CellTable<Lancamento> cellTable;
	private SingleSelectionModel<Lancamento> selectionModel = new SingleSelectionModel<Lancamento>();
	private Button buttonPagar;
	private Button buttonLiberar;
	private Button buttonIncluir;
	private Button buttonExcluir;
	
	static{
		comboBoxMes = new ListBox();
		comboBoxMes.setName("mes");
		comboBoxMes.addItem("Janeiro");
		comboBoxMes.addItem("Fevereiro");
		comboBoxMes.addItem("Marco");
		comboBoxMes.addItem("Abril");
		comboBoxMes.addItem("Maio");
		comboBoxMes.addItem("Junho");
		comboBoxMes.addItem("Julho");
		comboBoxMes.addItem("Agosto");
		comboBoxMes.addItem("Setembro");
		comboBoxMes.addItem("Outubro");
		comboBoxMes.addItem("Novembro");
		comboBoxMes.addItem("Dezembro");
	}	
	
	public MesAtualView() {
		this.initComponents();
	}
	
	private void initComponents(){
		HTML htmlTitulo = new HTML("<h1>Lan\u00E7amentos</h1>", true);
		htmlTitulo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		this.initCellTable();
		
		HorizontalPanel horizontalPanelBotoes = this.initPanelBotoes();
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(htmlTitulo);
		verticalPanel.setCellHorizontalAlignment(htmlTitulo, HasHorizontalAlignment.ALIGN_CENTER);		
		verticalPanel.add(comboBoxMes);
		verticalPanel.setCellHorizontalAlignment(comboBoxMes, HasHorizontalAlignment.ALIGN_LEFT);
		verticalPanel.add(this.cellTable);
		verticalPanel.setCellHorizontalAlignment(this.cellTable, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(horizontalPanelBotoes);
		verticalPanel.setCellHorizontalAlignment(horizontalPanelBotoes, HasHorizontalAlignment.ALIGN_CENTER);
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(verticalPanel);
		
		super.initWidget(decoratorPanel);
		
	}
	
	private HorizontalPanel initPanelBotoes() {
		this.buttonPagar = new Button("Pagar");
		this.buttonLiberar = new Button("Liberar");
		this.buttonIncluir = new Button("Incluir");
		this.buttonExcluir = new Button("Excluir");
		
		HorizontalPanel horizontalPanelBotoes = new HorizontalPanel();
		horizontalPanelBotoes.add(this.buttonPagar);		
		horizontalPanelBotoes.add(this.buttonLiberar);
		horizontalPanelBotoes.add(this.buttonIncluir);
		horizontalPanelBotoes.add(this.buttonExcluir);
		
		return horizontalPanelBotoes;
	}

	private void initCellTable(){
		cellTable = new CellTable<Lancamento>();
		cellTable.setSelectionModel(selectionModel);
		cellTable.setEmptyTableWidget(new HTML("<h5>Sem lancamentos</h5>"));
		
		DateCell dateCell = new DateCell(DateTimeFormat.getFormat("dd/MM/yy"));
		Column<Lancamento, Date> columnVencimento = new Column<Lancamento, Date>(dateCell) {
			
			@Override
			public Date getValue(Lancamento object) {
				return object.getVencimento();
			}
		};
		columnVencimento.setSortable(true);
		
		TextColumn<Lancamento> columnDescricao = new TextColumn<Lancamento>() {
			@Override
			public String getValue(Lancamento object) {
				return object.getDescricao();
			}
		};
		columnDescricao.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		
		TextColumn<Lancamento> columnValor = new TextColumn<Lancamento>() {
			@Override
			public String getValue(Lancamento object) {
				return Formatar.formatar(object.getValor());
			}
		};
		
		TextColumn<Lancamento> columnStatus = new TextColumn<Lancamento>() {
			@Override
			public String getValue(Lancamento object) {
				String retorno = "";
				if(1 == object.getStatus()){
					retorno = "PG";
				}
				return retorno;
			}
		};
		
		ListHandler<Lancamento> sortHandler = new ListHandler<Lancamento>(new ArrayList<Lancamento>());
		sortHandler.setComparator(columnVencimento,new Comparator<Lancamento>() {

			@Override
			public int compare(Lancamento o1, Lancamento o2) {
				return o1.getVencimento().compareTo(o2.getVencimento());
			}
		});
		
		
		cellTable.addColumn(columnVencimento,"Vencimento");
		cellTable.addColumn(columnDescricao, "Descri\u00E7\u00E3o");
		cellTable.setColumnWidth(columnDescricao, "50%");
		cellTable.addColumn(columnValor, "Valor");
		cellTable.addColumn(columnStatus, "Status");
		
		cellTable.addColumnSortHandler(sortHandler);
		cellTable.getColumnSortList().push(columnVencimento);
	}

	public static ListBox getComboBoxMes() {
		return comboBoxMes;
	}

	public static void setComboBoxMes(ListBox comboBoxMes) {
		MesAtualView.comboBoxMes = comboBoxMes;
	}

	public CellTable<Lancamento> getCellTable() {
		return cellTable;
	}

	public void setCellTable(CellTable<Lancamento> cellTable) {
		this.cellTable = cellTable;
	}

	public Button getButtonPagar() {
		return buttonPagar;
	}

	public void setButtonPagar(Button buttonPagar) {
		this.buttonPagar = buttonPagar;
	}

	public Button getButtonLiberar() {
		return buttonLiberar;
	}

	public void setButtonLiberar(Button buttonLiberar) {
		this.buttonLiberar = buttonLiberar;
	}

	public Button getButtonIncluir() {
		return buttonIncluir;
	}

	public void setButtonIncluir(Button buttonIncluir) {
		this.buttonIncluir = buttonIncluir;
	}

	public Button getButtonExcluir() {
		return buttonExcluir;
	}

	public void setButtonExcluir(Button buttonExcluir) {
		this.buttonExcluir = buttonExcluir;
	}

	public SingleSelectionModel<Lancamento> getSelectionModel() {
		return selectionModel;
	}

	public void setSelectionModel(SingleSelectionModel<Lancamento> selectionModel) {
		this.selectionModel = selectionModel;
	}
}
