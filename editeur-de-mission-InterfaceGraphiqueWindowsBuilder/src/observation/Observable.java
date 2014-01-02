package observation;


public interface Observable {
	public void addObservateur(Observateur obs);
	public void updateListeObervateur();
	public void updateSceneObervateur();
	public void delObservateur();
}
