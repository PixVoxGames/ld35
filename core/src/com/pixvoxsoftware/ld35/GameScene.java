package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScene implements Scene {

    private SpriteBatch spriteBatch;
    private Texture texture;

    private World world;

    public GameScene() {
        this.spriteBatch = new SpriteBatch();
        this.world = new World();
        this.texture = new Texture(Gdx.files.internal("sketch_gg_w.png"));
    }

    @Override
    public void draw() {
        this.spriteBatch.begin();
        this.spriteBatch.draw(this.texture, this.world.getPlayer().getX(),
                this.world.getPlayer().getY());
        this.spriteBatch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (world.onKeyPressed(keycode)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (world.onKeyReleased(keycode)) {
            return true;
        }
        return false;
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
