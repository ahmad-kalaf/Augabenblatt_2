package de.hawhh.informatik.sml.mediathek.werkzeuge;

import java.util.ArrayList;
import java.util.List;
import de.hawhh.informatik.sml.mediathek.medien.Medium;
import de.hawhh.informatik.sml.mediathek.services.ProtokollierException;
import de.hawhh.informatik.sml.mediathek.services.VerleihService;
import de.hawhh.informatik.sml.mediathek.services.Verleihkarte;
import de.hawhh.informatik.sml.mediathek.wertklassen.Datum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

/**
 * Ein RueckgabeWerkzeug stellt die Funktionalität der Rücknahme für die
 * Benutzungsoberfläche bereit. Die UI wird durch die RueckgabeUI gestaltet.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
public class RueckgabeWerkzeug
{
    /**
     * Die UI-Komponente der Rueckgabe.
     */
    private final RueckgabeUI _rueckgabeUI;

    /**
     * Der Service zum Ausleihen von Medien.
     */
    private final VerleihService _verleihService;

    /**
     * Initialisiert ein neues RueckgabeWerkzeug. Es wird die
     * Benutzungsoberfläche mit den Rückgabeaktionen erzeugt, Beobachter an den
     * Services registriert und die anzuzeigenden Materialien gesetzt.
     * 
     * @param verleihService
     *            Der zu benutzenden VerleihService.
     * 
     * @require verleihService != null
     */
    public RueckgabeWerkzeug(VerleihService verleihService)
    {
        assert verleihService != null : "Vorbedingung verletzt: verleihService != null";
        _verleihService = verleihService;

        // UI wird erzeugt.
        _rueckgabeUI = new RueckgabeUI();

        // Die Beobachter werden erzeugt und an den Services registriert.
        registriereServiceBeobachter();

        // Die Rückgabe-Aktionen werden erzeugt und an den UI-Widgets
        // registriert.
        registriereUIAktionen();

        // Die anzuzeigenden Materialien werden an der UI gesetzt.
        setzeAnzuzeigendeMaterialien();
    }

    /**
     * Setzt die Materialien an der UI, die angezeigt werden sollen.
     */
    private void setzeAnzuzeigendeMaterialien()
    {
        setzeAnzuzeigendeVerleihkarten();
    }

    /**
     * Registriert die Aktionen, die bei bestimmten UI-Events ausgeführt werden.
     */
    private void registriereUIAktionen()
    {
        registriereRuecknahmeAktion();
        registriereVerleihkartenAnzeigenAktion();
    }

    /**
     * Registriert die Aktion die ausgeführt wird, wenn Verleihkarten selektiert
     * werden.
     */
    private void registriereVerleihkartenAnzeigenAktion()
    {
        _rueckgabeUI.getVerleihkartenAuflisterTable().getSelectionModel()
                .selectedIndexProperty().addListener(event -> {
                    zeigeAusgewaehlteVerleihkarten();
                    aktualisiereRuecknahmeButton();
                });
    }

    /**
     * Registriert die Rücknahmeaktion.
     */
    private void registriereRuecknahmeAktion()
    {
        _rueckgabeUI.getRuecknahmeButton()
                .setOnAction(event -> nimmAusgewaehlteMedienZurueck());
    }

    /**
     * Registriert die Beobachter an den Services.
     */
    private void registriereServiceBeobachter()
    {
        _verleihService.registriereBeobachter(() -> setzeAnzuzeigendeVerleihkarten());
    }

    /**
     * Holt alle Verleihkarten vom Verleihservice und setzt diese an der UI.
     */
    private void setzeAnzuzeigendeVerleihkarten()
    {
        List<Verleihkarte> verleihkarten = _verleihService.getVerleihkarten();
        ObservableList<Verleihkarte> verleihkartenListe = FXCollections
                .observableArrayList();
        verleihkartenListe.addAll(verleihkarten);
        _rueckgabeUI.getVerleihkartenAuflisterTable().setItems(verleihkartenListe);
    }

    /**
     * Gibt die vom Benutzer ausgewählten Medien zurück.
     */
    private void nimmAusgewaehlteMedienZurueck()
    {
        List<Verleihkarte> verleihkarten = getSelectedVerleihkarten();
        List<Medium> medien = new ArrayList<Medium>();
        for (Verleihkarte verleihkarte : verleihkarten)
        {
            medien.add(verleihkarte.getMedium());
        }
        try
		{
			_verleihService.nimmZurueck(medien, Datum.heute());
		} catch(ProtokollierException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText("Beim Zurueckgeben ist ein Fehler aufgetreten");
			alert.setContentText("Fehlermeldung: " + e.getMessage());
			alert.showAndWait();
		}
    }

    /**
     * Zeigt die Details der ausgewählten Verleihkarten an.
     */
    private void zeigeAusgewaehlteVerleihkarten()
    {
        List<Verleihkarte> selektierteVerleihkarten = getSelectedVerleihkarten();
        TextArea _ausgewaehlteVerleihkartenTextArea = _rueckgabeUI
                .getVerleihkartenAnzeigerTextArea();
        _ausgewaehlteVerleihkartenTextArea.setText("");
        for (Verleihkarte verleihkarte : selektierteVerleihkarten)
        {
            _ausgewaehlteVerleihkartenTextArea
                    .appendText(verleihkarte.getFormatiertenString());
            _ausgewaehlteVerleihkartenTextArea.appendText("--------------- \n");
        }
    }

    /**
     * Graut den RuecknahmeButton aus, wenn keine Verleihkarten selektiert sind.
     */
    private void aktualisiereRuecknahmeButton()
    {
        _rueckgabeUI.getRuecknahmeButton()
                .setDisable(getSelectedVerleihkarten().isEmpty());
    }

    /**
     * Gibt das Panel, dass die UI-Koponente darstellt zurück.
     * 
     * @return Das Panel, dass die UI-Koponente darstellt.
     * 
     * @ensure result != null
     */
    public Pane getUIPane()
    {
        return _rueckgabeUI.getUIPane();
    }

    /**
     * Liefert die vom Benutzer selektierten Verleihkarten.
     * 
     * @return Die selektierten Verleihkarten.
     * 
     * @ensure result != null
     */
    private List<Verleihkarte> getSelectedVerleihkarten()
    {
        return _rueckgabeUI.getVerleihkartenAuflisterTable().getSelectionModel()
                .getSelectedItems();
    }
}
