package pruebacup;

import java.util.LinkedList;

public class VariablesSemanticas {

	private String tipoDato;
	private String identicador;
	private String asignador;
	private String valor;
	
	public VariablesSemanticas(String tipoDato, String identicador,String asignador,  String valor) {
		this.tipoDato = tipoDato;
		this.identicador = identicador;
		this.asignador = asignador;
		this.valor = valor;
	}
	
	public VariablesSemanticas(String tipoDato, String identicador) {
		this.tipoDato = tipoDato;
		this.identicador = identicador;
	}
	
	public VariablesSemanticas() {
	}

	@Override
	public String toString() {
		return "VariablesSemanticas [tipoDato=" + tipoDato + ", identicador=" + identicador + ", valor=" + valor + "]";
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getIdenticador() {
		return identicador;
	}

	public void setIdenticador(String identicador) {
		this.identicador = identicador;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getAsignador() {
		return asignador;
	}

	public void setAsignador(String asignador) {
		this.asignador = asignador;
	}

	public String getTipoDatoSymbol(String Elemento, LinkedList<VariablesSemanticas> LV)
	{
		String resultado = null;
		for(int i=0;i<LV.size();i++)
		{
			if(LV.get(i).getIdenticador().equals(Elemento))
			{
				resultado = LV.get(i).getTipoDato();
			}
		}
		return resultado;
	}
}
