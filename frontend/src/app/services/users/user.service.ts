import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {StorageService} from '../storage/storage.service';
import {Example} from '../../model/example';
import {USER_PATH} from '../../constants/path.constants';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient, private storageService: StorageService) {
  }

  retrieveAllUsers(): Observable<Example[]> {
    return this.httpClient.get<Example[]>(USER_PATH, {headers: this.authHeader()});
  }

  updateUser(user: Example) {
    this.httpClient.patch(USER_PATH, user, {headers: this.authHeader()}).subscribe();
  }

  addUser(user: Example) {
    this.httpClient.post(USER_PATH, user, {headers: this.authHeader()}).subscribe();
  }

  authHeader(): HttpHeaders {
    return new HttpHeaders({Authorization: this.storageService.getUserToken()});
  }
}
