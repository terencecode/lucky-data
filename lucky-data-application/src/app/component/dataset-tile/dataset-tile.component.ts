import {Component, Input, OnInit} from '@angular/core';
import {Dataset} from "../../model/dataset";

@Component({
  selector: 'app-dataset-tile',
  templateUrl: './dataset-tile.component.html',
  styleUrls: ['./dataset-tile.component.sass']
})
export class DatasetTileComponent implements OnInit {

  @Input() dataset: Dataset;
  constructor() { }

  ngOnInit(): void {
  }

}
