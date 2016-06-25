package find_the_number;

public class Juego {
	private Numero numGen;
	private Numero numIngresado;
	private ColeccionPuntaje guardarPtje;
	private Comparar comp;
	
	public Numero getNumGen() {
		return numGen;
	}

	public void setNumGen(Numero numGen) {
		this.numGen = numGen;
	}

	public Numero getNumIngresado() {
		return numIngresado;
	}

	public void setNumIngresado(Numero numIngresado) {
		this.numIngresado = numIngresado;
	}
	
	public Comparar getComp() {
		return comp;
	}

	public void setComp(Comparar comp) {
		this.comp = comp;
	}

	public boolean compararNum(String num){
		comp=new Comparar();
		if(numIngresado.validarTodo(num)){
			comp.setNumJugado(numIngresado);
			comp.setNumGen(numGen);
			comp.Pista();
			return true;
		}
		return false;
	}
	public ColeccionPuntaje getGuardarPtje() {
		return guardarPtje;
	}

	public void setGuardarPtje(ColeccionPuntaje guardarPtje) {
		this.guardarPtje = guardarPtje;
	}

	Juego(){
		numGen=new Numero(4,true);
		numIngresado=new Numero(numGen.getLongitud());
	}
	
}
