import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		GamePanel panel = new GamePanel();

		frame.add(panel, BorderLayout.CENTER);

		frame.setSize(900, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
