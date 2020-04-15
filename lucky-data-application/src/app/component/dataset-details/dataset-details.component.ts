import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {Dataset} from "../../model/dataset";
import {DatasetService} from "../../service/dataset.service";

@Component({
  selector: 'app-dataset-details',
  templateUrl: './dataset-details.component.html',
  styleUrls: ['./dataset-details.component.sass']
})
export class DatasetDetailsComponent implements OnInit {
  dataset: Dataset;

  constructor(private route: ActivatedRoute, private datasetService: DatasetService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.datasetService.getDataset(params.get('datasetId')).subscribe(dataset => this.dataset = dataset);
    });
  }

}
