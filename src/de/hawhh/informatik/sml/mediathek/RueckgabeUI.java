package de.hawhh.informatik.sml.mediathek;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * RueckgabeUIbeschreibt die grafische Benutzungsoberfläche für das
 * RueckgabeWerkzeug. Sie beinhaltet einen Verleihkartenauflister, einen
 * Verleihkartenanzeiger und einen Rücknahmebutton.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
class RueckgabeUI
{
    // UI-Komponenten
    private TextArea _verleihkartenAnzeigerTextArea;
    private Button _ruecknahmeButton;

    private TableView<Verleihkarte> _verleihkartenAuflisterTable;
    private Pane _hauptPane;

    /**
     * Erzeugt die Elemente der Benutzungsoberfläche.
     */
    public RueckgabeUI()
    {
        _hauptPane = erzeugeHauptPane();
    }

    /**
     * Erzeugt das Hauptpanel, in dem sich Verleihkartenauflister,
     * Verleihkartenanzeiger und Rücknahme-Button befinden.
     */
    private Pane erzeugeHauptPane()
    {
        HBox hauptPane = new HBox();
        SplitPane hauptSplitPane = new SplitPane();
        Pane auflisterPane = erzeugeVerleihkartenAuflisterPane();
        Pane ruecknahmePane = erzeugeRuecknahme();

        hauptSplitPane.setBackground(new Background(new BackgroundFill(
                UIConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        hauptSplitPane.setDividerPosition(0, 0.7);

        HBox.setHgrow(hauptSplitPane, Priority.ALWAYS);
        SplitPane.setResizableWithParent(auflisterPane, true);
        SplitPane.setResizableWithParent(ruecknahmePane, false);

        hauptSplitPane.getItems().add(auflisterPane);
        hauptSplitPane.getItems().add(ruecknahmePane);

        hauptPane.getChildren().add(hauptSplitPane);
        return hauptPane;
    }

    private Pane erzeugeVerleihkartenAuflisterPane()
    {
        VBox auflisterPane = new VBox();
        erzeugeVerleihkartenauflister();

        auflisterPane.setPadding(new Insets(10));
        VBox.setVgrow(_verleihkartenAuflisterTable, Priority.ALWAYS);

        auflisterPane.getChildren().add(erzeugeTableHeader());
        auflisterPane.getChildren().add(_verleihkartenAuflisterTable);

        return auflisterPane;
    }

    private Label erzeugeTableHeader()
    {
        Label label = new Label("Verleihkarten");
        label.setFont(UIConstants.LABEL_TEXT_FONT);
        label.setPadding(new Insets(10, 0, 0, 0));
        return label;
    }

    /**
     * Erzeuge das Tabellen-Panel, in dem die Verleihkarten angezeigt werden.
     */
    private void erzeugeVerleihkartenauflister()
    {
        _verleihkartenAuflisterTable = new TableView<>();
        _verleihkartenAuflisterTable.setPrefWidth(800);
        _verleihkartenAuflisterTable.setPrefHeight(550);
        _verleihkartenAuflisterTable.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Verleihkarte, String> kunde = new TableColumn<>("Kunde");
        TableColumn<Verleihkarte, String> medienTyp = new TableColumn<>("Medientyp");
        TableColumn<Verleihkarte, String> titel = new TableColumn<>("Titel");
        TableColumn<Verleihkarte, String> ausleihdatum = new TableColumn<>(
                "Ausleihdatum");
        TableColumn<Verleihkarte, Integer> ausleihdauer = new TableColumn<>(
                "Ausleihdauer (Tage)");
        TableColumn<Verleihkarte, String> mietgebuehr = new TableColumn<>(
                "Mietgebühr (€)");

        kunde.setCellValueFactory(p -> {
            Kunde entleiher = p.getValue().getEntleiher();
            String result = String.format("%s %s", entleiher.getVorname(),
                    entleiher.getNachname());
            return new ReadOnlyObjectWrapper<String>(result);
        });
        medienTyp.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(
                p.getValue().getMedium().getMedienBezeichnung()));
        titel.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(
                p.getValue().getMedium().getTitel()));
        ausleihdatum.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(
                p.getValue().getAusleihdatum().toString()));
        ausleihdauer.setCellValueFactory(
                p -> new ReadOnlyObjectWrapper<Integer>(p.getValue().getAusleihdauer()));
        mietgebuehr.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(
                p.getValue().getMietgebuehr().getFormatiertenString()));

