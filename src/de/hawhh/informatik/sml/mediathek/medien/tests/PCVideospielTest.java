package de.hawhh.informatik.sml.mediathek.medien.tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.medien.AbstractVideospiel;
import de.hawhh.informatik.sml.mediathek.medien.PCVideospiel;
import de.hawhh.informatik.sml.mediathek.wertklassen.Geldbetrag;

public class PCVideospielTest extends AbstractVideospielTest
{

	public PCVideospielTest()
	{
	}
	
	@Override
    public AbstractVideospiel erzeugeVideospiel() {
    	return new PCVideospiel(TITEL, KOMMENTAR, SYSTEM);
    }
    
    
    @Test @Override
    public void testBerechneMietgebuehr()
    {    			
    	assertEquals(Geldbetrag.get(700), _medium1.berechneMietgebuehr(10));
    	assertEquals(Geldbetrag.get(1700), _medium1.berechneMietgebuehr(20));
    	assertEquals(Geldbetrag.get(2700), _medium1.berechneMietgebuehr(30));
    }

	@Test
	public void testBerechneMietgebuehrErsteSiebenTage()
	{
		for(int i = 1; i <= 7; i++) {
			assertEquals(Geldbetrag.get(200), _medium1.berechneMietgebuehr(i));
		}
	}

	@Test
	public void testBerechneMietgebuehrAeqKlasseEins()
	{
		assertEquals(Geldbetrag.get(700), _medium1.berechneMietgebuehr(8));
		assertEquals(Geldbetrag.get(700), _medium1.berechneMietgebuehr(9));
		assertEquals(Geldbetrag.get(700), _medium1.berechneMietgebuehr(10));
		assertEquals(Geldbetrag.get(700), _medium1.berechneMietgebuehr(11));
		assertEquals(Geldbetrag.get(700), _medium1.berechneMietgebuehr(12));
	}

	@Test
	public void testBerechneMietgebuehrAeqKlasseZwei()
	{
		assertEquals(Geldbetrag.get(2200), _medium1.berechneMietgebuehr(23));
		assertEquals(Geldbetrag.get(2200), _medium1.berechneMietgebuehr(24));
		assertEquals(Geldbetrag.get(2200), _medium1.berechneMietgebuehr(25));
		assertEquals(Geldbetrag.get(2200), _medium1.berechneMietgebuehr(26));
		assertEquals(Geldbetrag.get(2200), _medium1.berechneMietgebuehr(27));
	}

	@Test
	public void testBerechneMietgebuehrGrenzwerteEins()
	{
		assertEquals(Geldbetrag.get(200), _medium1.berechneMietgebuehr(7));
		assertEquals(Geldbetrag.get(700), _medium1.berechneMietgebuehr(8));
	}

	@Test
	public void testBerechneMietgebuehrGrenzwerteZwei()
	{
		assertEquals(Geldbetrag.get(2200), _medium1.berechneMietgebuehr(27));
		assertEquals(Geldbetrag.get(2700), _medium1.berechneMietgebuehr(28));
	}

}