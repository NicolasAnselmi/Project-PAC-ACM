import macchine.*;

public class MAIN {
	
	static float pg = 0.9f;
	static float pf = 0.9f;
	static float nTorni = 2;
	static float nFrese = 3;
	static int quant = 30;
	
	public static void main(String[] args) 
	{
		Runnable[] macchine = new ThreadMacchina[5];
		
		for(int i = 0; i < nTorni; i++)
			macchine[i] = new ThreadMacchina(quant, new Tornio(pg, pf));
		
		for(int i = 0; i < nFrese; i++)
			macchine[i] = new ThreadMacchina(quant, new Fresa(pg, pf));
		
		for (Runnable runnable : macchine) 
			runnable.run();
		
	}
}
