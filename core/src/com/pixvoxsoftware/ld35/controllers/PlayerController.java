package com.pixvoxsoftware.ld35.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.pixvoxsoftware.ld35.entities.Entity;
import com.pixvoxsoftware.ld35.entities.Player;

public class PlayerController extends EntityController {
    private EntityController consumedSoulController;
    private Sprite playerSprite;

    @Override
    public void act(Entity entity) {
        super.act(entity);
        if (entity instanceof Player) {
            Player player = (Player) entity;
            Entity consumedSoul = player.getConsumedSoul();
            Body physicsBody;
            if (consumedSoul != null) {
                // now consumed soul sprite == player sprite
                physicsBody = consumedSoul.physicsBody;
            } else {
                physicsBody = player.physicsBody;
            }

            // movement
            float desiredVelocity = 100 * player.getDirection();
            float impulse = physicsBody.getMass() * (desiredVelocity - physicsBody.getLinearVelocity().x);
            physicsBody.applyLinearImpulse(new Vector2(impulse, 0), physicsBody.getWorldCenter(), true);

            // jumping, just doing the same thing, but by y-axis
            if (player.isJumping) {
                if (player.canJump()) {
                    desiredVelocity = 100;
                    impulse = player.physicsBody.getMass() * (desiredVelocity - player.physicsBody.getLinearVelocity().y);
                    physicsBody.applyLinearImpulse(new Vector2(0, impulse), player.physicsBody.getWorldCenter(), true);
                }
            }
        } else {
            // it's consumed soul, what we need to do here?
        }
    }

    public boolean onTouchDown(Player player, float worldX, float worldY, int pointer, int button) {
        Entity entity = player.world.getFirstEntityWithPoint(worldX, worldY);
        if (entity != null && canConsumeSoul(entity)) {
            consumeSoul(player, entity);
        }
        return true;
    }

    private void consumeSoul(Player player, Entity entity) {
        player.setConsumedSoul(entity);
        player.setVisible(false);
        // save controller for disable actions of consumed soul
        consumedSoulController = entity.getController();
        entity.setController(this);
        playerSprite = player.getSprite();
        player.setSprite(entity.getSprite());
        player.setConsumedSoul(entity);
    }

    private void spitSoul(Player player) {
        if (player.getConsumedSoul() == null) {
            return;
        }

        playerSprite.setPosition(player.getSprite().getX(), player.getSprite().getY());
        player.setSprite(playerSprite);
        player.setVisible(true);

        if (canEntitySurviveAfterSpit(player.getConsumedSoul())) {
            player.getConsumedSoul().setController(consumedSoulController);
        } else {
            player.getConsumedSoul().kill();
        }
        player.setConsumedSoul(null);
    }

    private boolean canEntitySurviveAfterSpit(Entity e) {
        return true;
    }

    private boolean canConsumeSoul(Entity entity) {
        return true;
    }

    @Override
    public boolean onKeyPressed(Entity entity, int keycode) {
        Player player = (Player) entity;
        switch (keycode) {
            case Input.Keys.E:
                spitSoul(player);
                return true;
            case Input.Keys.W:
            case Input.Keys.SPACE:
                player.isJumping = true;
                return true;

            case Input.Keys.S:
                return true;

            case Input.Keys.A:
                // Move left
                switch (player.getState()) {
                    case IDLE:
                        player.setState(Player.State.MOVE);
                        player.setDirection(Player.DIRECTION_LEFT);
                    case MOVE:
                        if (player.getDirection() != Player.DIRECTION_LEFT) {
                            player.setState(Player.State.IDLE);
                        }
                }
                return true;

            case Input.Keys.D:
                // Move right
                switch (player.getState()) {
                    case IDLE:
                        player.setState(Player.State.MOVE);
                        player.setDirection(Player.DIRECTION_RIGHT);
                    case MOVE:
                        if (player.getDirection() != Player.DIRECTION_RIGHT) {
                            player.setState(Player.State.IDLE);
                        }
                }
                return true;

            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onKeyReleased(Entity entity, int keycode) {
        Player player = (Player) entity;
        switch (keycode) {
            case Input.Keys.A:
                switch (player.getState()) {
                    case IDLE:
                        player.setState(Player.State.MOVE);
                        if (player.getDirection() == Player.DIRECTION_LEFT) {
                            player.setDirection(-player.getDirection());  // opposite direction
                        }
                        break;
                    case MOVE:
                        player.setState(Player.State.IDLE);
                        player.setDirection(0f);
                        break;
                }
                return true;

            case Input.Keys.D:
                switch (player.getState()) {
                    case IDLE:
                        player.setState(Player.State.MOVE);
                        if (player.getDirection() == Player.DIRECTION_RIGHT) {
                            player.setDirection(-player.getDirection());  // opposite direction
                        }
                        break;
                    case MOVE:
                        player.setState(Player.State.IDLE);
                        player.setDirection(0f);
                        break;
                }
                return true;
            case Input.Keys.W:
            case Input.Keys.SPACE:
                player.isJumping = false;
                return true;
        }
        return false;
    }
}
