import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Router} from '@angular/router';

@Component({
  selector: 'app-trending',
  templateUrl: './trending.component.html',
  styleUrls: ['./trending.component.css']
})
export class TrendingComponent implements OnInit {

  posts: any;
  constructor(private http: HttpClient,
              private router: Router) { }

  ngOnInit() {
    const httpOptions = {
      headers: new HttpHeaders(
        {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        })
    };
    this.http.get(environment.uploadPostUrl + 'trending', httpOptions).subscribe(
      (data) => {
        this.posts = data['posts'];
      }/*, (error) => {
        this.router.navigateByUrl('/404');
      }*/
    );
  }

}
