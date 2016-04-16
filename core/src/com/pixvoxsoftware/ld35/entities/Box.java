package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pixvoxsoftware.ld35.Entity;
import com.pixvoxsoftware.ld35.controllers.EntityController;

public class Box extends Entity {
    private Sprite sprite;

    public Box(float x, float y) {
        sprite = new Sprite(new Texture(Gdx.files.internal("sketch_gg_w.png")));
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public EntityController getController() {
        return null;
    }
}
