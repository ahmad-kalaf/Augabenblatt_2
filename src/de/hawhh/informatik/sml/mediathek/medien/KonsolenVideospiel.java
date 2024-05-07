package de.hawhh.informatik.sml.mediathek.medien;

public class KonsolenVideospiel extends AbstractVideospiel
{

	public KonsolenVideospiel(String titel, String kommentar, String system)
	{
		super(titel, kommentar, system, "Konsolen-Videospiel");
	}

	@Override
	public int getPreisNachTagen(int tage)
	{
		 return (int)Math.ceil((double)tage / 3) * 700;
	}

}
