import {Component, OnInit} from '@angular/core';
import {PostModel} from '../../model/post.model';
import {Observable, of} from 'rxjs';
import {MatDialog} from '@angular/material/dialog';
import {InfoComponent} from '../info/info.component';
import {PostService} from '../../services/posts/post.service';
import {EncryptionService} from '../../services/encryption/encryption.service';
import {EncryptionModel} from '../../model/encryption.model';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  posts: Observable<PostModel[]>;
  encryption: EncryptionModel;

  constructor(private postService: PostService, private dialog: MatDialog, private encryptionService: EncryptionService) {
  }

  ngOnInit(): void {
    this.postService.retrieveAllPosts().subscribe(value => this.posts = of(value));
    this.encryptionService.getPublicKey().subscribe(response => {
      this.encryption = response;
      console.log(this.encryption);
    });
  }

  update(post: PostModel) {
    post.likeCount += 1;
    this.postService.updatePost(post.id, post);
  }

  openModal(post: PostModel) {
    this.dialog.open(InfoComponent, {
      width: '250px',
      data: post
    });
  }

}
