package pruebacup;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

public class Sintactico {
	public static final String fileName="Entrada.txt";

	public void arch()
	{
	    // referencia un linea cada vez
        String line = null;
        int numline=0;
        try {
            // FileReader lee los archivos de texto con encoding por defecto.
            FileReader fileReader = new FileReader(fileName);

            // siempre envuelve FileReader en un BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //while
        	Pattern patwhile = Pattern.compile("^while\\(\\s([a-zA-Z0-9]+) (<|>|<=|>=|!=|==) ([a-zA-Z0-9]+)\\s\\)\\{");
        	Pattern patwhileC = Pattern.compile("while\\( ([a-zA-Z0-9]+ (<|>|<=|>=|!=|==) [a-zA-Z0-9]+ )(&{2}|\\|{2}) ([a-zA-Z0-9]+ (<|>|<=|>=|!=|==) [a-zA-Z0-9]+ )\\)\\{");
        	Pattern patwhileB = Pattern.compile("while\\(\\s+[a-zA-Z]+\\s+\\)\\{");
        	Pattern patwhileXB = Pattern.compile("while\\(\\s([a-zA-Z0-9]+) (<|>|<=|>=|!=|==) ([a-zA-Z0-9]+)\\s\\)\\{");
        	Pattern patwhileN = Pattern.compile("while\\(\\s+[0-9]+\\s+\\)\\{");
       
        	//if
        	Pattern patif = Pattern.compile("if\\(\\s([a-zA-Z0-9]+) (<|>|<=|>=|!=|==) ([a-zA-Z0-9]+)\\s\\)\\{");
        	Pattern patifCM = Pattern.compile("if\\( ([a-zA-Z0-9]+ (<|>|<=|>=|!=|==) [a-zA-Z0-9]+ )(&{2}|\\|{2}) ([a-zA-Z0-9]+ (<|>|<=|>=|!=|==) [a-zA-Z0-9]+ )\\)\\{");
        	Pattern patifB = Pattern.compile("if\\(\\s+[a-zA-Z]+\\s+\\)\\{");
        	Pattern patifXB = Pattern.compile("if\\(\\s([a-zA-Z0-9]+) (<|>|<=|>=|!=|==) ([a-zA-Z0-9]+)\\s\\)\\{");
        	
        	//for
        	Pattern patforC = Pattern.compile("for\\( +(int [a-zA-Z]+|[a-zA-z]+) = [0-9]+; [a-zA-Z0-9]+ (<=|>=|<|>) [0-9]+; [A-Za-z]+(\\+|\\-){2} \\)\\{");
        	Pattern patforE = Pattern.compile("for\\( (string|int)? ?[a-zA-Z]+ : [a-zA-Z]+ \\)\\{");
        	
        	//counter
        	Pattern patcount = Pattern.compile("[a-zA-Z](\\+|\\-){2};");
        	Pattern patcountMr = Pattern.compile("[a-zA-Z](\\+|\\-|\\*|\\/)=[0-9]+;");

        	//print
        	Pattern patprintC = Pattern.compile("print\\( ([\"'])(.*?)\\1 \\+ ([a-zA-Z0-9]+) \\);");
        	Pattern patprintV = Pattern.compile("\\s?print\\( +(?!if|for|while|int|string|boolean)(.*) +\\);");
        	Pattern patprintS = Pattern.compile("print\\(\\s([\"'])(.*?)\\1\\s\\);");
        	
        	//String asignacion
        	Pattern patSH = Pattern.compile("string [a-zA-Z]+;");
        	Pattern patSC = Pattern.compile("^string [a-zA-Z]+\\s?=\\s?([\"'])(.*?)\\1;");
        	
        	//int asignacion
        	Pattern patIH = Pattern.compile("int [a-zA-Z]+;");
        	Pattern patIC = Pattern.compile("int [a-zA-Z]+\\s=\\s[0-9]+;");
        	
        	//bolean asignado
        	Pattern patBH = Pattern.compile("^boolean [a-zA-Z]+;");
        	Pattern patBC = Pattern.compile("^boolean [a-zA-Z]+\\s?=\\s?(true|false);");
        	
        	//main
        	Pattern patmain = Pattern.compile("int main\\(\\)\\{");

        	Matcher patwhileM;
        	Matcher patwhileCM;
        	Matcher patwhileBM;
        	Matcher patwhileXBM;
        	Matcher patwhileNM;

        	Matcher patifM;
        	Matcher patifCMM;
        	Matcher patifBM;
        	Matcher patifBXM;
        	
        	Matcher patforCM;
        	Matcher patforEM;
        	
        	Matcher patcountM;
        	Matcher patcountMrM;
        	
        	Matcher patprintCM;
        	Matcher patprintVM;
        	Matcher patprintSM;
        	
        	Matcher patSHM;
        	Matcher patSCM;
        	
        	Matcher patIHM;
        	Matcher patICM;
        	
        	Matcher patBHM;
        	Matcher patBCM;
        	
        	Matcher patmainM;
        	while((line = bufferedReader.readLine()) != null) {
            	patwhileM = patwhile.matcher(line);
            	patwhileCM = patwhileC.matcher(line);
            	patwhileBM = patwhileB.matcher(line);
            	patwhileXBM = patwhileXB.matcher(line);
            	patwhileNM = patwhileN.matcher(line);

            	patifM = patif.matcher(line);
            	patifCMM = patifCM.matcher(line);
            	patifBM = patifB.matcher(line);
            	patifBXM = patifXB.matcher(line);

            	patforCM = patforC.matcher(line);
            	patforEM = patforE.matcher(line);
            
            	patcountM  = patcount.matcher(line);
            	patcountMrM = patcountMr.matcher(line);
            
            	patprintCM = patprintC.matcher(line);
            	patprintVM = patprintV.matcher(line);
            	patprintSM = patprintS.matcher(line);
            
            	patSHM = patSH.matcher(line);
            	patSCM = patSC.matcher(line);
            	
            	patIHM = patIH.matcher(line);
            	patICM = patIC.matcher(line);
            	
            	patBHM = patBH.matcher(line);
            	patBCM = patBC.matcher(line);
            	
            	patmainM = patmain.matcher(line);
            	if(line.startsWith("wh")) {
	            	if(!patwhileM.matches() && !patwhileCM.matches() && !patwhileBM.matches() && !patwhileXBM.matches() && !patwhileM.matches() && !patwhileNM.matches())
	            		System.out.println("Error de sintaxis en la linea: "+ numline);
            	}
            	if(line.startsWith("if")) {
	            	if(!patifM.matches() && !patifCMM.lookingAt() && !patifBM.matches() && patifBXM.matches())
	            		System.out.println("Error de sintaxis en la linea: " + numline);
            	}
            	if(line.startsWith("fo")) {
	            	if(!patforCM.matches() && !patforEM.matches())
	            		System.out.println("Error de sintaxis en la linea " + numline);
            	}
            	if(line.matches("[a-zA-Z]+\\+")) {
	            	if(!patcountM.matches() && !patcountMrM.matches())
	            		System.out.println("Error de sintaxis en la linea " + numline);
            	}
            	if(line.startsWith("pr")) {
	            	if(!patprintCM.matches() && !patprintVM.matches() && !patprintSM.matches())
	            		System.out.println("Error de sintaxis en la linea " + numline);
            	}
            	if(line.startsWith("string")) {
	        		if(!patSHM.matches() && !patSCM.matches())
	        			System.out.println("Error de sintaxis en la linea " + numline);
            	}
        		if(line.startsWith("in")) {
	        		if(!patIHM.matches() && !patICM.matches() && !patmainM.matches())
	        			System.out.println("Error de sintaxis en la linea " + numline);
        		}
        		if(line.startsWith("bo")) {
	            	if(!patBHM.matches() && !patBCM.matches())
	            		System.out.println("Error de sintaxis en la linea wwhy" + numline);
        		}
        		numline++;
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
	}
	
}
