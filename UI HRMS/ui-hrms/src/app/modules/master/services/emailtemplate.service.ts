import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmailTemplate } from '../models/emailTemplate';
import { BehaviorSubject, Observable } from 'rxjs';
import {IndividualConfig, ToastrService } from 'ngx-toastr';
import { ColumnsMetadata } from '../models/columnMetaData';
import { ApiResponse } from '../models/response';

@Injectable()
export class EmailtemplateService {

  constructor(private http: HttpClient,private toastrService: ToastrService) { }
private dataSubject = new BehaviorSubject<string>('');
  public data$ = this.dataSubject.asObservable();
  createEmailTemplate(emailtemplate:EmailTemplate): Observable<EmailTemplate> {
    return this.http.post<EmailTemplate>(
      'https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/template/create',
      emailtemplate
    );
  }

  // createEmailTemplate(emailtemplate:EmailTemplate): Observable<EmailTemplate> {
  //   return this.http.post<EmailTemplate>(
  //     'https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/email/schedule',
  //     emailtemplate
  //   );
  // }

  getEmailTemplateHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/data-table-metadata/template-info'
    );
  }

  searchEmailTemplateById(id: number): Observable<EmailTemplate> {
    return this.http.get<EmailTemplate>(
      'https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/template/' + id
    );
  }

  updateEmailTemplateById(id: number): Observable<EmailTemplate> {
    return this.http.put<EmailTemplate>(
      'https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/template/update',
      id
    );
  }

  search(
    params: HttpParams
  ): Observable<{ content: Array<EmailTemplate>; totalElements: number }> {
    return this.http.get<{ content: Array<EmailTemplate>; totalElements: number }>(
      'https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/template/get-all',
      {
        params: params,
      }
    );
  }

  deleteEmailTemplate(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/template/' +
        id +
        '?updatedBy=Admin'
    );
  }
  //https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/template/get-all
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
