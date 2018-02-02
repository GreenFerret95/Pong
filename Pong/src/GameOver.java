import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOver {
	private GamePanel panel;
	private Color color = Color.BLACK;
	private int x = 350;
	private int y = 200;
	private int count = 0;
	private int min = 50;
	private int max = 70;

	public GameOver(GamePanel panel) {
		this.panel = panel;
	}

	public void draw(Graphics g) {

		g.setFont(new Font("Monospaced", Font.BOLD, 40));
		if (panel.getWin() == true) {

			g.setColor(color);
			g.drawString("YOU WIN", x, y);
		} else if (panel.getWin() == false) {

			g.setColor(color);
			g.drawString("YOU LOSE", x, y);

		}
	}

	public void step() {
		count++;
		if (count < min) {
			if (panel.getWin() == true) {
				color = Color.GREEN;
			} else if (panel.getWin() == false) {
				color = Color.RED;
			}

		} else if (count > min && count <= max) {
			color = Color.BLACK;
			if (count == max) {
				count = 0;
			}
		}

	}

}
