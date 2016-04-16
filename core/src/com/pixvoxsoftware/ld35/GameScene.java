package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScene implements Scene {

    private SpriteBatch spriteBatch;
    private SpriteBatch fontBatch;
    private BitmapFont font;
    private FollowCamera cam;

    private World world;
    private boolean renderDebugText = true;

    public GameScene() {
        spriteBatch = new SpriteBatch();
        fontBatch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/arial-15.fnt"));
        font.setColor(1, 1, 1, 1);
        world = new World();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new FollowCamera(w, h, world.getPlayer());
    }

    @Override
    public void draw() {
        world.act();
        cam.update();
        spriteBatch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        for (Entity entity : world.getEntities()) {
            spriteBatch.draw(entity.getTexture(), entity.getX(), entity.getY());
        }
        spriteBatch.end();

        if (renderDebugText) {
            drawDebugText();
        }
    }

    private void drawDebugText() {
        fontBatch.begin();
        font.draw(fontBatch, "fps: " + Integer.toString(Gdx.graphics.getFramesPerSecond()), 2, Gdx.graphics.getHeight() - 2);
        font.draw(fontBatch, "entities count: " + Integer.toString(world.getEntities().size()), 2, Gdx.graphics.getHeight() - 19);
        fontBatch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.F5) {
            renderDebugText = !renderDebugText;
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
        return false;
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
