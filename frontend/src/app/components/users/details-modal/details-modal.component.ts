import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {UsersComponent} from '../users.component';
import {Example} from '../../../model/example';

@Component({
  selector: 'app-details-modal',
  templateUrl: './details-modal.component.html',
  styleUrls: ['./details-modal.component.css']
})
export class DetailsModalComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<UsersComponent>, @Inject(MAT_DIALOG_DATA) public selectedUser: Example) {

  }

  ngOnInit(): void {
  }

  toggleClose() {
    this.dialogRef.close();
  }

}
