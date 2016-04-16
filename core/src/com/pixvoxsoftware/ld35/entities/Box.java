package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.pixvoxsoftware.ld35.Entity;

public class Box extends Entity {
    private Texture texture;

    public Box(float x, float y) {
        texture = new Texture(Gdx.files.internal("sketch_gg_w.png"));
    }

    @Override
    public void act() {

    }

    @Override
    public Texture getTexture() {
        return texture;
    }
}
