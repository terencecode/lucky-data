import { Component, OnInit } from '@angular/core';
import {ModelService} from "../../service/model.service";
import {Model} from "../../model/model";
import {MatButtonToggleChange} from "@angular/material/button-toggle";

@Component({
  selector: 'app-model-list',
  templateUrl: './model-list.component.html',
  styleUrls: ['./model-list.component.sass']
})
export class ModelListComponent implements OnInit {

  models: Model[];

  constructor(private modelService: ModelService) { }

  ngOnInit(): void {
    this.modelService.getModels().subscribe(models => this.models = models);
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
    this.modelService.getModels(params).subscribe(models => {
      this.models = models
    }, (error) => {
      console.log(error);
    });
  }

}
