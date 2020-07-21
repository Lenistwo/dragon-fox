import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginComponent} from './components/login/login.component';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MenuBarComponent} from './components/menu-bar/menu-bar.component';
import {RouterModule} from '@angular/router';
import {RoutingModule} from './modules/routing/routing.module';
import {HttpClientModule} from '@angular/common/http';
import {MainComponent} from './components/main/main.component';
import {RegisterComponent} from './components/register/register.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {ReactiveFormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {LikeButtonComponent} from './components/like-button/like-button.component';
import {InfoComponent} from './components/info/info.component';
import {MatDialogModule} from '@angular/material/dialog';
import {NgxLocalStorageModule} from 'ngx-localstorage';
import { UsersComponent } from './components/users/users.component';
import { DetailsModalComponent } from './components/users/details-modal/details-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MenuBarComponent,
    MainComponent,
    RegisterComponent,
    LikeButtonComponent,
    InfoComponent,
    UsersComponent,
    DetailsModalComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    RouterModule,
    RoutingModule,
    HttpClientModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatIconModule,
    MatDialogModule,
    NgxLocalStorageModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
