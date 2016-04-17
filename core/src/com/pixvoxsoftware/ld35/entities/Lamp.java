package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pixvoxsoftware.ld35.Entity;
import com.pixvoxsoftware.ld35.GameWorld;
import com.pixvoxsoftware.ld35.controllers.EntityController;

public class Lamp extends Entity {
;

    public Lamp(GameWorld world, float x, float y) {
        this.sprite = new Sprite(new Texture(Gdx.files.internal("light.png")));
        this.world = world;
        createPhysicsBody();
        setPosition(x, y);
        setController(new EntityController() {
            @Override
            public boolean onKeyPressed(Entity entity, int keycode) {
                return false;
            }

            @Override
            public boolean onKeyReleased(Entity entity, int keycode) {
                return false;
            }
        });
    }

    @Override
    public boolean isGround() {
        return false;
    }
}
