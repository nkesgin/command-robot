import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Tile} from "../model/tile.model";
import {RobotAction} from "../model/robot-action.model";
import {MoveAction} from "../model/move-action.model";
import {RobotComponent} from "../components/robot/robot.component";
import {ActionType} from "../model/enums/action-type.enum";
import {Subscription} from "rxjs";
import {error} from "util";

@Injectable()
export class ActionService {

  private readonly actionUrl: string;

  tiles: Tile[][];
  robot: RobotComponent;

  constructor(private http: HttpClient) {
    this.actionUrl = 'http://localhost:8080';
  }

  public create(createRobotScript: RobotAction) {
    return this.http.post<RobotAction>(this.actionUrl, createRobotScript).toPromise().then(value => {
      this.renderAction(value);
      return;
    });
  }

  public calculateAndRender(action: MoveAction) {
    return this.http.post<RobotAction>(this.actionUrl + '/actions', action).toPromise().then(value => {
      if (value.x >= this.tiles.length || value.y >= this.tiles[0].length || value.x < 0 || value.y < 0) {
        console.log(`This action causes map area violation!(x: ${value.x} y: ${value.y})`);
        return;
      } else {
        this.renderAction(value);
        return;
      }
    });
  }

  public list(): Subscription {
    return this.http.get<RobotAction[]>(this.actionUrl + '/actions').subscribe(value => value);
  }

  public renderAction(action: RobotAction): void {
    switch (action.actionType) {
      case ActionType.POSITION:
        this.robot.appear(action);
        break;
      case ActionType.FORWARD:
        this.robot.forward(action);
        break;
      case ActionType.LEFT:
        this.robot.turnLeft(action);
        break;
      case ActionType.RIGHT:
        this.robot.turnRight(action);
        break;
      case ActionType.TURNAROUND:
        this.robot.turnaround(action);
        break;
      case ActionType.WAIT:
        this.robot.wait(action);
        break;
      default:
        throw error('Undefined action type!');
    }
  }

}
