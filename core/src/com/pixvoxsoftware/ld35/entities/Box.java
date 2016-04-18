package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pixvoxsoftware.ld35.GameWorld;
import com.pixvoxsoftware.ld35.WorldConstants;
import com.pixvoxsoftware.ld35.controllers.BoxController;

public class Box extends Entity {
    public Box(GameWorld world, TextureRegion textureRegion, float x, float y) {
        this.world = world;
        sprite = new Sprite(textureRegion);
        sprite.setSize(sprite.getWidth() / WorldConstants.PIXELS_PER_METER, sprite.getHeight() / WorldConstants.PIXELS_PER_METER);
        createPhysicsBody();
        setPosition(x, y);
        setController(new BoxController());
    }

    @Override
    public boolean isGround() {
        return false;
    }

    @Override
    public short getCategory() {
        return WorldConstants.ENTITY_CATEGORY;
    }

    @Override
    public short getCollisionMask() {
        return WorldConstants.OBSTACLE_CATEGORY;
    }

    @Override
    public int renderPass() {
        return 2;
    }
}
