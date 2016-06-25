package find_the_number;

import java.util.Random;



public class Numero {
	private int longitud;
	private int[] num;
	private Validar val;

	public Validar getVal() {
		return val;
	}

	public void setVal(Validar val) {
		this.val = val;
	}

	Numero() {
	}
	Numero(int cifras){
		this.longitud=cifras;
	}
	Numero(int cifras,boolean generado) {
		this.longitud = cifras;
		if(generado){
			generarNumero();
		}
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public int[] getNum() {
		return num;
	}

	public void setNum(int[] num) {
		this.num = num;
	}

	private void generarNumero() {
		val = new Validar();
		boolean flag = false;
		this.num = new int[this.longitud];
		Random rnd = new Random();
		while (!flag) {
			for (int i = 0; i < this.longitud; i++) {
				num[i] = rnd.nextInt(10);
			}
			val.setNumero(this.num);
			flag = val.repeticion(this.num);
		}

	}
	public boolean validarTodo(String numero){
		val=new Validar();
		if(val.esNumero(numero)){
			if(val.longitud(numero)){
				val.StrToArray(numero);
				if(val.repeticion(val.getNumero())){
					this.num=val.getNumero();
					return true;
				}
			}
		}
		return false;
	}
}
