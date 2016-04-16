package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.pixvoxsoftware.ld35.controllers.EntityController;
import com.pixvoxsoftware.ld35.controllers.PlayerController;
import com.pixvoxsoftware.ld35.entities.Box;

import java.util.ArrayList;
import java.util.Iterator;

public class World {

    private Player player;
    private ArrayList<Entity> entities = new ArrayList<>();
    private TiledMap map;

    public World() {
        player = new Player(0, 0);
        map = new TmxMapLoader().load("map.tmx");
        addEntity(player);
        addEntity(new Box(-10, -10));
    }

    public void act() {
        for (Iterator<Entity> iterator = entities.iterator(); iterator.hasNext();) {
            Entity entity = iterator.next();
            // if entity is killed, remove it
            if (entity.isKilled()) {
                iterator.remove();
            } else {
                EntityController controller = entity.getController();
                if (controller != null) {
                    controller.act(entity);
                }
            }
        }
    }

    public void addEntity(Entity e) {
        EntityController controller = e.getController();
        if (controller != null) {
            controller.setWorld(this);
        }
        entities.add(e);
    }

    public TiledMap getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean onKeyPressed(int keycode) {
        for (Entity entity : entities) {
            EntityController controller = entity.getController();
            if (controller != null && controller.onKeyPressed(entity, keycode)) {
                return true;
            }
        }
        return false;
    }

    public boolean onKeyReleased(int keycode) {
        for (Entity entity : entities) {
            EntityController controller = entity.getController();
            if (controller != null && controller.onKeyReleased(entity, keycode)) {
                return true;
            }
        }
        return false;
    }

    public boolean touchDown(float worldX, float worldY, int pointer, int button) {
        PlayerController playerController = (PlayerController) player.getController();
        return playerController.onTouchDown(player, worldX, worldY, pointer, button);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Entity getFirstEntityWithPoint(float x, float y) {
        for (Entity entity : entities) {
            if (entity.getSprite().getBoundingRectangle().contains(x, y)) {
                return entity;
            }
        }
        return null;
    }

    public String[] getDebugStrings() {
        return new String[] {
                "consumed soul: " + Boolean.toString(player.getConsumedSoul() != null),
        };
    }
}
