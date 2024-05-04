package de.hawhh.informatik.sml.mediathek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

/**
 * Ein KundenauflisterWerkzeug ist ein Werkzeug zum auflisten von Kunden.
 * 
 * Das Werkzeug ist beobachtbar und informiert über Änderungen des selektieren
 * Kunden.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
class KundenauflisterWerkzeug extends ObservableSubWerkzeug
{
    private KundenauflisterUI _ui;
    private KundenstammService _kundenstamm;

    /**
     * Initialisiert ein neues KundenauflisterWerkzeug. Es wird die
     * Benutzungsoberfläche mit zum Darstellen der Kunden erzeugt.
     * 
     * @param kundenstamm
     *            Der Kundenstamm.
     * 
     * @require kundenstamm != null
     */
    public KundenauflisterWerkzeug(KundenstammService kundenstamm)
    {
        assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm != null";

        _kundenstamm = kundenstamm;

        // UI wird erzeugt.
        _ui = new KundenauflisterUI();

        // Die Ausleihaktionen werden erzeugt und an der UI registriert.
        registriereUIAktionen();

        // Die anzuzeigenden Materialien werden in den UI-Widgets gesetzt.
        setzeAnzuzeigendeMaterialien();
    }

    /**
     * Registriert die Aktionen, die bei bestimmten UI-Events ausgeführt werden.
     */
    private void registriereUIAktionen()
    {
        registriereKundenAnzeigenAktion();
    }

    /**
     * Holt die anzuzeigenden Kunden aus den Services und setzt diese bei dem
     * TableModel.
     */
    private void setzeAnzuzeigendeMaterialien()
    {
        ObservableList<Kunde> kundenListe = FXCollections.observableArrayList();
        kundenListe.addAll(_kundenstamm.getKunden());
        _ui.getKundenTable().setItems(kundenListe);
    }

    /**
     * Registiert die Aktion, die ausgeführt wird, wenn ein Kunde ausgewählt
     * wird.
     */
    private void registriereKundenAnzeigenAktion()
    {
        _ui.getKundenTable().getSelectionModel().selectedIndexProperty()
                .addListener(event -> informiereUeberAenderung());
    }

    /**
     * Gibt den vom Benutzer selektierten Kunden zurück. Falls kein Kunde
     * selektiert ist, gebe null zurück.
     * 
     * @return Den aktuell selektierten Kunden, oder null wenn keine Selektion
     *         existiert.
     */
    public Kunde getSelectedKunde()
    {
        return _ui.getKundenTable().getSelectionModel().getSelectedItem();

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
