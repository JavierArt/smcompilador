//suma, resta, multiplicacion cuadrado, multiplicacion asignada(imul), nultiplicacion, sum,sub,mul,div, raiz cuadrada floating point, division,sin,cos,tan,multiplicacion ^n,log hiperbolicas revisar sin cos tan

package pruebacup;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;

public class GeneracionCodigo {
		
	HashMap<String, Integer> reg8b = new HashMap<>(); 
	HashMap<String, Integer> reg16b = new HashMap<>(); 
	HashMap<String, Integer> reg32b = new HashMap<>(); 
	HashMap<String, Integer> reg64b = new HashMap<>(); 
	HashMap<String, Double> reg128b = new HashMap<>();
	
    private static <T> T getLastElement(final Iterable<T> elements) {
        final Iterator<T> itr = elements.iterator();
        T lastElement = itr.next();

        while(itr.hasNext()) {
            lastElement=itr.next();
        }

        return lastElement;
    }

    //movss addss,subss,mulss,divss,sqrtss
    public void asm(String instruccion,String registro, Double variable)
    {
    	double valor;
    	double res=0;
    	if(instruccion.equals("movss"))
    	{
    		//128 and 256
    		if(registro.equals("xmm0") || registro.equals("xmm1") || registro.equals("xmm2") || registro.equals("xmm3") || registro.equals("xmm4") || registro.equals("xmm5")
    			|| registro.equals("xmm6") || registro.equals("xmm7") || registro.equals("xmm8") || registro.equals("xmm9") || registro.equals("xmm10") || registro.equals("xmm11")
    			|| registro.equals("xmm12") || registro.equals("xmm13") || registro.equals("xmm14") || registro.equals("xmm15"))
    				reg128b.put(registro, variable);
    	}
    	if(instruccion.equals("addss"))
    	{
    		if(reg128b.containsKey(registro))
    		{
    			valor = reg128b.get(registro);
				res = valor + variable;
				reg128b.put(registro, res);
    		}
    	}
    	if(instruccion.equals("subss"))
    	{
    		if(reg128b.containsKey(registro)) {
				valor = reg128b.get(registro);
				res = valor - variable;
				reg128b.put(registro, res);
			}
    	}
    	if(instruccion.equals("mulss"))
    	{
    		if(reg128b.containsKey(registro)) {
				valor = reg128b.get(registro);
				res = valor * variable;
				reg128b.put(registro, res);
			}
    	}
    	if(instruccion.equals("divss"))
    	{
    		if(reg128b.containsKey(registro))
    		{
    			valor = reg128b.get(registro);
    			res = valor / variable;
    			reg128b.put(registro, res);
    		}
    	}
    	if(instruccion.equals("sqrtss"))
    	{
    		res = Math.sqrt(variable);
    		reg128b.put(registro, res);
    	}
    }

