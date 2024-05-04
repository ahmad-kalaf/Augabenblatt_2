package de.hawhh.informatik.sml.mediathek;
import java.util.ArrayList;
import java.util.List;

/**
 * Eine abstrakte Implementation des ObservableService Interfaces, die die
 * Verwaltung und Benachrichtigung der Beobachter bereitstellt.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
abstract class AbstractObservableService implements ObservableService
{

    /**
     * Die Liste der registrierten Beobachter.
     */
    private List<ServiceObserver> _beobachterListe;

    /**
     * Initialisiert einen neuen AbstractObservableService.
     */
    public AbstractObservableService()
    {
        _beobachterListe = new ArrayList<ServiceObserver>();
    }

    @Override
    public void registriereBeobachter(ServiceObserver beobachter)
    {
        assert beobachter != null : "Vorbedingung verletzt: beobachter != null";
        if (!_beobachterListe.contains(beobachter))
        {
            _beobachterListe.add(beobachter);
        }
    }

    @Override
    public void entferneBeobachter(ServiceObserver beobachter)
    {
        assert beobachter != null : "Vorbedingung verletzt: beobachter != null";
        _beobachterListe.remove(beobachter);
    }

    /**
     * Informiert alle angemeldeten Beobachter dass eine relevante Änderung
     * eingetreten ist.
     */
    protected void informiereUeberAenderung()
    {
        for (ServiceObserver beobachter : _beobachterListe)
        {
            beobachter.reagiereAufAenderung();
        }
    }
}
