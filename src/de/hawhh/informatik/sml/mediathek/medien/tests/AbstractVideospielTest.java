package de.hawhh.informatik.sml.mediathek.medien.tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.medien.AbstractMedium;
import de.hawhh.informatik.sml.mediathek.medien.AbstractVideospiel;

public abstract class AbstractVideospielTest extends AbstractMediumTest
{
	protected static final String SYSTEM = "System";
    
    public AbstractVideospielTest()
	{
	}
    
    public abstract AbstractVideospiel erzeugeVideospiel();
	
    @Override
    public AbstractMedium erzeugeMedium() {
    	return erzeugeVideospiel();
    }
	
	@Test @Override
    public void testKonstruktor()
    {
        super.testKonstruktor();
        assertEquals(SYSTEM, ((AbstractVideospiel) _medium1).getSystem());
    }
}
