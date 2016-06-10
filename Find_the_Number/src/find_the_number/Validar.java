package find_the_number;


public class Validar {
	private int[] numero;

	public void setNumero(int[] num){
		this.numero=num;
	}
	public int[] getNumero(){
		return this.numero;
	}
	public boolean repeticion(){
		boolean flag=false;
		for(int i=0;i<numero.length;i++){
			for (int j=0;j<i; j++){
				if (numero[i]==numero[j]){
					return flag;
				}
			}
		}
		return true;
	}
	public boolean esNumero(String num){
		try{
			Integer.parseInt(num);
		}catch(NumberFormatException e){
			return false;
		}
		
		return true;
	}
	public void StrToArray(String num){
		int[] numero=new int[num.length()];
		for (int i=0;i<numero.length;i++){
			numero[i]=Character.getNumericValue(num.charAt(i));
		}
		this.numero=numero;
	}
	
	//Requiere revision, debido a que longitud
	//varía de acuerdo a numero GENERADO
	public boolean longitud(String num){
		if (num.length()!=4){
			return false;
		}
		return true;
	}
}

