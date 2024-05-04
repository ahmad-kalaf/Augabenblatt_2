package de.hawhh.informatik.sml.medien;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.Geldbetrag;

public abstract class AbstractMediumTest
{
	protected static final String KOMMENTAR = "Kommentar";
	protected static final String TITEL = "Titel";
	protected static final int LAENGE = 100;
	private final AbstractMedium _medium;
	private final AbstractMedium _medium1;

	public AbstractMediumTest()
	{
		_medium = getMedium();
		_medium1 = getMedium();
	}

	protected abstract AbstractMedium getMedium();

	@Test
	public void testEinTagMiete()
	{
		assertEquals(Geldbetrag.get(300 * 1), _medium.berechneMietgebuehr(1));
	}

	@Test
	public void testZweiTageMiete()
	{
		assertEquals(Geldbetrag.get(300 * 2), _medium.berechneMietgebuehr(2));
	}

	@Test
	public void testDreizigTageMiete()
	{
		assertEquals(Geldbetrag.get(300 * 30), _medium.berechneMietgebuehr(30));
	}

	@Test
	public void testTitelUndKommentar()
	{
		assertEquals(TITEL, _medium.getTitel());
		assertEquals(KOMMENTAR, _medium.getKommentar());
	}

	public final void testTitelKommentarSetter()
	{
		_medium.setTitel("Titel2");
		assertEquals(_medium.getTitel(), "Titel2");
		_medium.setKommentar("Kommentar2");
		assertEquals(_medium.getKommentar(), "Kommentar2");
	}

	/*
	 * Von ein und der selben CD kann es mehrere Exemplare geben, die von
	 * unterschiedlichen Personen ausgeliehen werden können.
	 */
	public void testEquals()
	{
		assertFalse("Mehrere Exemplare der gleichen CD sind ungleich",
				_medium.equals(_medium1));
		assertTrue("Dasselbe Exemplare der gleichen CD ist gleich",
				_medium.equals(_medium1));
	}

	@Test
	public final void testGetFormatiertenString()
	{
		Medium medium = getMedium();
		assertNotNull(medium.getFormatiertenString());
	}
}
