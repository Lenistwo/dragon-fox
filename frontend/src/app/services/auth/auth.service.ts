import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Router} from '@angular/router';
import {LocalStorageService} from 'ngx-localstorage';
import {LoginModel} from '../../model/login.model';
import {User} from '../../model/user';
import {LOGIN_PATH} from '../../constants/path.constants';
import {AUTHENTICATION} from '../../constants/storage.constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  status = false;

  constructor(private httpClient: HttpClient,
              private router: Router,
              private localStorage: LocalStorageService) {

  }

  login(user: LoginModel) {
    return this.httpClient.post<User>(LOGIN_PATH, user, {observe: 'response'});
  }

  authenticate(response: HttpResponse<User>) {
    if (response.status === 200) {
      this.localStorage.set(AUTHENTICATION, response.body);
      this.router.navigate(['main']).then(() => this.router.navigate(['main']));
    }
  }

  logout() {
    if (this.localStorage.get(AUTHENTICATION) != null) {
      this.localStorage.remove(AUTHENTICATION);
      this.router.navigate(['main']).then(() => this.router.navigate(['main']));
    }
  }
}
