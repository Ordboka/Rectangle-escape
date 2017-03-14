package ord.boka.game;

import java.awt.Color;

/**
 * @author Benjamin Buan
 *
 */
public class Ball implements DrawableObject{
	
	private int up, down, left, right;
	private Color color;
	public final static int BALLDIAMETER = 20;
	private int ballX = Main.WIDTH/2-BALLDIAMETER/2,ballY= Main.HEIGHT/2-BALLDIAMETER/2;
	private int ballXSpeed = 0, ballYSpeed= 0;
	private double speedIncrease = 2;
	
	/**
	 * Constructor for Ball where the keys used for moving the ball is given together with the color used.
	 * @param up The key that is used for moving up
	 * @param down The key that is used for moving down
	 * @param left The key that is used for moving left
	 * @param right The key that is used for moving right
	 * @param color The color of the ball
	 */
	public Ball(int up, int down, int left, int right, Color color){
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.color = color;
	}
	/**
	 * Increases the x and y coordinates of the ball with ballXSpeed and ballYSpeed
	 */
	public void moveBall(){
		ballX+=ballXSpeed;
		ballY+=ballYSpeed;
	}
	public int getUp() {
		return up;
	}
	public int getDown() {
		return down;
	}
	public int getLeft() {
		return left;
	}
	public int getRight() {
		return right;
	}
	public int getX(){
		return ballX;
	}
	public int getY(){
		return ballY;
	}
	public Color getColor(){
		return color;
	}
	
	public int getBallXSpeed() {
		return ballXSpeed;
	}
	public int getBallYSpeed() {
		return ballYSpeed;
	}
	public void upPressed(){
		ballYSpeed-=speedIncrease;
	}
	public void downPressed(){
		ballYSpeed+=speedIncrease;
	}
	public void yReleased(){
		ballYSpeed = 0;
	}
	public void leftPressed(){
		ballXSpeed-=speedIncrease;
	}
	public void rightPressed(){
		ballXSpeed+=speedIncrease;
	}
	public void xReleased(){
		ballXSpeed = 0;
	}
	@Override
	public int getHeight() {
		return BALLDIAMETER;
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return BALLDIAMETER;
	}
}
