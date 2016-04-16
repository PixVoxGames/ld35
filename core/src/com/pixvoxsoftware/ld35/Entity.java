package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.pixvoxsoftware.ld35.controllers.EntityController;

public abstract class Entity {
    abstract public Sprite getSprite();
    abstract public EntityController getController();
}
