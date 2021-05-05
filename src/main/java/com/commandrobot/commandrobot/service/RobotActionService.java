package com.commandrobot.commandrobot.service;

import java.util.List;

import com.commandrobot.commandrobot.model.MoveAction;
import com.commandrobot.commandrobot.model.Robot;
import com.commandrobot.commandrobot.model.RobotAction;

public interface RobotActionService {

    RobotAction create(RobotAction action);

    RobotAction calculateNextAction(MoveAction action);

    List<RobotAction> list();

    Robot getRobot();
}
