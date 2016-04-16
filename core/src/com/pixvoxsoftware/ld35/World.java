package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.pixvoxsoftware.ld35.entities.Box;

import java.util.ArrayList;

public class World {

    private Player player;
    private ArrayList<Entity> entities = new ArrayList<>();

    public World() {
        player = new Player(0, 0);
        entities.add(player);
        entities.add(new Box(-10, -10));
    }

    public void act() {
        for (Entity entity : entities) {
            entity.act();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public boolean onKeyPressed(int keycode) {
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

    public boolean onKeyReleased(int keycode) {
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

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