    //mov add, sub
	public void asm(String instruccion,String registro, Integer variable)
	{
		int valor;
		int res = 0;
		if(instruccion.equals("mov"))
		{
			//8 bits
			if(registro.equals("al") || registro.equals("bl") || registro.equals("cl") || registro.equals("sil") || registro.equals("dil") || registro.equals("bpl") 
				|| registro.equals("spl") || registro.equals("r8b") || registro.equals("r9b") || registro.equals("r10b") || registro.equals("r11b") || registro.equals("r12b"))
				reg8b.put(registro,variable);

			//16 bits
			if(registro.equals("ax") || registro.equals("bx") || registro.equals("cx") || registro.equals("dx") || registro.equals("si") || registro.equals("di") 
				|| registro.equals("bp") || registro.equals("sp") || registro.equals("r8w") || registro.equals("r9w") || registro.equals("r10w") || registro.equals("r11w"))
				reg16b.put(registro,variable);
			
			//32 bits	
			if(registro.equals("eax") || registro.equals("ebx") || registro.equals("ecx") || registro.equals("edx") || registro.equals("esi") || registro.equals("edi")
					|| registro.equals("ebp") || registro.equals("esp") || registro.equals("r8d") || registro.equals("r9d") || registro.equals("r10d") || registro.equals("r11d"))
				reg32b.put(registro,variable);
			
			//64 bits
			if(registro.equals("rax") || registro.equals("rbx") || registro.equals("rcx") || registro.equals("rdx") || registro.equals("rsi") || registro.equals("rdi")
					|| registro.equals("rbp") || registro.equals("rsp") || registro.equals("r8") || registro.equals("r9") || registro.equals("r10") || registro.equals("r11"))
				reg64b.put(registro,variable);
		}
		if(instruccion.equals("add"))
		{
			if(reg8b.containsKey(registro)) {
				valor = reg8b.get(registro);
				res = Math.abs(valor) + Math.abs(variable);
				reg8b.put(registro, res);
			}
			if(reg16b.containsKey(registro)) {
				valor = reg16b.get(registro);
				res = Math.abs(valor) + Math.abs(variable);
				reg16b.put(registro, res);
			}
			if(reg32b.containsKey(registro)) {
				valor = reg32b.get(registro);
				res = Math.abs(valor) + Math.abs(variable);
				reg32b.put(registro, res);
			}
			if(reg64b.containsKey(registro)) {
				valor = reg64b.get(registro);
				res = Math.abs(valor) + Math.abs(variable);
				reg64b.put(registro, res);
			}
		}
		if(instruccion.equals("sub"))
		{
			if(reg8b.containsKey(registro)) {
				valor = reg8b.get(registro);
				res = Math.abs(valor) - Math.abs(variable);
				reg8b.put(registro, res);
			}
			if(reg16b.containsKey(registro)) {
				valor = reg16b.get(registro);
				res = Math.abs(valor) - Math.abs(variable);
				reg16b.put(registro, res);
			}
			if(reg32b.containsKey(registro)) {
				valor = reg32b.get(registro);
				res = Math.abs(valor) - Math.abs(variable);
				reg32b.put(registro, res);
			}
			if(reg64b.containsKey(registro)) {
				valor = reg64b.get(registro);
				res = Math.abs(valor) - Math.abs(variable);
				reg64b.put(registro, res);
			}
		}
		if(instruccion.equals("imul"))
		{
			if(reg8b.containsKey(registro)) {
				valor = reg8b.get(registro);
				res = valor * variable;
				reg8b.put(registro, res);
			}
			if(reg16b.containsKey(registro)) {
				valor = reg16b.get(registro);
				res = valor * variable;
				reg16b.put(registro, res);
			}
			if(reg32b.containsKey(registro)) {
				valor = reg32b.get(registro);
				res = valor * variable;
				reg32b.put(registro, res);
			}
			if(reg64b.containsKey(registro)) {
				valor = reg64b.get(registro);
				res = valor * variable;
				reg64b.put(registro, res);
			}
		}

	}
	
	//mul^2
	public void asm(String instruccion,String registro){ 
		int valor=0;
		int res;
		if(instruccion.equals("mul"))
		{
			if(reg8b.containsKey(registro)) {
				valor = reg8b.get(registro);
				res = valor * valor;
				reg8b.put(registro, res);
			}
			if(reg16b.containsKey(registro)) {
				valor = reg16b.get(registro);
				res = valor - valor;
				reg16b.put(registro, res);
			}
			if(reg32b.containsKey(registro)) {
				valor = reg32b.get(registro);
				res = valor - valor;
				reg32b.put(registro, res);
			}
			if(reg64b.containsKey(registro)) {
				valor = reg64b.get(registro);
				res = valor - valor;
				reg64b.put(registro, res);
			}
		}
	}

