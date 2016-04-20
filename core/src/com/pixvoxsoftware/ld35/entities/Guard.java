package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pixvoxsoftware.ld35.AnimatedSprite;
import com.pixvoxsoftware.ld35.GameWorld;
import com.pixvoxsoftware.ld35.WorldConstants;

import java.util.ArrayList;

public class Guard extends Entity {

    public enum State {IDLE, MOVING, RAGE}
    public enum Direction {RIGHT, LEFT};

    private State state;
    private Direction direction;
    private int stepsLeft, stepsRight;
    private float spawnX, spawnY;
    private float targetX, targetY;
    private float visionRadius;

    private AnimatedSprite movingSprite, idleSprite;

    private Player target;

    public Guard(GameWorld world, float x, float y, int stepsLeft, int stepsRight, float visionRadius) {
        this.world = world;
        movingSprite = new AnimatedSprite(Gdx.files.internal("guard.png"), 15, 0.09f);
        idleSprite = new AnimatedSprite(Gdx.files.internal("guard_st.png"), 4, 0.09f);
        idleSprite.setMirroredVertically(true);
        this.sprite = movingSprite;
        this.stepsLeft = stepsLeft;
        this.stepsRight = stepsRight;
        this.visionRadius = visionRadius;
        spawnX = x;
        spawnY = y;
        createPhysicsBody();
        setPosition(x, y);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public Player getTarget() {
        return target;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    public float getTargetY() {
        return targetY;
    }

    public float getSpawnX() {
        return spawnX;
    }

    public float getSpawnY() {
        return spawnY;
    }

    public void setTargetX(float targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(float targetY) {
        this.targetY = targetY;
    }

    public ArrayList<Entity> getObjectsAround() {
        return world.getEntitiesInArea(getX() + getSprite().getWidth() * 32  / (2 * WorldConstants.PIXELS_PER_METER), getY(), visionRadius);
    }

    @Override
    public Sprite getSprite() {
        if (state == State.IDLE) {
            return idleSprite;
        } else {
            return movingSprite;
        }
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

    public float getVisionRadius() {
        return visionRadius;
    }
}
