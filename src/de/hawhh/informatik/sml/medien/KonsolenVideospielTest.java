package de.hawhh.informatik.sml.medien;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.Geldbetrag;

public class KonsolenVideospielTest extends AbstractVideospielTest
{
	private AbstractVideospiel _konsolenSpiel;

	public KonsolenVideospielTest()
	{
		_konsolenSpiel = getMedium();
	}
	
	@Test
	public void testBerechneMietgebuehrAeqKlasseEins()
	{
		assertEquals(Geldbetrag.get(900), _konsolenSpiel.berechneMietgebuehr(1));
		assertEquals(Geldbetrag.get(900), _konsolenSpiel.berechneMietgebuehr(2));
		assertEquals(Geldbetrag.get(900), _konsolenSpiel.berechneMietgebuehr(3));
	}

	@Test
	public void testBerechneMietgebuehrAeqKlasseZwei()
	{
		assertEquals(Geldbetrag.get(7200), _konsolenSpiel.berechneMietgebuehr(28));
		assertEquals(Geldbetrag.get(7200), _konsolenSpiel.berechneMietgebuehr(29));
		assertEquals(Geldbetrag.get(7200), _konsolenSpiel.berechneMietgebuehr(30));
	}

	@Test
	public void testBerechneMietgebuehrGrenzwerteEins()
	{
		assertEquals(Geldbetrag.get(900), _konsolenSpiel.berechneMietgebuehr(3));
		assertEquals(Geldbetrag.get(1600), _konsolenSpiel.berechneMietgebuehr(4));
	}
	
	@Test
	public void testBerechneMietgebuehrGrenzwerteZwei()
	{
		assertEquals(Geldbetrag.get(7200), _konsolenSpiel.berechneMietgebuehr(30));
		assertEquals(Geldbetrag.get(7900), _konsolenSpiel.berechneMietgebuehr(31));
	}

	@Test
	public void testGetMedienBezeichnung()
	{
		assertEquals(BEZEICHNUNG, _konsolenSpiel.getMedienBezeichnung());
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
}