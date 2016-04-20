package com.pixvoxsoftware.ld35.tasks;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.pixvoxsoftware.ld35.entities.Guard;

public class MurderTask extends LeafTask<Guard> {
    @Override
    public Status execute() {
        if (getObject().getTarget() != null) {
            getObject().setState(Guard.State.MOVING);
            if (Math.abs(getObject().getTarget().getSprite().getX() - getObject().getX()) < 1.5f &&
                    Math.abs(getObject().getTarget().getSprite().getY() - getObject().getY()) < 1.5f) {
                getObject().getTarget().kill();
                return Status.SUCCEEDED;
            }
            return Status.RUNNING;
        }
        return Status.FAILED;
    }

    @Override
    protected Task<Guard> copyTo(Task<Guard> task) {
        return task;
    }
}
