package display;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Display {

	private long window;

	public Display() {

		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		this.window = glfwCreateWindow(vidmode.width(), vidmode.height(), "Hello World!", NULL, NULL);

		this.setCallBack();

		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);

		GL.createCapabilities();

	}

	private void setCallBack() {
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_D && action == GLFW_RELEASE)
				System.out.println(key);
		});
	}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void swap() {
		glfwSwapBuffers(this.window); // swap the color buffers
	}

	public void pollEvents() {
		glfwPollEvents();
	}

	public void dispose() {
		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(this.window);
	}
}
