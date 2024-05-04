package de.hawhh.informatik.sml.medien;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CDTest extends AbstractMediumTest
{
	private static final String CD_BEZEICHNUNG = "CD";
	private static final String INTERPRET = "CD Interpret";
	private CD _cd1;

	public CDTest()
	{
		_cd1 = getCD();
	}

	@Test
	public void testGetMedienBezeichnung()
	{
		String cdBezeichnung = CD_BEZEICHNUNG;
		assertEquals(cdBezeichnung, _cd1.getMedienBezeichnung());
	}

	@Test
	public void testKonstruktor()
	{
		assertEquals(LAENGE, _cd1.getSpiellaenge());
		assertEquals(INTERPRET, _cd1.getInterpret());
	}

	@Test
	public final void testSetter()
	{
		_cd1.setInterpret("Interpret2");
		assertEquals(_cd1.getInterpret(), "Interpret2");
		_cd1.setSpiellaenge(99);
		assertEquals(_cd1.getSpiellaenge(), 99);
	}

	private CD getCD()
	{
		return new CD(TITEL, KOMMENTAR, INTERPRET, LAENGE);
	}

	@Override
	protected AbstractMedium getMedium()
	{
		return getCD();
	}
}