package find_the_number;

public class TestNumero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Numero num=new Numero(4);
		
		System.out.println(num.getNum()[0]+""+num.getNum()[1]+""+num.getNum()[2]+""+num.getNum()[3]);
		int i=num.getVal().getNumero()[0];
		System.out.println(i);
		System.out.println(num.validarTodo("1234"));
		System.out.println(num.getNum()[0]+""+num.getNum()[1]+""+num.getNum()[2]+""+num.getNum()[3]);
		int i1=num.getVal().getNumero()[0];
		System.out.println(i1);
	}

}
