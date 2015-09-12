package br.com.cris.server;
/**
 * Responsavel pelos parametros do sistema
 * @author cris
 *
 */
public class ParametrosSistema {
	
	public static final String adminPWN = "cris";
	public static MODO modo = MODO.TESTE;
	
	public enum MODO {
		PRODUCAO,
		TESTE;
	}

}
