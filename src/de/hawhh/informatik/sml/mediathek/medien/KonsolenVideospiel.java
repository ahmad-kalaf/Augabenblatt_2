package de.hawhh.informatik.sml.mediathek.medien;

public class KonsolenVideospiel extends AbstractVideospiel
{

	public KonsolenVideospiel(String titel, String kommentar, String system)
	{
		super(titel, kommentar, system);
	}

	@Override
	public int getPreisNachTagen(int tage)
	{
		// Diese Formel berechnet den Geldbetrag in Euro-Cent
		int betrag = 700 * (((tage - 1) / 3) + 1);
		return betrag;
	}
}
