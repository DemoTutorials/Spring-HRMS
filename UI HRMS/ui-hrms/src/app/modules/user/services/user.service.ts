import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject, Observable } from 'rxjs';
import { ColumnsMetadata } from '../models/columns-metadata';
import { User } from '../models/user.model';
import { ApiResponse } from '../models/response';
import { CommonMaster } from '../models/common-master.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private dataSubject = new BehaviorSubject<string>('');
  public data$ = this.dataSubject.asObservable();

  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  getUserHeaders(): Observable<{
    columnsMetadata: Array<ColumnsMetadata>;
  }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/data-table-metadata/user'
    );
  }

  search(
    params: HttpParams
  ): Observable<{ content: Array<User>; totalElements: number }> {
    return this.http.get<{ content: Array<User>; totalElements: number }>(
      'https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/user/search',
      {
        params: params,
      }
    );
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>('https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/user/create', user);
  }

  getUsers(): Observable<Array<User>> {
    return this.http.get<Array<User>>('https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/user/get-all');
  }

  searchUserById(userId: string): Observable<User> {
    return this.http.get<User>('https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/user/' + userId);
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>('https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/user', user);
  }

  deleteUser(userId: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7080-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/user/' + userId
    );
  }

  getUserStatus(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://localhost:7000/employee/common-master/User Status?sort=priority,code'
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
