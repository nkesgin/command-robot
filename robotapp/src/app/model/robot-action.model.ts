import {Action} from "./action.model";
import {Direction} from "./enums/direction.enum";

export class RobotAction extends Action {
  x: number;
  y: number;
  direction: Direction;
}
