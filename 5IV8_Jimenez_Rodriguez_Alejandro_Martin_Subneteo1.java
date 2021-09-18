/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Scanner;


/* Name of the class has to be "Main" only if the class is public. */
class Acs{
	
	public static String convertir(int num){
		int potencia = 128;
		String res = "";
		while(potencia > 0){
			if(num >= potencia){
				num-= potencia;
				res+="1";
			}else{
				res+="0";
			}
			potencia/=2;
		}
		return res;
	}
	
	public static String ipABinario(String s){
		String nueva = "", aux = "";
		for(int i = 0; i < s.length(); ++i){
			if(s.charAt(i) != '.'){
				aux+= s.charAt(i);
			}else{
				nueva+=convertir(Integer.parseInt(aux));
				nueva+=".";
				aux = "";
			}
		}
		nueva += convertir(Integer.parseInt(aux));
		aux = "";
		return nueva;
	}
	
	public static String binario(long num){
		String bin = "", res = "";
		while(num > 0){
			if(num == 2){
				bin+= '1';
				num = 0;
			}else{
				if(num % 2 == 1){
					bin+= "1";
				}else{
					bin+="0";
				}
				num/=2;
			}
		}
		return bin;
	}
	
	public static String sub(String s, long num){
		String aux = binario(num), res = "";
		int ind = 0;
		for(int i = 0; i < s.length(); ++i){
			if(s.charAt(i) == '0'){
				if(ind + 1 <= aux.length()){
					res+= aux.charAt(ind);
					ind++;
				}else{
					res+= s.charAt(i);
				}
			}else{
				res+= s.charAt(i);
			}
		}
		return res;
	}
	
	public static long cHost(String s){
		long res = 1;
		for(int i = 0; i < s.length(); ++i){
			if(s.charAt(i) == '0'){
				res*=2;
			}
		}
		res-=2;
		return res;
	}
	
	public static void subRedes(String ip, int l, int r){
		int num = 0, clase = 0;
		String aux = "";
		for(int i = 0; i < ip.length(); ++i){
			if(ip.charAt(i) == '.'){
				if(Integer.parseInt(aux) > 0){
					clase++;
				}
				aux = "";
			}else{
				aux+= ip.charAt(i);
			}
		}
		String mascara = "", ipOriginal = "";
		long pot = 0, salto = 0;
		if(clase == 1){
			mascara = "255.0.0.0";
			pot = 16777216;
		}else{
			if(clase == 2){
				mascara = "255.255.0.0";
				pot = 65536;
			}else{
				mascara = "255.255.255.0";
				pot = 256;
			}
		}
		
		
		salto = pot / r;
		mascara = ipABinario(mascara);
		ipOriginal = ipABinario(ip);
		System.out.println("IP original en binario: " + ipOriginal);
		System.out.println("Mascara " + mascara);
		System.out.println("Salto de red: " + salto);
		System.out.println("Host: " + cHost(mascara));
		for(int i = l; i <= r; ++i){
			System.out.print("Mascara de Subred No." + i + " ");
			System.out.println(sub(mascara, i * salto));
			
		}
	}
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner Leer = new Scanner(System.in);
		System.out.println("Ingrese la ip y el rango de L-R de las subredes que desea obetener, el salto de red sera conforme a R");
		int L = 0,R = 0;
		String primerIp = "";
		System.out.println("ingrese L");
		L = Leer.nextInt();
		System.out.println("ingrese R");
		R = Leer.nextInt();
                Leer.nextLine();
		System.out.println("ingrese ingrese la IP");
		primerIp = Leer.nextLine();
		subRedes(primerIp, L, R);
	
	}
}