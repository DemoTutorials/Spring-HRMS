import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IndividualConfig, ToastrService } from 'ngx-toastr';
import { Parent } from '../models/parent';
import { ColumnsMetadata } from '../models/columnMetaData';
import { ApiResponse } from '../models/response';

@Injectable()
export class ChildService {
  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  getChilds(masterName: string): Observable<Array<Parent>> {
    return this.http.get<Array<Parent>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/masterName/search-childs'
    );
  }

  getChildHeaders(): Observable<{ columnsMetadata: Array<ColumnsMetadata> }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/child-common-master'
    );
  }

  createChild(data: Parent): Observable<Parent> {
    return this.http.post<Parent>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/create',
      data
    );
  }
  searchChildById(id: number): Observable<Parent> {
    return this.http.get<Parent>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/get/' + id
    );
  }

  deleteChild(childId: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/' +
        childId +
        '?updatedBy=Admin'
    );
  }

  search(
    masterName: string,
    params: HttpParams
  ): Observable<{ content: Array<Parent>; totalElements: number }> {
    const url = `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/${masterName}/search-childs?sort=priority,code`;

    return this.http.get<{ content: Array<Parent>; totalElements: number }>(
      url,
      {
        params: params,
      }
    );
  }

  updateChild(data: Parent): Observable<Parent> {
    console.log(data);
    return this.http.put<Parent>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/update',
      data
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
