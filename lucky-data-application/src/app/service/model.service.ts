import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Model} from "../model/model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ModelService {

  constructor(private http: HttpClient) {
  }

  public getModel(modelId: string | bigint): Observable<Model> {
    return this.http.get<Model>(environment.baseUrl + '/model/' + modelId);
  }

  public getModels(params?): Observable<Model[]> {
    return this.http.get<Model[]>(environment.baseUrl + '/model/models', {params: params ? params : {orderBy: "uploadedAt desc"}});
  }

  public downloadFile(modelId: string): Observable<any>{
    return this.http.get(environment.baseUrl + '/model/download/' + modelId, { observe: "response", responseType : "blob"  } );
  }

  public uploadModelByFile(formData: FormData, params) {
    return this.http.post(environment.baseUrl + '/model/upload/file', formData, {params: params});
  }
}
