package com.pixvoxsoftware.ld35.entities;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.pixvoxsoftware.ld35.GameWorld;
import com.pixvoxsoftware.ld35.WorldConstants;

public class Lamp extends Entity {
    private Sprite spriteOn;
    private Sprite spriteOff;
    private float offsetY;
    private float offsetX;
    private PointLight light;
    private float x;
    private float y;

    public Lamp(GameWorld world, TextureRegion textureRegion, float offsetX, float offsetY, float x, float y) {
        spriteOn = new Sprite(textureRegion);
        spriteOn.setSize(spriteOn.getWidth() / WorldConstants.PIXELS_PER_METER, spriteOn.getHeight() / WorldConstants.PIXELS_PER_METER);
        spriteOff = spriteOn;
        sprite = spriteOn;
        spriteOn.setPosition(x, y);
        spriteOff.setPosition(x, y);
        this.world = world;
        this.x = x;
        this.y = y;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        createLight();
    }

    public Lamp(GameWorld world, TextureRegion textureRegion, float x, float y) {
        this(world, textureRegion, 0, 0, x, y);
    }

    public Lamp(GameWorld world, Sprite spriteOn, Sprite spriteOff, float offsetX, float offsetY, float x, float y) {
        spriteOn.setSize(spriteOn.getWidth() / WorldConstants.PIXELS_PER_METER, spriteOn.getHeight() / WorldConstants.PIXELS_PER_METER);
        spriteOff.setSize(spriteOff.getWidth() / WorldConstants.PIXELS_PER_METER, spriteOff.getHeight() / WorldConstants.PIXELS_PER_METER);
        this.spriteOn = spriteOn;
        this.spriteOff = spriteOff;
        this.sprite = spriteOn;
        spriteOn.setPosition(x, y);
        spriteOff.setPosition(x, y);
        this.world = world;
        this.x = x;
        this.y = y;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        createLight();
    }

    public Lamp(GameWorld world, Sprite sprite, float x, float y) {
        this(world, sprite, sprite, 0, 0, x, y);
    }

    @Override
    public boolean isGround() {
        return false;
    }

    @Override
    public short getCategory() {
        return WorldConstants.ENTITY_CATEGORY;
    }

    @Override
    public void setPosition(float x, float y) {

    }

    @Override
    public float getX() {
        return sprite.getX();
    }

    @Override
    public float getY() {
        return sprite.getY();
    }

    private void createLight() {
        light = new PointLight(
                world.rayHandler,
                1000, new Color(1, 228f / 255f, 181f / 255f, 1),
                120 / WorldConstants.PIXELS_PER_METER,
                (x + offsetX),
                (y + offsetY)
        );
    }

    public void setActive(boolean enabled) {
        if (enabled) {
            sprite = spriteOn;
        } else {
            sprite = spriteOff;
        }
        light.setActive(enabled);
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }

    public boolean isActive() {
        return light.isActive();
    }

    @Override
    public short getCollisionMask() {
        return 0;
    }

    @Override
    public int renderPass() {
        return 0;
    }
}
