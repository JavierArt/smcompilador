package pruebacup;

public class VariableSymbol {

	private String variable;
	private String symbol;
	
	public VariableSymbol(String variable, String symbol) {
		this.variable = variable;
		this.symbol = symbol;
	}

	public VariableSymbol(String variable) {
		this.variable = variable;
	}

	@Override
	public String toString() {
		return "VariableSymbol [variable=" + variable + ", symbol=" + symbol + "]";
	}

	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}
