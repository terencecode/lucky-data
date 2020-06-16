import { Component, OnInit } from '@angular/core';
import {Model} from "../../model/model";
import {ActivatedRoute} from "@angular/router";
import {ModelService} from "../../service/model.service";

@Component({
  selector: 'app-model-details',
  templateUrl: './model-details.component.html',
  styleUrls: ['./model-details.component.sass']
})
export class ModelDetailsComponent implements OnInit {
  model: Model;
  title = 'File download';

  constructor(private route: ActivatedRoute, private modelService: ModelService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.modelService.getModel(params.get('modelId')).subscribe(model => this.model = model);
    });
  }
  download() {
    this.modelService.downloadFile(this.model.id.toString()).subscribe(
      response => {
        let url = window.URL.createObjectURL(new Blob([response.body], {type: this.model.contentType}));
        const a = document.createElement('a');
        a.href = url
        let contentDisposition = response.headers.get('content-disposition');
        console.log(contentDisposition);
        let filename = contentDisposition.split(';')[1].split('filename')[1].split('=')[1].trim().replace(/\"/g, '');
        console.log(filename);
        a.download = filename;
        console.log(a);
        a.click();
        this.fetchModel(this.model.id);
      },
      error => console.log('Error downloading the file'),
      () => console.log('File downloaded successfully'));
  }

  fetchModel(modelId: bigint) {
    this.modelService.getModel(modelId).subscribe(model => this.model = model);
  }

}
