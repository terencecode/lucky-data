import {Component, Input, OnInit} from '@angular/core';
import {Model} from "../../model/model";

@Component({
  selector: 'app-model-tile',
  templateUrl: './model-tile.component.html',
  styleUrls: ['./model-tile.component.sass']
})
export class ModelTileComponent implements OnInit {

  @Input() model: Model;
  constructor() { }

  ngOnInit(): void {
  }

}
