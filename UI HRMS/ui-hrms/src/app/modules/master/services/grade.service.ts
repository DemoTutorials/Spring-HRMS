import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ColumnsMetadata } from '../models/columnMetaData';
import { Grade } from '../models/grade.model';
import { IndividualConfig, ToastrService } from 'ngx-toastr';
import { ApiResponse } from '../models/response';
import { GradeType } from '../models/gradeType';
@Injectable()
export class GradeService {
  constructor(private http: HttpClient, private toastrService: ToastrService) { }

  ngOnInit(): void { }

  getGrades(): Observable<Array<Grade>> {
    return this.http.get<Array<Grade>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/grade/get-all'
    );

  }

  getGradesHeaders(): Observable<{ columnsMetadata: Array<ColumnsMetadata> }> {
    return this.http.get<{ columnsMetadata: Array<ColumnsMetadata> }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/data-table-metadata/grade'
    );
  }

  createGrade(data: Grade): Observable<Array<Grade>> {
    console.log('in create service', data);

    return this.http.post<Array<Grade>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/grade/create',
      data
    );
  }

  searchByGradeId(id: string): Observable<Grade> {
    return this.http.get<Grade>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/grade/' + id
    );
  }

  updateGrade(data: Grade): Observable<Array<Grade>> {
    console.log(data);
    return this.http.put<Array<Grade>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/grade/update',
      data
    );
  }

  deleteGrade(gradeId: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/grade/delete/' +
      gradeId +
      '?updatedBy=Admin'
    );
  }

  gradeTypeFromCommonMaster(): Observable<{ gradeType: Array<GradeType> }> {
    return this.http.get<{ gradeType: Array<GradeType> }>(
      'https://localhost:8000/utility/masters/commonMaster/Grade Type'
    );
  }

  search(
    params: HttpParams
  ): Observable<{ content: Array<Grade>; totalElements: number }> {
    return this.http.get<{ content: Array<Grade>; totalElements: number }>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/grade/get',
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
}
