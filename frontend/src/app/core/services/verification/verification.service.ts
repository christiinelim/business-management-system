import { Injectable } from '@angular/core';
import { GlobalService } from '../global/global.service';
import { HttpClient } from '@angular/common/http';
import { Verification } from '../../models/verification/verification.model';

@Injectable({
  providedIn: 'root'
})
export class VerificationService {

  constructor(private http: HttpClient, private globalService: GlobalService) {
  }

  public save(verification: Verification) {
    return this.http.post<Verification>(this.globalService.apiUrl+"/auth/verify", verification)
  }
}
