import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ColumnsMetadata } from '../models/columnMetaData';
import { Organization } from '../models/organization.model';
import { IndividualConfig, ToastrService } from 'ngx-toastr';
@Injectable({
  providedIn: 'root',
})
export class OrganizationService {
  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  ngOnInit(): void {}

  getOrganizations(): Observable<Array<Organization>> {
    return this.http.get<Array<Organization>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/organization/get-all'
    );
  }

  getOrganizationsHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/organization'
    );
  }

  createOrganization(data: Organization): Observable<Array<Organization>> {
    console.log('in create service', data);

    return this.http.post<Array<Organization>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/organization/create',
      data
    );
  }

  searchOrganizationByCode(orgCode: string): Observable<Organization> {
    return this.http.get<Organization>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/organization/' + orgCode
    );
  }

  updateOrganization(data: Organization): Observable<Array<Organization>> {
    return this.http.put<Array<Organization>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/organization/update',
      data
    );
  }

  deleteOrganization(orgCode: string): Observable<string> {
    return this.http.delete<string>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/organization/' +
        orgCode +
        '?updatedBy=Admin'
    );
  }

  search(
    params: HttpParams
  ): Observable<{ content: Array<Organization>; totalElements: number }> {
    return this.http.get<{
      content: Array<Organization>;
      totalElements: number;
    }>('https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/organization/search', {
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
