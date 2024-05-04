package de.hawhh.informatik.sml.mediathek;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * MedienDetailAnzeigerUI beschreibt die grafische Benutzungsoberfläche für das
 * MedienDetailAnzeigerWerkzeug.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
class MedienDetailAnzeigerUI
{
    private Pane _hauptPane;
    private TextArea _medienAnzeigerTextArea;

    /**
     * Initialisiert eine neue MedienDetailAnzeigerUI.
     */
    public MedienDetailAnzeigerUI()
    {
        _hauptPane = erzeugeHauptPane();
    }

    /**
     * Ereugt das Haupt-Pane.
     */
    private Pane erzeugeHauptPane()
    {
        BorderPane hauptPane = new BorderPane();
        hauptPane.setTop(erzeugeTextAreaHeader());
        hauptPane.setCenter(erzeugeMedienAnzeiger());
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
     * Erzeugt das Panel in dem die Mediendetails angezeigt werden.
     */
    private TextArea erzeugeMedienAnzeiger()
    {
        _medienAnzeigerTextArea = new TextArea();
        _medienAnzeigerTextArea.setEditable(false);
        _medienAnzeigerTextArea.setFont(UIConstants.TEXT_FONT);

        _medienAnzeigerTextArea.setStyle("-fx-border-color: black");

        return _medienAnzeigerTextArea;
    }

    /**
     * Gibt die TextArea, die zur Anzeige der ausgewählten Medien verwendet
     * wird.
     * 
     * @ensure result != null
     */
    public TextArea getMedienAnzeigerTextArea()
    {
        return _medienAnzeigerTextArea;
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
