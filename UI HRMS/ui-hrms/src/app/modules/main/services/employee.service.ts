import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject, Observable } from 'rxjs';
import { ColumnsMetadata } from '../models/columns-metadata';
import { Employees } from '../models/employee.model';
import { Visa } from 'src/app/modules/main/models/visa.model';
import { ApiResponse } from '../models/response';
import { Title } from '@angular/platform-browser';
import { CommonMaster } from '../models/common-master.model';
import { EmployeePersonalDetails } from '../models/employee-personal-details';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  // notify(arg0: string) {
  //   throw new Error('Method not implemented.');
  // }
  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  private dataSubject = new BehaviorSubject<string>('');
  public data$ = this.dataSubject.asObservable();

  createEmployee(employee: Employees): Observable<Employees> {
    console.log(employee);
    return this.http.post<Employees>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/add',
      employee
    );
  }

  AddPersonalDetails(
    employee: EmployeePersonalDetails,
    emp_id: number
  ): Observable<EmployeePersonalDetails> {
    return this.http.post<EmployeePersonalDetails>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/personal-details/create/' + emp_id,
      employee
    );
  }

  AddVisaDetails(visa: Visa, emp_id: number): Observable<Visa> {
    //visa: Visa
    return this.http.post<Visa>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/visa/' + emp_id + '/add',
      visa
    );
  }

  updateEmployee(Id: string): Observable<Employees> {
    return this.http.put<Employees>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/update',
      Id
    );
  }

  updateEmployeevisa(Id: string, emp_id: number): Observable<Visa> {
    console.log('visaaaid', Id);
    return this.http.put<Visa>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/visa/' + emp_id + '/update',
      Id
    );
  }

  updateEmployeePersonalDetails(
    Id: string,
    emp_id: number
  ): Observable<EmployeePersonalDetails> {
    console.log('visaaaid', Id);
    return this.http.put<EmployeePersonalDetails>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/personal-details/update/' + emp_id,
      Id
    );
  }

  searchEmployeeById(Id: string): Observable<Employees> {
    return this.http.get<Employees>('https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/' + Id);
  }

  getEmployeeHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/employee'
    );
  }

  getEmployeeVisaHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/visa'
    );
  }

  search(
    params: HttpParams
  ): Observable<{ content: Array<Employees>; totalElements: number }> {
    return this.http.get<{ content: Array<Employees>; totalElements: number }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/search',
      {
        params: params,
      }
    );
  }

  searchVisa(
    params: HttpParams,
    id: number
  ): Observable<{ content: Array<Visa>; totalElements: number }> {
    return this.http.get<{ content: Array<Visa>; totalElements: number }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/visa/' + id + '/search',
      {
        params: params,
      }
    );
  }

  searchVisaById(emp_id: string, visaid: number): Observable<Visa> {
    return this.http.get<Visa>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/visa/' + emp_id + '/' + visaid
    );
  }

  searchPersonalDetailsById(
    emp_id: string
  ): Observable<EmployeePersonalDetails> {
    return this.http.get<EmployeePersonalDetails>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/personal-details/' + emp_id
    );
  }

  deleteEmployee(employeeId: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/' + employeeId + '?updatedBy=Admin'
    );
  }

  deleteEmployeeVisa(visa_id: any, employeeId: any): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/visa/' +
        employeeId +
        '/' +
        visa_id +
        '?updatedBy=Admin'
    );
  }

  notify(message: string) {
    this.toastrService.success(message);
  }

  warn(message: string) {
    this.toastrService.warning(message);
  }

  sendData(data: string) {
    this.dataSubject.next(data);
  }

  getTitle(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Title?sort=priority,code'
    );
  }

  getGender(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Gender?sort=priority,code'
    );
  }

  getAllEmployee(): Observable<Array<Employees>> {
    return this.http.get<Array<Employees>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/get-all'
    );
  }

  getMaritalStatus(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Marital Status?sort=priority,code'
    );
  }

  getBloodGroup(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Blood Group?sort=priority,code'
    );
  }

  getCountryCode(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Country?sort=priority,code'
    );
  }

  getStatus(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Status?sort=priority,code'
    );
  }

  deleteProfileImage(employeeId: number): Observable<Employees> {
    return this.http.delete<Employees>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/delete/image/' + employeeId
    );
  }
}
