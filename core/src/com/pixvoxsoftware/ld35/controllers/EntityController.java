package com.pixvoxsoftware.ld35.controllers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Transform;
import com.pixvoxsoftware.ld35.AnimatedSprite;
import com.pixvoxsoftware.ld35.Entity;
import com.pixvoxsoftware.ld35.GameWorld;

public abstract class EntityController {
    public void act(Entity entity) {
        Vector2 position = entity.physicsBody.getTransform().getPosition();
        Sprite sprite = entity.getSprite();
        sprite.setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
    }

    public abstract boolean onKeyPressed(Entity entity, int keycode);
    public abstract boolean onKeyReleased(Entity entity, int keycode);
}
