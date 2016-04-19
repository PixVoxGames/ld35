package com.pixvoxsoftware.ld35.tasks;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.pixvoxsoftware.ld35.entities.Guard;

public class CheckTask extends LeafTask<Guard> {

    @Override
    public Status execute() {
        if (getObject().getTarget() != null) {
            return Status.SUCCEEDED;
        }
        return Status.FAILED;
    }

    @Override
    protected Task<Guard> copyTo(Task<Guard> task) {
        return task;
    }
}
