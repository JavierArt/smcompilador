package pruebacup;
import java.util.*;
import java.io.*;


public class Lexico {
	
	//lee un archivo y separa las palabras que estan en el por medio del espacio  y retornara una lista de palabras  con las que comparar si estan en el array de  palabras 
	//reservadas se guardan en la lista que se muestra en el main si no estan simplemente se ignoran
	
	private static ArrayList<String> aritmeticos(){
		ArrayList<String> aritmeticos = new ArrayList<String>();
		aritmeticos.add("+");
		aritmeticos.add("-");
		aritmeticos.add("*");
		aritmeticos.add("/");
		aritmeticos.add("%");
		
		return (aritmeticos);
	}

	private static ArrayList<String> logicos(){
		ArrayList<String> logicos = new ArrayList<String>();
		logicos.add("&&");
		logicos.add("||");
		logicos.add("!");
		logicos.add("==");

		return (logicos);
	}

	private static ArrayList<String> relacionales(){
		ArrayList<String> relacionales = new ArrayList<String>();
		relacionales.add(">");
		relacionales.add("<");
		relacionales.add("<=");
		relacionales.add(">=");
		relacionales.add("!=");
		relacionales.add("==");

		return (relacionales);
	}
	
	private static ArrayList<String> reservadas(){
		ArrayList<String> reservadas = new ArrayList<String>();
		reservadas.add("if");
		reservadas.add("while");
		reservadas.add("for");
		reservadas.add("void");
		reservadas.add("int");
		reservadas.add("string");
		reservadas.add("boolean");
		reservadas.add("main");
		reservadas.add("print");
		return (reservadas);
	}	
	
	public void masterlex()
	{
		//arraylist que contiene a las palabras reservadas, Aritmeticos,Logicos,relaciones
		ArrayList<String> resArr = reservadas();
		ArrayList<String> aritArr = aritmeticos();
		ArrayList<String> logArr = logicos();
		ArrayList<String> relArr = relacionales();

		//Array donde se agregara elementos de cada tipo
		ArrayList<String> resADD = new ArrayList<String>();
		ArrayList<String> aritADD = new ArrayList<String>();
		ArrayList<String> logADD = new ArrayList<String>();
		ArrayList<String> relADD = new ArrayList<String>();
		ArrayList<String> simADD = new ArrayList<String>();
		
        // referencia un linea cada vez
        String line = null;
        // contador con numero de linea
        int numline = 0;

        try {
            // FileReader lee los archivos de texto con encoding por defecto.
            FileReader fileReader = new FileReader(fileName);

            // siempre envuelve FileReader en un BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	String[] splited = line.split("\\s+");
            	//for each de cada palabra
            	for (String part : splited) {
            		//comparas si cada lista la palabra de part
        			if(resArr.contains(part)) {
        				resADD.add(part);
        			}
        			if(aritArr.contains(part)) {
        				aritADD.add(part);
        			}
        			if(logArr.contains(part)) {
        				logADD.add(part);
        			}
        			if(relArr.contains(part)) {
        				relADD.add(part);
        			}
        			if(!relADD.contains(part) && !logADD.contains(part) && !aritADD.contains(part) && !resADD.contains(part)) {
        				simADD.add(part);
        			}        			
                }
            	//aumenta el contador
                numline++;
            }
            
            //muestra contador de lineas y listas
            System.out.println(numline);
			System.out.println("Reservadas: "+resADD.toString());
			System.out.println("Aritmeticos: "+aritADD.toString());
			System.out.println("Logicos: "+logADD.toString());
			System.out.println("Relacionales: "+relADD.toString());
			System.out.println("tokens: "+simADD.toString());

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
	
	public static final String fileName= "Entrada.txt";
	
	public static void main(String[] args) {

	}
}
