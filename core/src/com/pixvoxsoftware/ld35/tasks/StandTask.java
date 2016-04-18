package com.pixvoxsoftware.ld35.tasks;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.pixvoxsoftware.ld35.entities.Guard;

public class StandTask extends LeafTask<Guard> {

    private float time = 0f;

    @Override
    public void start() {

    }

    @Override
    public Status execute() {
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Guard> copyTo(Task<Guard> task) {
        return null;
    }
}
