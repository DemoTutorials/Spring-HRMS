import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { ColumnsMetadata } from '../models/columns-metadata';
import { Scope } from '../models/scope.model';
import { ApiResponse } from '../models/response';

@Injectable({
  providedIn: 'root',
})
export class ScopeService {
  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  getScopeHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/data-table-metadata/scope'
    );
  }

  search(
    params: HttpParams
  ): Observable<{ content: Array<Scope>; totalElements: number }> {
    return this.http.get<{ content: Array<Scope>; totalElements: number }>(
      'https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/scope/search',
      {
        params: params,
      }
    );
  }

  createScope(scope: Scope): Observable<Scope> {
    return this.http.post<Scope>(
      'https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/scope/create',
      scope
    );
  }

  getScopes(): Observable<Array<Scope>> {
    return this.http.get<Array<Scope>>(
      'https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/scope/get-all'
    );
  }

  searchScopeById(scopeId: string): Observable<Scope> {
    return this.http.get<Scope>('https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/scope/' + scopeId);
  }

  updateScope(scope: Scope): Observable<Scope> {
    return this.http.put<Scope>('https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/scope', scope);
  }

  deleteScope(scopeId: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/scope/' + scopeId
    );
  }

  notify(message: string) {
    this.toastrService.success(message);
  }

  warn(message: string) {
    this.toastrService.warning(message);
  }
}
