import {Component, Input} from '@angular/core';
import {Tile} from "../../model/tile.model";

@Component({
  selector: 'ide-map',
  templateUrl: './game-map.component.html',
  styleUrls: ['./game-map.component.css']
})
export class GameMapComponent {

  @Input() tiles: Tile[][];

}
