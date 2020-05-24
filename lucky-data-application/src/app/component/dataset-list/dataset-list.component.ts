import { Component, OnInit } from '@angular/core';
import {DatasetService} from "../../service/dataset.service";
import {Dataset} from "../../model/dataset";
import {MatButtonToggleChange} from "@angular/material/button-toggle";

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

  sort(event: MatButtonToggleChange) {
    const value = event.value;
    let orderBy: String;
    switch (value) {
      case "popular":
        orderBy = "downloads desc";
        break;
      case "unpopular":
        orderBy = "downloads asc";
        break;
      case "latest":
        orderBy = "uploadedAt desc";
        break;
      case "oldest":
        orderBy = "uploadedAt asc";
        break;
      case "alphabetical":
        orderBy = "title asc";
        break;
      case "not-alphabetical":
        orderBy = "title desc";
        break;
      default:
        orderBy = "date desc";
        break;
    }
    const params = {
      orderBy: orderBy,
    };
    this.datasetService.getDatasets(params).subscribe(datasets => {
      this.datasets = datasets
    }, (error) => {
      console.log(error);
    });
  }

}
