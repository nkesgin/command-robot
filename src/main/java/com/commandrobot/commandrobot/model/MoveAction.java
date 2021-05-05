package com.commandrobot.commandrobot.model;

import com.commandrobot.commandrobot.model.enums.ActionType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MoveAction extends Action{
    int steps;

    @Builder
    public MoveAction(ActionType actionType, int steps) {
        super(actionType);
        this.steps = steps;
    }
}
