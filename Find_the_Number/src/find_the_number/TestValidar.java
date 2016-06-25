package find_the_number;

public class TestValidar {

	public static void main(String[] args) {
		Validar val=new Validar();
		int[] num=new int[4];
		num[1]=2;
		num[2]=2;
		num[0]=3;
		num[3]=4;
		val.setNumero(num);
		//System.out.println(val.repeticion());
		System.out.println(val.esNumero("e9"));
		val.StrToArray("3758");
		int []num3=val.getNumero();
		for(int i=0;i<4;i++){
			System.out.print(num3[i]);
		}
		System.out.println();
		System.out.println(val.longitud("456lp79"));
		
	}

}
