package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class Lamp extends Entity {
;

    public Lamp(TextureRegion textureRegion, float x, float y) {
        sprite = new Sprite(textureRegion);
        sprite.setPosition(x, y);
    }

    @Override
    public boolean isGround() {
        return false;
    }
}
