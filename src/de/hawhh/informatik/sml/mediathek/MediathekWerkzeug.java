package de.hawhh.informatik.sml.mediathek;
import javafx.stage.Stage;

/**
 * Das Gesamtwerkzeug der Mediathek, das die anderen Werkzeuge zusammenfasst.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
class MediathekWerkzeug
{
    /**
     * Die UI der Mediathek.
     */
    private MediathekUI _mediathekUI;

    /**
     * Der Kundenstamm.
     */
    private final KundenstammService _kundenstamm;

    /**
     * Der Medienbestand.
     */
    private final MedienbestandService _medienbestand;

    /**
     * Der Service zum Verleihen und Zurücknehmen von Medien.
     */
    private final VerleihService _verleihService;

    /**
     * Das AusleihWerkzeug.
     */
    private final AusleihWerkzeug _ausleihWerkzeug;

    /**
     * Das RueckgabeWerkzeug.
     */
    private final RueckgabeWerkzeug _rueckgabeWerkzeug;

    /**
     * Initialisiert ein neues MediathekWerkzeug.
     * 
     * @param medienbestand
     *            der Medienbestand
     * @param kundenstamm
     *            die Kunden
     * @param verleihService
     *            Service um Medien zu verleihen.
     * 
     * @require medienbestand != null
     * @require kundenstamm != null
     * @require verleihService != null
     */
    public MediathekWerkzeug(Stage primaryStage, MedienbestandService medienbestand,
            KundenstammService kundenstamm, VerleihService verleihService)
    {
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand != null";
        assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm != null";
        assert verleihService != null : "Vorbedingung verletzt: verleihService != null";

        _medienbestand = medienbestand;
        _kundenstamm = kundenstamm;
        _verleihService = verleihService;

        // Erzeuge Subwerkzeuge
        _ausleihWerkzeug = new AusleihWerkzeug(_medienbestand, _kundenstamm,
                _verleihService);
        _rueckgabeWerkzeug = new RueckgabeWerkzeug(_verleihService);

        // Erzeuge UI für dieses Werkzeug
        _mediathekUI = new MediathekUI(primaryStage, _ausleihWerkzeug.getUIPane(),
                _rueckgabeWerkzeug.getUIPane());

        registriereUIAktionen();
    }

    /**
     * Registriert die Aktionen an der UI, die bei bestimmten Benutzeraktionen
     * ausgeführt werden sollen.
     */
    private void registriereUIAktionen()
    {
        registriereZeigeAusleiheAktion();
        registriereZeigeRueckgabeAktion();
    }

    /**
     * Registriert die Aktion, die ausgeführt wird, um die Ausleihe anzuzeigen.
     */
    private void registriereZeigeAusleiheAktion()
    {
        _mediathekUI.getAusleiheButton()
                .setOnAction(event -> _mediathekUI.zeigeAusleihe());
    }

    /**
     * Registriert die Aktion, die ausgeführt wird, um die Rueckgabe anzuzeigen.
     */
    private void registriereZeigeRueckgabeAktion()
    {
        _mediathekUI.getRueckgabeButton()
                .setOnAction(event -> _mediathekUI.zeigeRueckgabe());
    }

}
