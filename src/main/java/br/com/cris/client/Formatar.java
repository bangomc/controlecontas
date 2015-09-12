package br.com.cris.client;


import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
/**
 * Utilitario para formatacao.
 * @author cris
 *
 */
public class Formatar {
		
	static DateTimeFormat dtf = DateTimeFormat.getFormat("dd/MM/yy");
	static NumberFormat nf = NumberFormat.getCurrencyFormat();
	
	public static String formatar(Date data){
		return dtf.format(data);
	}
	
	public static String formatar(Double valor){
		return nf.format(valor);
	}

}
