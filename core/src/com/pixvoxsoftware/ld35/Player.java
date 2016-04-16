package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity {

    public final static float DIRECTION_RIGHT = 1f;
    public final static float DIRECTION_LEFT = -1f;

    public enum State {
        IDLE,
        MOVE
    }

    private Texture texture;

    private float direction;
    private State state;

    public Player(float x, float y) {
        setPosition(x, y);
        state = State.IDLE;
        direction = 0f;
        texture = new Texture(Gdx.files.internal("sketch_gg_w_64.png"));
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
        position.set(x, y);
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void act() {
        if (getState() == Player.State.MOVE) {
            setX(getX() + getDirection() * Gdx.graphics.getDeltaTime() * 100);
        }
    }
}
