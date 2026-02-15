import { NgModule, Pipe, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Login } from './component/login/login';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Dashboard } from './component/dashboard/dashboard';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { Transfer } from './component/transfer/transfer';
import { History } from './component/history/history';
import { Profile } from './component/profile/profile';
import { CommonModule } from '@angular/common';
import { HttpinterceptorService } from './service/httpinterceptor';
import { HTTP_INTERCEPTORS, provideHttpClient, withFetch, withInterceptors, withInterceptorsFromDi } from '@angular/common/http';

@NgModule({
  declarations: [
    App,
    Login,
    Dashboard,
    Transfer,
    History,
    Profile
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterOutlet,
    RouterLink,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule
  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: HttpinterceptorService,
    multi: true
  },
    provideBrowserGlobalErrorListeners(),
    provideHttpClient(
      withFetch(),
      withInterceptorsFromDi()
    ),
    provideClientHydration(withEventReplay())
  ],
  bootstrap: [App]
})
export class AppModule { }
