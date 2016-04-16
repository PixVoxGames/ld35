package com.pixvoxsoftware.ld35;

public class Player {

    public final static float DIRECTION_RIGHT = 1f,
                        DIRECTION_LEFT = -1f;

    public enum State{IDLE, MOVE}

    private float x, y;
    private float direction;
    private State state;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.state = State.IDLE;
        this.direction = 0f;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public State getState() {
        return this.state;
    }

    public float getDirection() {
        return this.direction;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
