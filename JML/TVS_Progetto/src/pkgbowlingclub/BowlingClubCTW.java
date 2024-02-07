package pkgbowlingclub;

public class BowlingClubCTW {
		private boolean aperto;
		private int pista[];
		private int guadagno;
		public static enum giorno{lun,mar,mer,gio,ven,sab,dom};
	
		public BowlingClubCTW() {
			aperto = false;
			pista = new int[5];
			guadagno = 0;
		}
		
		public boolean accettoClienti(boolean aperto,int giocatori,int pista,int guadagno, giorno giornosett) {
			if(!aperto)
				return false;
			if(giocatori > 4 || giocatori < 0)
				return false;
			if(pista > 5 | pista < 0)
				return false;
			if(guadagno <0 || guadagno > 1000)
				return false;
			return true;
		}
}
