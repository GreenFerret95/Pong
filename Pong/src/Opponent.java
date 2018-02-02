import java.awt.Graphics;

public class Opponent {

	GamePanel panel;

	private int oppX = 840;
	private int oppY = 200;
	private int oppWidth = 20;
	private int oppHeight = 100;

	private int accuracy = 30;

	private int deltaY = 4;

	public Opponent(GamePanel panel) {
		this.panel = panel;

	}

	public void draw(Graphics g) {
		g.fillRect(oppX, oppY, oppWidth, oppHeight);
	}

	public void step() {

		int nextBallY = panel.ball.getBallY() + panel.ball.getDeltaY();
		int nextBallDy = panel.ball.getBallY() + panel.ball.getDiameter() + panel.ball.getDeltaY();

		if (nextBallY - accuracy < oppY) {
			oppY -= deltaY;
		}

		if (nextBallDy + accuracy > oppY + oppHeight) {
			oppY += deltaY;
		}

	}

	public int getX() {
		return oppX;
	}

	public int getY() {
		return oppY;
	}

	public int getWidth() {
		return oppWidth;
	}

	public int getHeight() {
		return oppHeight;
	}

	public int getSpeed() {
		return deltaY;
	}

	public void center() {
		oppY = 200;
	}

}
