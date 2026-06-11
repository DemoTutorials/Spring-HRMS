import { HttpClient, HttpParams } from '@angular/common/http';

import { Injectable } from '@angular/core';

import { ToastrService } from 'ngx-toastr';

import { BehaviorSubject, Observable } from 'rxjs';

import { JoiningDetails } from '../models/joining-details.model';

import { ApiResponse } from '../models/response';

import { CommonMaster } from '../models/common-master.model';

@Injectable({
  providedIn: 'root',
})
export class JoiningDetailsService {
  constructor(private http: HttpClient, private toastrService: ToastrService) { }

  private dataSubject = new BehaviorSubject<string>('');

  public data$ = this.dataSubject.asObservable();

  createJoiningDetails(
    employeeId: string,
    joiningDetails: JoiningDetails
  ): Observable<JoiningDetails> {
    return this.http.post<JoiningDetails>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/joining-detail/create/${employeeId}`,

      joiningDetails
    );
  }
  getByEmployeeId(id: number): Observable<JoiningDetails> {
    return this.http.get<JoiningDetails>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/joining-detail/' + id
    );
  }
  updateJoiningDetails(
    employeeId: string,
    joiningDetails: JoiningDetails
  ): Observable<JoiningDetails> {
    return this.http.put<JoiningDetails>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/joining-detail/update/${employeeId}`,

      joiningDetails
    );
  }

  // Add other methods as needed...

  notify(message: string) {
    this.toastrService.success(message);
  }

  warn(message: string) {
    this.toastrService.warning(message);
  }

  sendData(data: string) {
    this.dataSubject.next(data);
  }
}
