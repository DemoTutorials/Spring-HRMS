import { Injectable } from '@angular/core';
import { EmergencyContacts } from '../models/emergency-contacts.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ApiResponse } from '../models/response';
import { ToastrService } from 'ngx-toastr';
import { ColumnsMetadata } from '../models/columns-metadata';

@Injectable()
export class EmergencyContactsService {
  private dataSubject = new BehaviorSubject<string>('');
  public data$ = this.dataSubject.asObservable();

  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  getEmergencyContactHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/emergency-contact'
    );
  }

  AddEmergencyContact(
    contact: EmergencyContacts,
    id: number
  ): Observable<EmergencyContacts> {
    console.log(id);
    return this.http.post<EmergencyContacts>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/contact/' + id + '/add',
      contact
    );
  }

  updateEmergencyContact(
    contact: EmergencyContacts,
    id: number
  ): Observable<EmergencyContacts> {
    return this.http.put<EmergencyContacts>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/contact/' + id + '/update',
      contact
    );
  }

  deleteEmergencyContact(
    contactId: number,
    id: number
  ): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/contact/${id}/${contactId}?updatedBy=Admin`
    );
  }

  search(
    params: HttpParams,
    id: number
  ): Observable<{ content: Array<EmergencyContacts>; totalElements: number }> {
    return this.http.get<{
      content: Array<EmergencyContacts>;
      totalElements: number;
    }>(`https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/contact/${id}/search`, {
      params: params,
    });
  }

  getByEmployeeId(
    employeeId: number,
    contactId: number
  ): Observable<EmergencyContacts> {
    return this.http.get<EmergencyContacts>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/contact/${employeeId}/${contactId}`
    );
  }

  notify(message: string) {
    this.toastrService.success(message);
  }

  warn(message: string) {
    this.toastrService.warning(message);
  }

  sendData(data: string) {
    console.log(data);
    this.dataSubject.next(data);
  }
}
