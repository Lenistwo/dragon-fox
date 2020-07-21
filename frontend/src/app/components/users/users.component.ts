import {Component, OnInit} from '@angular/core';
import {Example} from '../../model/example';
import {MatDialog} from '@angular/material/dialog';
import {DetailsModalComponent} from './details-modal/details-modal.component';
import {UserService} from '../../services/users/user.service';
import {Observable, of} from 'rxjs';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  user: Observable<Example[]>;

  constructor(private userService: UserService, private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.userService.retrieveAllUsers().subscribe(users => {
      this.user = of(users);
      console.log(this.user[0].id);
    });
  }

  addUser(user: Example) {
    this.userService.addUser(user);
  }

  toggleDetailsModal(selected: Example) {
    this.dialog.open(DetailsModalComponent, {
      width: '250px',
      data: selected
    });
  }
}
