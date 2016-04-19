package com.pixvoxsoftware.ld35.tasks;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.pixvoxsoftware.ld35.entities.Guard;

public class MurderTask extends LeafTask<Guard> {
    @Override
    public Status execute() {
        if (getObject().getTarget() != null) {
            if (Math.abs(getObject().getTarget().getSprite().getX() - getObject().getX()) < 0.05f) {
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
