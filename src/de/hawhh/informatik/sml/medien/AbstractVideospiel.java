package de.hawhh.informatik.sml.medien;
import de.hawhh.informatik.sml.mediathek.Geldbetrag;
import javafx.scene.layout.Background;

/**
 * {@link Videospiel} ist ein {@link Medium} mit einer zusätzlichen
 * Informationen zum kompatiblen System.
 * 
 * @author SE2-Team, PR2-Team, PR2-Team
 * @version SoSe 2024
 */
public abstract class AbstractVideospiel extends AbstractMedium
{
	/**
	 * Das System, auf dem das Spiel lauffähig ist
	 */
	private String _system;
	/**
	 * Der Basismietpreis von Videospielen
	 */
	protected static final int BASISPREIS = 200;

	/**
	 * Initialisiert ein neues Videospiel.
	 * 
	 * @param titel     Der Titel des Spiels
	 * @param kommentar Ein Kommentar zum Spiel
	 * @param system    Die Bezeichnung des System
	 * 
	 * @require titel != null
	 * @require kommentar != null
	 * @require system != null
	 * 
	 * @ensure getTitel() == titel
	 * @ensure getKommentar() == kommentar
	 * @ensure getSystem() == system
	 */
	public AbstractVideospiel(String titel, String kommentar, String system)
	{
		super(titel, kommentar);
		assert system != null : "Vorbedingung verletzt: system != null";
		_system = system;
	}

	/**
	 * Liefert den Zeitabhängigen Mietpreis nach Tagen.
	 * 
	 * @param tage die Anzahl der Miettage
	 * @return result Geldbetrag in Euro-Cent
	 * 
	 * @require tage > 0
	 * 
	 * @ensure result != null
	 */
	public abstract int getPreisNachTagen(int tage); 

	@Override
	public Geldbetrag berechneMietgebuehr(int tage)
	{
		// Einschuboperation: getPreisNachTagen(int)
		// Schablonenmethode: berechneMietgebuehr(int)
		return Geldbetrag.get(BASISPREIS + getPreisNachTagen(tage));
	}

	@Override
	public String getMedienBezeichnung()
	{
		return "Videospiel";
	}

	/**
	 * Gibt das System zurück, auf dem das Spiel lauffähig ist.
	 * 
	 * @return Das System, auf dem das Spiel ausgeführt werden kann.
	 * 
	 * @ensure result != null
	 */
	public String getSystem()
	{
		return _system;
	}

	@Override
	public String toString()
	{
		return getFormatiertenString();
	}

	@Override
	public String getFormatiertenString()
	{
		return super.getFormatiertenString() + "    " + "System: " + _system
				+ "\n";
	}
}