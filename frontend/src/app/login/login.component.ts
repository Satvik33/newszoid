import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService,
              private router: Router) { }
  errorMessage: string = null;

  ngOnInit() {
  }

  loginUser(myForm: NgForm) {
    const user: any = {
      username: myForm.value.username,
      password: myForm.value.password
    };
    user.username = myForm.value.username;
    user.password = myForm.value.password;
    console.log(user.username + ' ' + user.password);
    this.authenticationService.loginUser(user).subscribe(data => {
      const jwt = data.jwtToken;
      localStorage.setItem('jwt', jwt);
      localStorage.setItem('username', user.username);
    }, error => {
      this.errorMessage = 'Incorrect username or password';
    });
  }

}
