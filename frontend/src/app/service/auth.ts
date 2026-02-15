import { HttpClient } from '@angular/common/http';
import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  SESSION_KEY: string = 'auth_user'
	TOKEN_KEY: string = 'auth_token'

	username: string = '';
	password: string = '';

 private isBrowser: boolean;

 constructor(private http : HttpClient, @Inject(PLATFORM_ID) private platformId: Object) {
		this.isBrowser = isPlatformBrowser(this.platformId);
 }

 authenticate(username:string, password :string){
		const credentials = `${username}:${password}`;
		let encoded = '';
		if (typeof btoa !== 'undefined') {
			encoded = btoa(credentials);
		} else if ((globalThis as any).Buffer) {
			encoded = (globalThis as any).Buffer.from(credentials).toString('base64');
		}

		return this.http.get(`/auth`, { headers: { authorization: 'Basic ' + encoded }}).pipe(map((res)=>{
				this.username = username;
				this.password = password;
				if (this.isBrowser) {
					try {
						sessionStorage.setItem(this.SESSION_KEY, username);
						sessionStorage.setItem(this.TOKEN_KEY, encoded);
					} catch { }
				}
				return res;
			}));
	};

  logout() {
		if (this.isBrowser) {
			try { sessionStorage.removeItem(this.SESSION_KEY); } catch { }
		}
		this.username = '';
		this.password = '';
	};

 	isUserLoggedin() {
		if (!this.isBrowser) return false;
		try {
			const user = sessionStorage.getItem(this.SESSION_KEY);
			return user !== null;
		} catch {
			return false;
		}
	};

	getLoggedinUser() {
		if (!this.isBrowser) return '';
		try {
			const user = sessionStorage.getItem(this.SESSION_KEY);
			return user === null ? '' : user;
		} catch {
			return '';
		}
	};

	getAuthToken() {
		if (!this.isBrowser) return '';
		try {
			const token = sessionStorage.getItem(this.TOKEN_KEY);
			return token === null ? '' : token;
		} catch {
			return '';
		}
	};
}