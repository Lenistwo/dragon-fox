import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../services/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm = new FormGroup({
    username: new FormControl('', Validators.required),
    email: new FormControl('', Validators.email),
    password: new FormControl('', Validators.required),
    terms: new FormControl('', Validators.required)
  });


  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  valid() {
    return !this.registerForm.valid;
  }

  register() {

  }


}
