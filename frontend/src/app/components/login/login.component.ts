import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {LoginModel} from '../../model/login.model';
import {LocalStorageService} from 'ngx-localstorage';
import {AuthService} from '../../services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  auth: string;
  loginGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  constructor(private authService: AuthService, private localStorageService: LocalStorageService) {
  }

  ngOnInit(): void {
  }

  valid() {
    return !this.loginGroup.valid;
  }

  onSubmit() {
    this.authService.login(this.getFormData()).subscribe(response => {
      this.authService.authenticate(response);
    });
  }

  getFormData(): LoginModel {
    const user = new LoginModel();
    user.username = this.loginGroup.value.username;
    user.password = this.loginGroup.value.password;
    return user;
  }


}
