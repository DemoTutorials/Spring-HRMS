import { Injectable } from '@angular/core';

import { HttpClient, HttpParams } from '@angular/common/http';

import { BehaviorSubject, Observable } from 'rxjs';

import { ToastrService } from 'ngx-toastr';

import { Address } from '../models/address.model';

import { CommonMaster } from '../models/common-master.model';

@Injectable()
export class AddressService {
  private dataSubject = new BehaviorSubject<string>('');

  public data$ = this.dataSubject.asObservable();

  constructor(private http: HttpClient, private toastrService: ToastrService) {}

  createAddress(formDataArray: Address[], id: number): Observable<Address[]> {
    console.log(id);

    return this.http.post<Address[]>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/address/${id}/add`,

      formDataArray
    );
  }

  updateAddress(formDataArray: Address[], id: number): Observable<Address[]> {
    console.log('update' + id);

    return this.http.put<Address[]>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/address/${id}/update-addresses`,

      formDataArray
    );
  }

  getAllAddressById(id: number): Observable<Address[]> {
    return this.http.get<Address[]>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/address/${id}/get-all`
    );
  }

  getAddressById(id: number, addressType: string): Observable<Address> {
    console.log('get address by employee id :' + id);

    return this.http.get<Address>(
      `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/address/${id}/${addressType}`
    );
  }

  getAddressType(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Address Type?sort=priority,code'
    );
  }

  getOwnershipStatus(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Ownership Status?sort=priority,code'
    );
  }

  getCountry(): Observable<Array<CommonMaster>> {
    return this.http.get<Array<CommonMaster>>(
      'https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/Country?sort=priority,code'
    );
  }

  getState(code: string): Observable<Array<CommonMaster>> {
    console.log('state foreign key' + code);

    const url = `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/State?sort=priority,code&dependent=${code}`;

    return this.http.get<Array<CommonMaster>>(url);
  }

  getCity(code: string): Observable<Array<CommonMaster>> {
    console.log('city foreign key' + code);

    const url = `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/City?sort=priority,code&dependent=${code}`;

    return this.http.get<Array<CommonMaster>>(url);
  }

  allCity(): Observable<Array<CommonMaster>> {
    console.log('city foreign key');

    const url = `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/City?sort=priority,code`;

    return this.http.get<Array<CommonMaster>>(url);
  }

  allState(): Observable<Array<CommonMaster>> {
    console.log('state foreign key');

    const url = `https://7000-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/employee/common-master/State?sort=priority,code`;

    return this.http.get<Array<CommonMaster>>(url);
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
