import java.awt.Graphics;

public class Ball {
	GamePanel panel;

	private int ballX = 425;
	private int ballY = 225;
	private int diameter = 25;
	private int ballDeltaX = 0;
	private int ballDeltaY = 0;

	public Ball(GamePanel panel) {
		this.panel = panel;

	}

	public void draw(Graphics g) {
		g.fillOval(ballX, ballY, diameter, diameter);
	}

	public void step() {
		handleCollision();
		// move ball
		ballX += ballDeltaX;
		ballY += ballDeltaY;

	}

	public void handleCollision() {

		int nextBallX = ballX + ballDeltaX;
		int nextBallDx = ballX + diameter + ballDeltaX;
		int nextBallY = ballY + ballDeltaY;
		int nextBallDy = ballY + diameter + ballDeltaY;

		int playerX = panel.player.getX();
		int playerDx = panel.player.getX() + panel.player.getWidth();
		int playerY = panel.player.getY();
		int playerDy = panel.player.getY() + panel.player.getHeight();

		int oppX = panel.opp.getX();
		int oppDx = panel.opp.getX() + panel.opp.getWidth();
		int oppY = panel.opp.getY();
		int oppDy = panel.opp.getY() + panel.opp.getHeight();

		/*--------------------------------------
		 * Ceiling Collision
		 * -------------------------------------
		 */

		if (nextBallY < 0) {
			ballDeltaY *= -1;
		}

		/*----------------------------------------
		 * Floor Collision
		 * ---------------------------------------
		 */

		if (nextBallDy > panel.getHeight()) {
			ballDeltaY *= -1;
		}

		/*-----------------------------------
		 * Player collision
		 *-----------------------------------
		 */
		if (nextBallX < playerDx && nextBallDy > playerY && nextBallY < playerDy) {
			ballX = playerDx;
			ballDeltaX *= -1;

			if (ballDeltaY == 0) {
				if (panel.getSPressed()) {
					ballDeltaY += panel.player.getSpeed();
				} else if (panel.getWPressed()) {
					ballDeltaY -= panel.player.getSpeed();
				}

			}

			else if (panel.getSPressed()) {
				if (ballDeltaY > 0) {
					ballDeltaY += panel.player.getSpeed();
				}
				if (ballDeltaY < 0) {
					ballDeltaY += (panel.player.getSpeed() - 2);
				}

			} else if (panel.getWPressed()) {
				if (ballDeltaY < 0) {
					ballDeltaY -= panel.player.getSpeed();
				}
				if (ballDeltaY > 0) {
					ballDeltaY -= (panel.player.getSpeed() - 2);
				}
			}

			System.out.println("YSpeed: " + ballDeltaY);

		}

		/*--------------------------------------
		 * Left wall collision
		 * -------------------------------------
		 */

		else if (nextBallX < 0) {
			centerBall();
			panel.player.center();
			panel.incOppScore();
			panel.opp.center();
			panel.setPlaying(false);
		}

		/*----------------------------------
		 * Opponent collision
		 * ----------------------------------
		 */

		if (nextBallDx > oppX && nextBallDy > oppY && nextBallY < oppDy) {
			ballX = oppX - diameter;
			ballDeltaX *= -1;
			if (ballDeltaX > 0) {
				ballDeltaX++;
			} else if (ballDeltaX < 0) {
				ballDeltaX--;
			}

			if (panel.opp.getSpeed() > 0) {
				ballY += panel.opp.getSpeed();
			}

			else if (panel.opp.getSpeed() < 0) {
				ballY -= panel.opp.getSpeed();
			}

			// TODO add y incremenation
		}

		/*--------------------------------------
		 * Right wall collision
		 * -------------------------------------
		 */

		else if (nextBallDx > panel.getWidth()) {
			centerBall();
			panel.player.center();
			panel.incPlayerScore();
			panel.opp.center();
			panel.setPlaying(false);
		}

	}

	public void centerBall() {
		ballX = (panel.getWidth() / 2) - diameter;
		ballY = (panel.getHeight() / 2) - diameter;
		ballDeltaY = 0;
		ballDeltaX = 0;
		System.out.println(ballY);
	}

	public void incBallXSpeed() {
		ballDeltaX++;

	}

	public void decBallXSpeed() {

		ballDeltaX--;

	}

	public void incBallYSpeed() {
		ballDeltaY++;
	}

	public void decBallYSpeed() {
		ballDeltaY--;
	}

	public void resetBallSpeed() {
		ballDeltaX = 0;
		ballDeltaY = 0;
	}

	public int getBallDiameter() {
		return diameter;
	}

	public int getBallX() {
		return ballX;
	}

	public int getBallY() {
		return ballY;
	}

	public int getDiameter() {
		return diameter;
	}

	public int getDeltaX() {
		return ballDeltaX;
	}

	public int getDeltaY() {
		return ballDeltaY;
	}

	public void startRound() {
		ballDeltaX = -3;
	}

}
