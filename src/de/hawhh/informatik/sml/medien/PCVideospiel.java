package de.hawhh.informatik.sml.medien;
public class PCVideospiel extends AbstractVideospiel
{
	public PCVideospiel(String titel, String kommentar, String system)
	{
		super(titel, kommentar, system);
	}

	@Override
	public int getPreisNachTagen(int tage)
	{
		int result = 0;
		if(tage > 7)
		{
			int zusatzTage = tage - 7;
			result = 500 * ((zusatzTage + 4) / 5);
		}
		return result;
	}
}
