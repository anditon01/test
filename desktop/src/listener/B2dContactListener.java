package listener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.B2dModel;

import views.MainScreen;

public class B2dContactListener implements ContactListener {
	private B2dModel parent;
	private MainScreen game;
	public B2dContactListener(B2dModel parent, MainScreen game) {
		this.parent = parent;
		this.game = game;
	}
	public B2dContactListener(B2dModel parent) {
		this.parent = parent;
	}
	@Override
	public void beginContact(Contact contact) {
		System.out.println("Contact");
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());
		if (contact.getFixtureA().isSensor() || contact.getFixtureB().isSensor()) {
			System.out.println("sensor");
            //game.showMessage("You have entered the sensor area!");
        }
	}

	@Override
	public void endContact(Contact contact) {
		System.out.println("end contact");

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
