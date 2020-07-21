import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {EncryptionModel} from '../../model/encryption.model';
import {ENCRYPTION_PATH} from '../../constants/path.constants';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EncryptionService {

  constructor(private httpClient: HttpClient) {
  }

  getPublicKey(): Observable<EncryptionModel> {
    return this.httpClient.get<EncryptionModel>(ENCRYPTION_PATH);
  }
}
