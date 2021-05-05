import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'ide-tile',
  templateUrl: './tile.component.html',
  styleUrls: ['./tile.component.css']
})
export class TileComponent implements OnInit{

  @Input() xCoordination: number;
  @Input() yCoordination: number;

  ngOnInit(): void {
  }

}
