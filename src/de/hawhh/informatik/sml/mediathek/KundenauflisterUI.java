package de.hawhh.informatik.sml.mediathek;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * KundenauflisterUI beschreibt die grafische Benutzungsoberfläche für das
 * KundenauflisterWerkzeug.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
public class KundenauflisterUI
{
    private Pane _hauptPane;
    private TableView<Kunde> _kundenTable;

    /**
     * Initialisiert eine neue KundenauflisterUI.
     */
    public KundenauflisterUI()
    {
        erzeugeKundenTable();
        _hauptPane = erzeugeHauptPane();
    }

    /**
     * Ereugt das Haupt-Panel.
     */
    private Pane erzeugeHauptPane()
    {
        VBox hauptPane = new VBox();
        VBox.setVgrow(_kundenTable, Priority.ALWAYS);
        hauptPane.getChildren().add(erzeugeTableHeader());
        hauptPane.getChildren().add(_kundenTable);
        hauptPane.setPadding(new Insets(0, 0, 10, 0));
        return hauptPane;
    }

    private Label erzeugeTableHeader()
    {
        Label label = new Label("Kunden");
        label.setFont(UIConstants.LABEL_TEXT_FONT);
        label.setPadding(new Insets(10, 0, 0, 0));
        return label;
    }

    /**
     * Erzeugt die Tabelle für die Anzeige der Kunden.
     */
    private void erzeugeKundenTable()
    {
        _kundenTable = new TableView<Kunde>();

        TableColumn<Kunde, Kundennummer> kundennummer = new TableColumn<>("Kundennummer");
        TableColumn<Kunde, String> kundenName = new TableColumn<>("Nachname");
        TableColumn<Kunde, String> kundenVorname = new TableColumn<>("Vorname");

        kundennummer.setCellValueFactory(p -> new ReadOnlyObjectWrapper<Kundennummer>(
                p.getValue().getKundennummer()));
        kundenName.setCellValueFactory(
                p -> new ReadOnlyObjectWrapper<String>(p.getValue().getNachname()));
        kundenVorname.setCellValueFactory(
                p -> new ReadOnlyObjectWrapper<String>(p.getValue().getVorname()));

        _kundenTable.getColumns().add(kundennummer);
        _kundenTable.getColumns().add(kundenName);
        _kundenTable.getColumns().add(kundenVorname);

        _kundenTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        _kundenTable.setStyle("-fx-border-color: black");

    }

    /**
     * Gibt die Kundentabelle (TableView) zurück.
     * 
     * @ensure result != null
     */
    public TableView<Kunde> getKundenTable()
    {
        return _kundenTable;
    }

    /**
     * Gibt das Haupt-Pane der UI zurück.
     * 
     * @ensure result != null
     */
    public Pane getUIPane()
    {
        return _hauptPane;
    }
}