	//multiplicacion por variable
	public void asm(String instruccion,Integer variable)
	{
		int res=0;
		Integer last;
		String lastr;
		Collection<Integer> values8 = reg8b.values();
		Collection<Integer> values16 = reg16b.values();
		Collection<Integer> values32 = reg32b.values();
		Collection<Integer> values64 = reg64b.values();
		Collection<String> values8keys = reg8b.keySet();
		Collection<String> values16keys = reg16b.keySet();
		Collection<String> values32keys = reg32b.keySet();
		Collection<String> values64keys = reg64b.keySet();
		if(instruccion.equals("mul"))
		{	
			if(!values8keys.isEmpty())
			{
				last = getLastElement(values8);
				lastr = getLastElement(values8keys);
				res = last * Math.abs(variable);
				reg8b.put(lastr, res);
			}
			if(!values16keys.isEmpty())
			{
				last = getLastElement(values16);
				lastr = getLastElement(values16keys);
				res = last * Math.abs(variable);
				reg16b.put(lastr, res);
			}
			if(!values32keys.isEmpty())
			{		
				last = getLastElement(values32);
				lastr = getLastElement(values32keys);
				res = last * Math.abs(variable);
				reg32b.put(lastr, res);
			}
			if(!values64keys.isEmpty())
			{
				last = getLastElement(values64);
				lastr = getLastElement(values64keys);
				res = last * Math.abs(variable);
				reg64b.put(lastr, res);
			}
		}
		if(instruccion.equals("div"))
		{
			if(!values8keys.isEmpty())
			{
				last = getLastElement(values8);
				lastr = getLastElement(values8keys);
				res = last / Math.abs(variable);
				reg8b.put(lastr, res);
			}
			if(!values16keys.isEmpty())
			{
				last = getLastElement(values16);
				lastr = getLastElement(values16keys);
				res = last / Math.abs(variable);
				reg16b.put(lastr, res);
			}
			if(!values32keys.isEmpty())
			{		
				last = getLastElement(values32);
				lastr = getLastElement(values32keys);
				res = last / Math.abs(variable);
				reg32b.put(lastr, res);
			}
			if(!values64keys.isEmpty())
			{
				last = getLastElement(values64);
				lastr = getLastElement(values64keys);
				res = last / Math.abs(variable);
				reg64b.put(lastr, res);
			}			
		}
		if(instruccion.equals("idiv"))
		{
			if(!values8keys.isEmpty())
			{
				last = getLastElement(values8);
				lastr = getLastElement(values8keys);
				res = last / variable;
				reg8b.put(lastr, res);
			}
			if(!values16keys.isEmpty())
			{
				last = getLastElement(values16);
				lastr = getLastElement(values16keys);
				res = last / variable;
				reg16b.put(lastr, res);
			}
			if(!values32keys.isEmpty())
			{		
				last = getLastElement(values32);
				lastr = getLastElement(values32keys);
				res = last / variable;
				reg32b.put(lastr, res);
			}
			if(!values64keys.isEmpty())
			{
				last = getLastElement(values64);
				lastr = getLastElement(values64keys);
				res = last / variable;
				reg64b.put(lastr, res);
			}
		}
	}
		
