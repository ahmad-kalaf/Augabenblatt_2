package de.hawhh.informatik.sml.mediathek.medien;

import de.hawhh.informatik.sml.mediathek.wertklassen.Geldbetrag;

public abstract class AbstractMedium implements Medium
{
	/**
	 * Ein Kommentar zum Medium
	 */
	private String _kommentar;
	/**
	 * Der Titel des Mediums
	 * 
	 */
	private String _titel;

	/**
	 * Initialisiert ein AbstractMedium requier titel != null requier kommentar
	 * != null
	 * 
	 * ensure getKommentar != null ensure getTitel != null
	 */
	public AbstractMedium(String titel, String kommentar)
	{
		assert titel != null : "Vorbedingung verletzt: titel != null";
		assert kommentar != null : "Vorbedingung verletzt: kommentar != null";
		_titel = titel;
		_kommentar = kommentar;
	}

	/**
	 * Berechnet die Mietgebühr des Mediums bassierend auf der Mietdauer.
	 * 
	 * @require tage > 0
	 * 
	 * @ensure result != null
	 */
	public Geldbetrag berechneMietgebuehr(int tage)
	{
		assert tage > 0 : "Vorbedingung verletzt: tage > 0";
		int betrag = tage * 300;
		Geldbetrag euroCentBetrag = Geldbetrag.get(betrag);
		return euroCentBetrag;
	}

	/**
	 * Gibt einen formatierten Text mit allen Eigenschaften des Mediums zurück.
	 * Jedes Attribute steht in einer eigenen Zeile mit der Form "Attributename:
	 * Attributwert". Hinweis: Ein Zeilenumbruch wird durch den Character '\n'
	 * dargestellt.
	 * 
	 * @return Eine Textrepräsentation des Mediums.
	 * 
	 * @ensure result != null
	 */
	public String getFormatiertenString()
	{
		return getMedienBezeichnung() + ":\n" + "    " + "Titel: " + _titel
				+ "\n" + "    " + "Kommentar: " + _kommentar + "\n";
	}

	/**
	 * Gibt den Kommentar zu diesem Medium zurück.
	 * 
	 * @return Den Kommentar zu diesem Medium.
	 * 
	 * @ensure result != null
	 */
	public String getKommentar()
	{
		return _kommentar;
	}

	/**
	 * Ändert den Kommentar des Mediums
	 * 
	 * @param kommentar Ein Kommentar zu diesem Medium
	 * 
	 * @require kommentar != null
	 * @ensure getKommentar() == kommentar
	 */
	public void setKommentar(String kommentar)
	{
		assert kommentar != null : "Vorbedingung verletzt: kommentar != null";
		_kommentar = kommentar;
	}

	/**
	 * Gibt die Bezeichnung für die Medienart zurück.
	 * 
	 * @return Die Bezeichnung für die Medienart.
	 * 
	 * @ensure result != null
	 */
	public String getMedienBezeichnung()
	{
		return "AbstractMedium";
	}

	/**
	 * Gibt den Titel des Mediums zurück.
	 * 
	 * @return Den Titel des Mediums
	 * 
	 * @ensure result != null
	 */
	public String getTitel()
	{
		return _titel;
	}

	/**
	 * Ändert den Titel des Mediums.
	 * 
	 * @param titel Der Titel des Mediums
	 * 
	 * @require titel != null
	 * @ensure getTitel() == titel
	 */
	public void setTitel(String titel)
	{
		assert titel != null : "Vorbedingung verletzt: titel != null";
		_titel = titel;
	}
}