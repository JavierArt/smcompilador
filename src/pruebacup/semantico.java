//solo tiene como tipos de dato int,string y boolean
//solo tiene como sentecias while if for print
//para hacer el sintactico pienso usar expresiones regulares de modo que si entran entonces esta bien si no mandas error de sintaxis en x linea
//para la generacion de codigo es una calculadora cientifica con hashmap y ensamblador
package pruebacup;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.regex.*;

public class semantico {

	public static final String fileName="Entrada.txt";

	//ordena lista para valores repetidos
	private LinkedList<VariablesSemanticas> orderList(LinkedList<VariablesSemanticas> d) 
	{
		Collections.sort(d, new Comparator<VariablesSemanticas>() {
		  
			@Override
			public int compare(VariablesSemanticas o1, VariablesSemanticas o2) {
				return Collator.getInstance().compare(o1.getIdenticador(), o2.getIdenticador());
			}
		 });
		return d;		
	}
	
	//revisa si hay valores repetidos y los muestra si existen
	public void multipleDeclaration(LinkedList<VariablesSemanticas> LO)
	{
		LinkedList<VariablesSemanticas> repeat = orderList(LO);
		LinkedList<String> repeated = new LinkedList<String>();
		for(int i=0;i<repeat.size()-1;i++)
		{
			if(repeat.get(i).getIdenticador().equals(repeat.get(i+1).getIdenticador()))
			{
				repeated.add(repeat.get(i).getIdenticador());
			}
		}
		if(repeated.size()!=0)
		{
			System.out.println("Error: Esta(s) declaracion(es) se repiten: "+repeated );
		}
	}

