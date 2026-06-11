import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject, Observable } from 'rxjs';
import { CommonMaster } from '../models/common-master.model';
import { CompanyDetails } from '../models/company-details.model';

@Injectable()
export class CompanyDetailsService {
  private dataSubject = new BehaviorSubject<string>('');
  public data$ = this.dataSubject.asObservable();

  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  getAllDefaultShift(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Default Shift?sort=priority,code'
    );
  }

  getAllProbations(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Probation?sort=priority,code'
    );
  }

  getAllBillables(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Billable?sort=priority,code'
    );
  }

  updateCompanyDetails(
    companyDetails: CompanyDetails,
    employeeId: number
  ): Observable<CompanyDetails> {
    return this.http.put<CompanyDetails>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/company-detail/update/' + employeeId,
      companyDetails
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
  createComapanyDetails(companyDetails: CompanyDetails, id: number) {
    return this.http.post<CompanyDetails>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/company-detail/create/' + id,
      companyDetails
    );
  }

  searchCompanyDetailsById(Id: string): Observable<CompanyDetails> {
    return this.http.get<CompanyDetails>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/company-detail/' + Id
    );
  }

  // updateCompanyDetails(formData: any) {
  //   return this.http.put<Employees>(
  //     'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/update',
  //     Id
  //   );
  // }
}
