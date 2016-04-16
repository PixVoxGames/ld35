package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.pixvoxsoftware.ld35.AnimatedSprite;
import com.pixvoxsoftware.ld35.Entity;
import com.pixvoxsoftware.ld35.GameWorld;
import com.pixvoxsoftware.ld35.controllers.EntityController;

public class Box extends Entity {
    public Box(GameWorld world, float x, float y) {
        this.world = world;
        sprite = new AnimatedSprite(Gdx.files.internal("sketch_gg_w.png"), 1);
    }

    @Override
    public EntityController getController() {
        return null;
    }
}
