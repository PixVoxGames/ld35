package com.pixvoxsoftware.ld35.controllers;

import com.pixvoxsoftware.ld35.Entity;
import com.pixvoxsoftware.ld35.World;

public abstract class EntityController {
    protected World world;

    public void setWorld(World world) {
        this.world = world;
    }

    abstract public void act(Entity entity);
    public abstract boolean onKeyPressed(Entity entity, int keycode);
    public abstract boolean onKeyReleased(Entity entity, int keycode);
}
