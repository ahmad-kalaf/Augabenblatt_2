package de.hawhh.informatik.sml.medien;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public abstract class AbstractVideospielTest
{
	protected static final String KOMMENTAR = "Kommentar";
	protected static final String TITEL = "Titel";
	protected static final String BEZEICHNUNG = "Videospiel";
	protected static final String SYSTEM = "System";
	private AbstractVideospiel _videospiel;

	public AbstractVideospielTest()
	{
		_videospiel = getVideospiel();
	}

	protected abstract AbstractVideospiel getVideospiel();

	@Test
	public void testeVideospiel()
	{
		assertEquals(TITEL, _videospiel.getTitel());
		assertEquals(KOMMENTAR, _videospiel.getKommentar());
		assertEquals(SYSTEM, _videospiel.getSystem());
	}

	@Test
	public void testGetMedienBezeichnung()
	{
		assertEquals(BEZEICHNUNG, _videospiel.getMedienBezeichnung());
	}

	@Test
	public final void testSetKommentar()
	{
		AbstractVideospiel medium = getVideospiel();
		medium.setKommentar("Kommentar2");
		assertEquals(medium.getKommentar(), "Kommentar2");
	}

	@Test
	public final void testSetTitel()
	{
		AbstractVideospiel medium = getVideospiel();
		medium.setTitel("Titel2");
		assertEquals(medium.getTitel(), "Titel2");
	}
}
