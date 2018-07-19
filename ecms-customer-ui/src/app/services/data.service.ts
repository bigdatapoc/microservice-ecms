import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class DataService {

  constructor(private http: HttpClient) { }

  private domain = environment.apiPath;
  public uploadObservable = new BehaviorSubject<Boolean>(false);
  
  isFileUploaded = this.uploadObservable.asObservable();

  updateFileUploadStatus(status) {
    this.uploadObservable.next(status);
  }

  saveContent(data): Observable<any>{

    return this.http.post(this.domain + '/saveContent', data)
                    .pipe(
                        catchError((error: any) => Observable.throw(error.json().error || 'server error'))
                    );
  }

}
