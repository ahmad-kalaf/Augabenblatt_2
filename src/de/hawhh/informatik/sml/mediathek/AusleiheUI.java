package de.hawhh.informatik.sml.mediathek;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * AusleiheUI beschreibt die grafische Benutzungsoberfläche für das
 * AusleihWerkzeug. Sie beinhaltet einen Kundenauflister, einen Kundenanzeiger,
 * einen Medienauflister, einen Medienanzeiger und einen Ausleihbutton.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
class AusleiheUI
{
    // UI-Komponenten
    private Button _ausleihButton;
    private Pane _hauptPane;

    private final Pane _kundenauflisterPane;
    private final Pane _medienauflisterPane;
    private final Pane _mediendetailAnzeigerPane;
    private final Pane _kundendetailAnzeigerPane;

    /**
     * Erzeugt die Elemente der Benutzungsoberfläche.
     * 
     * @param kundenauflisterPane
     *            Das UI-Pane des Kundenauflisters.
     * @param medienauflisterPane
     *            Das UI-Pane des Medienauflisters.
     * @param mediendetailAnzeigerPane
     *            Das UI-Pane des Mediendetailanzeigers.
     * 
     * @require (kundenauflisterPane != null)
     * @require (medienauflisterPane != null)
     * @require (kundendetailAnzeigerPane != null)
     * @require (mediendetailAnzeigerPane != null)
     */
    public AusleiheUI(Pane kundenauflisterPane, Pane medienauflisterPane,
            Pane kundendetailAnzeigerPane, Pane mediendetailAnzeigerPane)
    {
        assert kundenauflisterPane != null : "Vorbedingung verletzt: (kundenauflisterPane != null)";
        assert medienauflisterPane != null : "Vorbedingung verletzt: (medienauflisterPane != null)";
        assert kundendetailAnzeigerPane != null : "Vorbedingung verletzt: (kundendetailAnzeigerPane != null)";
        assert mediendetailAnzeigerPane != null : "Vorbedingung verletzt: (mediendetailAnzeigerPane != null)";

        _kundenauflisterPane = kundenauflisterPane;
        _medienauflisterPane = medienauflisterPane;
        _mediendetailAnzeigerPane = mediendetailAnzeigerPane;
        _kundendetailAnzeigerPane = kundendetailAnzeigerPane;

        _hauptPane = erzeugeHauptPane();
    }

    /**
     * Erzeugt das Hauptpanel, in das die Auflister und Materialanzeiger gepackt
     * werden.
     */
    private Pane erzeugeHauptPane()
    {
        HBox hauptPane = new HBox();
        SplitPane hauptSplitPane = new SplitPane();
        Pane ausleihePane = erzeugeAusleihePane();
        SplitPane auflisterPane = erzeugeAuflisterPane();

        hauptSplitPane.setBackground(new Background(new BackgroundFill(
                UIConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox.setHgrow(hauptSplitPane, Priority.ALWAYS);
        SplitPane.setResizableWithParent(auflisterPane, true);
        SplitPane.setResizableWithParent(ausleihePane, false);

        hauptSplitPane.getItems().add(auflisterPane);
        hauptSplitPane.getItems().add(ausleihePane);
        hauptSplitPane.setDividerPosition(0, 0.7);
        hauptPane.getChildren().add(hauptSplitPane);

        return hauptPane;
    }

    /**
     * Erzeugt das Panel für die Anzeige der Kunden- und Medien-Tabelle, die
     * durch eine Splittpane voneinander getrennt sind.
     * 
     */
    private SplitPane erzeugeAuflisterPane()
    {
        SplitPane auflisterSplitpane = new SplitPane();
        auflisterSplitpane.setOrientation(Orientation.VERTICAL);
        auflisterSplitpane.setDividerPositions(0.4f);
        auflisterSplitpane.setBackground(new Background(new BackgroundFill(
                UIConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        auflisterSplitpane.setPadding(new Insets(10));
        SplitPane.setResizableWithParent(_kundenauflisterPane, true);
        SplitPane.setResizableWithParent(_medienauflisterPane, true);

        // Kundendarstellung
        auflisterSplitpane.getItems().add(_kundenauflisterPane);
        // Mediendarstellung
        auflisterSplitpane.getItems().add(_medienauflisterPane);

        return auflisterSplitpane;
    }

    /**
     * Erzeugt das Ausleih-Panel, in dem die Zusammenfassung und der
     * Ausleih-Button angezeigt werden.
     */
    private Pane erzeugeAusleihePane()
    {
        VBox ausleihePane = new VBox();
        erzeugeAusleihButton();

        VBox.setVgrow(_mediendetailAnzeigerPane, Priority.ALWAYS);
        ausleihePane.setPrefSize(240, -1);
        ausleihePane.setPadding(new Insets(10));
        ausleihePane.setSpacing(10);

        ausleihePane.getChildren().add(_kundendetailAnzeigerPane);
        ausleihePane.getChildren().add(_mediendetailAnzeigerPane);
        ausleihePane.getChildren().add(_ausleihButton);
        return ausleihePane;
    }

    /**
     * Erzeugt den Ausleih-Button.
     */
    private void erzeugeAusleihButton()
    {
        _ausleihButton = new Button("ausleihen");
        _ausleihButton.setMinSize(140, 100);
        _ausleihButton.setMaxWidth(Double.MAX_VALUE);
        _ausleihButton.setFont(UIConstants.BUTTON_FONT);
        _ausleihButton.setDisable(true);
    }

    /**
     * Gibt den Ausleih-Button zurück.
     * 
     * @ensure result != null
     */
    public Button getAusleihButton()
    {
        return _ausleihButton;
    }

    /**
     * Gibt das Haupt-Panel der UI zurück.
     * 
     * @ensure result != null
     */
    public Pane getUIPane()
    {
        return _hauptPane;
    }

}
