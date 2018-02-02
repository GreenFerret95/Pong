import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	Player player;
	Ball ball;
	Opponent opp;
	Timer timer;
	StartScreen startScreen;
	GameOver gameOver;

	private int playerScore = 0;
	private int oppScore = 0;

	private boolean wPressed = false;
	private boolean sPressed = false;

	private boolean playing = false;
	private boolean end = false;
	private boolean win = false;

	public GamePanel() {
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);

		player = new Player(this);
		ball = new Ball(this);
		opp = new Opponent(this);
		startScreen = new StartScreen();
		gameOver = new GameOver(this);

		timer = new Timer(1000 / 60, this);

		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospaced", Font.BOLD, 40));
		g.drawString(playerScore + " : " + oppScore, this.getWidth() / 2 - 67, 40);
		g.setFont(new Font("Monospaced", Font.BOLD, 15));
		g.setColor(Color.GRAY);
		g.drawString("W", 650, 20);
		g.drawString("|", 650, 35);
		g.drawString("S", 650, 50);
		g.drawString("5 POINTS WINS", 700, 35);
		g.setColor(Color.WHITE);

		if (!end) {
			ball.draw(g);
			player.draw(g);
			opp.draw(g);

			if (!playing) {
				startScreen.draw(g);

			}
		} else if (end) {
			gameOver.draw(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (!end) {
			if (!playing) {
				startScreen.step();
				repaint();

			}

			else if (playing) {
				ball.step();
				player.step();
				opp.step();
				repaint();

			}
		} else if (end) {
			gameOver.step();
			repaint();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			// player.moveUp();
			wPressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			// player.moveDown();
			sPressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			ball.incBallYSpeed();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			ball.decBallYSpeed();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			ball.incBallXSpeed();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			ball.decBallXSpeed();
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (ball.getDeltaX() == 0) {
				ball.startRound();
				playing = true;
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			wPressed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			sPressed = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean getWPressed() {
		return wPressed;
	}

	public boolean getSPressed() {
		return sPressed;
	}

	public void incPlayerScore() {
		playerScore++;
		if (playerScore == 3) {
			end = true;
			win = true;

		}

	}

	public void incOppScore() {
		oppScore++;

		if (oppScore == 3) {
			end = true;
			win = false;
		}

	}

	public Player getPlayer() {
		return player;
	}

	public Opponent getOpp() {
		return opp;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public boolean getWin() {
		return win;
	}

}
