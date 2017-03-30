package ord.boka.game;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JPanel{

		public final static String NAMEOFGAME = "Rectangle Escape";
		public final static int WIDTH = 500, HEIGHT = 500;
		private Collection<Ball> balls = new ArrayList<Ball>();
		private List<Wall> walls = new ArrayList<Wall>();
		private Collection<Wall> wallsToBeRemoved = new ArrayList<Wall>();
		public double wallSpeed;
		private int wallNumber = 0;
		private int score = 0, highScore = 0;
		private final static int INITIALSPEEDCONSTANT = 200;
		private JLabel scoreText = new JLabel();
		private boolean gameRunning;
		private static JFrame frame;
		private static Main game;
		private static Menu menu;
		
		public Main(){
			setPreferredSize(new Dimension(WIDTH,HEIGHT));
			scoreText.setFont(scoreText.getFont().deriveFont(25f));
			add(scoreText);
			KeyListener listener = new MyKeyListener();
			addKeyListener(listener);
			setFocusable(true);
		}
		
		@Override
		public void paint(Graphics g) {
			if(gameRunning){
				super.paint(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				for(Ball ball: balls){
					g2d.setColor(ball.getColor());
					g2d.fillOval(ball.getX(), ball.getY(), Ball.BALLDIAMETER, Ball.BALLDIAMETER);
				}
				for(Wall wall: walls){
					g2d.setColor(Color.GREEN);
					g2d.fillRect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
				}
				scoreText.setText(Integer.toString(score/100) + "   High: " + highScore);				
			}
		}
		
		//Flytter ballene en gang hvert tiende millisekund, kalles av mainmetoden
		private void moveBalls(){
			for(Ball ball: balls){
				ball.moveBall();
			}
		}
		
		//Flytter veggene en gang hvert tiende millisekund, kalles av mainmetoden
			private void moveWalls(){
				for(Wall wall: walls){
					wall.moveWall();
				}
				removeWalls();
			}
		private void runGame(){
			startNewGame();
			gameRunning=true;
			while(true){
				moveBalls();
				moveWalls();
				checkForCollisions();
				repaint();
				increaseScore();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public static void startGame(){
			System.out.println(game);
			frame.add(game);
			frame.remove(menu);
			frame.revalidate();
			frame.repaint();
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			game.runGame();
		}

	public static void main(String[] args){
		frame = new JFrame(NAMEOFGAME);
		menu = new Menu();
		game = new Main();
		JPanel cards = new JPanel(new CardLayout());
		cards.add(menu, "menu");
		cards.add(game, "game");
		CardLayout cardLayout = (CardLayout) cards.getLayout();
		cardLayout.show(cards, "game");
		frame.add(cards);
		//frame.add(menu);
		//frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.runGame();
	}

	private void increaseScore() {
		score++;
		if(score == (int) (INITIALSPEEDCONSTANT/2+2*Wall.WIDTH)){
			makeNewWall(true);
			makeNewWall(false);
		}
	}

	private void checkForCollisions() {
		Collection<Ball> collidingBalls = new ArrayList<Ball>();
		for(Ball ball : balls){
			for(Wall wall: walls){
				if(wall.checkForCollision(ball)==true){
					collidingBalls.add(ball);
				}
			}
		}
		for(Ball ball: collidingBalls){
			balls.remove(ball);
		}
		if(balls.size()==0){
			this.startNewGame();
		}
	}
	
	public void startNewGame(){
		wallSpeed = HEIGHT/INITIALSPEEDCONSTANT;
		wallNumber = 0;
		for(Wall wall: walls){
			wall = null;
		}
		walls.clear();
		if(score/100>highScore){
			highScore = score/100;
		}
		score = 0;
		makeNewWall(true);
		makeNewWall(false);
		Ball ball1 = new Ball(38, 40, 37, 39, Color.PINK);
		this.balls.add(ball1);
		Ball ball2 = new Ball(87, 83, 65, 68, Color.CYAN);
		this.balls.add(ball2);
//		if(host == true){
//			Host myHost = new Host();
//		}else{
//			Client client = new Client("localhost", 27182);
//		}
	}
	
	public void makeNewWall(boolean vertical){
		Random random = new Random();
		int hole = (int) (random.nextInt( (int) (WIDTH*0.4))+WIDTH*0.5);
		if(walls.size()<4){
		Wall wall1 = new Wall(vertical,wallSpeed,true,hole,this,0-Wall.WIDTH*3);
		this.walls.add(wall1);
		Wall wall2 = new Wall(vertical,wallSpeed,false,hole,this,0-Wall.WIDTH*3);
		this.walls.add(wall2);}
		else{
			int closestToCenter = HEIGHT;
			int middleCoordinate = 0;
			for(Wall wall : walls){
				if(wall.isVertical()){
					if(Math.abs(wall.getY()-HEIGHT/2)<closestToCenter){
						closestToCenter = Math.abs(wall.getY()-HEIGHT/2);
						middleCoordinate = wall.getY();
						System.out.println(middleCoordinate);
					}
				}
			}
			Wall wall1 = new Wall(vertical,wallSpeed,true,hole,this,middleCoordinate-HEIGHT/2-Wall.WIDTH*3);
			this.walls.add(wall1);
			Wall wall2 = new Wall(vertical,wallSpeed,false,hole,this,middleCoordinate-HEIGHT/2-Wall.WIDTH*3);
			this.walls.add(wall2);
		}
		wallNumber++;
		if(wallNumber%4==0){
			wallSpeed+= (double) (HEIGHT)/10000;
			System.out.println(wallSpeed);
		}
	}
	
	public void removeWall(boolean vertical, Wall wall) {
		wallsToBeRemoved.add(wall);
	}
	
	public void removeWalls(){
		for(Wall wall: wallsToBeRemoved){
			if(wall.isTop()){
				makeNewWall(wall.isVertical());
			}
			walls.remove(wall);
		}
		wallsToBeRemoved.clear();
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
					ball.upPressed();
				}else if(e.getKeyCode()==ball.getDown() && ball.getBallYSpeed()==0){
					ball.downPressed();
				}else if(e.getKeyCode()==ball.getLeft() && ball.getBallXSpeed()==0){
					ball.leftPressed();
				}else if(e.getKeyCode()==ball.getRight() && ball.getBallXSpeed()==0){
					ball.rightPressed();
				}
				
			}
//			System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode())+ ". The code is " + e.getKeyCode());
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
//			System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
		}
		
	}


}
