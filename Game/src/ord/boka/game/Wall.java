package ord.boka.game;

import java.awt.Color;

public class Wall implements DrawableObject{
	//Bestemmer om veggen skal bevege seg vertikalt eller horisontalt
	private boolean vertical;
	//Bryr seg bare om kordinaten i den retningen den beveger seg.
	private int xCorner, yCorner;
	private int speed;
	private int holeCoordinate;
	private static final int WIDTH = Main.WIDTH/20;
	private static final int HOLEWIDTH = Main.WIDTH/10;
	private Color color = Color.green;
	
	public Wall(boolean vertical,int speed, boolean top) {
		this.vertical = vertical;
		this.speed = speed;
		this.holeCoordinate = holeCoordinate;
		//Må fylles ut for å gi tilfeldig hullkordinat
				if(vertical){
					holeCoordinate = Main.WIDTH/2;
				}else{
					holeCoordinate = Main.HEIGHT/2;
				}
		if(top){
		//Hvis den beveger seg vertikalt skal bare y-koordinaten endre seg. Ellers bare x
			if(vertical){
				xCorner = 0;
				yCorner = 0-WIDTH;
			}else{
				xCorner = 0-WIDTH;
				yCorner = 0;
			}
		}else{
			if(vertical){
				xCorner = holeCoordinate+HOLEWIDTH/2;
				yCorner = 0-WIDTH;
			}else{
				xCorner = 0-WIDTH;
				yCorner = holeCoordinate+HOLEWIDTH/2;
			}
		}
		
	}
	
	public void moveWall(){
		if(vertical){
			yCorner+=speed;
		}else{
			xCorner+=speed;
		}
	}
	
	public int getX(){
		return xCorner;
	}
	public int getY(){
		return yCorner;
	}
	public int getHeight(){
		if(vertical){
			return WIDTH;
		}else{
			return holeCoordinate-HOLEWIDTH/2;
		}
	}
	public int getWidth(){
		if(vertical){
			return holeCoordinate-HOLEWIDTH/2;
		}else{
			return WIDTH;
		}
	}


	public Color getColor() {
		return color;
	}
}
