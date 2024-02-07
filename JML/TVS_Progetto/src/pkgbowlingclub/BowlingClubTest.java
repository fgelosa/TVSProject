package pkgbowlingclub;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlingClubTest {

	@Test
	public void JunitTesting() {
		BowlingClub bc = new BowlingClub();
		System.out.println("Occupo pista "+bc.occupaPista(3)+" con 3 giocatori");
		assertEquals(bc.pista[0],3);
		System.out.println("Libero pista 0");
		bc.liberaPista(0);
		assertEquals(bc.pista[0],0);
		System.out.println("Occupo pista "+bc.occupaPista(2)+" con 2 giocatori");
		System.out.println("Occupo pista "+bc.occupaPista(4)+" con 4 giocatori");
		assertEquals(bc.pista[0],2);
		assertEquals(bc.pista[1],4);
		System.out.println("Guadagno Temporaneo: "+bc.getGuadagnoGiornaliero());		
		System.out.println("Libero pista 2");
		bc.liberaPista(2);
		assertEquals(bc.pista[2],0);
		System.out.println("Occupo pista "+bc.occupaPista(2)+" con 2 giocatori");
		assertEquals(bc.pista[2],2);
		System.out.println("Chiudo Bownling Club!");
		bc.chiudiBowlingClub();
		assertEquals(bc.aperto,false);
		System.out.println("Guadagno Giornaliero: "+bc.getGuadagnoGiornaliero());
		System.out.println("Apro Bownling Club!");
		bc.apriBowlingClub();
		assertEquals(bc.aperto,true);
		System.out.println("Occupo pista "+bc.occupaPista(3)+" con 3 giocatori");
		assertEquals(bc.pista[0],3);
		bc.liberaPista(0);
		System.out.println("Occupo pista "+bc.occupaPista(2)+" con 2 giocatori");
		assertEquals(bc.pista[0],2);
		System.out.println("Occupo pista "+bc.occupaPista(4)+" con 4 giocatori");
		assertEquals(bc.pista[1],4);
		System.out.println("Chiudo Bownling Club!");
		bc.chiudiBowlingClub();
		assertEquals(bc.aperto,false);
		System.out.println("Guadagno Giornaliero: "+bc.getGuadagnoGiornaliero());
		System.out.println("Guadagno Settimanale: "+bc.getGuadagnoSettimana());
	}
	
	@Test
	public void statementCoverage() {
		BowlingClub bc = new BowlingClub();
		bc.occupaPista(3);
		bc.liberaPista(3);
		bc.getGuadagnoGiornaliero();
		bc.occupaPista(100);
		bc.occupaPista(-7);
		bc.occupaPista(10);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.chiudiBowlingClub();
		bc.occupaPista(10);
		bc.getGuadagnoGiornaliero();
		bc.getGuadagnoSettimana();
		bc.apriBowlingClub();
	}
	
	@Test
	public void BranchCoverage() {
		BowlingClub bc = new BowlingClub();
		bc.occupaPista(3);
		bc.liberaPista(3);
		bc.apriBowlingClub();
		bc.getGuadagnoGiornaliero();
		bc.occupaPista(100);
		bc.occupaPista(-7);
		bc.occupaPista(10);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.chiudiBowlingClub();
		bc.chiudiBowlingClub();
		bc.liberaPista(44);
		bc.occupaPista(10);
		bc.getGuadagnoGiornaliero();
		bc.getGuadagnoSettimana();
		bc.apriBowlingClub();
	}
	
	@Test
	public void MCDCCoverage() {
		BowlingClub bc = new BowlingClub();
		bc.occupaPista(3);
		bc.liberaPista(3);
		bc.getGuadagnoGiornaliero();
		bc.occupaPista(100);
		bc.occupaPista(-7);
		bc.occupaPista(6);
		bc.occupaPista(10);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.occupaPista(3);
		bc.chiudiBowlingClub();
		bc.chiudiBowlingClub();
		bc.liberaPista(44);
		bc.liberaPista(-1);
		bc.occupaPista(10);
		bc.getGuadagnoGiornaliero();
		bc.getGuadagnoSettimana();
	}
}
