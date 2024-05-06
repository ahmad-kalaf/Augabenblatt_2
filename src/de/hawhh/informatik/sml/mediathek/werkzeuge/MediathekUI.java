package de.hawhh.informatik.sml.mediathek.werkzeuge;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Das Hauptfenster fuer die Anwendung
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
class MediathekUI
{
    public static final String AUSLEIHE = "Ausleihe-Ansicht";
    public static final String NAME = "PM2-Mediathek 2024 Blatt 02";
    public static final String RUECKGABE = "Rückgabe-Ansicht";

    private ToggleButton _ausleiheButton;
    private ToggleButton _rueckgabeButton;

    private ToggleGroup _group;

    private Pane _menuPane;
    private Pane _anzeigePane;

    private Pane _ausleihePane;
    private Pane _rueckgabePane;

    /**
     * Initialisiert die OberflÃ¤che der Mediathek
     * 
     * @param ausleihePane
     *            Das Pane der Ausleihe.
     * @param rueckgabePane
     *            Das Pane der Rueckgabe.
     * 
     * @require ausleihePane != null
     * @require rueckgabePane != null
     */
    public MediathekUI(Stage primaryStage, Pane ausleihePane, Pane rueckgabePane)
    {
        assert ausleihePane != null : "Vorbedingung verletzt: ausleihePane != null";
        assert rueckgabePane != null : "Vorbedingung verletzt: rueckgabePane != null";

        _group = new ToggleGroup();

        _ausleihePane = ausleihePane;
        _rueckgabePane = rueckgabePane;

        primaryStage.setTitle(NAME);
        VBox contentPane = new VBox();

        erzeugeMenuPane();
        erzeugeAnzeigePane();
        VBox.setVgrow(_anzeigePane, Priority.ALWAYS);
        contentPane.getChildren().add(_menuPane);
        contentPane.getChildren().add(_anzeigePane);

        Scene scene = new Scene(contentPane, 1120, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Erzeugt das Anzeige-Pane, in dem die Panes fÃ¼r die verschiedenen
     * Ansichten (wie beispielsweise das RÃ¼ckgabe-Pane) angezeigt und versteckt
     * werden kÃ¶nnen.
     * 
     * FÃ¼gt die Panes fÃ¼r die verschiedenen Ansichten in das Anzeige-Pane ein.
     */
    private void erzeugeAnzeigePane()
    {
        _anzeigePane = new StackPane();
        _anzeigePane.setBackground(new Background(new BackgroundFill(
                UIConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        _anzeigePane.getChildren().add(_ausleihePane);
    }

    /**
     * Erzeugt den Ausleih-Button fÃ¼r das MenÃ¼
     */
    private void erzeugeAusleiheButton()
    {
        _ausleiheButton = new ToggleButton();
        initialisiereMenuButton(_ausleiheButton, AUSLEIHE);
        _ausleiheButton.setSelected(true);
    }

    /**
     * Erzeugt den Rückgabe-Button für das Menü.
     */
    private void erzeugeRueckgabeButton()
    {
        _rueckgabeButton = new ToggleButton();
        initialisiereMenuButton(_rueckgabeButton, RUECKGABE);
    }

    /**
     * Erzeugt das Menü mit Ausleih- und Rückgabe-Button und Titel.
     */
    private void erzeugeMenuPane()
    {
        HBox menuPane = new HBox();
        menuPane.setSpacing(4);
        menuPane.setAlignment(Pos.CENTER);
        _menuPane = menuPane;
        _menuPane.setBackground(new Background(new BackgroundFill(
                UIConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        _menuPane.setPadding(new Insets(10));
        erzeugeAusleiheButton();
        erzeugeRueckgabeButton();
        erzeugeTitel();
    }

    /**
     * Initialisiert einen MenÃ¼-Button.
     * 
     * @param button
     *            Der Button.
     * @param buttonText
     *            Der Text der auf dem Button stehen soll.
     */
    private void initialisiereMenuButton(ToggleButton button, String buttonText)
    {
        button.setToggleGroup(_group);
        button.setText(buttonText);
        button.setMinSize(180, 50);
        button.setFont(UIConstants.BUTTON_FONT);
        _menuPane.getChildren().add(button);
    }

    /**
     * Erzeugt den Titel fÃ¼r das MenÃ¼.
     */
    private void erzeugeTitel()
    {
        Pane spacerPane = new Pane();
        _menuPane.getChildren().add(spacerPane);
        HBox.setHgrow(spacerPane, Priority.ALWAYS);

        spacerPane.setBackground(new Background(new BackgroundFill(
                UIConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        Label titelLabel = new Label();
        _menuPane.getChildren().add(titelLabel);
        titelLabel.setText(NAME);
        titelLabel.setFont(UIConstants.TITLE_FONT);
        titelLabel.setTextFill(UIConstants.TITLE_COLOR);
        titelLabel.setBackground(new Background(new BackgroundFill(
                UIConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Zeigt das Werkzeug mit dem angebenen Namen.
     * 
     * @param werkzeugName
     *            Der Name eines Werkzeugs.
     * 
     * @require werkzeugName != null
     */
    private void zeigeAn(String werkzeugName)
    {
        assert werkzeugName != null : "Vorbedingung verletzt: werkzeugName != null";

        _anzeigePane.getChildren().clear();
        ToggleButton buttonToSelect;

        if (werkzeugName.equals(AUSLEIHE))
        {
            _anzeigePane.getChildren().add(_ausleihePane);
            buttonToSelect = getAusleiheButton();
        }
        else if (werkzeugName.equals(RUECKGABE))
        {
            _anzeigePane.getChildren().add(_rueckgabePane);
            buttonToSelect = getRueckgabeButton();
        }
        else
        {
            throw new IllegalArgumentException("Werkzeugname unbekannt: "
                    + werkzeugName);
        }

        buttonToSelect.setSelected(true);
    }

    /**
     * Gibt den Ausleihe-Button zurÃ¼ck.
     * 
     * @return Den Ausleihe-Button.
     */
    public ToggleButton getAusleiheButton()
    {
        return _ausleiheButton;
    }

    /**
     * Gibt den Rueckgabe-Button zurück.
     * 
     * @return Den Rueckgabe-Button.
     */
    public ToggleButton getRueckgabeButton()
    {
        return _rueckgabeButton;
    }

    /**
     * Zeigt die Ausleihe-Sicht
     */
    public void zeigeAusleihe()
    {
        zeigeAn(MediathekUI.AUSLEIHE);
    }

    /**
     * Zeigt die Rückgabe-Sicht
     */
    public void zeigeRueckgabe()
    {
        zeigeAn(MediathekUI.RUECKGABE);
    }

}
