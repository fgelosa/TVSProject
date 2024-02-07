package pkgbowlingclub;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlingClubCTWTest {

	@Test
	public void testAccetta() {
		BowlingClubCTW bc = new BowlingClubCTW();
		assertEquals(true,bc.accettoClienti(true, 4, 4, 0, BowlingClubCTW.giorno.gio));
	}

	@Test
	public void testRifiuta() {
		BowlingClubCTW bc = new BowlingClubCTW();
		assertEquals(false,bc.accettoClienti(false, 0, 0, 1000, BowlingClubCTW.giorno.ven));
	}
}
