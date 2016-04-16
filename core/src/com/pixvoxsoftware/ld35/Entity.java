package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.physics.box2d.*;
import com.pixvoxsoftware.ld35.controllers.EntityController;

public abstract class Entity {
    protected boolean visible = true;
    protected EntityController controller;
    protected AnimatedSprite sprite;
    protected boolean killed = false;

    public GameWorld world;
    public Body physicsBody;

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

    public GameWorld getWorld() {
        return world;
    }

    public void setWorld(GameWorld world) {
        this.world = world;
    }

    public void initPhysics() {
        createPhysicsBody();
        if (isGroundContactCheckNeeded()) {
//            createGroundedContactListener();
        }
    }

    public void createPhysicsBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        physicsBody = world.physicsWorld.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = physicsBody.createFixture(fixtureDef);

        polygonShape.dispose();

        physicsBody.setUserData(this);
    }

    public boolean isGroundContactCheckNeeded() {
        return false;
    }
}
