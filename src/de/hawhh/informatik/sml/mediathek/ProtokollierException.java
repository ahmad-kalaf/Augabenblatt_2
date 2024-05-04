package de.hawhh.informatik.sml.mediathek;
/**
 * Eine ProtokollierException signalisiert, dass das Protokollieren eines
 * Verleihvorgangs fehlgeschlagen ist.
 * 
 * @author SE2-Team, PR2-Team
 * @version SoSe 2024
 */
class ProtokollierException extends Exception
{

    private static final long serialVersionUID = 1L;

    /**
     * Initialisiert eine neue Exception mit der übergebenen Fehlermeldung.
     * 
     * @param message
     *            Eine Fehlerbeschreibung.
     */
    public ProtokollierException(String message)
    {
        super(message);
    }
}
