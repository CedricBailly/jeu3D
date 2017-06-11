import display.Display;

import static org.lwjgl.opengl.GL11.glClearColor;

public class HelloWorld {

	private Display display;

	public HelloWorld() {
		this.display = new Display();
	}

	private void loop() {

		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

		while (!this.display.shouldClose()) {

			this.display.clear();

			this.display.swap();

			this.display.pollEvents();

		}

		this.display.dispose();
	}


	public static void main(String[] args) {
		new HelloWorld().loop();
	}

}
