import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  apiUrl: string = 'http://localhost:8080/api';
}
