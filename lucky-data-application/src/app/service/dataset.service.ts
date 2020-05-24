import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from "rxjs";
import {environment} from "../../environments/environment";
import {Dataset} from "../model/dataset";


@Injectable({
  providedIn: 'root'
})
export class DatasetService {


  constructor(private http: HttpClient) {
  }

  public getDataset(datasetId: string | bigint): Observable<Dataset> {
    return this.http.get<Dataset>(environment.baseUrl + '/dataset/' + datasetId);
  }

  public getDatasets(params?): Observable<Dataset[]> {
    return this.http.get<Dataset[]>(environment.baseUrl + '/dataset/datasets', {params: params ? params : {orderBy: "uploadedAt desc"}});
  }

  public downloadFile(datasetId: string): Observable<any>{
    return this.http.get(environment.baseUrl + '/dataset/download/' + datasetId, { responseType : "blob"  } );
  }

  public uploadDataset(formData: FormData, params) {
    return this.http.post(environment.baseUrl + '/dataset/upload', formData, {params: params});
  }
}
