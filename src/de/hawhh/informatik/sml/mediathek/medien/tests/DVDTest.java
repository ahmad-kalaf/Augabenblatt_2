package de.hawhh.informatik.sml.mediathek.medien.tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.medien.AbstractMedium;
import de.hawhh.informatik.sml.mediathek.medien.DVD;

public class DVDTest extends AbstractMediumTest
{
	private static final String DVD_BEZEICHNUNG = "DVD";
    private static final String REGISSEUR = "DVD Regisseur";
    
    public DVDTest()
	{
		super();
	}
	
    
    @Override
    public AbstractMedium erzeugeMedium() {
    	return new DVD(TITEL, KOMMENTAR, REGISSEUR, LAENGE);
    }
    
    @Test @Override
    public void testKonstruktor()
    {
        super.testKonstruktor();
        assertEquals(LAENGE, ((DVD) _medium1).getLaufzeit());
        assertEquals(REGISSEUR, ((DVD) _medium1).getRegisseur());
    }

    @Test @Override
    public final void testSetter()
    {
    	super.testSetter();
    	((DVD) _medium1).setLaufzeit(90);
        assertEquals(90, ((DVD) _medium1).getLaufzeit());
        ((DVD) _medium1).setRegisseur("Regisseur2");
        assertEquals("Regisseur2", ((DVD) _medium1).getRegisseur());
    }

}
