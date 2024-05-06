package de.hawhh.informatik.sml.mediathek.medien.tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.medien.AbstractVideospiel;
import de.hawhh.informatik.sml.mediathek.medien.PCVideospiel;
import de.hawhh.informatik.sml.mediathek.wertklassen.Geldbetrag;

public class PCVideospielTest extends AbstractVideospielTest
{
	
	private static final String BEZEICHNUNG = "PCVideospiel";

	public PCVideospielTest()
	{
	}

	@Test
	public void testBerechneMietgebuehrErsteSiebenTage()
	{
		for(int i = 1; i <= 7; i++) {
			assertEquals(Geldbetrag.get(200), _videospiel.berechneMietgebuehr(i));
		}
	}

	@Test
	public void testBerechneMietgebuehrAeqKlasseEins()
	{
		assertEquals(Geldbetrag.get(700), _videospiel.berechneMietgebuehr(8));
		assertEquals(Geldbetrag.get(700), _videospiel.berechneMietgebuehr(9));
		assertEquals(Geldbetrag.get(700), _videospiel.berechneMietgebuehr(10));
		assertEquals(Geldbetrag.get(700), _videospiel.berechneMietgebuehr(11));
		assertEquals(Geldbetrag.get(700), _videospiel.berechneMietgebuehr(12));
	}

	@Test
	public void testBerechneMietgebuehrAeqKlasseZwei()
	{
		assertEquals(Geldbetrag.get(2200), _videospiel.berechneMietgebuehr(23));
		assertEquals(Geldbetrag.get(2200), _videospiel.berechneMietgebuehr(24));
		assertEquals(Geldbetrag.get(2200), _videospiel.berechneMietgebuehr(25));
		assertEquals(Geldbetrag.get(2200), _videospiel.berechneMietgebuehr(26));
		assertEquals(Geldbetrag.get(2200), _videospiel.berechneMietgebuehr(27));
	}

	@Test
	public void testBerechneMietgebuehrGrenzwerteEins()
	{
		assertEquals(Geldbetrag.get(200), _videospiel.berechneMietgebuehr(7));
		assertEquals(Geldbetrag.get(700), _videospiel.berechneMietgebuehr(8));
	}

	@Test
	public void testBerechneMietgebuehrGrenzwerteZwei()
	{
		assertEquals(Geldbetrag.get(2200), _videospiel.berechneMietgebuehr(27));
		assertEquals(Geldbetrag.get(2700), _videospiel.berechneMietgebuehr(28));
	}

	protected AbstractVideospiel getMedium()
	{
		return new PCVideospiel(TITEL, KOMMENTAR, SYSTEM);
	}

	@Override
	protected AbstractVideospiel getVideospiel()
	{
		return getMedium();
	}
	
	@Override
	public String getErweiterteBezeichnung() {
		return BEZEICHNUNG;
	}
}