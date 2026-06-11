import { Injectable } from '@angular/core';
import { Certification } from '../models/certification.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { IndividualConfig, ToastrService } from 'ngx-toastr';
import { ColumnsMetadata } from '../models/columns-metadata';
import { ApiResponse } from '../models/response';

@Injectable({
  providedIn: 'root',
})
export class CertificationService {
  createCertifications(
    certification: Certification,
    employeeId: number
  ): Observable<Certification> {
    return this.http.post<Certification>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/certification/${employeeId}/add`,
      certification
    );
  }

  updateCertification(certification: Certification, employeeId: number) {
    return this.http.put<Certification>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/certification/${employeeId}/update`,
      certification
    );
  }
  private dataSubject = new BehaviorSubject<string>('');
  public data$ = this.dataSubject.asObservable();

  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  getCertifications(employeeId: number): Observable<Array<Certification>> {
    return this.http.get<Array<Certification>>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/education/${employeeId}/get-all`
    );
  }

  getCertificationsHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/certification'
    );
  }

  getByEmployeeId(
    employeeId: number,
    certificationId: number
  ): Observable<Certification> {
    return this.http.get<Certification>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/certification/${employeeId}/${certificationId}`
    );
  }

  deleteCertifications(
    certificationsId: number,
    employeeId: number
  ): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/certification/${employeeId}/${certificationsId}?updatedBy='Admin'`
    );
  }
  search(
    params: HttpParams,
    employeeId: number
  ): Observable<{
    content: Array<Certification>;
    totalElements: number;
  }> {
    return this.http.get<{
      content: Array<Certification>;
      totalElements: number;
    }>(`https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/certification/${employeeId}/search`, {
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
