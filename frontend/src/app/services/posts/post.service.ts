import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PostModel} from '../../model/post.model';
import {POST_PATH} from '../../constants/path.constants';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient: HttpClient) {

  }

  retrieveAllPosts(): Observable<PostModel[]> {
    return this.httpClient.get<PostModel[]>(POST_PATH);
  }

  updatePost(id: string, postModel: PostModel) {
    this.httpClient.patch(POST_PATH, postModel).subscribe();
  }
}
