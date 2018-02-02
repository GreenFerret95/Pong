import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class StartScreen {

	private Color color = Color.BLACK;
	private int count = 0;

	private int min = 50;
	private int max = 70;

	public void draw(Graphics g) {
		g.setColor(color);
		g.setFont(new Font("Monospaced", Font.BOLD, 15));
		g.drawString("PRESS SPACE TO START", 25, 35);
	}

	public void step() {
		count++;
		if (count < min) {
			color = Color.GRAY;
		} else if (count > min && count <= max) {
			color = Color.BLACK;
			if (count == max) {
				count = 0;
			}
		}
	}

}
