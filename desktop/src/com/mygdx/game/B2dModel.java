package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import controller.KeyboardController;
import listener.B2dContactListener;

public class B2dModel {
	
	public World world;
	private KeyboardController controller;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Body bodyd;
	private Body bodys;
	private Body bodyk;
	public Body player;

	public B2dModel(KeyboardController controller, OrthographicCamera camera) {
		this.world = new World(new Vector2(0,0f), true);
		this.controller = controller;
		this.camera = camera;
		world.setContactListener(new B2dContactListener(this));
		//createFloor();
		//createObject();
		//createMovingObject();
		BodyFactory bodyFactory = BodyFactory.getInstance(world);
		player =bodyFactory.makeBoxPolyBody(1, 1, 2, 2, BodyFactory.RUBBER, BodyType.DynamicBody, true);
		bodyFactory.makeBoxPolyBody(0, -10, 50,10, BodyFactory.RUBBER, BodyType.StaticBody, false);
		//bodyFactory.makeCirclePolyBody(1, 1, 2, BodyFactory.RUBBER, BodyType.DynamicBody, false);
		//bodyFactory.makeCirclePolyBody(4, 1, 2, BodyFactory.STEEL, BodyType.DynamicBody, false);
		bodyFactory.makeCirclePolyBody(-4, 1, 2, BodyFactory.STONE, BodyType.DynamicBody, false);
	}
	
//	private void createMovingObject() {
//		BodyDef bodyDef = new BodyDef();
//		bodyDef.type = BodyDef.BodyType.KinematicBody;
//		bodyDef.position.set(0,-12);
//		
//		bodyk = world.createBody(bodyDef);
//		
//		PolygonShape shape =  new PolygonShape();
//		shape.setAsBox(1, 1);
//		
//		FixtureDef fixtureDef = new FixtureDef();
//		fixtureDef.shape = shape;
//		fixtureDef.density=1f;
//		
//		bodyk.createFixture(shape,0.0f);
//		shape.dispose();
//		bodyk.setLinearVelocity(0,0.75f);
//	}

//	private void createFloor() {
//		BodyDef bodyDef = new BodyDef();
//		bodyDef.type = BodyDef.BodyType.StaticBody;
//		bodyDef.position.set(0,-10);
//		bodyd = world.createBody(bodyDef);
//		
//		PolygonShape shape = new PolygonShape();
//		shape.setAsBox(50, 1);
//		
//		bodyd.createFixture(shape,0.0f);
//		
//		shape.dispose();
//	}

	public void logicStep(float delta) {
		
		if(controller.left){
			camera.translate(-1, 0);
			camera.update();
			System.out.println(camera.getPickRay(delta, delta));
			player.applyForceToCenter(-10, 0,true);
		}else if(controller.right){
			camera.translate(1, 0);
			camera.update();
			player.applyForceToCenter(10, 0,true);
		}else if(controller.up){
			camera.translate(0, 1);
			camera.update();
			player.applyForceToCenter(0, 10,true);
		}else if(controller.down){
			camera.translate(0, -1);
			camera.update();
			player.applyForceToCenter(0, -10,true);
		}
			
		world.step(delta , 3, 3);
	}
	
//	private void createObject() {
//		BodyDef bodyDef = new BodyDef();
//		bodyDef.type = BodyDef.BodyType.DynamicBody;
//		bodyDef.position.set(3,0);
//		
//		bodys = world.createBody(bodyDef);
//		
//		PolygonShape shape = new PolygonShape();
//		shape.setAsBox(1, 1);
//		
//		FixtureDef fixtureDef = new FixtureDef();
//		fixtureDef.shape = shape;
//		fixtureDef.density=1f;
//		
//		bodys.createFixture(shape,0.0f);
//		
//		shape.dispose();
//	}

}
