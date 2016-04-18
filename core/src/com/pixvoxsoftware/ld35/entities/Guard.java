package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.Gdx;
import com.pixvoxsoftware.ld35.AnimatedSprite;
import com.pixvoxsoftware.ld35.GameWorld;
import com.pixvoxsoftware.ld35.WorldConstants;
import com.pixvoxsoftware.ld35.controllers.GuardController;

public class Guard extends Entity {

    enum State {IDLE, }

    public Guard(GameWorld world, float x, float y, int stepsLeft, int stepsRight) {
        this.world = world;
        this.sprite = new AnimatedSprite(Gdx.files.internal("guard.png"), 15, 0.09f);
        createPhysicsBody();
        setPosition(x, y);
        setController(new GuardController());
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
        return 2;
    }
}
