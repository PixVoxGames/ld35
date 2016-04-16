package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.pixvoxsoftware.ld35.controllers.PlayerController;

public class Player extends Entity {

    public final static float DIRECTION_RIGHT = 1f;
    public final static float DIRECTION_LEFT = -1f;
    private Entity consumedSoul = null;

    public enum State {
        IDLE,
        MOVE
    }

    private float direction;
    private State state;


    public Player(GameWorld world, float x, float y) {
        this.world = world;
        sprite = new AnimatedSprite(Gdx.files.internal("gg_w/out.png"), 12, 0.05f);
        state = State.IDLE;
        direction = 0f;
        createPhysicsBody();
        controller = new PlayerController();
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
        physicsBody.setTransform(x, y, 0);
    }

    public void setConsumedSoul(Entity consumedSoul) {
        this.consumedSoul = consumedSoul;
    }

    public Entity getConsumedSoul() {
        return consumedSoul;
    }

    @Override
    public boolean isGroundContactCheckNeeded() {
        return true;
    }
}