        _verleihkartenAuflisterTable.getColumns().add(kunde);
        _verleihkartenAuflisterTable.getColumns().add(medienTyp);
        _verleihkartenAuflisterTable.getColumns().add(titel);
        _verleihkartenAuflisterTable.getColumns().add(ausleihdatum);
        _verleihkartenAuflisterTable.getColumns().add(ausleihdauer);
        _verleihkartenAuflisterTable.getColumns().add(mietgebuehr);

        _verleihkartenAuflisterTable
                .setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        _verleihkartenAuflisterTable.setStyle("-fx-border-color: black");

    }

    /**
     * Erzeuge das Rücknahme-Panel, in dem der Verleihkartenanzeiger und der
     * Rüchnahme-Button liegen.
     */
    private Pane erzeugeRuecknahme()
    {
        VBox ruecknahmePane = new VBox();
        erzeugeVerleihkartenAnzeiger();
        erzeugeRuecknahmeButton();
        Pane anzeigePane = erzeugeMedienAnzeigePane();

        VBox.setVgrow(anzeigePane, Priority.ALWAYS);
        ruecknahmePane.setPrefSize(240, -1);
        ruecknahmePane.setPadding(new Insets(0, 10, 10, 10));
        ruecknahmePane.setSpacing(10);

        ruecknahmePane.getChildren().add(anzeigePane);
        ruecknahmePane.getChildren().add(_ruecknahmeButton);
        return ruecknahmePane;
    }

    private Pane erzeugeMedienAnzeigePane()
    {
        VBox hauptPane = new VBox();

        VBox.setVgrow(_verleihkartenAnzeigerTextArea, Priority.ALWAYS);
        _verleihkartenAnzeigerTextArea.setStyle("-fx-border-color: black");

        hauptPane.getChildren().add(erzeugeTextAreaHeader());
        hauptPane.getChildren().add(_verleihkartenAnzeigerTextArea);
        return hauptPane;
    }

    private Label erzeugeTextAreaHeader()
    {
        Label label = new Label("Ausgewählte Medien");
        label.setFont(UIConstants.TEXT_FONT);
        label.setPadding(new Insets(20, 0, 0, 0));
        return label;
    }

    /**
     * Erzeuge das Zusammenfassung-Panel, in dem die ausgewählten Verleihkarten
     * im Detail angezeigt werden.
     */
    private void erzeugeVerleihkartenAnzeiger()
    {
        _verleihkartenAnzeigerTextArea = new TextArea();
        _verleihkartenAnzeigerTextArea.setEditable(false);
        _verleihkartenAnzeigerTextArea.setFont(UIConstants.TEXT_FONT);

    }

    /**
     * Erzeuge den Rücknahme-Button.
     */
    private void erzeugeRuecknahmeButton()
    {
        _ruecknahmeButton = new Button("zurücknehmen");
        _ruecknahmeButton.setPrefSize(140, 100);
        _ruecknahmeButton.setMaxWidth(Double.MAX_VALUE);
        _ruecknahmeButton.setDisable(true);
        _ruecknahmeButton.setFont(UIConstants.BUTTON_FONT);
    }

    /**
     * Gibt den Rücknahme-Button zurück.
     * 
     * @return Den Rücknahme-Button.
     * 
     * @ensure result != null
     */
    public Button getRuecknahmeButton()
    {
        return _ruecknahmeButton;
    }

    /**
     * Gibt die TextArea zur Darstellung der ausgewählten Verleihkarte zurück.
     * 
     * @return Die TextArea zur darstellung der ausgewählten Verleihkarte.
     * 
     * @ensure result != null
     */
    public TextArea getVerleihkartenAnzeigerTextArea()
    {
        return _verleihkartenAnzeigerTextArea;
    }

    /**
     * Gibt die Tabelle zur Darstellung der Verleihkarten zurück.
     * 
     * @return Die Tabelle zur Darstellung der Verleihkarten.
     * 
     * @ensure result != null
     */
    public TableView<Verleihkarte> getVerleihkartenAuflisterTable()
    {
        return _verleihkartenAuflisterTable;
    }

    /**
     * Gibt das Haupt-Pane der UI zurück.
     * 
     * @return Das Haupt-Pane der UI.
     * 
     * @ensure result != null
     */
    public Pane getUIPane()
    {
        return _hauptPane;
    }
}
