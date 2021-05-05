import {AfterContentInit, AfterViewInit, ChangeDetectorRef, Component, ViewChild} from '@angular/core';
import {Tile} from "./model/tile.model";
import {ActionService} from "./service/action.service";
import {RobotAction} from "./model/robot-action.model";
import {ActionType} from "./model/enums/action-type.enum";
import {Direction} from "./model/enums/direction.enum";
import {RobotComponent} from "./components/robot/robot.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterContentInit, AfterViewInit {

  public initialRobotScript: RobotAction = {actionType: ActionType.POSITION, x: 1, y: 3, direction: Direction.EAST};

  tiles: Tile[][]

  @ViewChild('robot') robot: RobotComponent;

  constructor(private readonly actionService: ActionService,
              private changeDetectorRef: ChangeDetectorRef) {
  }

  ngAfterContentInit(): void {
    this.createMap(5, 5);
    this.initializeRobot();
  }

  ngAfterViewInit() {
    this.changeDetectorRef.detectChanges();
  }

  createMap(row: number, col: number): void {
    this.tiles = new Array(col);
    for (let i = 0; i < col; i++) {
      this.tiles[i] = new Array(row);
    }
    for (let i = 0; i < col; i++) {
      for (let j = 0; j < row; j++) {
        this.tiles[i][j] = {x: i, y: j};
      }
    }
    this.actionService.tiles = this.tiles;
  }

  private initializeRobot() {
    this.actionService.robot = this.robot;
    this.actionService.create(this.initialRobotScript).then(() => this.renderActionScript());
  }

  private renderActionScript() {
    this.actionService.calculateAndRender({actionType: ActionType.FORWARD, steps: 3}).then(() =>
      this.actionService.calculateAndRender({actionType: ActionType.WAIT, steps: 0}).then(() =>
        this.actionService.calculateAndRender({actionType: ActionType.TURNAROUND, steps: 0}).then(() =>
          this.actionService.calculateAndRender({actionType: ActionType.FORWARD, steps: 1}).then(() =>
            this.actionService.calculateAndRender({actionType: ActionType.RIGHT, steps: 0}).then(() =>
              this.actionService.calculateAndRender({actionType: ActionType.FORWARD, steps: 2})
            ))))
    );
  }
}
