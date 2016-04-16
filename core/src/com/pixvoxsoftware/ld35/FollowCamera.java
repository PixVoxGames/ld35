package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.graphics.OrthographicCamera;

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
            // z-coordinate doesn't matters, because ortho-projection
            position.set(
                    target.getX() + target.getTexture().getWidth() / 2,
                    target.getY() + target.getTexture().getHeight() / 2,
                    1
            );
        };
    }
}
