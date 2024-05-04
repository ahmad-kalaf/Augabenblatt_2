package de.hawhh.informatik.sml.mediathek;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/**
 * Ein KundenDetailAnzeigerWerkzeug ist ein Werkzeug um die Details von Kunden
 * anzuzeigen.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
class KundenDetailAnzeigerWerkzeug
{
    private KundenDetailAnzeigerUI _ui;

    /**
     * Initialisiert ein neues KundenDetailAnzeigerWerkzeug.
     */
    public KundenDetailAnzeigerWerkzeug()
    {
        _ui = new KundenDetailAnzeigerUI();
    }

    /**
     * Setzt den Kunden, dessen Details angezeigt werden sollen.
     * 
     * @param kunde
     *            Ein Kunde, oder null um die Detailanzeige zu leeren.
     * 
     */
    public void setKunde(Kunde kunde)
    {
        TextArea selectedKundenTextArea = _ui.getKundenAnzeigerTextArea();
        selectedKundenTextArea.setText("");
        if (kunde != null)
        {
            selectedKundenTextArea.setText(kunde.toString());
        }
    }

    /**
     * Gibt das Panel dieses Subwerkzeugs zurück.
     * 
     * @ensure result != null
     */
    public Pane getUIPane()
    {
        return _ui.getUIPane();
    }
}
