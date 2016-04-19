package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.Gdx;
import com.pixvoxsoftware.ld35.AnimatedSprite;
import com.pixvoxsoftware.ld35.GameWorld;
import com.pixvoxsoftware.ld35.WorldConstants;
import com.pixvoxsoftware.ld35.controllers.EntityController;

public class Merlin extends Entity {
    public Merlin(GameWorld world, float x, float y) {
        this.world = world;
        sprite = new AnimatedSprite(Gdx.files.internal("gg_b.png"), 12, 0.06f);
        sprite.setSize(sprite.getWidth() / WorldConstants.PIXELS_PER_METER, sprite.getHeight() / WorldConstants.PIXELS_PER_METER);
        createPhysicsBody();
        setPosition(x, y);
        setController(EntityController.instance);
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
        return WorldConstants.ANY_CATEGORY;
    }

    @Override
    public int renderPass() {
        return 1;
    }


}
