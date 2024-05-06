package de.hawhh.informatik.sml.mediathek;

import de.hawhh.informatik.sml.mediathek.medien.Medium;

/**
 * Der Formatierer für Medien in der Ausleiheansicht.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
class AusleiheMedienFormatierer
{

    private final Medium _medium;

    private final boolean _istVerliehen;

    /**
     * Initialisiert einen neuen AusleiheMedienformatierer für das angegebene
     * Medium.
     * 
     * @param medium
     *            Das adaptierte Medium.
     * @param istVerliehen
     *            Der Verleihstatus des Mediums.
     * 
     * @require medium != null
     */
    public AusleiheMedienFormatierer(Medium medium, boolean istVerliehen)
    {
        assert medium != null : "Vorbedingung verletzt: medium != null";

        _medium = medium;
        _istVerliehen = istVerliehen;
    }

    /**
     * Gibt das Medium des Formatieres zurück.
     * 
     * @return Das Medium des Formatieres.
     */
    public Medium getMedium()
    {
        return _medium;
    }

    /**
     * Gibt die Medienbezeichnung zurück.
     */
    public String getMedienBezeichnung()
    {
        return _medium.getMedienBezeichnung();
    }

    /**
     * Gibt den Medientitel zurück.
     */
    public String getTitel()
    {
        return _medium.getTitel();
    }

    /**
     * Gibt den Ausleihstatus an.
     */
    public String getAusleihstatus()
    {
        String result = "ja";
        if (_istVerliehen)
        {
            result = "nein";
        }
        return result;
    }

}