	//obtiene las declaraciones  de variables (declaraciones) a lo largo del texto con expresiones regulares y retorna una lista de objetos
	public LinkedList<VariablesSemanticas> obtenerVariables()
	{
		//declaracion de lista donde se van a poner las variables
		LinkedList<VariablesSemanticas> varlk = new LinkedList<VariablesSemanticas>();

	    // referencia un linea cada vez
        String line = null;

        try {
            // FileReader lee los archivos de texto con encoding por defecto.
            FileReader fileReader = new FileReader(fileName);

            // siempre envuelve FileReader en un BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //declaracion de patrones para int,string,bool asignado y no asignado
        	Pattern patintval = Pattern.compile("^int [a-zA-Z]+\\s?=\\s?([0-9]+;|(true|false);)");
        	//int asignado con texto debe dar error abajo
        	Pattern patintvalerror = Pattern.compile("^int [a-zA-Z]+\\s?=\\s?([\"'])(.*?)\\1;");

        	//string asignado con '*' o "*"
        	Pattern patstringval = Pattern.compile("^string [a-zA-Z]+\\s?=\\s?([\"'])(.*?)\\1;");
        	//string con error
        	Pattern patstringvalerror = Pattern.compile("string [a-zA-Z]+\\s?=\\s?([0-9]+;|(true|false);)");
        	
        	//bool asignado como verdadero o falso
        	Pattern patboolval = Pattern.compile("^boolean [a-zA-Z]+\\s?=\\s?(true|false);");
        	//bool asignado con error(numero)
        	Pattern patboolvalerror = Pattern.compile("^boolean [a-zA-Z]+\\s?=\\s?[0-9]+;");
        	//bool asignado con error(letras)
        	Pattern patboolvalerror2 = Pattern.compile("^boolean [a-zA-Z]+\\s?=\\s?([\"'])(.*?)\\1;");
        	//int no asignado
        	Pattern patint = Pattern.compile("^int [a-zA-Z]+;");
        	//string no asignado
        	Pattern patstring = Pattern.compile("^string [a-zA-Z]+;");
        	//bool no asignado
        	Pattern patbool = Pattern.compile("^boolean [a-zA-z]+;");

        	//predeclaras Matcher de cada uno
        	Matcher intasigned;
        	Matcher intasignederror;
        	Matcher stringasigned;
        	Matcher stringasignederror;
        	Matcher boolasigned;
        	Matcher boolasignederror;
        	Matcher boolasignederror2;
        	Matcher intNA;
        	Matcher stringNA;
        	Matcher boolNA;
        	String h;
        	while((line = bufferedReader.readLine()) != null) {
        		//declaras Matcher de cada uno
            	intasigned = patintval.matcher(line);
            	intasignederror = patintvalerror.matcher(line);
            	stringasigned = patstringval.matcher(line);
            	stringasignederror = patstringvalerror.matcher(line);
            	boolasigned = patboolval.matcher(line);
            	boolasignederror = patboolvalerror.matcher(line);
            	boolasignederror2 = patboolvalerror2.matcher(line);
            	intNA = patint.matcher(line);
            	stringNA = patstring.matcher(line);
            	boolNA = patbool.matcher(line);
            	
            	//compruebas coincidencias, separas elementos y lo pones an la lista de objetos
            	if(intasigned.matches() || stringasigned.matches() || boolasigned.matches() || intasignederror.matches() 
            			|| stringasignederror.matches() || boolasignederror.matches() || boolasignederror2.matches())
            	{
                	String[] splited = line.split("\\s+");
                	h = splited[3].substring(0, splited[3].indexOf(';'));
            		varlk.add(new VariablesSemanticas(splited[0],splited[1],splited[2],h));
            	}
            	if(intNA.lookingAt() || stringNA.matches() || boolNA.matches())
            	{
            		String[] splited = line.split("\\s+");
            		h = splited[1].substring(0, splited[1].indexOf(';'));
            		varlk.add(new VariablesSemanticas(splited[0],h));
            	}
            }
            //Cierra el archivo
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "no puedo abrir el archivo '" + 
                fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                "Error leyendo el archivo '" 
                + fileName + "'");
        }
		return varlk;
	}

	//obtiene las variables a lo largo del texto con expresion regular
	public ArrayList<String> buscaVarArch()
	{
		ArrayList<String> var = new ArrayList<>();
		
		// referencia un linea cada vez
        String line = null;
        try 
        {
            // FileReader lee los archivos de texto con encoding por defecto.
            FileReader fileReader = new FileReader(fileName);

            // siempre envuelve FileReader en un BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //variables dentro de parentesis
        	Pattern varInArch = Pattern.compile(".*\\( +(?!if|for|while|int|string|boolean)([a-zA-Z0-9 <|<=|>|>=|&{2}]+) +\\);?");
        	//variables dentro de print
        	Pattern varprint = Pattern.compile("\\s?print\\( +(?!if|for|while|int|string|boolean)(.*) +\\);"); 
        	//variables contadores
        	Pattern cont = Pattern.compile("[A-Za-z]+\\+{2};");
        	//variables de igualdad
        	Pattern vari = Pattern.compile("[A-Za-z]+ +?= +?([A-Za-z0-9 ?\\+|-|*|\\/]+);");
        	//palabras dentro de parentesis se usa mas abajo
        	Pattern palabras = Pattern.compile("[A-Za-z]+ ?;?");
        	//variables dentro de print
        	Pattern printpat = Pattern.compile("\\s?print\\( +?.* +?\\);");
        	
        	Matcher varArch;
        	Matcher co;
        	Matcher varin;
        	Matcher palabrin;
        	Matcher printvar;
        	Matcher varpr;
        	
            while((line = bufferedReader.readLine()) != null) 
            {
            	varArch = varInArch.matcher(line);
            	co = cont.matcher(line);
            	varin = vari.matcher(line);
            	printvar = printpat.matcher(line);
            	varpr = varprint.matcher(line);

            	if(varArch.matches() || printvar.matches() || varpr.matches())
            	{
                	String[] splited = line.split("\\s+");
                	for(String letras: splited)
                	{
                		palabrin = palabras.matcher(letras);
                		if(letras.endsWith(";"))
                		{
                			letras = letras.replace(";","");
                		}
                		if(palabrin.matches())
                			var.add(letras);
                	}
            	}
            	if(co.matches())
            	{
            		String[] splited = line.split("\\+");
            		var.add(splited[0]);
            	}
            	if(varin.matches())
            	{
            		String[] splited = line.split("\\s+");
                	for(String letras: splited)
                	{
                		palabrin = palabras.matcher(letras);
                		if(letras.endsWith(";"))
                		{
                			letras = letras.replace(";","");
                		}
                		if(palabrin.matches())
                			var.add(letras);
                	}
            	}
            }
            //Cierra el archivo
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "no puedo abrir el archivo '" + 
                fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                "Error leyendo el archivo '" 
                + fileName + "'");
        }
		return var;
	}

	//obtiene las listas de variables del texto y declaradas y muestra las variables del texto que no esten declaradas
	public void notdeclared(ArrayList<String> d,LinkedList<VariablesSemanticas> LV)
	{
		ArrayList<String> notdec = new ArrayList<String>();
		
		int i=0;
		int j=0;
		int cont=0;
		while(i <= LV.size()-1)
		{
			if(!LV.get(i).getIdenticador().equals(d.get(j)))
			{
				cont++;
				if(cont== LV.size()-1 && j < d.size()-1)
				{
					notdec.add(d.get(j));
					cont=0;
					i=0;
					j++;
				}
			}
			if(LV.get(i).getIdenticador().equals(d.get(j)) && j < d.size()-1 )
			{
				cont=0;
				i=0;
				j++;
			}
			//para reiniciar
			if(i == LV.size()-1 && j < d.size()-1)
			{
				i=0;
				j++;
			}
			i++;
		}
		if(notdec.size()!=0)
		{
			System.out.println("Error: Estas variables no se declararon: "+notdec);
		}
	
	}


	//revisa la asignacion correcta a los tipos de variable
	public void rulesassigment(LinkedList<VariablesSemanticas> LV) 
	{
		Pattern numbers = Pattern.compile("[0-9]+");
		Pattern bool = Pattern.compile("(true|false)");
		Pattern str = Pattern.compile("([\"'])(.*?)\\1");
		for(int i=0;i<LV.size();i++) {
			if(LV.get(i).getTipoDato().equals("int") && LV.get(i).getValor() != null)
			{
				Matcher matnum = numbers.matcher(LV.get(i).getValor());
				if(!matnum.matches())
					System.out.println("Error: type mismatch int en: " + LV.get(i).getIdenticador());
			}
			if(LV.get(i).getTipoDato().equals("string") && LV.get(i).getValor() != null)
			{
				Matcher stri = str.matcher(LV.get(i).getValor());
				if(!stri.matches())
				System.out.println("Error: type mismatch string en: " + LV.get(i).getIdenticador());
			}
			if(LV.get(i).getTipoDato().equals("boolean") && LV.get(i).getValor() != null)
			{
				Matcher booli = bool.matcher(LV.get(i).getValor());
				if(!booli.matches())
					System.out.println("Error: type mismatch boolean en: " + LV.get(i).getIdenticador());
			}
		}
	}

	//revisa variables en archivo y sus usos
	private LinkedList<VariableSymbol> rulesop(ArrayList<String> dec )
	{
		LinkedList<VariableSymbol> varsim = new LinkedList<>();
		
        // referencia un linea cada vez
        String line = null;
        String next;
        try {
            // FileReader lee los archivos de texto con encoding por defecto.
            FileReader fileReader = new FileReader(fileName);

            // siempre envuelve FileReader en un BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
            {
            	if(line.matches("(int |boolean )[a-zA-Z0-9]+ = [a-zA-Z0-9]+;") || line.matches("string [a-zA-Z0-9]+ = ([\"'])(.*?)\\1;") || line.matches("//.*") 
            	  || line.matches("(int |string |boolean )[a-zA-Z]+;") || line.equals("int main(){")) 
            	{}
            	else
            	{
            		String[] splited = line.split("\\s");
		            for(int i=0;i<splited.length;i++) {
	            		if(line.matches("[a-zA-Z]+\\+{2};"))
	            		{
	            			String[] spl = line.split("\\+");
	            			if(dec.contains(spl[0]))
	            			{
	            				varsim.add(new VariableSymbol(spl[0]));
	            			}
	            		}
	            		if(dec.contains(splited[i])) {
	            			next = splited[i+1];
	            			varsim.add(new VariableSymbol(splited[i],next));
	            		}
		            }
            	}
            }
        bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "no puedo abrir el archivo '" + 
                fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                "Error leyendo el archivo '" 
                + fileName + "'");
        }
		return varsim;
	}
	
	public void realrules(LinkedList<VariableSymbol> varsim, LinkedList<VariablesSemanticas> varsem)
	{
		VariablesSemanticas v = new VariablesSemanticas();
		for(int i=0;i<varsim.size()-1;i++)
		{
			//valor de cada variable
			String valor = varsim.get(i).getVariable();
			//simbolo siguiente de cada variable
			String symbol = varsim.get(i).getSymbol();
			//tipo de dato de cada variable
			String dataType = v.getTipoDatoSymbol(valor, varsem);
			//para no comparar con ) en los if,while,for
			if(symbol!=null && symbol.equals(")"))
			{
				symbol = varsim.get(i-1).getSymbol();
			}
			//para no compara con ) en los print
			if(symbol!=null && symbol.equals(");"))
			{
				symbol = varsim.get(i-1).getSymbol();
			}
			if(dataType.equals("string") && symbol !=null && symbol.equals("-") )
			{
				System.out.println("Error: string no se puede restar");
			}
			if(dataType.equals("string") && symbol !=null && symbol.equals("*"))
			{
				System.out.println("Error: string no se puede multiplicar");
			}
			if(dataType.equals("string") && symbol !=null && symbol.equals("/"))
			{
				System.out.println("Error: string no se puede dividir");
			}
			if(dataType.equals("string") && symbol !=null && symbol.equals("%"))
			{
				System.out.println("Error: no puedo sacarle modulo a un string");
			}
			if(dataType.equals("string") && symbol !=null && symbol.equals("<"))
			{
				System.out.println("Error: string no se puede < ");
			}
			if(dataType.equals("string") && symbol !=null && symbol.equals(">"))
			{
				System.out.println("Error: string no se puede > ");
			}
			if(dataType.equals("string") && symbol !=null && symbol.equals("<="))
			{
				System.out.println("Error: string no se puede <= ");
			}
			if(dataType.equals("string") && symbol !=null && symbol.equals(">="))
			{
				System.out.println("Error: string no se puede >= ");
			}
			
			if(dataType.equals("boolean") && symbol !=null && symbol.equals("+"))
			{
				System.out.println("Error: boolean no se puede sumar");
			}
			if(dataType.equals("boolean") && symbol !=null && symbol.equals("-"))
			{
				System.out.println("Error: boolean no se puede restar");
			}
			if(dataType.equals("boolean") && symbol !=null && symbol.equals("*"))
			{
				System.out.println("Error: boolean no se puede multiplicar");
			}
			if(dataType.equals("boolean") && symbol !=null && symbol.equals("%"))
			{
				System.out.println("Error: no puedo sacar modulo a un boolean");
			}
			if(dataType.equals("boolean") && symbol !=null && symbol.equals("<"))
			{
				System.out.println("Error: boolean no se puede <");
			}
			if(dataType.equals("boolean") && symbol !=null && symbol.equals(">"))
			{
				System.out.println("Error: boolean no se puede >");
			}
			if(dataType.equals("boolean") && symbol !=null && symbol.equals("<="))
			{
				System.out.println("Error: boolean no se puede <=");
			}
			if(dataType.equals("boolean") && symbol !=null && symbol.equals(">="))
			{
				System.out.println("Error: boolean no se puede >=");
			}
		}
	}
	
	public static void main(String[] args)
	{
		//objeto de clases
		semantico se = new semantico();
		Sintactico sin = new Sintactico();
		Lexico lex = new Lexico();

		//lista donde poner return de variables
		LinkedList<VariablesSemanticas> varlka = new LinkedList<VariablesSemanticas>();
		//lista donde poner variables de archivo
		ArrayList<String> dec = new ArrayList<>();
		//lista con variables y simbolo
		LinkedList<VariableSymbol> varsim = new LinkedList<VariableSymbol>();

		//analizador lexico
		lex.masterlex();
		//analizador sintactico
		sin.arch();
		//return de obtener lista
		varlka = se.obtenerVariables();
		//return de variables de archivo
		dec = se.buscaVarArch();
		//return de rulesop
		varsim = se.rulesop(dec);

        //System.out.println("variables del archivo"+dec);
        se.multipleDeclaration(varlka);	
		se.notdeclared(dec,varlka);
		se.rulesassigment(varlka);
		se.realrules(varsim,varlka);
	}     
}