	//comparacion
	private boolean asmcmp(String instruccion,int variable,int comparador)
	{
		if(instruccion.equals("cmp"))
		{
			if(variable==comparador)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}

	//comparacion
	private boolean asmcmp(String instruccion,Double variable,Double comparador)
	{
		if(instruccion.equals("cmp"))
		{
			if(Math.abs(variable)==Math.abs(comparador))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}

	//para factorial con ensamblador
	public int asm(int num)
	{
			int bans = 0;
			boolean t = false;
			int min=num-1; //numero para multiplicar
			asm("mov", "al", num); //num a al
			while(t!=true) {
				asm("imul","al",min); //al = num*min
				bans=asm("mov",bans,"al");
				asm("mov","al",bans);
				min--;
				t=asmcmp("cmp", min,1);
			}
		return bans;
	}
	
	public Double asmp(Double base,Double exp)
	{
		Double bans=0.0;
		Double cont=1.0;
		boolean t=false;
		asm("movss","xmm2",base);
		asm("mulss","xmm2");
		while(t!=true)
		{
			asm("mulss","xmm2",base);
			bans=asm("movss", bans,"xmm2");
			cont++;
			t=asmcmp("cmp",cont,exp);
		}
		return bans;
	}

	public Double asmpneg(Double base,Double exp)
	{
		Double bans=0.0;
		Double cont=1.0;
		Double preans=0.0;
		boolean t=false;
		asm("movss","xmm2",base);
		asm("mulss","xmm2");
		while(t!=true)
		{
			asm("mulss","xmm2",base);
			preans=asm("movss", preans,"xmm2");
			cont++;
			t=asmcmp("cmp",cont,exp);
		}
		bans=1/preans;
		return bans;
	}

	public void asmlog(Double d)
	{
		Double cont=0.0;
		Double bans=0.0;
		boolean t=false;
		asm("movss","xmm0",d);
		while(t!=true)
		{
			asm("divss","xmm0",10.0);
			bans=asm("movss",bans,"xmm0");
			asm("movss","xmm0",bans);
			cont++;
			t= (bans>=1.0 && bans<10.0) ? true:false;
		}
		String banS = String.valueOf(bans);
		float decNumbert = Float.parseFloat(banS.substring(banS.indexOf('.')));
		System.out.println("resultado aproximado: "+ cont+decNumbert);
	}
	
	public Double asmsin(int grados)
	{
		Double bans=0.0;
		Double resqr=0.0;
		if(grados == 30) {
			asm("movss","xmm0",1.0);
			asm("divss","xmm0",2.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 45) {
			asm("sqrtss","xmm0",2.0);
			resqr=asm("movss", resqr, "xmm0");
			asm("movss","xmm0",resqr);
			asm("divss","xmm0",2.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 60) {
			asm("sqrtss","xmm0",3.0);
			resqr=asm("movss", resqr, "xmm0");
			asm("movss","xmm0",resqr);
			asm("divss","xmm0",2.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 90) {
			asm("movss","xmm0",1.0);
			asm("divss","xmm0",1.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 180) {
			asm("movss","xmm0",0.0);
			asm("divss","xmm0",0.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 270) {
			asm("movss","xmm0",1.0);
			asm("divss","xmm0",-1.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 360) {
			asm("movss","xmm0",0.0);
			asm("divss","xmm0",0.0);
			bans=asm("movss",bans,"xmm0");
		}
		return bans;
	}

	public Double asmcos(int grados)
	{
		Double bans=0.0;
		Double resqr=0.0;
		if(grados == 30) {
			asm("sqrtss","xmm0",3.0);
			resqr=asm("movss", resqr, "xmm0");
			asm("movss","xmm0",resqr);
			asm("divss","xmm0",2.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 45) {
			asm("sqrtss","xmm0",2.0);
			resqr=asm("movss", resqr, "xmm0");
			asm("movss","xmm0",resqr);
			asm("divss","xmm0",2.0);
			bans=asm("movss",bans,"xmm0");

		}
		if(grados == 60) {
			asm("movss","xmm0",1.0);
			asm("divss","xmm0",2.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 90) {
			asm("movss","xmm0",0.0);
			asm("divss","xmm0",0.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 180) {
			asm("movss","xmm0",-1);
			asm("divss","xmm0",1);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 270) {
			asm("movss","xmm0",0);
			asm("divss","xmm0",0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 360) {
			asm("movss","xmm0",1);
			asm("divss","xmm0",1);
			bans=asm("movss",bans,"xmm0");
		}
		return bans;
	}

	public Double asmtan(int grados)
	{
		Double bans=0.0;
		Double resqr=0.0;
		if(grados == 30) {
			asm("sqrtss","xmm0",3.0);
			resqr=asm("movss", resqr, "xmm0");
			asm("movss","xmm0",resqr);
			asm("divss","xmm0",3.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 45) {
			asm("movss","xmm0",1.0);
			asm("divss","xmm0",1.0);
			bans=asm("movss",bans,"xmm0");

		}
		if(grados == 60) {
			asm("sqrtss","xmm0",3.0);
			bans=asm("movss", bans, "xmm0");
		}
		if(grados == 90) {
			System.out.println("undefined");
		}
		if(grados == 180) {
			asm("movss","xmm0",0.0);
			asm("divss","xmm0",0.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 270) {
			System.out.println("undefined");
		}
		if(grados == 360) {
			asm("movss","xmm0",0.0);
			asm("divss","xmm0",0.0);
			bans=asm("movss",bans,"xmm0");
		}
		return bans;
	}

	public Double asmcottan(int grados)
	{
		Double bans=0.0;
		Double resqr=0.0;
		if(grados == 30) {
			asm("sqrtss","xmm0",3.0);
			bans=asm("movss", bans, "xmm0");
		}
		if(grados == 45) {
			asm("movss","xmm0",1.0);
			asm("divss","xmm0",1.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 60) {
			asm("sqrtss","xmm0",3.0);
			resqr=asm("movss", resqr, "xmm0");
			asm("movss","xmm0",resqr);
			asm("divss","xmm0",3.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 90) {
			asm("movss","xmm0",0.0);
			asm("divss","xmm0",0.0);
			bans=asm("movss",bans,"xmm0");

		}
		if(grados == 180) {
			System.out.println("undefined");
		}
		if(grados == 270) {
			asm("movss","xmm0",0.0);
			asm("divss","xmm0",0.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 360) {
			System.out.println("undefined");
		}
		return bans;
	}

	public Double asmsec(int grados)
	{
		Double bans=0.0;
		Double resqr=0.0;
		Double preres=0.0;
		if(grados == 30) {
			asm("sqrtss","xmm0",3.0);
			resqr=asm("movss", resqr, "xmm0");
			asm("movss","xmm0",resqr);
			asm("divss","xmm0",3.0);
			preres=asm("movss",preres,"xmm0");
			asm("mulss","xmm0",2);
			bans=asm("movss", bans, "xmm0");
		}
		if(grados == 45) {
			asm("sqrtss","xmm0",2.0);
			bans=asm("movss", bans, "xmm0");
		}
		if(grados == 60) {
			asm("movss","xmm0",2.0);
			asm("divss","xmm0",1.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 90) {
			System.out.println("undefined");
		}
		if(grados == 180) {
			asm("movss","xmm0",1.0);
			asm("divss","xmm0",-1.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 270) {
			System.out.println("undefined");
		}
		if(grados == 360) {
			asm("movss","xmm0",1.0);
			asm("divss","xmm0",1.0);
			bans=asm("movss",bans,"xmm0");
		}
		return bans;
	}

	public Double asmcosec(int grados)
	{
		Double bans=0.0;
		Double resqr=0.0;
		Double preres=0.0;
		if(grados == 30) {
			asm("movss","xmm0",2.0);
			asm("divss","xmm0",1.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 45) {
			asm("sqrtss","xmm0",2.0);
			bans=asm("movss", bans, "xmm0");
		}
		if(grados == 60) {
			asm("sqrtss","xmm0",3.0);
			resqr=asm("movss", resqr, "xmm0");
			asm("movss","xmm0",resqr);
			asm("divss","xmm0",3.0);
			preres=asm("movss",preres,"xmm0");
			asm("mulss","xmm0",2);
			bans=asm("movss", bans, "xmm0");
		}
		if(grados == 90) {
			asm("movss","xmm0",1.0);
			asm("divss","xmm0",1.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 180) {
			System.out.println("undefined");
		}
		if(grados == 270) {
			asm("movss","xmm0",1.0);
			asm("divss","xmm0",-1.0);
			bans=asm("movss",bans,"xmm0");
		}
		if(grados == 360) {
			System.out.println("undefined");
		}
		return bans;
	}


	public Double asmsinh(Double numero)
	{
		Double bans=0.0;
		Double res1=asmp(Math.E,numero);
		Double res2=asmpneg(Math.E, -numero);
		bans= ((res1 - res2)/2);
		return bans;
	}
	
	public Double asmcosh(Double numero)
	{
		Double bans=0.0;
		Double res1=asmp(Math.E,numero);
		Double res2=asmpneg(Math.E, -numero);
		bans=( res1 + res2 )/2;
		return bans;
	}
	
	public Double asmtanh(Double numero)
	{
		Double bans=0.0;
		bans=(asmp(Math.E,numero) - asmpneg(Math.E,-numero))/(asmp(Math.E,numero) + asmpneg(Math.E,-numero));
		return bans;
	}
	
	public Double asmcoth(Double numero)
	{
		Double bans=0.0;		
		bans=(asmp(Math.E,numero) + asmpneg(Math.E,-numero))/(asmp(Math.E,numero) - asmpneg(Math.E,-numero));		
		return bans;
	}
	
	public Double asmsech(Double numero)
	{
		Double bans=0.0;		
		bans=2/(asmp(Math.E,numero) + asmpneg(Math.E,-numero));
		return bans;
	}
	
	public Double asmcosech(Double numero)
	{
		Double bans=0.0;		
		bans=2/(asmp(Math.E,numero) - asmpneg(Math.E,-numero));
		return bans;
	}

	//ultima linea
	public int asm(String instruccion,Integer variable, String registro)
	{
		int valor;
		if(instruccion.equals("mov"))
		{
			if(reg8b.containsKey(registro)) {
				valor = reg8b.get(registro);
				variable=valor;
			}
			if(reg16b.containsKey(registro)) {
				valor = reg16b.get(registro);
				variable=valor;
			}
			if(reg32b.containsKey(registro)) {
				valor = reg32b.get(registro);
				variable=valor;
			}
			if(reg64b.containsKey(registro)) {
				valor = reg64b.get(registro);
				variable=valor;
			}
		}
		return variable;
	}	

	//ultima linea floating point
	public Double asm(String instruccion,Double variable, String registro)
	{
		Double valor;
		if(instruccion.equals("movss"))
		{
			if(reg128b.containsKey(registro)) {
				valor = reg128b.get(registro);
				variable=valor;
			}
		}
		return variable;
	}	

	//funcion para imprimir
	public void print(int res)
	{
		System.out.format("resultado: %d", res);
	}

	//funcion para imprimir floating point
	public void print(Double res)
	{
		System.out.format("resultado: %f", res);
	}
	private void print(long res) {
		System.out.format("resultado: %08d%n", res);
	}
	
	public static void main(String[] args) {
		GeneracionCodigo gen = new GeneracionCodigo();
		int a=9;
		int b=3;
		int bans=0;

		double catop=30.0;
		double catad=1.0;
		double hip=2.0;
		
		double c=4.0;
		double d= 3.5;
		double bansf=0.0;
		
		//suma add
		/*gen.asm("mov", "al", a);
		gen.asm("add","al", b);
		bans=gen.asm("mov", bans,"al");*/
		
		//resta
/*		gen.asm("mov", "bl", a);
		gen.asm("sub","bl", b);
		bans=gen.asm("mov", bans,"bl");*/
		
		//multiplicacion^2
/*		gen.asm("mov", "bl",a);
		gen.asm("mul", "bl");
		bans = gen.asm("mov", bans, "bl");*/
		
		//imul le importa el signo
/*		gen.asm("mov", "al",a);
		gen.asm("imul", "al",b);
		bans=gen.asm("mov", bans,"al");*/
		
		//multiplicacion no le importa el signo
/*		gen.asm("mov","al", a);
		gen.asm("mul", b);
		bans=gen.asm("mov", bans,"al");*/
			
		//division div  no le importa el signo
/*		gen.asm("mov", "al",a);
		gen.asm("div", b);
		bans=gen.asm("mov", bans,"al");*/
		
		//division idiv le importa el signo
/*		gen.asm("mov", "al",a);
		gen.asm("idiv", b);
		bans=gen.asm("mov", bans,"al");*/
		
		//factorial asm(Integer variable)
		//bans=gen.asm(5);
		
		//potencia a la n cambiar bansf print
		//bansf=gen.asmp(3.0,3.0);
		
		//potencia a la -n
		//bansf=gen.asmpneg(3.0, -3.0);
		
		//logaritmo
		//gen.asmlog(100.0);
		
		//floating point solo es cambiar addss,subss,mulss,divss y el print 
		/*gen.asm("movss", "xmm0", c);
		gen.asm("addss","xmm0",d);
		bansf=gen.asm("movss", bansf, "xmm0");*/
		
		/*gen.asm("movss", "xmm0", c);
		gen.asm("subss","xmm0",d);
		bansf=gen.asm("movss", bansf, "xmm0");*/
		
		/*gen.asm("movss", "xmm0", c);
		gen.asm("mulss","xmm0",d);
		bansf=gen.asm("movss", bansf, "xmm0");*/
		
		/*gen.asm("movss", "xmm0", c);
		gen.asm("divss","xmm0",d);
		bansf=gen.asm("movss", bansf, "xmm0");*/

		//floating point squared error sqrtss y cambiar print
/*		gen.asm("sqrtss","xmm0",4.0);
		bansf=gen.asm("movss", bansf, "xmm0");*/

		//sin(x)
		//bansf=gen.asmsin(90);
		
		//cos(x)
		//bansf=gen.asmcos(60);
		
		//tan(x)
		//bansf=gen.asmtan(30);

		//cotan(x)
		//bansf=gen.asmcottan(45);
		
		//sec(x)
		//bansf=gen.asmsec(45);
		
		//cosec(x)
		//bansf=gen.asmcosec(90);
		
		//sinh
		//bansf=gen.asmsinh(6.0);
		//cosh
		//bansf=gen.asmcosh(6.0);
		//tanh
		//bansf=gen.asmtanh(4.0);
		//coth
		//bansf=gen.asmcoth(2.0);
		//sech
		//bansf=gen.asmsech(11.0);
		//cosech		
		//bansf=gen.asmcosech(9.0);
		gen.print(bansf);
	}
}
