package de.hawhh.informatik.sml.mediathek;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * AusleiheMedienauflisterUI beschreibt die grafische Benutzungsoberfläche für
 * das AusleiheMedienauflisterWerkzeug.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
public class AusleiheMedienauflisterUI
{
    private Pane _hauptPane;
    private TableView<AusleiheMedienFormatierer> _medienTable;

    /**
     * Initialisiert eine neue AusleiheMedienauflisterUI.
     */
    public AusleiheMedienauflisterUI()
    {
        erzeugeMedienTable();
        _hauptPane = erzeugeHauptPane();
    }

    /**
     * Erzeugt das Haupt-Panel.
     */
    private Pane erzeugeHauptPane()
    {
        VBox hauptPane = new VBox();
        VBox.setVgrow(_medienTable, Priority.ALWAYS);
        hauptPane.getChildren().add(erzeugeTableHeader());
        hauptPane.getChildren().add(_medienTable);
        return hauptPane;
    }

    private Label erzeugeTableHeader()
    {
        Label label = new Label("Medien");
        label.setFont(UIConstants.LABEL_TEXT_FONT);
        label.setPadding(new Insets(10, 0, 0, 0));
        return label;
    }

    /**
     * Erzeugt die Tabelle für die Anzeige der Medien.
     */
    private void erzeugeMedienTable()
    {
        _medienTable = new TableView<AusleiheMedienFormatierer>();
        _medienTable.setPrefWidth(500);
        _medienTable.setPrefWidth(500);

        TableColumn<AusleiheMedienFormatierer, String> medientyp = new TableColumn<>(
                "Medientyp");
        TableColumn<AusleiheMedienFormatierer, String> titel = new TableColumn<>("Titel");
        TableColumn<AusleiheMedienFormatierer, String> ausleihbar = new TableColumn<>(
                "Ausleihbar");

        medientyp.setCellValueFactory(
                p -> new ReadOnlyObjectWrapper<String>(
                        p.getValue().getMedienBezeichnung()));
        titel.setCellValueFactory(
                p -> new ReadOnlyObjectWrapper<String>(p.getValue().getTitel()));
        ausleihbar.setCellValueFactory(
                p -> new ReadOnlyObjectWrapper<String>(p.getValue().getAusleihstatus()));

        _medienTable.getColumns().add(medientyp);
        _medienTable.getColumns().add(titel);
        _medienTable.getColumns().add(ausleihbar);

        _medienTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        _medienTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        _medienTable.setStyle("-fx-border-color: black");

    }

    /**
     * Gibt die Medienauflistertabelle (TableView) zurück.
     * 
     * @ensure result != null
     */
    public TableView<AusleiheMedienFormatierer> getMedienAuflisterTable()
    {
        return _medienTable;
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
