package observation;

import java.util.ArrayList;


public interface Observateur {
	public void updateListe(ArrayList<String> liste);
	public void updateFond(String url);
	public void updateScene();
}
