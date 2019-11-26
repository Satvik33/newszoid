import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import {LoginComponent} from '../login/login.component';
import {UploadComponent} from '../upload/upload.component';
import {Router} from '@angular/router';
import {CategoryComponent} from '../category/category.component';
import { RegisterService } from '../services/register.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public dialog: MatDialog,
              private router: Router,
              private category: CategoryComponent,
              private registerService: RegisterService) { }

  categories: {
    name: string;
    icon: string;
  }[] = [
    {
      name: 'National',
      icon: 'flag'
    },
    {
      name: 'International',
      icon: 'public'
    },
    {
      name: 'Business',
      icon: 'business'
    },
    {
      name: 'Technology',
      icon: 'memory'
    },
    {
      name: 'Entertainment',
      icon: 'movie'
    },
    {
      name: 'Sports',
      icon: 'directions_bike'
    },
    {
      name: 'Science',
      icon: 'whatshot'
    },
    {
      name: 'Health',
      icon: 'fitness_center'
    },
  ];

username:String;
  ngOnInit() {
    console.log("asdas"+localStorage.getItem('username'));
  }

  openLogin(): void {
    const dialogRef = this.dialog.open(LoginComponent, {
      width: '250px',
    });
    // dialogRef.afterClosed().subscribe(result => {
    // });
  }

  openUpload(): void {
    const uploadRef = this.dialog.open(UploadComponent, {
      width: '350px'
    });
  }

  signOut() {
    localStorage.clear();
    this.ngOnInit();
    this.router.navigateByUrl('/trending');
  }

  loggedIn(): boolean {
    this.username=localStorage.getItem('username');
    return (localStorage.getItem('jwt') != null);
  }

  trending(): void {
      this.router.navigateByUrl('/trending');
  }

  recommendation(): void {
    if (this.loggedIn()) {
      this.router.navigateByUrl('/recommended');
    } else {
      this.openLogin();
    }
  }

  liked(): void {
    if (this.loggedIn()) {
      this.router.navigateByUrl('/liked');
    } else {
      this.openLogin();
    }
  }

  posted(): void {
    if (this.loggedIn()) {
      this.router.navigateByUrl('/posted');
    } else {
      this.openLogin();
    }
  }

  upload(): void {
    if (this.loggedIn()) {
      this.openUpload();
    } else {
      this.openLogin();
    }
  }
}
