package scene;


import fr.lirmm.game.Game;
import fr.lirmm.game.action.scenes.SceneChangeAction;
import fr.lirmm.game.agent.Script;
import fr.lirmm.game.artefact.Artefact;
import fr.lirmm.game.artefact.Attr;
import fr.lirmm.game.artefact.content.ContentType;
import fr.lirmm.game.input.listener.TouchListener;
import global.GameConstant;

public class TitleSceneBuilder extends Script implements TouchListener {

	private Artefact background;
	private Artefact textArea;

	public TitleSceneBuilder() {
		background = Game.artefacts.create(id, ContentType.SPRITE, GameConstant.TEXTURE_BG);
		background.setFloat(Attr.X, 0.0f);
		background.setFloat(Attr.Y, 0.0f);
		background.setFloat(Attr.WIDTH, (float)Game.graphics.getScreenWidth());
		background.setFloat(Attr.HEIGHT,(float) Game.graphics.getScreenHeight());		
		textArea = Game.artefacts.create("introtext", ContentType.TEXT,GameConstant.FONT_STANDARD);
		textArea.setFloat(Attr.X, (float)Game.graphics.getScreenWidth()/2-50f);
		textArea.setFloat(Attr.Y, (float) Game.graphics.getScreenHeight()/2);
		textArea.setFloat(Attr.WIDTH, 100.f);
		textArea.setFloat(Attr.HEIGHT,100.f);
		textArea.setString(Attr.TEXT,"Click to start the game !");
		}
	public void onUpdate(int delta) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onBegin() {
		super.onBegin();
		Game.scenes.getScene(GameConstant.SCENE_TITLE).addArtefact(background);
		Game.scenes.getScene(GameConstant.SCENE_TITLE).addArtefact(textArea);
		Game.input.addTouchListener(this);
		
	}
	@Override
	public void onEnd() {
		super.onEnd();
		Game.scenes.getScene(GameConstant.SCENE_TITLE).removeArtefact(background);
		Game.scenes.getScene(GameConstant.SCENE_TITLE).removeArtefact(textArea);
		Game.input.removeTouchListener(this);
	}
	public void onTouchDown(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	public void onTouchUp(int x, int y) {
		Game.actions.submit(new SceneChangeAction(GameConstant.SCENE_JEU));
	}
	public void onTouchDrag(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
