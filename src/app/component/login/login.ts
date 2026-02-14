import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Accountholderservice } from '../../service/accountholderservice';
import { Dashboard } from '../dashboard/dashboard';
import { AuthService } from '../../service/auth';
import { form } from '@angular/forms/signals';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
})
export class Login {
  service = inject(Accountholderservice);

  username: string = '';
	password : string = '';
	isLoggedin = false;
	error: string = '';
  data : any = {};

  constructor(private router : Router, private authService : AuthService){}
  ngOnInit(): void {
      this.isLoggedin = this.authService.isUserLoggedin();
      if(this.isLoggedin)
      {
        const userId = this.username;
        this.router.navigate(['/dashboard', userId]);
      }
  }

  doLogin() {
    if(this.username !== '' && this.username !== null 
        && this.password !== '' && this.password !== null) {
          this.authService.authenticate(this.username,this.password)
            .subscribe(data=>{
              this.data = data;
              console.log("after login : "+this.data);
              const userId = this.username;
              this.router.navigate(['/dashboard', userId]);
        }); 
        } else {
          window.alert("Invalid creditials!!!");
        }
  }
}
