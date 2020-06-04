import { Component, OnInit } from '@angular/core';
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
    this.datasetService.downloadFile(this.dataset.id.toString()).subscribe(
      response => {
        let url = window.URL.createObjectURL(new Blob([response.body], {type: this.dataset.contentType}));
        const a = document.createElement('a');
        a.href = url
        let contentDisposition = response.headers.get('content-disposition');
        console.log(contentDisposition);
        let filename = contentDisposition.split(';')[1].split('filename')[1].split('=')[1].trim().replace(/\"/g, '');
        console.log(filename);
        a.download = filename;
        console.log(a);
        a.click();
        this.fetchDataset(this.dataset.id);
      },
        error => console.log('Error downloading the file'),
      () => console.log('File downloaded successfully'));
  }

  fetchDataset(datasetId: bigint) {
    this.datasetService.getDataset(datasetId).subscribe(dataset => this.dataset = dataset);
  }
}
