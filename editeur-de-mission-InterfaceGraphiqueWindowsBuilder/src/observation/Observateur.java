package observation;

import java.util.List;

import data.Objet;

public interface Observateur {
	public void updateListe(List<Objet> liste);
	public void updateFond(String url);
	public void updateScene();
}
