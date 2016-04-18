package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.Gdx;
import com.pixvoxsoftware.ld35.AnimatedSprite;
import com.pixvoxsoftware.ld35.GameWorld;
import com.pixvoxsoftware.ld35.WorldConstants;

public class Guard extends Entity {

    public enum State {IDLE, MOVING};

    private State state;
    private int stepsLeft, stepsRight;
    private float targetX;

    public Guard(GameWorld world, float x, float y, int stepsLeft, int stepsRight) {
        this.world = world;
        this.sprite = new AnimatedSprite(Gdx.files.internal("guard.png"), 15, 0.09f);
        this.stepsLeft = stepsLeft;
        this.stepsRight = stepsRight;
        createPhysicsBody();
        setPosition(x, y);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getStepsLeft() {
        return stepsLeft;
    }

    public int getStepsRight() {
        return stepsRight;
    }

    public float getTargetX() {
        return targetX;
    }

    public void setTargetX(float targetX) {
        this.targetX = targetX;
    }

    @Override
    public boolean isGround() {
        return false;
    }

    @Override
    public short getCategory() {
        return WorldConstants.SOUL_CATEGORY;
    }

    @Override
    public short getCollisionMask() {
        return WorldConstants.OBSTACLE_CATEGORY | WorldConstants.SOUL_CATEGORY;
    }

    @Override
    public int renderPass() {
        return 1;
    }

    @Override
    public void createPhysicsBody() {
        super.createPhysicsBody();
        physicsBody.setFixedRotation(true);
    }
}
