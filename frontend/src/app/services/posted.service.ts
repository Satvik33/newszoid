import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import{environment} from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class PostedService {

  constructor(private http: HttpClient) { }

  getPosts() {
    const httpOptions = {
      headers: new HttpHeaders(
        {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('jwt'),
          'Access-Control-Allow-Origin': '*'
        })
    };
    const username = localStorage.getItem('username');
    const url = environment.registerUrl + '/' + username;
    return this.http.get(url, httpOptions);
  }
}
