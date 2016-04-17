package com.pixvoxsoftware.ld35.entities;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pixvoxsoftware.ld35.GameWorld;

public class Lamp extends Entity {

    public Lamp(GameWorld world, TextureRegion textureRegion, float x, float y) {
        sprite = new Sprite(textureRegion);
        sprite.setPosition(x, y);
        this.world = world;
        new PointLight(world.rayHandler, 1000, new Color(1, 228f / 255f, 181f / 255f, 1), 100, x + 16, y + 16);
    }

    @Override
    public boolean isGround() {
        return false;
    }
}
