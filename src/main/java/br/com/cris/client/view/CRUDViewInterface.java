package br.com.cris.client.view;

import com.google.gwt.user.client.ui.Button;

public interface CRUDViewInterface {
	
	public static String TELA_OBJETO = "TELA_OBJETO";
	public static String OBJETO_TELA = "OBJETO_TELA";
	
	/**
	 * Retorna o botao de INCLUIR
	 * @return
	 */
	public Button getButtonINC();
	
	/**
	 * Retorna o botao de APAGAR
	 * @return
	 */
	public Button getButtonDEL();
	
	/**
	 * Retorna o botao de ATUALIZAR
	 * @return
	 */
	public Button getButtonUPD();
	
	/**
	 * Retorna o botao de GRAVAR
	 * @return
	 */
	public Button getButtonGRV();

}
