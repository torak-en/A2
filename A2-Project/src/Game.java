import Entities.Tiles.Tile;
import Level.Level;
import Level.LevelHandler;
import Render.Render;

public class Game {
	private boolean running = true;
	private String state = "Start Menu";
	Render render = new Render();

	public static void main(String[] args) {
		System.out.println("Hello world!");
		LevelHandler nlh = new LevelHandler();
		Level nl = nlh.createLevel(1);
		System.out.println(nl.getTileLayer().length * nl.getTileLayer()[0].length);
		System.out.println(nl.getItemList().toString());
		System.out.println(nl.getActorList().toString());
		Tile tile =  nl.getTileLayer()[0][0];
		System.out.println(tile);
		System.out.println(tile.getType());
		System.out.println(tile.getType().getImage());

		Game g = new Game();
		g.renderThings(nl);
	}

	public void renderThings(Level nl){
		render.setLevel(nl);
		render.renderLevel();
	}

	public void ticking() {
		int ns = 1000000000;
		int ticksPerSecond = 60;
		int nsPerTick = ns / ticksPerSecond;
		double progress = 0;

		double last = System.nanoTime();

		while (running) {
			double now = System.nanoTime();
			double diff = now - last;
			last = now;

			progress += diff / nsPerTick;

			while (progress >= 1) {
				progress --;
				tick();
			}
		}
	}

	public void tick() {

	}
}