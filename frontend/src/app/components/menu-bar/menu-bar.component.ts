import {Component, OnInit} from '@angular/core';
import {StorageService} from '../../services/storage/storage.service';
import {AuthService} from '../../services/auth/auth.service';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {

  constructor(public storageService: StorageService, private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  logout() {
    this.authService.logout();
  }
}
