package de.hawhh.informatik.sml.mediathek.services;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import de.hawhh.informatik.sml.mediathek.kunden.Kunde;
import de.hawhh.informatik.sml.mediathek.kunden.Kundennummer;
import de.hawhh.informatik.sml.mediathek.medien.CD;
import de.hawhh.informatik.sml.mediathek.medien.DVD;
import de.hawhh.informatik.sml.mediathek.medien.KonsolenVideospiel;
import de.hawhh.informatik.sml.mediathek.medien.Medium;
import de.hawhh.informatik.sml.mediathek.medien.PCVideospiel;
import de.hawhh.informatik.sml.mediathek.wertklassen.Datum;

/**
 * Liest Medien aus einer Textdatei ein. Der MedienEinleser kann CDs, DVDs,
 * KonsolenVideospiele und PCVideospiele einlesen. Eine Zeile in der Textdatei
 * repraesentiert die Informationen für ein Medium, die durch Semikola
 * voneinander getrennt sind. Neben den Attributen eines Mediums wird auch die
 * Information eingelesen, ob das Medium als verliehen eingetragen werden soll.
 * Dazu muss eine Kundennummer des Ausleihers als zweites Token eingelesen
 * werden, ansonsten wird das Medium als nicht verliehen eingelesen. Dazu kann
 * das erste Token eine positive Anzahl an Tagen liefern, die als Ausleihtage
 * vor dem heutigen Datum berechnet werden; wird keine Zahl eingelesen, wird das
 * heutige Datum gewaehlt.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
class MedienEinleser
{
	// Dieses Pattern dient der Überprüfung einer Kundennummer
	private static final Pattern KUNDENNUMMER_PATTERN = Pattern
			.compile("([0-9]{6})");
	private static String LEERSTRING = "";
	private static String LEERZEICHEN = " ";

	/**
	 * Versucht aus dem übergebenen String eine positive Anzahl an Tagen zu
	 * extrahieren.
	 * 
	 * @return Ein Ausleihdatum vor tageString Tagen oder das heutige, wenn
	 *         keine Anzahl an Tagen extrahiert werden konnte.
	 */
	private static Datum ermittleAusleihdatum(String tageString)
	{
		Datum ausleihdatum = Datum.heute();
		if(tageString.matches("\\d+"))
		{
			int verlieheneTage = Integer.parseInt(tageString);
			ausleihdatum = ausleihdatum.minus(verlieheneTage);
		}
		return ausleihdatum;
	}

	/**
	 * Versucht aus dem übergebenen String eine Kundennummer zu extrahieren.
	 * 
	 * @return Eine ermittelte Kundennummer oder null, wenn keine Kundennummer
	 *         extrahiert werden konnte.
	 */
	private static Kundennummer ermittleKundennummer(String kundennummerString)
	{
		Kundennummer ergebnis = null;
		Matcher m = KUNDENNUMMER_PATTERN.matcher(kundennummerString);
		if(m.matches())
		{
			int nummer = Integer.parseInt(m.group(1), 10);
			if(Kundennummer.istGueltig(nummer))
			{
				ergebnis = Kundennummer.get(nummer);
			}
		}
		return ergebnis;
	}

	/**
	 * Liest Medien aus einer Textdatei ein und gibt alle eingelesenen Medien
	 * und eventuell dazugehörende Verleihkarten zurück.
	 * 
	 * @param kundenstamm Ein Kundenstamm, um Kunden anhand ihrer Kundennummer
	 *                    zu finden.
	 * @param medienDatei Die Datei in der die Medien gespeichert sind.
	 * @return Eine Map der Medien und zugehöriger Verleihkarten (falls
	 *         existent).
	 * @throws DateiLeseException wenn der Medien-Datenbestand nicht gelesen
	 *                            werden konnte.
	 * 
	 * @require kundenstamm != null
	 * @require medienDatei != null
	 * 
	 * @ensure result != null
	 */
	public Map<Medium, Verleihkarte> leseMedienEin(List<Kunde> kundenstamm,
			File medienDatei) throws DateiLeseException
	{
		assert kundenstamm != null
				: "Vorbedingung verletzt: kundenstamm != null";
		assert medienDatei != null
				: "Vorbedingung verletzt: medienDatei != null";
		Map<Medium, Verleihkarte> eingeleseneMedien = new HashMap<Medium, Verleihkarte>();
		// seit Java 7: try-with-resources
		try(BufferedReader reader = new BufferedReader(
				new FileReader(medienDatei));)
		{
			Map<Kundennummer, Kunde> kundenMap = new HashMap<Kundennummer, Kunde>();
			for(Kunde kunde : kundenstamm)
			{
				kundenMap.put(kunde.getKundennummer(), kunde);
			}
			String line = null;
			// liest die Datei Zeile für Zeile
			while((line = reader.readLine()) != null)
			{
				StringTokenizer tokenizer = new StringTokenizer(line, ";");
				Datum ausleihDatum = ermittleAusleihdatum(
						naechsterToken(tokenizer));
				Kundennummer kundennummer = ermittleKundennummer(
						naechsterToken(tokenizer));
				Medium medium = leseMediumEin(tokenizer);
				Verleihkarte verleihkarte = null;
				if(medium != null)
				{
					if(kundennummer != null)
					{
						Kunde kunde = kundenMap.get(kundennummer);
						verleihkarte = new Verleihkarte(kunde, medium,
								ausleihDatum);
					}
					eingeleseneMedien.put(medium, verleihkarte);
				}
			}
			reader.close();
		} catch(FileNotFoundException e)
		{
			throw new DateiLeseException(
					"Der Medien-Datenbestand konnte nicht eingelesen werden, da die Datei nicht gefunden wurde.");
		} catch(IOException e)
		{
			throw new DateiLeseException(
					"Der Medien-Datenbestand konnte nicht eingelesen werden, da die Datei nicht gelesen werden konnte.");
		}
		return eingeleseneMedien;
	}

	/**
	 * Liest die Daten für ein Medium aus dem übergebenen StringTokenizer aus
	 * und erzeugt ein konkretes Objekt eines Subtyps von Medium.
	 * 
	 * @param tokenizer Ein StringTokenizer, der die Daten liefert.
	 * @return ein neu erzeugtes Medium oder null, wenn kein Medium erzeugt
	 *         werden konnte.
	 */
	private static Medium leseMediumEin(StringTokenizer tokenizer)
	{
		String medienBezeichnung = naechsterToken(tokenizer);
		String titel = naechsterToken(tokenizer);
		String kommentar = naechsterToken(tokenizer);
		Medium medium = null;
		if(medienBezeichnung.equals("CD"))
		{
			String interpret = naechsterToken(tokenizer);
			String spiellaenge = naechsterToken(tokenizer);
			medium = new CD(titel, kommentar, interpret,
					Integer.parseInt(spiellaenge));
		} else if(medienBezeichnung.equals("DVD"))
		{
			String regisseur = naechsterToken(tokenizer);
			int laufzeit = Integer.valueOf(naechsterToken(tokenizer));
			medium = new DVD(titel, kommentar, regisseur, laufzeit);
		}
		// Die folgenden Kommentarzeichen entfernen, um PC- und
		// Konsolen-Videospiele in der Mediathek zu haben, und
		// den anschließenden else-Zweig auskommentieren.
		else if(medienBezeichnung.equals("KonsolenVideospiel")
				|| medienBezeichnung.equals("PCVideospiel"))
		{
			String system = naechsterToken(tokenizer);
			if(medienBezeichnung.equals("KonsolenVideospiel"))
			{
				medium = new KonsolenVideospiel(titel, kommentar, system);
			} else if(medienBezeichnung.equals("PCVideospiel"))
			{
				medium = new PCVideospiel(titel, kommentar, system);
			}
		}
//         Diesen else-Zweig auskommentieren, sobald
//         PC- und Konsolen-Videospiele existieren.
//        else if (medienBezeichnung.equals("Videospiel"))
//        {
//            String system = naechsterToken(tokenizer);
//
//            medium = new Videospiel(titel, kommentar, system);
//        }
		return medium;
	}

	/**
	 * Holt das nächste Token vom Tokenizer und dekodiert es.
	 * 
	 * @param tokenizer Ein Tokenizer.
	 * @return Das nächste dekodierte Token.
	 */
	private static String naechsterToken(StringTokenizer tokenizer)
	{
		return dekodiere(tokenizer.nextToken());
	}

	/**
	 * Dekodiert den übergebenen String.
	 * 
	 * @param text Ein String der dekodiert werden soll.
	 * @require text != null
	 * @return Ein dekodierter String.
	 */
	private static String dekodiere(String text)
	{
		String ergebnis = text;
		if(text.equals(LEERZEICHEN))
		{
			ergebnis = LEERSTRING;
		}
		return ergebnis;
	}
}
