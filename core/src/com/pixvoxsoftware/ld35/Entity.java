package com.pixvoxsoftware.ld35;

import com.pixvoxsoftware.ld35.controllers.EntityController;

public abstract class Entity {
    protected boolean visible = true;
    protected EntityController controller;
    protected AnimatedSprite sprite;
    protected boolean killed = false;

    public boolean isKilled() {
        return killed;
    }

    public void kill() {
        killed = true;
    }

    public AnimatedSprite getSprite() {
        return sprite;
    }

    public void setSprite(AnimatedSprite sprite) {
        this.sprite = sprite;
    }

    public void setController(EntityController controller) {
        this.controller = controller;
    }

    public EntityController getController() {
        return controller;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
