import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Role } from '../models/role.model';
import { ColumnsMetadata } from '../models/columnMetaData';
import { ApiResponse } from '../models/response';
import { IndividualConfig, ToastrService } from 'ngx-toastr';

@Injectable()
export class RoleService {
  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  getRoles(): Observable<Array<Role>> {
    return this.http.get<Array<Role>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/role/all-roles'
    );
  }

  getRolesHeaders(): Observable<{ columnsMetadata: Array<ColumnsMetadata> }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/role'
    );
  }

  createRole(data: Role): Observable<Array<Role>> {
    console.log('in create service', data);

    return this.http.post<Array<Role>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/role/create',
      data
    );
  }

  searchRoleById(id: string): Observable<Role> {
    return this.http.get<Role>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/role/search/' + id
    );
  }

  updateRole(data: Role): Observable<Array<Role>> {
    console.log(data);
    return this.http.put<Array<Role>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/role/update',
      data
    );
  }

  deleteRole(roleId: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/role/delete/' +
        roleId +
        '?updatedBy=Admin'
    );
  }

  search(
    params: HttpParams
  ): Observable<{ content: Array<Role>; totalElements: number }> {
    return this.http.get<{ content: Array<Role>; totalElements: number }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/role/search',
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
