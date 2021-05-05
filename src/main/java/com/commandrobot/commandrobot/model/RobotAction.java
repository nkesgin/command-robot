package com.commandrobot.commandrobot.model;

import com.commandrobot.commandrobot.model.enums.ActionType;
import com.commandrobot.commandrobot.model.enums.Direction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RobotAction extends Action{

    private int x;
    private int y;
    private Direction direction;

    @Builder
    public RobotAction(ActionType actionType, int x, int y, Direction direction) {
        super(actionType);
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
