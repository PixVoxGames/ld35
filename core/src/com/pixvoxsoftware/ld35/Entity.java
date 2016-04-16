package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    public Vector2 position = new Vector2(0, 0);

    public void setX(float x) {
        position.x = x;
    }
    public void setY(float y) {
        position.y = y;
    }

    public float getX() {
        return position.x;
    }
    public float getY() {
        return position.y;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    abstract public void act();
    abstract public Texture getTexture();
}
