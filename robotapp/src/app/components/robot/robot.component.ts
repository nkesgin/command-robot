import {Component, Input} from '@angular/core';
import {animate, query, sequence, stagger, state, style, transition, trigger} from "@angular/animations";
import {Tile} from "../../model/tile.model";
import {Direction} from "../../model/enums/direction.enum";
import {RobotAction} from "../../model/robot-action.model";
import {error} from "util";

@Component({
  selector: 'ide-robot',
  templateUrl: './robot.component.html',
  styleUrls: ['./robot.component.css'],
  animations: [
    trigger('actionState', [
      state('*', style({opacity: 1, left: '{{x}}', top: '{{y}}', transform: '{{degree}}'}), {params: {x: '0px', y: '0px', degree: 'rotate(0deg)'}}),
      transition('* => *',
        query(":self", [
          sequence([ //todo solve missing animation sequences issue
            stagger('1000ms 1000ms', animate('1000ms 1000ms'))
          ])
        ]))
    ])
  ]
})
export class RobotComponent {
  state: string = 'default';
  degreeVal = 0;
  robotSize = 100;

  @Input() currentTile: Tile;
  @Input() currentDirection: Direction;

  appear(action: RobotAction): void {
    this.updateRobot(action);
  }

  forward(action: RobotAction): void {
    this.updateRobot(action);
    this.state = 'forward';
  }

  turnaround(action: RobotAction): void {
    this.updateRobot(action);
    this.state = 'turnaround';
  }

  wait(action: RobotAction): void {
    this.updateRobot(action);
    this.state = 'wait';
  }

  turnLeft(action: RobotAction): void {
    this.updateRobot(action);
    this.state = 'turnLeft';
  }

  turnRight(action: RobotAction): void {
    this.updateRobot(action);
    this.state = 'turnRight';
  }

  private updateRobot(action: RobotAction) {
    this.currentDirection = action.direction;
    this.currentTile = {x: action.x, y: action.y};
    this.degreeVal = this.getDegree(action.direction);
  }

  public getDegree(direction: Direction): number {
    switch (direction) {
      case Direction.EAST:
        return 90;
      case Direction.NORTH:
        return 180;
      case Direction.SOUTH:
        return 0;
      case Direction.WEST:
        return -90;
      default:
        throw error('Undefined direction type!');
    }
  }
}
