package de.hawhh.informatik.sml.mediathek.medien.tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.medien.AbstractVideospiel;
import de.hawhh.informatik.sml.mediathek.medien.KonsolenVideospiel;
import de.hawhh.informatik.sml.mediathek.wertklassen.Geldbetrag;

public class KonsolenVideospielTest extends AbstractVideospielTest
{
	private static final String BEZEICHNUNG = "KonsolenVideospiel";
	
	@Test
	public void testBerechneMietgebuehrAeqKlasseEins()
	{
		assertEquals(Geldbetrag.get(900), _videospiel.berechneMietgebuehr(1));
		assertEquals(Geldbetrag.get(900), _videospiel.berechneMietgebuehr(2));
		assertEquals(Geldbetrag.get(900), _videospiel.berechneMietgebuehr(3));
	}

	@Test
	public void testBerechneMietgebuehrAeqKlasseZwei()
	{
		assertEquals(Geldbetrag.get(7200), _videospiel.berechneMietgebuehr(28));
		assertEquals(Geldbetrag.get(7200), _videospiel.berechneMietgebuehr(29));
		assertEquals(Geldbetrag.get(7200), _videospiel.berechneMietgebuehr(30));
	}

	@Test
	public void testBerechneMietgebuehrGrenzwerteEins()
	{
		assertEquals(Geldbetrag.get(900), _videospiel.berechneMietgebuehr(3));
		assertEquals(Geldbetrag.get(1600), _videospiel.berechneMietgebuehr(4));
	}
	
	@Test
	public void testBerechneMietgebuehrGrenzwerteZwei()
	{
		assertEquals(Geldbetrag.get(7200), _videospiel.berechneMietgebuehr(30));
		assertEquals(Geldbetrag.get(7900), _videospiel.berechneMietgebuehr(31));
	}

	protected AbstractVideospiel getMedium()
	{
		return new KonsolenVideospiel(TITEL, KOMMENTAR, SYSTEM);
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