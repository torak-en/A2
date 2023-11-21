public class Main {
	private boolean running = true;
	private String state = "Start Menu";

	public static void main(String[] args) {
		System.out.println("Hello world!");
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