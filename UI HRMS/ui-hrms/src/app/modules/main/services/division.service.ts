import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Division } from '../models/division.model';
import { ApiResponse } from '../models/response';
import { ToastrService } from 'ngx-toastr';
import { ColumnsMetadata } from '../models/columns-metadata';
@Injectable({
  providedIn: 'root',
})
export class DivisionService {
  private dataSubject = new BehaviorSubject<string>('');
  public data$ = this.dataSubject.asObservable();

  constructor(private http: HttpClient, private toastrService: ToastrService) { }

  createDivision(division: Division): Observable<Division> {
    return this.http.post<Division>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/division/create',
      division
    );
  }



  getDivisions(): Observable<Array<Division>> {
    return this.http.get<Array<Division>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/division/get-all'
    );
  }

  getDivisionHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/division'
    );
  }

  searchDivisionById(divisionId: string): Observable<Division> {
    return this.http.get<Division>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/division/' + divisionId
    );
  }

  updateDivision(divisionId: string): Observable<Division> {
    return this.http.put<Division>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/division/update',
      divisionId
    );
  }

  deleteDivision(divisionId: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/division/' +
      divisionId +
      '?updatedBy=Admin'
    );
  }
  search(
    params: HttpParams
  ): Observable<{ content: Array<Division>; totalElements: number }> {
    return this.http.get<{ content: Array<Division>; totalElements: number }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/division/search',
      {
        params: params,
      }
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
