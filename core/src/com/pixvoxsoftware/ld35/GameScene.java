package com.pixvoxsoftware.ld35;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.pixvoxsoftware.ld35.entities.Entity;


public class GameScene implements Scene {

    private SpriteBatch spriteBatch;
    private SpriteBatch staticSpritesBatch;
    private SpriteBatch fontBatch;
    private BitmapFont font;
    private FollowCamera cam;
    private Box2DDebugRenderer debugRenderer;
    private OrthogonalTiledMapRenderer mapRenderer;

    private ParallaxBackground background;

    private GameWorld world;
    private boolean renderDebugText = true;
    private boolean renderDebugPhysics = true;

    private float SCREEN_WIDTH, SCREEN_HEIGHT;

    public GameScene() {
        spriteBatch = new SpriteBatch();
        fontBatch = new SpriteBatch();
        staticSpritesBatch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/arial-15.fnt"));
        font.setColor(1, 1, 1, 1);
        world = new GameWorld();
        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();
        cam = new FollowCamera(SCREEN_WIDTH, SCREEN_HEIGHT, world.getPlayer());
        cam.setBounds(0, 0, SCREEN_WIDTH*2, SCREEN_HEIGHT*2);
        debugRenderer = new Box2DDebugRenderer();
        mapRenderer = new OrthogonalTiledMapRenderer(world.getMap());
        background = new ParallaxBackground(cam);
        background.addLayer(new ParallaxLayer(new Texture(Gdx.files.internal("background/wall.png")), 0.8f));
        background.addLayer(new ParallaxLayer(new Texture(Gdx.files.internal("background/light.png")), 0.95f));
        background.addLayer(new ParallaxLayer(new Texture(Gdx.files.internal("background/rocks.png")), 1f));
    }

    @Override
    public void draw() {
        world.act();
        cam.update();
        spriteBatch.setProjectionMatrix(cam.combined);
        background.update(cam);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        staticSpritesBatch.begin();
        for (ParallaxLayer layer : background.getLayers()) {
            staticSpritesBatch.draw(layer.getTexture(),
                    0,
                    0,
                    SCREEN_WIDTH,
                    SCREEN_HEIGHT,
                    layer.getX(),
                    layer.getY()+1f,
                    layer.getX()+1f,
                    layer.getY());
        }
        staticSpritesBatch.end();

        mapRenderer.setView(cam);
        mapRenderer.render();

        spriteBatch.begin();
        for (Entity entity : world.getEntities()) {
            if (entity.isVisible()) {
                entity.getSprite().draw(spriteBatch);
            }
        }
        spriteBatch.end();

        world.rayHandler.setCombinedMatrix(cam);
        world.rayHandler.updateAndRender();

        if (renderDebugPhysics) {
            debugRenderer.render(world.physicsWorld, cam.combined);
        }

        if (renderDebugText) {
            drawDebugText();
        }
    }

    private void drawDebugText() {
        fontBatch.begin();
        font.draw(fontBatch, "fps: " + Integer.toString(Gdx.graphics.getFramesPerSecond()), 2, Gdx.graphics.getHeight() - 2);
        font.draw(fontBatch, "entities count: " + Integer.toString(world.getEntities().size()), 2, Gdx.graphics.getHeight() - 19);
        int offset = -36;
        String[] debugStrings = world.getDebugStrings();
        for (int i = 0; i < debugStrings.length; i++) {
            font.draw(fontBatch, debugStrings[i], 2, Gdx.graphics.getHeight() + offset);
            offset -= 17;
        }
        fontBatch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.F5) {
            renderDebugText = !renderDebugText;
            return true;
        } else if (keycode == Input.Keys.F6) {
            renderDebugPhysics= !renderDebugPhysics;
            return true;
        }
        return world.onKeyPressed(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return world.onKeyReleased(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int x1 = Gdx.input.getX();
        int y1 = Gdx.input.getY();
        Vector3 input = new Vector3(x1, y1, 0);
        cam.unproject(input);
        return world.touchDown(input.x, input.y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
