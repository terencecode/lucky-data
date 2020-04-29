import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from "rxjs";
import {environment} from "../../environments/environment";
import {Dataset} from "../model/dataset";
import {HttpResponse} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class DatasetService {


  constructor(private http: HttpClient) {
  }

  public getDataset(datasetId: string): Observable<Dataset> {
    return this.http.get<Dataset>(environment.baseUrl + '/dataset/' + datasetId);
  }

  public getDatasets(): Observable<Dataset[]> {
    return this.http.get<Dataset[]>(environment.baseUrl + '/dataset/datasets');
  }

  public downloadFile(datasetId: string): Observable<any>{
    return this.http.get(environment.baseUrl + '/dataset/download/' + datasetId , { responseType : "blob"  } );
  }
}
