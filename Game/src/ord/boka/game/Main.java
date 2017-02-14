package ord.boka.game;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{

		final static String NAMEOFGAME = "Rectangle Escape";
		final static int WIDTH = 500, HEIGHT = 500;
		final static int BALLDIAMETER = 20;
		private int ballX = WIDTH/2-BALLDIAMETER/2,ballY= HEIGHT/2-BALLDIAMETER/2;
		private int ballXSpeed = 0, ballYSpeed= 0;
		
		public Main(){
			setPreferredSize(new Dimension(WIDTH,HEIGHT));
			KeyListener listener = new MyKeyListener();
			addKeyListener(listener);
			setFocusable(true);
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
			ballX+=ballXSpeed;
			ballY+=ballYSpeed;
		}

	public static void main(String[] args) throws InterruptedException{
		JFrame frame = new JFrame(NAMEOFGAME);
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
	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (KeyEvent.getKeyText(e.getKeyCode())){
			case "W":
			case "Up":
				if(ballYSpeed==0){
				ballYSpeed-=1;}
				break;
			case "S":
			case "Down":
				if(ballYSpeed==0){
					ballYSpeed+=1;}
				break;
			case "A":
			case "Left":
				if(ballXSpeed==0){
					ballXSpeed-=1;}
				break;
			case "D":
			case "Right":
				if(ballXSpeed==0){
					ballXSpeed+=1;}
				break;
			}
				
			System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (KeyEvent.getKeyText(e.getKeyCode())){
			case "W":
			case "Up":
				ballYSpeed=0;
				break;
			case "S":
			case "Down":
				ballYSpeed=0;
				break;
			case "A":
			case "Left":
				ballXSpeed=0;
				break;
			case "D":
			case "Right":
				ballXSpeed=0;
				break;
			}
			System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
		}
	}
}
