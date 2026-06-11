import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IndividualConfig, ToastrService } from 'ngx-toastr';
import { Parent } from '../models/parent';
import { ColumnsMetadata } from '../models/columnMetaData';
import { ApiResponse } from '../models/response';

@Injectable()
export class ParentService {
  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  getParents(): Observable<Array<Parent>> {
    return this.http.get<Array<Parent>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/search-parents'
    );
  }

  getParentsHeaders(): Observable<{ columnsMetadata: Array<ColumnsMetadata> }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/parent-common-master'
    );
  }

  createParent(data: Parent): Observable<Parent> {
    return this.http.post<Parent>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/create',
      data
    );
  }
  // Needs to modify
  searchParentById(id: number): Observable<Parent> {
    return this.http.get<Parent>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/get/' + id
    );
  }

  deleteParent(parentId: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/' +
        parentId +
        '?updatedBy=Admin'
    );
  }

  search(
    params: HttpParams
  ): Observable<{ content: Array<Parent>; totalElements: number }> {
    return this.http.get<{ content: Array<Parent>; totalElements: number }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/search-parents',
      {
        params: params,
      }
    );
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
