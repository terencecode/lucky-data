import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {DatasetService} from "../../service/dataset.service";
import {Dataset} from "../../model/dataset";

@Component({
  selector: 'app-dataset-details',
  templateUrl: './dataset-details.component.html',
  styleUrls: ['./dataset-details.component.sass']
})
export class DatasetDetailsComponent implements OnInit {
  dataset: Dataset;
  title = 'File download';

  constructor(private route: ActivatedRoute, private datasetService: DatasetService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.datasetService.getDataset(params.get('datasetId')).subscribe(dataset => this.dataset = dataset);
    });
  }
  download() {
    this.datasetService.downloadFile('1').subscribe(
      response => {
        window.open(window.URL.createObjectURL(response))
        this.fetchDataset(this.dataset.id);
      },
        error => console.log('Error downloading the file'),
      () => console.log('File downloaded successfully'));
  }

  fetchDataset(datasetId: bigint) {
    this.datasetService.getDataset(datasetId).subscribe(dataset => this.dataset = dataset);
  }
}
