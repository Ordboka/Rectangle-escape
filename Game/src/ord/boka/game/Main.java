package ord.boka.game;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{

		final static String NAMEOFGAME = "Rectangle Escape";
		final static int WIDTH = 500, HEIGHT = 500;
		final static int BALLDIAMETER = 20;
		private int ballX = WIDTH/2-BALLDIAMETER/2,ballY= HEIGHT/2-BALLDIAMETER/2;
		
		public Main(){
			setPreferredSize(new Dimension(WIDTH,HEIGHT));
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.fillOval(ballX, ballY, BALLDIAMETER, BALLDIAMETER);
		}
		
		public void moveBall(){
			ballX++;
			ballY++;
		}

	public static void main(String[] args) throws InterruptedException{
		JFrame frame = new JFrame("NAMEOFGAME");
		Main game = new Main();
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while(true){
			game.moveBall();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
