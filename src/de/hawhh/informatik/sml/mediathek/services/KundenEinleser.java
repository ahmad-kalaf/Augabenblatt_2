package de.hawhh.informatik.sml.mediathek.services;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import de.hawhh.informatik.sml.mediathek.kunden.Kunde;
import de.hawhh.informatik.sml.mediathek.kunden.Kundennummer;
import de.hawhh.informatik.sml.mediathek.wertklassen.PLZ;

/**
 * Liest Kunden aus einer CVS-Textdatei ein. Die Kundeninformationen müssen mit
 * einem ";" getrennt sein.
 * 
 * Die Reihenfolge der Kundeninformationen:
 * 
 * Kundennummer; Vorname; Nachname; Strasse; PLZ; Ort
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
class KundenEinleser
{

    /**
     * Liest Kunden aus einer Textdatei ein und gibt alle eingelesenen Kunden
     * zurück.
     * 
     * @param kundenDatei
     *            Die Datei in der die Kunden gespeichert sind.
     * @throws DateiLeseException
     *             wenn der Kundenstamm nicht gelesen werden konnte.
     * 
     * @require kundenDatei != null
     * 
     * @ensure result != null
     */
    public List<Kunde> leseKundenEin(File kundenDatei)
            throws DateiLeseException
    {
        assert kundenDatei != null : "Vorbedingung verletzt: kundenDatei != null";
        List<Kunde> eingeleseneKunden = new ArrayList<Kunde>();

        // seit Java 7: try-with-resources
        try (BufferedReader reader = new BufferedReader(new FileReader(kundenDatei));)
        {
            String line = null;
            // liest Datei Zeile für Zeile
            while ((line = reader.readLine()) != null)
            {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                int kundennummer = Integer.valueOf(tokenizer.nextToken());
                String vorname = tokenizer.nextToken();
                String nachname = tokenizer.nextToken();
                String strasse = tokenizer.nextToken();
                String plz = tokenizer.nextToken();
                String ort = tokenizer.nextToken();

                if (Kundennummer.istGueltig(kundennummer)
                        && PLZ.istGueltig(plz))
                {
                    Kunde kunde = new Kunde(Kundennummer.get(kundennummer),
                            vorname, nachname);
                    kunde.setStrasse(strasse);
                    kunde.setWohnort(ort);
                    kunde.setPLZ(PLZ.get(plz));

                    eingeleseneKunden.add(kunde);
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            throw new DateiLeseException(
                    "Der Kundenstamm konnte nicht eingelesen werden, da die Datei nicht gefunden wurde.");
        }
        catch (IOException e)
        {
            throw new DateiLeseException(
                    "Der Kundenstamm konnte nicht eingelesen werden, da die Datei nicht gelesen werden konnte.");
        }
        return eingeleseneKunden;
    }
}
