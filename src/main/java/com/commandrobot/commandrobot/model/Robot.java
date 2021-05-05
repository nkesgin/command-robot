package com.commandrobot.commandrobot.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.commandrobot.commandrobot.model.enums.Direction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Robot {

    private int currentX;
    private int currentY;
    private Direction currentDirection;
    private List<RobotAction> actions;

    public void addAction(RobotAction action) {
        if(actions == null) {
            actions = new ArrayList<RobotAction>();
        }
        actions.add(action);
    }

}
