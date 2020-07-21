import {Component, Inject, Input, OnInit} from '@angular/core';
import {PostModel} from '../../model/post.model';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MainComponent} from '../main/main.component';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<MainComponent>, @Inject(MAT_DIALOG_DATA) public selected: PostModel) {
  }

  ngOnInit(): void {
  }

}
