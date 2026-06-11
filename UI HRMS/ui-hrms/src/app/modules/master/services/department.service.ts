import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Department } from '../models/department.model';
import { ColumnsMetadata } from '../models/columnMetaData';
import { ApiResponse } from '../models/response';
import { IndividualConfig, ToastrService } from 'ngx-toastr';
@Injectable()
export class DepartmentService {
  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  private dataSubject = new BehaviorSubject<string>('');
  public data$ = this.dataSubject.asObservable();

  createDepartment(department: Department): Observable<Department> {
    return this.http.post<Department>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/departments/add-department',
      department
    );
  }

  getDepartments(): Observable<Array<Department>> {
    return this.http.get<Array<Department>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/departments/allDepartments'
    );
  }

  getDepartmentHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/department'
    );
  }

  searchDepartmentById(departmentId: string): Observable<Department> {
    return this.http.get<Department>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/departments/' + departmentId
    );
  }

  updateDepartment(departmentId: string): Observable<Department> {
    return this.http.put<Department>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/departments/update',
      departmentId
    );
  }

  deleteDepartment(departmentId: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/departments/' +
        departmentId +
        '?updatedBy=Admin'
    );
  }
  search(
    params: HttpParams
  ): Observable<{ content: Array<Department>; totalElements: number }> {
    return this.http.get<{ content: Array<Department>; totalElements: number }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/departments/search',
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

  sendData(data: string) {
    this.dataSubject.next(data);
  }
}
