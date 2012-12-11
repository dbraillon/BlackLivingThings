package com.dbraillon.blacklivingthings;

import org.newdawn.slick.GameContainer;

/*
 * 
 */
public class ThingsController {

	private GameContainer _gameContainer;
	
	public ThingsController(GameContainer gameContainer) {
		
		_gameContainer = gameContainer;
	}
	
	
	public boolean goForwardControl(Things things, double velocity) {
		
		boolean isBlocked = false;
		
		double direction = things.get_direction();
		
		// calcul de la position des angles
		double xTopLeftEdgePosition = things.get_xPosition();
		double yTopLeftEdgePosition = things.get_yPosition();
		
		double xTopRightEdgePosition = things.get_xPosition() + things.get_width() + 1;
		double yTopRightEdgePosition = things.get_yPosition();
		
		double xBottomLeftEdgePosition = things.get_xPosition();
		double yBottomLeftEdgePosition = things.get_yPosition() + things.get_height() + 1;
		
		double xBottomRightEdgePosition = things.get_xPosition() + things.get_width() + 1;
		double yBottomRightEdgePosition = things.get_yPosition() + things.get_height() + 1;
		
		// récupération des bornes
		double xMinPosition = 0;
		double xMaxPosition = _gameContainer.getWidth();
		double yMinPosition = 0;
		double yMaxPosition = _gameContainer.getHeight();
		
		
		// test de tous les cas possibles
		
		// test de mouvement vers le haut gauche
		if(direction > 90 && direction < 180) {
			
			double xTopLeftEdgePositionTest = xTopLeftEdgePosition + velocity * things.xMove;
			double yTopLeftEdgePositionTest = yTopLeftEdgePosition + velocity * things.yMove;
			
			double xBottomLeftEdgePositionTest = xBottomLeftEdgePosition + velocity * things.xMove;
			//double yBottomLeftEdgePositionTest = yBottomLeftEdgePosition + velocity * things.yMove;
			
			//double xTopRightEdgePositionTest = xTopRightEdgePosition + velocity * things.xMove;
			double yTopRightEdgePositionTest = yTopRightEdgePosition + velocity * things.yMove;
			
			double velocityTest = velocity;
			
			// vérification des x
			if(xTopLeftEdgePositionTest < xMinPosition || xBottomLeftEdgePositionTest < xMinPosition) {
				
				// supposition que les x de l'angle haut gauche et bas gauche sont égaux
				velocityTest = xTopLeftEdgePosition;
				isBlocked = true;
			}
			
			// vérification des y
			if(yTopLeftEdgePositionTest < yMinPosition || yTopRightEdgePositionTest < yMinPosition) {
				
				// supposition que les y de l'angle haut gauche et haut droite sont égaux
				velocityTest = yTopLeftEdgePosition;
				isBlocked = true;
			}
			
			things.goForward(velocityTest);
		}
		
		// test de mouvement vers le haut
		if(direction == 90) {
		
			//double xTopLeftEdgePositionTest = xTopLeftEdgePosition + velocity * things.xMove;
			double yTopLeftEdgePositionTest = yTopLeftEdgePosition + velocity * things.yMove;
			
			//double xTopRightEdgePositionTest = xTopRightEdgePosition + velocity * things.xMove;
			double yTopRightEdgePositionTest = yTopRightEdgePosition + velocity * things.yMove;
			
			double velocityTest = velocity;
			
			// vérification des y
			if(yTopLeftEdgePositionTest < yMinPosition || yTopRightEdgePositionTest < yMinPosition) {
				
				// supposition que les y de l'angle haut gauche et haut droite sont égaux
				velocityTest = yTopLeftEdgePosition;
				isBlocked = true;
			}
			
			things.goForward(velocityTest);
		}
		
		// test de mouvement vers le haut droite
		if(direction > 0 && direction < 90) {
			
			//double xTopLeftEdgePositionTest = xTopLeftEdgePosition + velocity * things.xMove;
			double yTopLeftEdgePositionTest = yTopLeftEdgePosition + velocity * things.yMove;
			
			double xTopRightEdgePositionTest = xTopRightEdgePosition + velocity * things.xMove;
			double yTopRightEdgePositionTest = yTopRightEdgePosition + velocity * things.yMove;
			
			double xBottomRightEdgePositionTest = xBottomRightEdgePosition + velocity * things.xMove;
			//double yBottomRightEdgePositionTest = yBottomRightEdgePosition + velocity * things.yMove;
			
			double velocityTest = velocity;
			
			// vérification des x
			if(xTopRightEdgePositionTest > xMaxPosition || xBottomRightEdgePositionTest > xMaxPosition) {
				
				// supposition que les x de l'angle haut droite et bas droite sont égaux
				velocityTest = xMaxPosition - xTopRightEdgePosition;
				isBlocked = true;
			}
			
			// vérification des y
			if(yTopLeftEdgePositionTest < yMinPosition || yTopRightEdgePositionTest < yMinPosition) {
				
				// supposition que les y de l'angle haut gauche et haut droite sont égaux
				velocityTest = yTopRightEdgePosition;
				isBlocked = true;
			}
			
			things.goForward(velocityTest);
		}
		
		// test de mouvement vers la gauche
		if(direction == 180) {
			
			double xTopLeftEdgePositionTest = xTopLeftEdgePosition + velocity * things.xMove;
			//double yTopLeftEdgePositionTest = yTopLeftEdgePosition + velocity * things.yMove;
			
			double xBottomLeftEdgePositionTest = xBottomLeftEdgePosition + velocity * things.xMove;
			//double yBottomLeftEdgePositionTest = yBottomLeftEdgePosition + velocity * things.yMove;
			
			double velocityTest = velocity;
			
			// vérification des x
			if(xTopLeftEdgePositionTest < xMinPosition || xBottomLeftEdgePositionTest < xMinPosition) {
				
				// supposition que les x de l'angle haut gauche et bas gauche sont égaux
				velocityTest = xTopLeftEdgePosition;
				isBlocked = true;
			}
			
			things.goForward(velocityTest);
		}
		
		// test de mouvement vers la droite
		if(direction == 0) {
			
			double xTopRightEdgePositionTest = xTopRightEdgePosition + velocity * things.xMove;
			//double yTopRightEdgePositionTest = yTopRightEdgePosition + velocity * things.yMove;
			
			double xBottomRightEdgePositionTest = xBottomRightEdgePosition + velocity * things.xMove;
			//double yBottomRightEdgePositionTest = yBottomRightEdgePosition + velocity * things.yMove;
			
			double velocityTest = velocity;
			
			// vérification des x
			if(xTopRightEdgePositionTest > xMaxPosition || xBottomRightEdgePositionTest > xMaxPosition) {
				
				// supposition que les x de l'angle haut droite et bas droite sont égaux
				velocityTest = xMaxPosition - xTopRightEdgePosition;
				isBlocked = true;
			}
			
			things.goForward(velocityTest);
		}
		
		// test de mouvement vers le bas gauche
		if(direction > 180 && direction < 270) {
			
			double xTopLeftEdgePositionTest = xTopLeftEdgePosition + velocity * things.xMove;
			//double yTopLeftEdgePositionTest = yTopLeftEdgePosition + velocity * things.yMove;
			
			double xBottomLeftEdgePositionTest = xBottomLeftEdgePosition + velocity * things.xMove;
			double yBottomLeftEdgePositionTest = yBottomLeftEdgePosition + velocity * things.yMove;
			
			//double xBottomRightEdgePositionTest = xBottomRightEdgePosition + velocity * things.xMove;
			double yBottomRightEdgePositionTest = yBottomRightEdgePosition + velocity * things.yMove;
			
			double velocityTest = velocity;
			
			// vérification des x
			if(xTopLeftEdgePositionTest < xMinPosition || xBottomLeftEdgePositionTest < xMinPosition) {
				
				// supposition que les x de l'angle haut gauche et bas gauche sont égaux
				velocityTest = xTopLeftEdgePosition;
				isBlocked = true;
			}
			
			// vérification des y
			if(yBottomLeftEdgePositionTest > yMaxPosition || yBottomRightEdgePositionTest > yMaxPosition) {
				
				// supposition que les y de l'angle bas gauche et bas droite sont égaux
				velocityTest = yMaxPosition - yBottomLeftEdgePosition;
				isBlocked = true;
			}
			
			things.goForward(velocityTest);
		}
		
		// test de mouvement vers le bas
		if(direction == 270) {
			
			//double xBottomLeftEdgePositionTest = xBottomLeftEdgePosition + velocity * things.xMove;
			double yBottomLeftEdgePositionTest = yBottomLeftEdgePosition + velocity * things.yMove;
			
			//double xBottomRightEdgePositionTest = xBottomRightEdgePosition + velocity * things.xMove;
			double yBottomRightEdgePositionTest = yBottomRightEdgePosition + velocity * things.yMove;
			
			double velocityTest = velocity;
			
			// vérification des y
			if(yBottomLeftEdgePositionTest > yMaxPosition || yBottomRightEdgePositionTest > yMaxPosition) {
				
				// supposition que les y de l'angle bas gauche et bas droite sont égaux
				velocityTest = yMaxPosition - yBottomLeftEdgePosition;
				isBlocked = true;
			}
			
			things.goForward(velocityTest);
		}
		
		// test de mouvement vers le bas droite
		if(direction > 270 && direction < 360) {
			
			double xTopRightEdgePositionTest = xTopRightEdgePosition + velocity * things.xMove;
			//double yTopRightEdgePositionTest = yTopRightEdgePosition + velocity * things.yMove;
			
			//double xBottomLeftEdgePositionTest = xBottomLeftEdgePosition + velocity * things.xMove;
			double yBottomLeftEdgePositionTest = yBottomLeftEdgePosition + velocity * things.yMove;
			
			double xBottomRightEdgePositionTest = xBottomRightEdgePosition + velocity * things.xMove;
			double yBottomRightEdgePositionTest = yBottomRightEdgePosition + velocity * things.yMove;
			
			double velocityTest = velocity;
			
			// vérification des x
			if(xTopRightEdgePositionTest > xMaxPosition || xBottomRightEdgePositionTest > xMaxPosition) {
				
				// supposition que les x de l'angle haut droite et bas droite sont égaux
				velocityTest = xMaxPosition - xTopRightEdgePosition;
				isBlocked = true;
			}
			
			// vérification des y
			if(yBottomLeftEdgePositionTest > yMaxPosition || yBottomRightEdgePositionTest > yMaxPosition) {
				
				// supposition que les y de l'angle bas gauche et bas droite sont égaux
				velocityTest = yMaxPosition - yBottomLeftEdgePosition;
				isBlocked = true;
			}
			
			things.goForward(velocityTest);
		}
		
		return !isBlocked;
	}
}
