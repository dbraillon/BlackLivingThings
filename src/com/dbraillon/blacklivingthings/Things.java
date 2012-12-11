package com.dbraillon.blacklivingthings;

import java.util.Random;

/*
 * Caractéristique :
 * 	apparence
 * 	ia
 * 	deplacement
 * 
 * 
 * Reproduction :
 * 	les choses sont asexué
 * 	il faut trois choses pour en créer une
 * 	la chose qui nait prend aléatoirement une caractéristique de chaque parent
 * 
 * 
 * Mort :
 * 	naturelle, pourçentage de chance augmenté au fur et à mesure de sa vie
 */
public class Things {

	// espérance de vie d'une chose
	public static double lifeExpectancy = 50.0;
	
	// position x de l'angle haut gauche
	private double _xPosition;
	
	// position y de l'angle haut gauche
	private double _yPosition;
	
	// hauteur de la chose
	private double _height;
	
	// largeur de la chose
	private double _width;
	
	// direction de la chose
	private double _directionDegrees;
	
	// age de la chose
	private double _age;
	
	public double xMove;
	public double yMove;
	
	public Things(double height, double width) {
		
		_xPosition = 0;
		_yPosition = 0;
		_height = height;
		_width = width;
		
		_age = 0.0;
		changeDirection(0);
		
	}

	public Things(double xPosition, double yPosition, double height, double width) {
		
		_xPosition = xPosition;
		_yPosition = yPosition;
		_height = height;
		_width = width;
		
		_age = 0.0;
		changeDirection(0);
	}
	
	
	public void changeDirection(double directionDegrees) {
		
		while(directionDegrees > 360) {
			
			directionDegrees -= 360;
		}
		
		_directionDegrees = directionDegrees;
		xMove = Math.cos(Math.toRadians(_directionDegrees));
		yMove = 0 - Math.sin(Math.toRadians(_directionDegrees));
		
		System.out.println("Changing direction to : " + _directionDegrees);
	}
	
	public void goForward(double velocity) {
		
		_xPosition += velocity * xMove;
		_yPosition += velocity * yMove;
	}
	
	public boolean makeOld() {
		
		_age = _age + 0.1;
		
		if(_age > lifeExpectancy) {
			
			Random r = new Random();
			
			
			if(r.nextFloat() == 0) {
				
				return true;
			}
		}
		
		return false;
	}
	
	
	public double get_xPosition() {
		return _xPosition;
	}

	public void set_xPosition(double _xPosition) {
		this._xPosition = _xPosition;
	}

	public double get_yPosition() {
		return _yPosition;
	}

	public void set_yPosition(double _yPosition) {
		this._yPosition = _yPosition;
	}

	public double get_height() {
		return _height;
	}

	public void set_height(double _height) {
		this._height = _height;
	}

	public double get_width() {
		return _width;
	}

	public void set_width(double _width) {
		this._width = _width;
	}

	public double get_age() {
		return _age;
	}

	public void set_age(double _age) {
		this._age = _age;
	}

	public double get_direction() {
		return _directionDegrees;
	}

	public void set_direction(double _direction) {
		this._directionDegrees = _direction;
	}
}
