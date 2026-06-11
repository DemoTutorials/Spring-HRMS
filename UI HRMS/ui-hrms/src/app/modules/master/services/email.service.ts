import { HttpClient,HttpHeaders,HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IndividualConfig, ToastrService } from 'ngx-toastr';
import { BehaviorSubject, Observable } from 'rxjs';
import { Email } from '../models/email';
import { ColumnsMetadata } from '../models/columnMetaData';
import { ApiResponse } from '../models/response';
@Injectable()
export class EmailService {

  constructor(private http: HttpClient,private toastrService: ToastrService) { }
  private dataSubject = new BehaviorSubject<string>('');
  public data$ = this.dataSubject.asObservable();

  getEmailHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/data-table-metadata/email-info'
    );
  }

  search(
    params: HttpParams
  ): Observable<{ content: Array<Email>; totalElements: number }> {
    return this.http.get<{ content: Array<Email>; totalElements: number }>(
      'https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/email/get-all',
      {
        params: params,
      }
    );
  }

  searchEmailById(id: string): Observable<Email> {
    return this.http.get<Email>(
      'https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/email/get?id=' + id
    );
  }
      createEmail(formData:FormData): Observable<any> {
 
        const httpOptions = {
          headers: new HttpHeaders({
           "Content-Type": "multipart/form-data,application/json",
           "Access-Control-Allow-Origin": "*" 
          })
        };
  
    return this.http.post('https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/email/send', formData);
   
  }

  notify(message: string) {
    const toastrConfig: Partial<IndividualConfig> = {
      timeOut: 2500,
    };
    this.toastrService.success(message, '', toastrConfig);
  }

  warn(message: string) {
    const toastrConfig: Partial<IndividualConfig> = {
      timeOut: 2500,
    };
    this.toastrService.warning(message, '', toastrConfig);
  }

  sendData(data: string) {
    this.dataSubject.next(data);
  }
}
