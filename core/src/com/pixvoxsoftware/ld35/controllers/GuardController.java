package com.pixvoxsoftware.ld35.controllers;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.math.Vector2;
import com.pixvoxsoftware.ld35.Loggers;
import com.pixvoxsoftware.ld35.WorldConstants;
import com.pixvoxsoftware.ld35.entities.Entity;
import com.pixvoxsoftware.ld35.entities.Guard;

public class GuardController extends EntityController {

    private BehaviorTree<Guard> behaviorTree;

    public GuardController(BehaviorTree<Guard> behaviorTree) {
        this.behaviorTree = behaviorTree;
        behaviorTree.start();
    }

    @Override
    public void act(Entity entity) {
        super.act(entity);
        behaviorTree.step();
        Guard guard = (Guard) entity;
        if (guard.getState() == Guard.State.MOVING) {
            float impulseX = guard.physicsBody.getMass() * (WorldConstants.PLAYER_MAX_X_VELOCITY * Math.signum(guard.getTargetX() - guard.getX()) - guard.physicsBody.getLinearVelocity().x);
            guard.physicsBody.applyLinearImpulse(new Vector2(impulseX, 0), guard.physicsBody.getWorldCenter(), true);
        } else {
            guard.physicsBody.applyLinearImpulse(new Vector2(-guard.physicsBody.getLinearVelocity().x * guard.physicsBody.getMass(), 0), guard.physicsBody.getWorldCenter(), true);
        }
    }

    @Override
    public boolean onKeyPressed(Entity entity, int keycode) {
        return false;
    }

    @Override
    public boolean onKeyReleased(Entity entity, int keycode) {
        return false;
    }
}
