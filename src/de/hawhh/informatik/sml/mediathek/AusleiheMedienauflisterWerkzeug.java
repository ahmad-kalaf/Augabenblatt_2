package de.hawhh.informatik.sml.mediathek;
import java.util.ArrayList;
import java.util.List;
import de.hawhh.informatik.sml.mediathek.medien.Medium;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

/**
 * Ein AusleiheMedienauflisterWerkzeug ist ein Werkzeug zum Auflisten von Medien
 * mit ihren Verleihinformationen.
 * 
 * Das Werkzeug ist beobachtbar und informiert darüber, wenn sich die Selektion
 * in der Medienliste ändert.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
class AusleiheMedienauflisterWerkzeug extends ObservableSubWerkzeug
{
    private AusleiheMedienauflisterUI _ui;
    private MedienbestandService _medienbestand;
    private final VerleihService _verleihService;

    /**
     * Initialisiert ein neues AusleiheMedienauflisterWerkzeug. Es wird die
     * Benutzungsoberfläche zum Darstellen der Medien erzeugt.
     * 
     * @param medienbestand
     *            Der Medienbestand.
     * @param verleihService
     *            Der Verleih-Service.
     * 
     * @require medienbestand != null
     * @require verleihService != null
     */
    public AusleiheMedienauflisterWerkzeug(MedienbestandService medienbestand,
            VerleihService verleihService)
    {
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand != null";
        assert verleihService != null : "Vorbedingung verletzt: verleihService != null";

        _medienbestand = medienbestand;
        _verleihService = verleihService;

        // UI wird erzeugt.
        _ui = new AusleiheMedienauflisterUI();

        // Die Ausleihaktionen werden erzeugt und an der UI registriert.
        registriereUIAktionen();

        // Die Beobachter werden erzeugt und an den Servies registriert.
        registriereServiceBeobachter();

        // Die anzuzeigenden Materialien werden in den UI-Widgets gesetzt.
        setzeAnzuzeigendeMedien();
    }

    /**
     * Registriert die Aktionen, die bei bestimmten UI-Events ausgeführt werden.
     */
    private void registriereUIAktionen()
    {
        registriereMedienAnzeigenAktion();
    }

    /**
     * Holt und setzt die Medieninformationen.
     */
    private void setzeAnzuzeigendeMedien()
    {
        List<Medium> medienListe = _medienbestand.getMedien();
        List<AusleiheMedienFormatierer> medienFormatierer = new ArrayList<>();
        for (Medium medium : medienListe)
        {
            boolean istVerliehen = _verleihService.istVerliehen(medium);
            medienFormatierer.add(new AusleiheMedienFormatierer(medium,
                    istVerliehen));
        }
        ObservableList<AusleiheMedienFormatierer> medien = FXCollections
                .observableArrayList();
        medien.addAll(medienFormatierer);
        _ui.getMedienAuflisterTable().setItems(medien);
        // _ui.getMedienAuflisterTableModel().setMedien(medienFormatierer);
    }

    /**
     * Registriert die Aktion, die ausgeführt wird, wenn ein Medium ausgewählt
     * wird.
     */
    private void registriereMedienAnzeigenAktion()
    {
        _ui.getMedienAuflisterTable().getSelectionModel().getSelectedIndices()
                .addListener((InvalidationListener) event -> informiereUeberAenderung());
    }

    /**
     * Registriert die Beobacheter für die Services.
     */
    private void registriereServiceBeobachter()
    {
        ServiceObserver beobachter = new ServiceObserver()
        {
            @Override
            public void reagiereAufAenderung()
            {
                // Wenn ein Service eine Änderung mitteilt, dann wird
                // die angezeigte Liste aller Medien aktualisiert:
                setzeAnzuzeigendeMedien();
            }
        };
        _medienbestand.registriereBeobachter(beobachter);
        _verleihService.registriereBeobachter(beobachter);
    }

    /**
     * Gibt die Liste der vom Benutzer selektierten Medien zurück.
     * 
     * @return Die Liste der vom Benutzer selektierten Medien.
     * 
     * @ensure result != null
     */
    public List<Medium> getSelectedMedien()
    {
        List<Medium> result = new ArrayList<Medium>();
        ObservableList<AusleiheMedienFormatierer> selectedRows = _ui
                .getMedienAuflisterTable().getSelectionModel().getSelectedItems();
        for (AusleiheMedienFormatierer formatierer : selectedRows)
        {
            result.add(formatierer.getMedium());
        }
        return result;
    }

    /**
     * Gibt das Pane dieses Subwerkzeugs zurück.
     * 
     * @ensure result != null
     */
    public Pane getUIPane()
    {
        return _ui.getUIPane();
    }
}
