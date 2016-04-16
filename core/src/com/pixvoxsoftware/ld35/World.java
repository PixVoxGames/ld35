package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class World {

    private Player player;

    public World() {
        this.player = new Player(0, 0);
    }

    public boolean onKeyPressed(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                player.setY(player.getY() + Gdx.graphics.getDeltaTime()*30);
                return true;

            case Input.Keys.S:
                player.setY(player.getY() - Gdx.graphics.getDeltaTime()*30);
                return true;

            case Input.Keys.A:
                player.setX(player.getX() - Gdx.graphics.getDeltaTime()*30);
                return true;

            case Input.Keys.D:
                player.setX(player.getX() + Gdx.graphics.getDeltaTime()*30);
                return true;

            default:
                break;
        }
        return false;
    }

    public Player getPlayer() {
        return this.player;
    }
    public boolean onKeyReleased(int keycode) {
        return false;
    }
}
