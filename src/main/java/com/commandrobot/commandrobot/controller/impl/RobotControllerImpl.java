package com.commandrobot.commandrobot.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commandrobot.commandrobot.controller.RobotController;
import com.commandrobot.commandrobot.model.MoveAction;
import com.commandrobot.commandrobot.model.RobotAction;
import com.commandrobot.commandrobot.service.RobotActionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/")
public class RobotControllerImpl implements RobotController {

    private final RobotActionService robotActionService;

    @Autowired
    public RobotControllerImpl(RobotActionService robotActionService) {
        this.robotActionService = robotActionService;
    }

    @PostMapping(path = "/")
    public RobotAction create(@RequestBody RobotAction action) {
        return robotActionService.create(action);
    }

    @PostMapping(path = "/actions")
    public RobotAction calculate(@RequestBody MoveAction action) {
        return robotActionService.calculateNextAction(action);
    }

    @GetMapping(path = "/actions")
    public List<RobotAction> list() {
        return robotActionService.list();
    }

}
