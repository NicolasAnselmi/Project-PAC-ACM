package macchine;

public class Tornio extends Macchina
{
private static int numTorni = 1;
	
	public Tornio(float probGuasto, float probFineMateriali)
	{
		super(probGuasto, probFineMateriali, "TORNIO-"+numTorni);
		numTorni++;
	}
}
