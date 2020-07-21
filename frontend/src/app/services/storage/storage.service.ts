import {Injectable} from '@angular/core';
import {LocalStorageService} from 'ngx-localstorage';
import {AUTHENTICATION} from '../../constants/storage.constants';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private ADMIN_ROLE = 'ADMIN';

  constructor(private localStorageService: LocalStorageService) {
  }

  getUsername(): string {
    return this.localStorageService.get(AUTHENTICATION).username;
  }

  getUserToken(): string {
    return this.localStorageService.get(AUTHENTICATION).token;
  }

  getUserRole(): string {
    return this.localStorageService.get(AUTHENTICATION).authorityCollections[0].authority;
  }

  log() {
    console.log(this.localStorageService.get('role'));
  }

  isAdministrator(): boolean {
    if (this.localStorageService.get(AUTHENTICATION) != null) {
      const authority = this.localStorageService.get(AUTHENTICATION).authorityCollections[0].authority;
      return authority === this.ADMIN_ROLE && authority != null;
    }
    return false;
  }

  isAuthenticated(): boolean {
    return this.localStorageService.get(AUTHENTICATION) != null;
  }
}
