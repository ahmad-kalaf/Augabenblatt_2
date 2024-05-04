package de.hawhh.informatik.sml.mediathek;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Diese Klasse ist zustaendig für die Protokollierung der Ausleih- und
 * Rueckgabevorgaenge.
 */
public class VerleihProtokollierer
{
	public VerleihProtokollierer()
	{
	}

	/**
	 * 
	 * @param ereignis     "Ausleihvorgang" beim Ausleihen oder
	 *                     "Rueckgabevorgang" beim Zurueckgeben eines Mediums
	 * @param verleihkarte vom entsprechenden Medium
	 * @throws IOException
	 * 
	 * @require ereignis != null
	 * @require verleihkarte != null
	 */
	public void protokolliere(String ereignis, Verleihkarte verleihkarte)
			throws ProtokollierException
	{
		assert ereignis != null : "Vorbedingung verletzt: ereignis != null";
		assert verleihkarte != null
				: "Vorbedingung verletzt: verleihkarte != null";
		try(FileWriter _fileWriter = new FileWriter("./bestand/protokolle.txt",
				true))
		{
			_fileWriter.write("Vorgang: " + ereignis + " Details: \n"
					+ verleihkarte.getFormatiertenString() + "\n");
		} catch(IOException e)
		{
			throw new ProtokollierException(e.getMessage());
		}
	}
}