package macchina;

import com.google.cloud.Timestamp;

import gestoreNotifiche.GestoreNotifiche;


public class Macchina implements Machinable {
	private String idMacchina;
	private String codiceLottoInLavorazione;
	private String timeStampUltimoMessaggio;
	private StatoMacchina statoMacchina;
	private TipoMacchina tipoMacchina;

	private GestoreNotifiche gestoreNotifiche;

	private Macchina() {
		gestoreNotifiche = GestoreNotifiche.getGestoreNotifiche();
	}

	public Macchina(String idMacchina, String tipoMacchina) {
		this();
		this.idMacchina = idMacchina;
		this.codiceLottoInLavorazione = "";
		this.timeStampUltimoMessaggio = Timestamp.now().toString();
		this.statoMacchina = StatoMacchina.Fermo;
		this.tipoMacchina = TipoMacchina.valueOf(tipoMacchina);
	}

	/**
	 * scompatta json, aggiunge log, DOVE AGGIUNGE LOG? manda solo notifiche mi
	 * sembra
	 */
	@Override
	public void aggiornaMacchina(String codiceLottoInLavorazione, String timeStampMessaggio, StatoMacchina statoMacchina) {
		this.codiceLottoInLavorazione = codiceLottoInLavorazione;
		this.timeStampUltimoMessaggio = timeStampMessaggio;
		if (!this.statoMacchina.equals(statoMacchina)) {
			String body, title;
			
			switch (statoMacchina) {
			case AttesaMateriale:
				body = this.idMacchina + " ha terminato il materiale e la produzione si è fermata";
				title = this.idMacchina + " FINE MATERIALE";
				break;
			case Guasta:
				body = this.idMacchina + " si è guastata e la produzione si è fermata";
				title = this.idMacchina + " GUASTA";
				break;
			case Fermo:
				body = this.idMacchina + " ha terminato la lavorazione";
				title = this.idMacchina + " LAVORAZIONE TERMINATA";
				break;
				
			default:
				this.statoMacchina = statoMacchina;
				return;
			}
			
			gestoreNotifiche.sendOperai(body, title);
			System.out.println("stampa " + title);
			this.statoMacchina = statoMacchina;
		}
	}

	public String getCodiceLottoInLavorazione() {
		return codiceLottoInLavorazione;
	}

	public void setCodiceLottoInLavorazione(String codiceLottoInLavorazione) {
		this.codiceLottoInLavorazione = codiceLottoInLavorazione;
	}

	public StatoMacchina getStatoMacchina() {
		return statoMacchina;
	}

	public String getidMacchina() {
		return idMacchina;
	}

	public String getTimeStampUltimoMessaggio() {
		return timeStampUltimoMessaggio;
	}
	
	public TipoMacchina getTipoMacchina()
	{
		return tipoMacchina;
	}

}
