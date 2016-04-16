package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.pixvoxsoftware.ld35.controllers.EntityController;

public abstract class Entity {
    protected boolean visible = true;
    protected EntityController controller;
    protected Sprite sprite;
    protected boolean killed = false;

    public boolean isKilled() {
        return killed;
    }

    public void kill() {
        killed = true;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
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
