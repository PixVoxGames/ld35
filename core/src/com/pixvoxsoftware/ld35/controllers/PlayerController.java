package com.pixvoxsoftware.ld35.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.pixvoxsoftware.ld35.Entity;
import com.pixvoxsoftware.ld35.Player;

public class PlayerController extends EntityController {
    @Override
    public void act(Entity entity) {
        Player player = (Player) entity;
        if (player.getState() == Player.State.MOVE) {
            player.getSprite().setX(player.getSprite().getX() + player.getDirection() * Gdx.graphics.getDeltaTime() * 100);
        }
    }

    public boolean onTouchDown(Player player, float worldX, float worldY, int pointer, int button) {
        Entity entity = world.getFirstEntityWithPoint(worldX, worldY);
        System.out.println(entity);
        return true;
    }

    @Override
    public boolean onKeyPressed(Entity entity, int keycode) {
        Player player = (Player) entity;
        switch (keycode) {
            case Input.Keys.W:
                // Jump
//                player.setY(player.getY() + Gdx.graphics.getDeltaTime()*30);
                return true;

            case Input.Keys.S:
                // Nothing
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
        }
        return false;
    }
}
