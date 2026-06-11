import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ColumnsMetadata } from '../models/columnMetaData';
import { Designation } from '../models/designation.model';
import { ApiResponse } from '../models/response';
import { IndividualConfig, ToastrService } from 'ngx-toastr';
@Injectable()
export class DesignationService {
  constructor(private http: HttpClient, private toastrService: ToastrService) { }

  ngOnInit(): void { }

  getDesignations(): Observable<Array<Designation>> {
    return this.http.get<Array<Designation>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/designation/get-all'

    );

  }

  getDesignationHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/designation'
    );
  }

  createDesignation(data: Designation): Observable<Array<Designation>> {
    console.log('in create service', data);

    return this.http.post<Array<Designation>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/designation/create',
      data
    );
  }

  searchDesignationById(id: string): Observable<Designation> {
    return this.http.get<Designation>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/designation/' + id
    );
  }

  updateDesignation(data: Designation): Observable<Array<Designation>> {
    console.log(data);
    return this.http.put<Array<Designation>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/designation/update',
      data
    );
  }

  deleteDesignation(designationId: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/designation/' +
      designationId +
      '?updatedBy=Admin'
    );
  }

  search(
    params: HttpParams
  ): Observable<{ content: Array<Designation>; totalElements: number }> {
    return this.http.get<{
      content: Array<Designation>;
      totalElements: number;
    }>('https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/designation/search', {
      params: params,
    });
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
}
