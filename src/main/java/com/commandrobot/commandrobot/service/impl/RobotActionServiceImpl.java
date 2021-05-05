package com.commandrobot.commandrobot.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.commandrobot.commandrobot.CommandRobotApplication;
import com.commandrobot.commandrobot.model.MoveAction;
import com.commandrobot.commandrobot.model.Robot;
import com.commandrobot.commandrobot.model.RobotAction;
import com.commandrobot.commandrobot.service.RobotActionService;

import static com.commandrobot.commandrobot.model.enums.Direction.*;
import static org.junit.Assert.*;

@Service
public class RobotActionServiceImpl implements RobotActionService {

    private Robot robot;
    private static final Logger logger = LoggerFactory.getLogger(CommandRobotApplication.class);

    @Override
    public RobotAction create(RobotAction action) {
        assertNotNull(action);
        this.robot = Robot.builder().currentX(action.getX()).currentY(action.getY()).currentDirection(action.getDirection()).build();
        return action;
    }

    @Override
    public RobotAction calculateNextAction(MoveAction action) {
        assertNotNull(robot);
        assertNotNull(action);
        RobotAction response = prepareNextAction(action);
        this.robot.addAction(response);
        return response;
    }

    @Override
    public List<RobotAction> list() {
        return this.robot.getActions();
    }

    public Robot getRobot() {
        return robot;
    }

    private RobotAction prepareNextAction(MoveAction action) {
        RobotAction robotAction = null;
        switch (action.getActionType()) {
            case FORWARD:
                robotAction = this.calculateForward(action);
                break;
            case LEFT:
                robotAction = this.calculateTurnLeft(action);
                break;
            case RIGHT:
                robotAction = this.calculateTurnRight(action);
                break;
            case TURNAROUND:
                robotAction = this.calculateTurnaround(action);
                break;
            case WAIT:
                robotAction = this.wait(action);
                break;
            default:
                logger.error("Invalid action type!");
                break;
        }
        updateRobot(robotAction);
        return robotAction;
    }

    private RobotAction calculateTurnaround(MoveAction action) {
        RobotAction robotAction = RobotAction.builder().x(robot.getCurrentX()).y(robot.getCurrentY()).actionType(action.getActionType()).build();
        switch (this.robot.getCurrentDirection()) {
            case WEST:
                robotAction.setDirection(EAST);
                break;
            case SOUTH:
                robotAction.setDirection(NORTH);
                break;
            case NORTH:
                robotAction.setDirection(SOUTH);
                break;
            case EAST:
                robotAction.setDirection(WEST);
                break;
            default:
                logger.error("Invalid direction type!");
                break;
        }
        updateRobot(robotAction);
        return robotAction;
    }

    private RobotAction calculateTurnRight(MoveAction action) {
        RobotAction robotAction = RobotAction.builder().x(robot.getCurrentX()).y(robot.getCurrentY()).actionType(action.getActionType()).build();
        switch (this.robot.getCurrentDirection()) {
            case WEST:
                robotAction.setDirection(NORTH);
                break;
            case SOUTH:
                robotAction.setDirection(WEST);
                break;
            case NORTH:
                robotAction.setDirection(EAST);
                break;
            case EAST:
                robotAction.setDirection(SOUTH);
                break;
            default:
                logger.error("Invalid direction type!");
                break;
        }
        updateRobot(robotAction);
        return robotAction;
    }

    private RobotAction calculateTurnLeft(MoveAction action) {
        RobotAction robotAction = RobotAction.builder().x(robot.getCurrentX()).y(robot.getCurrentY()).actionType(action.getActionType()).build();
        switch (this.robot.getCurrentDirection()) {
            case WEST:
                robotAction.setDirection(SOUTH);
                break;
            case SOUTH:
                robotAction.setDirection(EAST);
                break;
            case NORTH:
                robotAction.setDirection(WEST);
                break;
            case EAST:
                robotAction.setDirection(NORTH);
                break;
            default:
                logger.error("Invalid direction type!");
                break;
        }
        updateRobot(robotAction);
        return robotAction;
    }

    private RobotAction calculateForward(MoveAction action) {
        RobotAction nextAction = RobotAction.builder().x(robot.getCurrentX()).y(robot.getCurrentY()).direction(robot.getCurrentDirection()).actionType(action.getActionType()).build();
        switch (this.robot.getCurrentDirection()) {
            case EAST:
                nextAction.setX(nextAction.getX() + action.getSteps());
                break;
            case NORTH:
                nextAction.setY(nextAction.getY() - action.getSteps());
                break;
            case SOUTH:
                nextAction.setY(nextAction.getY() + action.getSteps());
                break;
            case WEST:
                nextAction.setX(nextAction.getX() - action.getSteps());
                break;
            default:
                logger.error("Invalid direction type!");
                break;
        }
        updateRobot(nextAction);
        return nextAction;
    }

    private RobotAction wait(MoveAction action) {
        return RobotAction.builder().actionType(action.getActionType()).x(this.robot.getCurrentX()).y(this.robot.getCurrentY()).direction(this.robot.getCurrentDirection()).build();
    }

    private void updateRobot(RobotAction robotAction) {
        try {
            this.robot.setCurrentX(robotAction.getX());
            this.robot.setCurrentY(robotAction.getY());
            this.robot.setCurrentDirection(robotAction.getDirection());
        } catch (Exception e) {
            logger.error("Update robot action params not completely filled. Exception message: " + e.getMessage());
        }
    }

}
