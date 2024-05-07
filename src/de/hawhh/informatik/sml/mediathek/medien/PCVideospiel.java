package de.hawhh.informatik.sml.mediathek.medien;
public class PCVideospiel extends AbstractVideospiel
{
	public PCVideospiel(String titel, String kommentar, String system)
	{
		super(titel, kommentar, system, "PC-Videospiel");
	}

	@Override
	public int getPreisNachTagen(int tage)
	{
		if(tage > 7)
		{
			return (int)Math.ceil(((double)(tage - 7) / 5)) * 500;
		}
		return 0;
	}

}
