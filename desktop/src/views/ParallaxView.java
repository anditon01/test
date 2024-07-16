package views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ParallaxView {
	private Texture[] layers;
    private float[] parallaxRatios;
    private Vector2 speed;
    private Vector2 position;
    private int screenWidth;
    private int screenHeight;
    
    public ParallaxView(Texture[] layers, float[] parallaxRatios, Vector2 speed, int width, int height) {
        this.layers = layers;
        this.parallaxRatios = parallaxRatios;
        this.speed = speed;
        this.position = new Vector2();
        this.screenWidth = width;
        this.screenHeight = height;
    }

    public void update(float delta) {
        position.add(speed.cpy().scl(delta));

        for (int i = 0; i < layers.length; i++) {
            float layerSpeed = speed.x * parallaxRatios[i];
            position.x %= layers[i].getWidth();
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < layers.length; i++) {
            float layerSpeed = speed.x * parallaxRatios[i];
            float x = -position.x * parallaxRatios[i];
            while (x < screenWidth) {
                float scale = (float) screenHeight / layers[i].getHeight();
                batch.draw(layers[i], x, 0, layers[i].getWidth() * scale, screenHeight);
                x += layers[i].getWidth() * scale;
            }
        }
    }

    public void dispose() {
        for (Texture layer : layers) {
            layer.dispose();
        }
    }

}
