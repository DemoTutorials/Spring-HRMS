import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FileUploadService {
  constructor(private http: HttpClient, private toastrService: ToastrService) { }

  uploadImage(file: File): Observable<any> {
    const form = new FormData();
    form.append('file', file);
    return this.http.post('https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/file/save', form);
  }

  removeImage(file: String): Observable<any> {
    return this.http.delete(
      'https://7010-cs-c654acac-fddf-4efb-9c03-55cbb459e207.cs-asia-southeast1-palm.cloudshell.dev/file/delete?file=' + file
    );
  }

  notify(message: string) {
    this.toastrService.success(message);
  }

  warn(message: string) {
    this.toastrService.warning(message);
  }
}
