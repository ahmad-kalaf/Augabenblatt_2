package de.hawhh.informatik.sml.mediathek;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * KundenDetailAnzeigerUI beschreibt die grafische Benutzungsoberfläche für das
 * KundenDetailAnzeigerWerkzeug.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
public class KundenDetailAnzeigerUI
{
    private Pane _hauptPane;
    private TextArea _kundenAnzeigerTextArea;

    /**
     * Initialisiert eine neue KundenDetailAnzeigerUI.
     */
    public KundenDetailAnzeigerUI()
    {
        erzeugeKundenAnzeiger();
        _hauptPane = erzeugeHauptPane();
    }

    /**
     * Ereugt das Haupt-Panel.
     */
    private Pane erzeugeHauptPane()
    {
        VBox hauptPane = new VBox();
        hauptPane.getChildren().add(erzeugeTextAreaHeader());
        hauptPane.getChildren().add(_kundenAnzeigerTextArea);
        return hauptPane;
    }

    private Label erzeugeTextAreaHeader()
    {
        Label label = new Label("Ausgewählter Kunde");
        label.setFont(UIConstants.TEXT_FONT);
        label.setPadding(new Insets(10, 0, 0, 0));
        return label;
    }

    /**
     * Erzeugt das Panel in dem die Kundendetails angezeigt werden.
     */
    private void erzeugeKundenAnzeiger()
    {
        _kundenAnzeigerTextArea = new TextArea();
        _kundenAnzeigerTextArea.setBackground(new Background(new BackgroundFill(
                UIConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        _kundenAnzeigerTextArea.setEditable(false);

        _kundenAnzeigerTextArea.setFont(UIConstants.TEXT_FONT);

        _kundenAnzeigerTextArea.setStyle("-fx-border-color: black");
    }

    /**
     * Gibt die TextArea, die zur Anzeige des ausgewählten Kunden verwendet
     * wird.
     * 
     * @ensure result != null
     */
    public TextArea getKundenAnzeigerTextArea()
    {
        return _kundenAnzeigerTextArea;
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
