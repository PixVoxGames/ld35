package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.Gdx;
import com.pixvoxsoftware.ld35.AnimatedSprite;
import com.pixvoxsoftware.ld35.Entity;
import com.pixvoxsoftware.ld35.controllers.EntityController;

public class Box extends Entity {
    public Box(float x, float y) {
        sprite = new AnimatedSprite(Gdx.files.internal("sketch_gg_w.png"), 1);
    }

    @Override
    public EntityController getController() {
        return null;
    }
}
