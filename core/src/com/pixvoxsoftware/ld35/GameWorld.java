package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.pixvoxsoftware.ld35.controllers.EntityController;
import com.pixvoxsoftware.ld35.controllers.PlayerController;
import com.pixvoxsoftware.ld35.entities.Box;
import java.util.ArrayList;
import java.util.Iterator;

public class GameWorld {

    private Player player;
    private ArrayList<Entity> entities = new ArrayList<>();
    private TiledMap map;
    private Vector2 gravity = new Vector2(0, -100);
    private float accumulator;
    public World physicsWorld;

    public GameWorld() {
        map = new TmxMapLoader().load("map.tmx");
        physicsWorld = new World(new Vector2(0, -10), true);

        // fake ground
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(1000f, 1));
        Body groundBody = physicsWorld.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(1000f, 0.5f);
        Fixture fixture = groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();
        // just for testing
        fixture.setUserData(new Box(this, -10, -10));

        player = new Player(this, 100, 100);
        addEntity(player);
//        addEntity(new Box(this, -10, -10));

        physicsWorld.setContactListener(new GroundCheckContactListener());

        Loggers.game.debug("game world initialized");
    }

    public void act() {
        float frameTime = Math.min(Gdx.graphics.getDeltaTime(), 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1 / 60f) {
            physicsWorld.step(1 / 60f, 6, 2);
            accumulator -= 1 / 60f;
        }

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

    public Vector2 getGravity() {
        return gravity;
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
                "player x: " + Float.toString(player.getSprite().getX()),
                "player y: " + Float.toString(player.getSprite().getY()),
                "player direction: " + Float.toString(player.getDirection()),
                "player grounded: " + Boolean.toString(player.canJump()),
        };
    }
}