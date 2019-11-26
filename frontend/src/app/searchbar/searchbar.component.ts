import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent implements OnInit {

  constructor(private http: HttpClient,
              private router: Router) { }

  ngOnInit() {
  }

  search(query) {
    this.router.navigateByUrl('/').then(() => {
      this.router.navigateByUrl('/search/' + query);
    });
  }
}
