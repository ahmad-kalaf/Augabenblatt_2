package de.hawhh.informatik.sml.mediathek.medien.tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.medien.AbstractMedium;
import de.hawhh.informatik.sml.mediathek.medien.CD;

public class CDTest extends AbstractMediumTest
{
	private static final String INTERPRET = "CD Interpret";

	public CDTest()
	{
	}

    @Override
    public AbstractMedium erzeugeMedium() {
    	return new CD(TITEL, KOMMENTAR, INTERPRET, LAENGE);
    }
    

    @Test @Override
    public void testKonstruktor()
    {
    	super.testKonstruktor();
        assertEquals(LAENGE, ((CD) _medium1).getSpiellaenge());
        assertEquals(INTERPRET, ((CD) _medium1).getInterpret());
    }

    @Test @Override
    public final void testSetter()
    {

    	super.testSetter();
        ((CD) _medium1).setInterpret("Interpret2");
        assertEquals(((CD) _medium1).getInterpret(), "Interpret2");
        ((CD) _medium1).setSpiellaenge(99);
        assertEquals(((CD) _medium1).getSpiellaenge(), 99);
    }
}