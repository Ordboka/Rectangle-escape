package ord.boka.game;

import java.awt.Color;

public class Wall implements DrawableObject{
	//Bestemmer om veggen skal bevege seg vertikalt eller horisontalt
	private boolean vertical, top;
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
		this.top = top;
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
			if(yCorner>Main.HEIGHT+Main.HEIGHT/20){
				yCorner = 0-WIDTH;
			}
		}else{
			xCorner+=speed;
			if(xCorner>Main.WIDTH+Main.WIDTH/20){
				xCorner = 0-WIDTH;
			}
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
