package macchine;
import java.util.ArrayDeque;
import accessori.Lavorazione;

public class Macchina implements Machinable
{
	protected float probGuasto;
	protected float probFineMateriali;
	
	protected String IDMacchina;
	protected StatoMacchina statoMacchina;
	protected ArrayDeque<Lavorazione> codaLavorazioni;
	
	public Macchina(float probGuasto, float probFineMateriali, String IDMacchina)
	{
		this.probFineMateriali = probFineMateriali;
		this.probGuasto = probGuasto;
		this.IDMacchina = IDMacchina;
	}

	@Override
	public void caricaSuServer() {
		// TODO Auto-generated method stub
		
		//calcolo time stamp
		//id macchina è fisso
		//stato macchina è la variabile
		//ID lotto lo prende dalla lavorazione corrente
		//pusha tutto sul server con api
	}

	@Override
	public void inizializzaMacchina() {
		// TODO Auto-generated method stub
		
		//scarica lista lavorazioni con api
	}

	@Override
	public void aggiornaMacchina() {
		// TODO Auto-generated method stub
		
	}
	
}
