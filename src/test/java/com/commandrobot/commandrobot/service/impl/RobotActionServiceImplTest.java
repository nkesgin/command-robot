package com.commandrobot.commandrobot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commandrobot.commandrobot.model.MoveAction;
import com.commandrobot.commandrobot.model.Robot;
import com.commandrobot.commandrobot.model.RobotAction;
import com.commandrobot.commandrobot.model.enums.ActionType;
import com.commandrobot.commandrobot.model.enums.Direction;
import com.commandrobot.commandrobot.service.RobotActionService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RobotActionServiceImplTest {

    @Autowired RobotActionService robotActionService;

    @Test
    void createRobot() {
        RobotAction robotActionRequest = RobotAction.builder().actionType(ActionType.POSITION).direction(Direction.EAST).x(0).y(0).build();
        RobotAction robotActionResponse = robotActionService.create(robotActionRequest);
        assertSame(robotActionRequest, robotActionResponse);
        assertSame(robotActionService.getRobot().getCurrentDirection(), robotActionRequest.getDirection());
        assertEquals(robotActionService.getRobot().getCurrentX(), robotActionRequest.getX());
        assertEquals(robotActionService.getRobot().getCurrentY(), robotActionRequest.getY());
    }

    @Test
    void calculateNextActionForward() {
        int steps = 2;
        RobotAction robotActionRequest = RobotAction.builder().actionType(ActionType.POSITION).direction(Direction.EAST).x(0).y(0).build();
        robotActionService.create(robotActionRequest);
        MoveAction moveActionRequest = MoveAction.builder().actionType(ActionType.FORWARD).steps(steps).build();
        RobotAction moveActionResponse = robotActionService.calculateNextAction(moveActionRequest);
        assertSame(robotActionService.getRobot().getCurrentDirection(), moveActionResponse.getDirection());
        assertEquals(robotActionService.getRobot().getCurrentX(), moveActionResponse.getX());
        assertEquals(robotActionService.getRobot().getCurrentY(), moveActionResponse.getY());
    }

    @Test
    void calculateNextActionTurnaround() {
        RobotAction robotActionRequest = RobotAction.builder().actionType(ActionType.POSITION).direction(Direction.EAST).x(0).y(0).build();
        robotActionService.create(robotActionRequest);
        MoveAction moveActionRequest = MoveAction.builder().actionType(ActionType.TURNAROUND).build();
        RobotAction moveActionResponse = robotActionService.calculateNextAction(moveActionRequest);
        assertSame(robotActionService.getRobot().getCurrentDirection(), moveActionResponse.getDirection());
        assertEquals(robotActionService.getRobot().getCurrentX(), moveActionResponse.getX());
        assertEquals(robotActionService.getRobot().getCurrentY(), moveActionResponse.getY());
    }

    @Test
    void calculateNextActionWrongInput() {
        RobotAction robotActionRequest = RobotAction.builder().actionType(ActionType.POSITION).direction(Direction.EAST).x(0).y(0).build();
        robotActionService.create(robotActionRequest);
        MoveAction moveActionRequest = MoveAction.builder().actionType(ActionType.POSITION).build();
        assertDoesNotThrow(() ->robotActionService.calculateNextAction(moveActionRequest));
    }
}
