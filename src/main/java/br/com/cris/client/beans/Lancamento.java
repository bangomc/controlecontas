package br.com.cris.client.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Representa um lancamento no financeiro. Uma conta a pagar.
 * @author cris
 *
 */
public class Lancamento implements Serializable{
	
	private static final long serialVersionUID = 4750808774370784543L;
	
	private int codigo;
	private Usuario usuario;
	private String descricao;
	private Date vencimento;
	private Date pagamento;
	private double valor;
	private int status;
	private Lancamento lancamentoPai;
	private boolean pai;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	public Date getPagamento() {
		return pagamento;
	}
	public void setPagamento(Date pagamento) {
		this.pagamento = pagamento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Lancamento getLancamentoPai() {
		return lancamentoPai;
	}
	public void setLancamentoPai(Lancamento lancamentoPai) {
		this.lancamentoPai = lancamentoPai;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isPai() {
		return pai;
	}
	public void setPai(boolean pai) {
		this.pai = pai;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((lancamentoPai == null) ? 0 : lancamentoPai.hashCode());
		result = prime * result
				+ ((pagamento == null) ? 0 : pagamento.hashCode());
		result = prime * result + (pai ? 1231 : 1237);
		result = prime * result + status;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((vencimento == null) ? 0 : vencimento.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (codigo != other.codigo)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (lancamentoPai == null) {
			if (other.lancamentoPai != null)
				return false;
		} else if (!lancamentoPai.equals(other.lancamentoPai))
			return false;
		if (pagamento == null) {
			if (other.pagamento != null)
				return false;
		} else if (!pagamento.equals(other.pagamento))
			return false;
		if (pai != other.pai)
			return false;
		if (status != other.status)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		return true;
	}
	
	

}
