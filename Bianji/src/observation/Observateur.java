package observation;

import java.util.ArrayList;
import java.util.List;

import view.LabelArtefact;
import data.Objet;

public interface Observateur {
	public void updateListe(List<Objet> liste);
	public void updateFond(String url);
	public void updateScene(ArrayList<LabelArtefact> elems);
	public void updateMissions(List<String> idMission);
}
