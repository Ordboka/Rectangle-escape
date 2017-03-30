package ord.boka.game;

import java.awt.Color;

public class Wall implements DrawableObject{
	//Bestemmer om veggen skal bevege seg vertikalt eller horisontalt
	private boolean vertical, top;
	//Bryr seg bare om kordinaten i den retningen den beveger seg.
	private double xCorner, yCorner;
	private double speed;
	private int holeCoordinate;
	public static final int WIDTH = Main.WIDTH/20;
	private static final int HOLEWIDTH = Main.WIDTH/8;
	private Color color = Color.green;
	Main game;
	
	public Wall(boolean vertical,double speed, boolean top, int holeCoordinate, Main game, int cornerCoordinate) {
		this.vertical = vertical;
		this.speed = speed;
		this.top = top;
		this.holeCoordinate = holeCoordinate;
		this.game = game;
		if(top){
		//Hvis den beveger seg vertikalt skal bare y-koordinaten endre seg. Ellers bare x
			if(vertical){
				xCorner = 0;
				yCorner = cornerCoordinate;
			}else{
				xCorner =  cornerCoordinate;
				yCorner = 0;
			}
		}else{
			if(vertical){
				xCorner = holeCoordinate+HOLEWIDTH/2;
				yCorner =  cornerCoordinate;
			}else{
				xCorner =  cornerCoordinate;
				yCorner = holeCoordinate+HOLEWIDTH/2;
			}
		}
		
	}
	
	public void moveWall(){
		if(vertical){
			yCorner+=speed;
			if(yCorner>Main.HEIGHT+Main.HEIGHT/20){
				game.removeWall(vertical, this);
			}
		}else{
			xCorner+=speed;
			if(xCorner>Main.WIDTH+Main.WIDTH/20){
				game.removeWall(vertical, this);
			}
		}
	}
	
	public int getX(){
		return (int)xCorner;
	}
	public int getY(){
		return (int)yCorner;
	}
	public int getHeight(){
		if(top){
			if(vertical){
				return WIDTH;
			}else{
				return holeCoordinate-HOLEWIDTH/2;
			}
		}else{
			if(vertical){
				return WIDTH;
			}else{
				return Main.HEIGHT-(holeCoordinate+HOLEWIDTH/2);
			}
		}
		
	}
	
	public boolean isTop(){
		return top;
	}
	
	public boolean isVertical(){
		return vertical;
	}
	
	public int getWidth(){
		if(top){
			if(vertical){
				return holeCoordinate-HOLEWIDTH/2;
			}else{
				return WIDTH;
			}
		}else{
			if(vertical){
				return Main.WIDTH-(holeCoordinate+HOLEWIDTH/2);
			}else{
				return WIDTH;
			}
		}
		
	}


	public Color getColor() {
		return color;
	}
	public boolean checkForCollision(Ball ball){
		//Sjekker om sentrum av sirkelen er innenfor rektangelet
		//Sjekker først om x-kordinaten er innenfor 
		if(ball.getCenterX()>this.getX()&&ball.getCenterX()<(this.getX()+this.getWidth())){
			//Sjkker så y-kordinaten
			if(ball.getCenterY()>this.getY()&&ball.getCenterY()<(this.getY()+this.getHeight())){
				return true;
			}
		}
		//Sjekker om en av sidene i rektangelet berør sirkelen. Bruker det faktum at sidene kan skrives som enter y = c eller x = c
		//Sirkelen kan skrives som (x-x0)^2+(y-y0)^2=r^2. Hvis man da fyller inn verdien av x eller y fra siden kan man se om den har en løsning.
		//Hvis denne løsningen ligger innenfor rett høyde/bredde vet man at det er kollisjon
		//Sjekker først for øverste side
		//Sjekker først om løsning vil være et reelt tall
		double toBeSqrt = Math.pow(ball.getWidth()/2, 2)-Math.pow((getY()-ball.getCenterY()), 2);
		if(toBeSqrt>=0){
			//Finner da xKordinaten
			double collisionX = Math.sqrt(toBeSqrt) + ball.getCenterX();
			double collisionXNegative = -Math.sqrt(toBeSqrt) + ball.getCenterX();
			//Sjekker om denne kordinaten er innefor rektangelet, må sjekke begge løsninger
			if(collisionX>getX()&&collisionX<getX()+getWidth()){
				return true;
			}else if(collisionXNegative>getX()&&collisionXNegative<getX()+getWidth()){
				return true;
			}
		}
		//Sjekker nederste side
		toBeSqrt = Math.pow(ball.getWidth()/2, 2)-Math.pow((getY()+getHeight()-ball.getCenterY()),2);
		if(toBeSqrt>=0){
			//Finner da xKordinaten
			double collisionX = Math.sqrt(toBeSqrt) + ball.getCenterX();
			double collisionXNegative = -Math.sqrt(toBeSqrt) + ball.getCenterX();
			//Sjekker om denne kordinaten er innefor rektangelet, må sjekke begge løsninger
			if(collisionX>getX()&&collisionX<getX()+getWidth()){
				return true;
			}else if(collisionXNegative>getX()&&collisionXNegative<getX()+getWidth()){
				return true;
			}
		}
		//Sjekker venstre side
		toBeSqrt = Math.pow(ball.getWidth()/2, 2)-Math.pow((getX()-ball.getCenterX()),2);
		if(toBeSqrt>=0){
			//Finner da yKordinaten
			double collisionY = Math.sqrt(toBeSqrt) + ball.getCenterY();
			double collisionYNegative = -Math.sqrt(toBeSqrt) + ball.getCenterY();
			//Sjekker om denne kordinaten er innefor rektangelet, må sjekke begge løsninger
			if(collisionY>getY()&&collisionY<getY()+getHeight()){
				return true;
			}else if(collisionYNegative>getY()&&collisionYNegative<getY()+getHeight()){
				return true;
			}
		}
		//Sjekker høyre side
		toBeSqrt = Math.pow(ball.getWidth()/2, 2)-Math.pow((getX()+getWidth()-ball.getCenterX()),2);
		if(toBeSqrt>=0){
			//Finner da yKordinaten
			double collisionY = Math.sqrt(toBeSqrt) + ball.getCenterY();
			double collisionYNegative = -Math.sqrt(toBeSqrt) + ball.getCenterY();
			//Sjekker om denne kordinaten er innefor rektangelet, må sjekke begge løsninger
			if(collisionY>getY()&&collisionY<getY()+getHeight()){
				return true;
			}else if(collisionYNegative>getY()&&collisionYNegative<getY()+getHeight()){
				return true;
			}
		}
		return false;
	}
}
