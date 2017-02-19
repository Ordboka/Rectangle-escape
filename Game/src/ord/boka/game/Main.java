package ord.boka.game;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{

		public final static String NAMEOFGAME = "Rectangle Escape";
		public final static int WIDTH = 500, HEIGHT = 500;
		private ArrayList<Ball> balls = new ArrayList<Ball>();
		
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
			for(Ball ball: balls){
				g2d.setColor(ball.getColor());
				g2d.fillOval(ball.getX(), ball.getY(), Ball.BALLDIAMETER, Ball.BALLDIAMETER);
			}
			
		}
		
		public void moveBall(){
			for(Ball ball: balls){
				ball.moveBall();
			}
			
		}

	public static void main(String[] args) throws InterruptedException{
		JFrame frame = new JFrame(NAMEOFGAME);
		Main game = new Main();
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Ball ball1 = new Ball(38, 40, 37, 39, Color.PINK);
		game.balls.add(ball1);
		Ball ball2 = new Ball(87, 83, 65, 68, Color.CYAN);
		game.balls.add(ball2);
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
			for(Ball ball: balls){
				//Sjekker hvilken knapp som ble trykket, og om den allerede beveger seg i denne retningen.
				//hvis dette er tilfelle vil ikke bevegelsen endre seg
				if(e.getKeyCode()==ball.getUp() && ball.getBallYSpeed()==0){
					System.out.println(ball.getUp());
					ball.upPressed();
				}else if(e.getKeyCode()==ball.getDown() && ball.getBallYSpeed()==0){
					ball.downPressed();
				}else if(e.getKeyCode()==ball.getLeft() && ball.getBallXSpeed()==0){
					ball.leftPressed();
				}else if(e.getKeyCode()==ball.getRight() && ball.getBallXSpeed()==0){
					ball.rightPressed();
				}
				
			}
			System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode())+ ". The code is " + e.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent e) {
			for(Ball ball: balls){
				//Stopper bevegelsen når knappen er sluppet
				if(e.getKeyCode()==ball.getUp() || e.getKeyCode()==ball.getDown()){
					ball.yReleased();
				}else if(e.getKeyCode()==ball.getLeft() || e.getKeyCode()==ball.getRight()){
					ball.xReleased();
				}
			}
			System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
		}
	}
}
