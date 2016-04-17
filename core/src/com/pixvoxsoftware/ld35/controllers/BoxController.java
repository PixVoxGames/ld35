package com.pixvoxsoftware.ld35.controllers;

import com.badlogic.gdx.math.MathUtils;
import com.pixvoxsoftware.ld35.entities.Entity;

public class BoxController extends EntityController {

    @Override
    public void act(Entity entity) {
        super.act(entity);
        entity.getSprite().setRotation(MathUtils.radiansToDegrees*entity.physicsBody.getAngle());
    }

    @Override
    public boolean onKeyPressed(Entity entity, int keycode) {
        return false;
    }

    @Override
    public boolean onKeyReleased(Entity entity, int keycode) {
        return false;
    }
}
