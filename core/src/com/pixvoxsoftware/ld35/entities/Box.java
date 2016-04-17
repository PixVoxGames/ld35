package com.pixvoxsoftware.ld35.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.pixvoxsoftware.ld35.AnimatedSprite;
import com.pixvoxsoftware.ld35.Entity;
import com.pixvoxsoftware.ld35.GameWorld;
import com.pixvoxsoftware.ld35.controllers.BoxController;
import com.pixvoxsoftware.ld35.controllers.EntityController;

public class Box extends Entity {
    public Box(GameWorld world, float x, float y) {
        this.world = world;
        sprite = new Sprite(new Texture(Gdx.files.internal("box.png")));
        createPhysicsBody();
        setPosition(x, y);
        setController(new BoxController());
    }

    @Override
    public boolean isGround() {
        return false;
    }
}
