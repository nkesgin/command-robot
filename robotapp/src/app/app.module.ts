import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {TileComponent} from "./components/tile/tile.component";
import {GameMapComponent} from "./components/game-map/game-map.component";
import {RobotComponent} from "./components/robot/robot.component";
import {ActionService} from "./service/action.service";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";


@NgModule({
  declarations: [
    AppComponent,
    GameMapComponent,
    TileComponent,
    RobotComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    RouterModule
  ],
  providers: [ActionService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
