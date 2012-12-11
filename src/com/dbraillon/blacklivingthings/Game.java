package com.dbraillon.blacklivingthings;

import java.util.Random;
import java.util.Vector;

import org.lwjgl.Sys;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {

	private Vector<Things> _things;
	private ThingsController _thingsController;
	private long _lastFrame;
	private double _velocity;
	private int _delta;
	
	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
		
		for(Things t : _things) {
			
			graphics.drawRect((float) t.get_xPosition(), (float) t.get_yPosition(),
					(float) t.get_width(), (float) t.get_height());
		}
		
		graphics.drawString("Nombre de Choses : " + _things.size(), 10, 40);
		graphics.drawString("Temps écoulé : " + getTime(), 10, 60);
		graphics.drawString("Velocité : " + _velocity, 10, 80);
		graphics.drawString("Delta : " + _delta, 10, 100);
		graphics.drawString("Nombre de pixel parcouru toutes les 10ms : " + _velocity * 10 / _delta, 10, 120);
	}

	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		
		_things = new Vector<Things>();
		
		for(int i = 0; i < 10000; i++) {
		
			_things.add(new Things(800, 300, 0, 0));
		}
		
		_thingsController = new ThingsController(gameContainer);
		_lastFrame = 0;
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		
		/*
		 * la vitesse des choses doit être de 2 pixels par milliseconde
		 * et non pas 2 pixels par frame, un calcul est nécessaire
		 */
		
		_delta = getDelta();
		_velocity = (double) _delta * 2 / 10;
		
		Vector<Things> deathThings = new Vector<Things>();
		
		for(Things t : _things) {
			
			if(t.makeOld()) {
				
				deathThings.add(t);
			}
			
			if(!_thingsController.goForwardControl(t, _velocity)) {
				
				Random r = new Random();
				float direction = r.nextInt(360);
				
				t.changeDirection(direction);
			}
		}
		
		for(Things dt : deathThings) {
			
			_things.remove(dt);
		}
	}
	
	private long getTime() {
		
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/*
	 * donne le delta en ms entre la dernière frame et la courante
	 */
	private int getDelta() {
		
		long time = getTime();
		int delta = (int) (time - _lastFrame);
		_lastFrame = time;
		
		return delta;
	}
	
	public static void main(String[] args) throws SlickException {
		
		AppGameContainer app = new AppGameContainer(new Game("Black Living Things"));
		app.setDisplayMode(800, 600, false);
		app.setTargetFrameRate(60);
		app.start();
	}
}
