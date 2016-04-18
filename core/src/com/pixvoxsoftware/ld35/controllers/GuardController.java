package com.pixvoxsoftware.ld35.controllers;

import com.pixvoxsoftware.ld35.entities.Entity;

public class GuardController extends EntityController {

    @Override
    public void act(Entity entity) {
        super.act(entity);


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
