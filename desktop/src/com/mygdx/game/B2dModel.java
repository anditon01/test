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
	//private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Body muro;
	public Body sensor;
	private Body entrada;
	public Body player;
	private float movSpeed = 5f;

	public B2dModel(KeyboardController controller, OrthographicCamera camera) {
		this.world = new World(new Vector2(0, 0f), true);
		this.controller = controller;
		this.camera = camera;
		world.setContactListener(new B2dContactListener(this));
		// createFloor();
		// createObject();
		// createMovingObject();
		BodyFactory bodyFactory = BodyFactory.getInstance(world);
		player = bodyFactory.makeBoxPolyBody(1, 1, 2, 2, BodyFactory.RUBBER, BodyType.DynamicBody, true);
		bodyFactory.makeBoxPolyBody(0, -10, 50, 10, BodyFactory.RUBBER, BodyType.StaticBody, false);
		muro = bodyFactory.makeBoxPolyBody(-10, 0, 10, 15, BodyFactory.STONE, BodyType.StaticBody, false);
		entrada = bodyFactory.makeBoxPolyBody(10, 10, 4, 2, BodyFactory.RUBBER, BodyType.KinematicBody, false);
		bodyFactory.makeConeSensor(entrada, 5);
		// bodyFactory.makeCirclePolyBody(1, 1, 2, BodyFactory.RUBBER,
		// BodyType.DynamicBody, false);
		// bodyFactory.makeCirclePolyBody(4, 1, 2, BodyFactory.STEEL,
		// BodyType.DynamicBody, false);
		bodyFactory.makeCirclePolyBody(-4, 1, 2, BodyFactory.STONE, BodyType.DynamicBody, false);
	}
	public World getWorld() {
        return world;
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
		float movx = 0;
		float movy = 0;
		if (controller.left) {
			movx = -1;
			// player.applyForceToCenter(-10, 0, true);
		} else if (controller.right) {
			movx = 1;
			// player.a<<pplyForceToCenter(10, 0, true);
		}
		
		if (controller.up) {
			movy = 1;
			// player.applyForceToCenter(0, 10, true);
		} else if (controller.down) {
			movy = -1;
			// player.applyForceToCenter(0, -10, true);
		}
		
		Vector2 movement = new Vector2(movx, movy);
        if (movement.len2() > 0) {
            movement.nor().scl(movSpeed);
        }
		player.setLinearVelocity(movement);
		world.step(delta, 3, 3);
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
