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
        const userId = this.authService.getLoggedinUser();
        this.router.navigate(['/dashboard', userId]);
      }
  }

  doLogin() {

  if (this.username && this.password) {

    this.authService.authenticate(this.username, this.password)
      .subscribe({
        next: (data) => {
          this.data = data;

          // prefer numeric id from server response if present
          let userId: any = this.username;
          if (data) {
            if ((data as any).id) userId = (data as any).id;
            else if ((data as any).accountId) userId = (data as any).accountId;
            else if ((data as any).accId) userId = (data as any).accId;
          }

          this.router.navigate(['/dashboard', userId]);
        },

        error: (err) => {
          console.error("Login failed:", err);
          window.alert("Invalid username or password!");
        }
      });

  } else {
    window.alert("Please enter username and password!");
  }
}

}
