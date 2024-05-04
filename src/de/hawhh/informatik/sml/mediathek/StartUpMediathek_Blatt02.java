package de.hawhh.informatik.sml.mediathek;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Startet die Hauptanwendung mit grafischer Oberfläche.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
public class StartUpMediathek_Blatt02 extends Application
{
	private static final File KUNDEN_DATEI = new File(
			"./bestand/kundenstamm.txt");
	private static final File MEDIEN_DATEI = new File(
			"./bestand/medienbestand.txt");
	private static KundenstammService _kundenstamm;
	private static MedienbestandService _medienbestand;
	private static VerleihService _verleihService;
	private static VerleihProtokollierer _protokollierer;

	/**
	 * Die Main-Methode.
	 * 
	 * @param args die Aufrufparameter.
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

	/**
	 * Erstellt die Services und lädt die Daten.
	 * 
	 * @throws IOException
	 */
	private static void erstelleServices()
	{
		try
		{
			_protokollierer = new VerleihProtokollierer();
			DatenEinleser datenEinleser = new DatenEinleser(MEDIEN_DATEI,
					KUNDEN_DATEI);
			datenEinleser.leseDaten();
			_medienbestand = new MedienbestandServiceImpl(
					datenEinleser.getMedien());
			_kundenstamm = new KundenstammServiceImpl(
					datenEinleser.getKunden());
			_verleihService = new VerleihServiceImpl(_kundenstamm,
					_medienbestand, datenEinleser.getVerleihkarten(),
					_protokollierer);
		} catch(DateiLeseException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		erstelleServices();
		new MediathekWerkzeug(primaryStage, _medienbestand, _kundenstamm,
				_verleihService);
	}
}
