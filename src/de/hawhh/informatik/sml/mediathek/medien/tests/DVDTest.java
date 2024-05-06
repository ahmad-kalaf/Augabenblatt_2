package de.hawhh.informatik.sml.mediathek.medien.tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.medien.AbstractMedium;
import de.hawhh.informatik.sml.mediathek.medien.DVD;

public class DVDTest extends AbstractMediumTest
{
	private static final String BEZEICHNUNG = "DVD";
	private static final String REGISSEUR = "DVD Regisseur";
	private DVD _dvd1;

	public DVDTest()
	{
		_dvd1 = getDVD();
	}

	@Test
	public void testGetMedienBezeichnung()
	{
		String dvdBezeichnung = BEZEICHNUNG;
		assertEquals(dvdBezeichnung, _dvd1.getMedienBezeichnung());
	}

	@Test
	public void testKonstruktor()
	{
		assertEquals(LAENGE, _dvd1.getLaufzeit());
		assertEquals(REGISSEUR, _dvd1.getRegisseur());
	}

	@Test
	public final void testSetter()
	{
		_dvd1.setLaufzeit(90);
		assertEquals(90, _dvd1.getLaufzeit());
		_dvd1.setRegisseur("Regisseur2");
		assertEquals("Regisseur2", _dvd1.getRegisseur());
	}

	protected DVD getDVD()
	{
		return new DVD(TITEL, KOMMENTAR, REGISSEUR, LAENGE);
	}

	@Override
	protected AbstractMedium getMedium()
	{
		return getDVD();
	}
}
