package accessori;

public class Lotto 
{
	//input
	private int IDLotto;
	private String IDProdotto;
	private int numPezzi;
	private float[] tempoUnitarioLavorazione;
	private float tempoDiSetUp;
	private String[] tipologiaMacchina;
	private Priorita priorita;
	
	//autogenerato
	public int getIDLotto() {
		return IDLotto;
	}
	public String getIDProdotto() {
		return IDProdotto;
	}
	public int getNumPezzi() {
		return numPezzi;
	}
	public float[] getTempoUnitarioLavorazione() {
		return tempoUnitarioLavorazione;
	}
	public float getTempoDiSetUp() {
		return tempoDiSetUp;
	}
	public Priorita getPriorita() {
		return priorita;
	}
	public String[] getTipologiaMacchina() {
		return tipologiaMacchina;
	}
	
	
}
