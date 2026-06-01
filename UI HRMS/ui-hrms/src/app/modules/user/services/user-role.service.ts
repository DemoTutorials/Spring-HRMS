import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject, Observable } from 'rxjs';
import { ColumnsMetadata } from '../models/columns-metadata';
import { UserRole } from '../models/user-role.model';
import { ApiResponse } from '../models/response';

@Injectable({
  providedIn: 'root',
})
export class UserRoleService {
  private dataSubject = new BehaviorSubject<string>('');
  public data$ = this.dataSubject.asObservable();

  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  getRoleHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'http://localhost:7080/data-table-metadata/userRoles'
    );
  }

  search(
    params: HttpParams
  ): Observable<{ content: Array<UserRole>; totalElements: number }> {
    return this.http.get<{ content: Array<UserRole>; totalElements: number }>(
      'http://localhost:7080/role/page-all-realm',
      {
        params: params,
      }
    );
  }

  createRole(role: UserRole): Observable<UserRole> {
    return this.http.post<UserRole>(
      'http://localhost:7080/role/create',
      role
    );
  }

  getRoles(): Observable<Array<UserRole>> {
    return this.http.get<Array<UserRole>>(
      'http://localhost:7080/role/get-all-realm'
    );
  }

  searchRoleById(roleId: string): Observable<UserRole> {
    return this.http.get<UserRole>('http://localhost:7080/role/' + roleId);
  }

  updateRole(role: UserRole): Observable<UserRole> {
    return this.http.put<UserRole>('http://localhost:7080/role', role);
  }

  deleteRole(roleId: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'http://localhost:7080/role/' + roleId
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
