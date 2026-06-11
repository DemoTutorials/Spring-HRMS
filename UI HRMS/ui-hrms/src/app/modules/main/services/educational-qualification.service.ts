import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IndividualConfig, ToastrService } from 'ngx-toastr';
import { BehaviorSubject, Observable } from 'rxjs';
import { EducationalQualification } from '../models/educational-qualification.model';
import { ColumnsMetadata } from '../models/columns-metadata';
import { ApiResponse } from '../models/response';
import { CommonMaster } from '../models/common-master.model';

@Injectable({
  providedIn: 'root',
})
export class EducationalQualificationService {
  createEducationalQualification(
    educationalQualification: EducationalQualification,
    employeeId: number
  ): Observable<EducationalQualification> {
    return this.http.post<EducationalQualification>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/education/${employeeId}/add`,
      educationalQualification
    );
  }

  updateEducationalQualification(
    educationalQualification: EducationalQualification,
    employeeId: number
  ) {
    return this.http.put<EducationalQualification>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/education/${employeeId}/update`,
      educationalQualification
    );
  }
  private dataSubject = new BehaviorSubject<string>('');
  public data$ = this.dataSubject.asObservable();

  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  getEducationalQualifications(
    employeeId: number
  ): Observable<Array<EducationalQualification>> {
    return this.http.get<Array<EducationalQualification>>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/education/${employeeId}/get-all`
    );
  }

  getQualificationLevels(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Qualification Level?sort=priority,code`
    );
  }

  getEducationalQualificationHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/educational-qualification'
    );
  }

  getByEmployeeId(
    employeeId: number,
    educationalId: number
  ): Observable<EducationalQualification> {
    return this.http.get<EducationalQualification>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/education/${employeeId}/${educationalId}`
    );
  }

  deleteEducationalQualification(
    EducationalQualificationId: number,
    employeeId: number
  ): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/education/${employeeId}/${EducationalQualificationId}?updatedBy='Admin'`
    );
  }
  search(
    params: HttpParams,
    employeeId: number
  ): Observable<{
    content: Array<EducationalQualification>;
    totalElements: number;
  }> {
    return this.http.get<{
      content: Array<EducationalQualification>;
      totalElements: number;
    }>(`https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/education/${employeeId}/search`, {
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

  sendData(data: string) {
    console.log(data);
    this.dataSubject.next(data);
  }
}
