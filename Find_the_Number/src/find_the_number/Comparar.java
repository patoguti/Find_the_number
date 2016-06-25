package find_the_number;

public class Comparar {
	private Numero numGen;
	private Numero numJugado;
	private int toque;
	private int fama;
	public Numero getNumGen() {
		return numGen;
	}
	public void setNumGen(Numero numGen) {
		this.numGen = numGen;
	}
	public Numero getNumJugado() {
		return numJugado;
	}
	public void setNumJugado(Numero numJugado) {
		this.numJugado = numJugado;
	}
	public int getToque() {
		return toque;
	}
	public void setToque(int toque) {
		this.toque = toque;
	}
	public int getFama() {
		return fama;
	}
	public void setFama(int fama) {
		this.fama = fama;
	}
	public void Pista(){  
        for (int i = 0; i < numGen.getLongitud(); i++) {
            for (int j = 0; j < numGen.getLongitud(); j++) {
                if ((numGen.getNum()[i] == numJugado.getNum()[j]) && (i == j)) {
                    this.fama++;
                }
                else if ((numGen.getNum()[i] == numJugado.getNum()[j]) && (i != j)) {
                    this.toque++;
                }
            }
        }
    }

}
