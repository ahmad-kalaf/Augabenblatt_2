package de.hawhh.informatik.sml.mediathek.medien.tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.Geldbetrag;
import de.hawhh.informatik.sml.mediathek.medien.AbstractVideospiel;
import de.hawhh.informatik.sml.mediathek.medien.PCVideospiel;

public class PCVideospielTest extends AbstractVideospielTest
{
	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
	private static final String BEZEICHNUNG = "Videospiel";
	private static final String SYSTEM = "System";
	private AbstractVideospiel _pcSpiel;

	public PCVideospielTest()
	{
		_pcSpiel = getMedium();
	}

	@Test
	public void testBerechneMietgebuehrErsteSiebenTage()
	{
		for(int i = 1; i <= 7; i++) {
			assertEquals(Geldbetrag.get(200), _pcSpiel.berechneMietgebuehr(i));
		}
	}

	@Test
	public void testBerechneMietgebuehrAeqKlasseEins()
	{
		assertEquals(Geldbetrag.get(700), _pcSpiel.berechneMietgebuehr(8));
		assertEquals(Geldbetrag.get(700), _pcSpiel.berechneMietgebuehr(9));
		assertEquals(Geldbetrag.get(700), _pcSpiel.berechneMietgebuehr(10));
		assertEquals(Geldbetrag.get(700), _pcSpiel.berechneMietgebuehr(11));
		assertEquals(Geldbetrag.get(700), _pcSpiel.berechneMietgebuehr(12));
	}

	@Test
	public void testBerechneMietgebuehrAeqKlasseZwei()
	{
		assertEquals(Geldbetrag.get(2200), _pcSpiel.berechneMietgebuehr(23));
		assertEquals(Geldbetrag.get(2200), _pcSpiel.berechneMietgebuehr(24));
		assertEquals(Geldbetrag.get(2200), _pcSpiel.berechneMietgebuehr(25));
		assertEquals(Geldbetrag.get(2200), _pcSpiel.berechneMietgebuehr(26));
		assertEquals(Geldbetrag.get(2200), _pcSpiel.berechneMietgebuehr(27));
	}

	@Test
	public void testBerechneMietgebuehrGrenzwerteEins()
	{
		assertEquals(Geldbetrag.get(200), _pcSpiel.berechneMietgebuehr(7));
		assertEquals(Geldbetrag.get(700), _pcSpiel.berechneMietgebuehr(8));
	}

	@Test
	public void testBerechneMietgebuehrGrenzwerteZwei()
	{
		assertEquals(Geldbetrag.get(2200), _pcSpiel.berechneMietgebuehr(27));
		assertEquals(Geldbetrag.get(2700), _pcSpiel.berechneMietgebuehr(28));
	}

	@Test
	public void testGetMedienBezeichnung()
	{
		assertEquals(BEZEICHNUNG, _pcSpiel.getMedienBezeichnung());
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
}