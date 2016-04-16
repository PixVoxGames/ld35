package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class FollowCamera extends OrthographicCamera {
    private Entity target;

    public FollowCamera(float w, float h, Entity target) {
        super(w, h);
        this.target = target;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    @Override
    public void update() {
        super.update();
        if (target != null) {
            // because it's a rectangle, we need to lookAt his center
            // z-coordinate doesn't matter, because of orthographic projection
            AnimatedSprite sprite = target.getSprite();
            position.set(
                    sprite.getX() + sprite.getWidth() / 2,
                    sprite.getY() + sprite.getHeight() / 2,
                    1
            );
        };
    }
}
