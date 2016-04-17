package com.pixvoxsoftware.ld35.controllers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.pixvoxsoftware.ld35.entities.Entity;

public abstract class EntityController {
    public void act(Entity entity) {
        Vector2 position = entity.physicsBody.getTransform().getPosition();
        Sprite sprite = entity.getSprite();
        sprite.setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
    }

    public abstract boolean onKeyPressed(Entity entity, int keycode);
    public abstract boolean onKeyReleased(Entity entity, int keycode);
}
