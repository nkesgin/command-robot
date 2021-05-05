package com.commandrobot.commandrobot.controller;

import java.util.List;

import com.commandrobot.commandrobot.model.MoveAction;
import com.commandrobot.commandrobot.model.RobotAction;

public interface RobotController {

    RobotAction create(RobotAction action);

    List<RobotAction> list();

    RobotAction calculate(MoveAction action);

}
