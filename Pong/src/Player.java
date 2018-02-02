import java.awt.Graphics;

public class Player {
	GamePanel panel;

	private int playerX = 25;
	private int playerY = 200;
	private int playerWidth = 20;
	private int playerHeight = 100;

	private int deltaY = 4;

	public Player(GamePanel panel) {
		this.panel = panel;

	}

	public void draw(Graphics g) {
		g.fillRect(playerX, playerY, playerWidth, playerHeight);
	}

	public void step() {
		/*-------------------------------
		 * AI CONTROLLED
		 * --------------------------------
		 */
		// playerY = panel.ball.getBallY();

		/*-----------------------------------
		 * USER CONTROLLED
		 *----------------------------------
		 */
		if (panel.getWPressed()) {
			moveUp();
		}

		if (panel.getSPressed()) {
			moveDown();
		}
	}

	public void moveUp() {

		if (playerY - deltaY > 0) {
			playerY -= deltaY;

		}
	}

	public void moveDown() {

		if (playerY + deltaY + playerHeight < panel.getHeight()) {
			playerY += deltaY;
		}
	}

	public int getX() {
		return playerX;
	}

	public int getWidth() {
		return playerWidth;
	}

	public int getY() {
		return playerY;
	}

	public int getHeight() {
		return playerHeight;
	}

	public int getSpeed() {
		return deltaY;
	}

	public void center() {
		this.playerY = (panel.getHeight() / 2) - 65;
	}

}
