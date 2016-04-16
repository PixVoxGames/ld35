package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pixvoxsoftware.ld35.controllers.EntityController;
import com.pixvoxsoftware.ld35.controllers.PlayerController;

public class Player extends Entity {

    public final static float DIRECTION_RIGHT = 1f;
    public final static float DIRECTION_LEFT = -1f;

    public enum State {
        IDLE,
        MOVE
    }

    private Sprite sprite;

    private float direction;
    private State state;
    private PlayerController controller = new PlayerController();

    public Player(float x, float y) {
        sprite = new Sprite(new Texture(Gdx.files.internal("sketch_gg_w_64.png")));
        state = State.IDLE;
        direction = 0f;
        setPosition(x, y);
    }

    public State getState() {
        return state;
    }

    public float getDirection() {
        return direction;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public EntityController getController() {
        return controller;
    }
}
