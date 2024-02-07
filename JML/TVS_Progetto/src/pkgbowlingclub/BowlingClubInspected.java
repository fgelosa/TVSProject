package pkgbowlingclub;

public class BowlingClubInspected {
	protected /*@spec_public@*/ int pista[]; 
	//ho 4 piste occupabili da max 4 giocatori ciascuna
	private /*@spec_public@*/ int guadagnoCorrente; 
	//guadagno corrente della serata		
	private /*@spec_public@*/ int guadagnoSerata; 
	//guadagno totale della serata		
	private /*@spec_public@*/ int guadagnoSettimana[];
	private /*@spec_public@*/ int giornoSettimana;
	//riepilogo settimanale dei guadagni
	protected /*@spec_public@*/ boolean aperto;
	//indica se e' aperto il bowling club
	private /*@spec_public@*/ int numGiocatori;
	//numero totale di giocatori su tutte le piste
	private /*@spec_public@*/ int maxGiocatoriPista = 4;
	private /*@spec_public@*/ int maxGiocatoriTotale = 16;
	
	//INVARIANTI
	//@ public invariant pista.length == 4 && pista != null;
	//@ public invariant guadagnoSerata >= 0;
	//@ public invariant guadagnoCorrente >= 0;
	//@ public invariant giornoSettimana <= 7;
	//@ public invariant numGiocatori >=0 && numGiocatori <= maxGiocatoriTotale;
	//@ public invariant (\forall int i; 0<= i && i < pista.length; pista[i] >= 0);
	//@ public invariant (\forall int i; 0<= i && i < pista.length; pista[i] <= maxGiocatoriPista);
	
	/*@ 
	  @ ensures pista != null && pista.length == 4;
	  @ ensures (\forall int i; 0<= i && i < pista.length; pista[i] == 0);
	  @ ensures guadagnoSerata == 0;
	  @ ensures guadagnoCorrente == 0;
	  @ ensures numGiocatori == 0; 
	  @ ensures aperto == true;
	  @*/
	public BowlingClubInspected(){
		pista = new int[] {0,0,0,0};
		guadagnoSerata = 0;
		guadagnoCorrente = 0;
		guadagnoSettimana = new int[] {0,0,0,0,0,0,0};
		numGiocatori = 0;
		aperto = true;
		}

	//@ ensures \result == (aperto ? guadagnoCorrente : guadagnoSerata);
	public int getGuadagnoGiornaliero() {
		if(aperto)
			return guadagnoCorrente;
		return guadagnoSerata;
	}
	
	//@ ensures \result >= 0;
	public int getGuadagnoSettimana() {
		int tmp = 0;
		for(int i = 0; i<7;i++)
			tmp += guadagnoSettimana[i];
		return tmp;
	}
	
	//@ ensures \old(aperto) ? (numGiocatori == 0 && guadagnoSerata == \old(guadagnoCorrente) && guadagnoCorrente == 0):true;
	public void chiudiBowlingClub() {
		if(aperto) {
			aperto = !aperto;
			int i = 0;
			while(i<4) {
				pista[i] = 0;
				i++;
			}
			numGiocatori = 0;
			guadagnoSerata = guadagnoCorrente;
			guadagnoCorrente = 0;
			guadagnoSettimana[giornoSettimana] = guadagnoSerata;
			giornoSettimana = (giornoSettimana + 1 ) % 7;
		}
	}
	
	//@ ensures \old(!aperto) ? (aperto && numGiocatori == 0 && guadagnoSerata == 0 && guadagnoCorrente == 0):true;
	public void apriBowlingClub() {
		if(!aperto) {
		pista = new int[] {0,0,0,0};
		guadagnoSerata = 0;
		guadagnoCorrente = 0;
		numGiocatori = 0;
		aperto = true;}
	}
		
	/*@ 
	  @ requires aperto;
	  @ requires giocatori <= 4 && giocatori >= 0;
	  @ ensures numGiocatori == \old(numGiocatori) + giocatori;
	  @ ensures guadagnoCorrente == \old(guadagnoCorrente) + 15*giocatori;
	  @*/
	public int occupaPista(int giocatori) {
		if(!aperto) {return -1;}
		if(giocatori>maxGiocatoriPista||giocatori<0) {return -1;}
		int pistaLibera = -1;	
		/*@ loop_invariant
		  @  i >= 0 && i <= 3; 
		  @ */
		for(int i = 0; i < 4;i++) {
			if(pista[i]==0) {
				pistaLibera = i;
				break;}
		}
		if(pistaLibera == -1) {return -1;}
		pista[pistaLibera] = giocatori;
		guadagnoCorrente += giocatori*15;
		numGiocatori += giocatori;
		return pistaLibera;
	}
	
	/*@ 
	  @ requires aperto;
	  @ requires i >= 0 && i <= 3;
	  @ ensures pista[i]==0;
	  @*/
	public void liberaPista(int i) {
		if(i>=0&&i<=3) {
			pista[i] = 0;
		}
	}
}




