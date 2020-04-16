import { Component, OnInit } from '@angular/core';
import {DatasetService} from "../../service/dataset.service";
import {Dataset} from "../../model/dataset";

@Component({
  selector: 'app-dataset-list',
  templateUrl: './dataset-list.component.html',
  styleUrls: ['./dataset-list.component.sass']
})
export class DatasetListComponent implements OnInit {

  datasets: Dataset[];

  constructor(private datasetService: DatasetService) { }

  ngOnInit(): void {
    this.datasetService.getDatasets().subscribe(datasets => this.datasets = datasets);
  }

}
