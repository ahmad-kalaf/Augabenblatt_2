package de.hawhh.informatik.sml.medien;

import de.hawhh.informatik.sml.mediathek.Geldbetrag;

/**
 * Ein Medium definiert Eigenschaften, die alle Medien unserer Mediathek
 * gemeinsam haben. Der Titel eines Mediums dient als eindeutige Identifikation.
 * Ein Medium kann ausgeliehen und zurückgegeben werden.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
public interface Medium
{
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
	String getFormatiertenString();

	/**
	 * Gibt den Kommentar zu diesem Medium zurück.
	 * 
	 * @return Den Kommentar zu diesem Medium.
	 * 
	 * @ensure result != null
	 */
	String getKommentar();

	/**
	 * Ändert den Kommentar des Mediums
	 * 
	 * @param kommentar Ein Kommentar zu diesem Medium
	 * 
	 * @require kommentar != null
	 * @ensure getKommentar() == kommentar
	 */
	public void setKommentar(String kommentar);

	/**
	 * Gibt die Bezeichnung für die Medienart zurück.
	 * 
	 * @return Die Bezeichnung für die Medienart.
	 * 
	 * @ensure result != null
	 */
	String getMedienBezeichnung();

	/**
	 * Gibt den Titel des Mediums zurück.
	 * 
	 * @return Den Titel des Mediums
	 * 
	 * @ensure result != null
	 */
	String getTitel();

	/**
	 * Ändert den Titel des Mediums.
	 * 
	 * @param titel Der Titel des Mediums
	 * 
	 * @require titel != null
	 * @ensure getTitel() == titel
	 */
	public void setTitel(String titel);

	/**
	 * Berechnet die Mietgebühr in Eurocent für eine angegebene Mietdauer * in
	 * Tagen *
	 * 
	 * @param mietTage Die Anzahl der Ausleihtage eines Mediums
	 * @return Die Mietgebühr in Eurocent als Geldbetrag
	 *
	 * @require mietTage > 0
	 *
	 * @ensure result != null
	 */
	Geldbetrag berechneMietgebuehr(int mietTage);
}
