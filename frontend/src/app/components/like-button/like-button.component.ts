import {Component, Input, OnInit} from '@angular/core';
import {PostModel} from '../../model/post.model';
import {PostService} from '../../services/posts/post.service';

@Component({
  selector: 'app-like-button',
  templateUrl: './like-button.component.html',
  styleUrls: ['./like-button.component.css']
})
export class LikeButtonComponent implements OnInit {

  clicked = false;
  @Input() post: PostModel;

  constructor(private postService: PostService) {
  }

  ngOnInit(): void {
  }

  update() {
    if (this.clicked) {
      this.post.likeCount -= 1;
    } else {
      this.post.likeCount += 1;
    }
    this.postService.updatePost(this.post.id, this.post);
    this.clicked = !this.clicked;
  }
}
