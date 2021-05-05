package com.commandrobot.commandrobot.model;

import com.commandrobot.commandrobot.model.enums.ActionType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Action {
    private final ActionType actionType;

}
