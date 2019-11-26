import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {

  post: any;
  postId: string;
  views: number;
  likes: number;
  flags: number;
  isLiked: boolean;
  isFlagged: boolean;
  user: any;

  httpOptions = {
    headers: new HttpHeaders(
      {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('jwt')
      })
  };

  constructor(private http: HttpClient,
              private route: ActivatedRoute) {}

  ngOnInit() {
    this.postId = this.route.snapshot.paramMap.get('postId');

    this.http.get(environment.uploadPostUrl + this.postId, this.httpOptions).subscribe(
      (data) => {
        this.post = data;
        if (!this.post.watchedBy) {
          this.views = 0;
        } else {
          this.views = this.post.watchedBy.length;
        }
        if (!this.post.likedBy) {
          this.likes = 0;
        } else {
          this.likes = this.post.likedBy.length;
          if (this.post.likedBy.indexOf(localStorage.getItem('username')) !== -1) {
            this.isLiked = true;
          }
        }

        if (!this.post.flaggedBy) {
          this.flags = 0;
        } else {
          this.flags = this.post.flaggedBy.length;
          if (this.post.flaggedBy.indexOf(localStorage.getItem('username')) !== -1) {
            this.isFlagged = true;
          }
        }
      }
    );
    this.http.get(environment.registerUrl + '/' + localStorage.getItem('username'), this.httpOptions).subscribe(
      (data) => {
        this.user = data;
      }
    );
  }

  like() {
    if (this.likes === 0) {
      let likes: string[] = [];
      likes.push(localStorage.getItem('username'));
      this.post.likedBy = likes;
    } else {
      const index = this.post.likedBy.indexOf(localStorage.getItem('username'));
      if (index !== -1) {
        this.post.likedBy.splice(index, 1);
        const ind = this.user.liked.indexOf(this.postId);
        if (ind !== -1) {
          this.user.liked.splice(ind, 1);
        }
        this.isLiked = false;
      } else {
        this.post.likedBy.push(localStorage.getItem('username'));
      }
    }

    this.http.put(environment.uploadPostUrl, this.post, this.httpOptions).subscribe(
      (data) => {
        this.http.put(environment.registerUrl, this.user, this.httpOptions).subscribe();
        this.ngOnInit();
      }
    );
  }

  flag() {
    if (this.flags === 0) {
      let flags: string[] = new Array();
      flags.push(localStorage.getItem('username'));
      this.post.flaggedBy = flags;
    } else {
      const index = this.post.flaggedBy.indexOf(localStorage.getItem('username'));
      if (index !== -1) {
        this.post.flaggedBy.splice(index, 1);
        const ind = this.user.flagged.indexOf(this.postId);
        if (ind !== -1) {
          this.user.flagged.splice(ind, 1);
        }
        this.isFlagged = false;
      } else {
        this.post.flaggedBy.push(localStorage.getItem('username'));
      }
    }

    this.http.put(environment.uploadPostUrl, this.post, this.httpOptions).subscribe(
      (data) => {
        this.http.put(environment.registerUrl, this.user, this.httpOptions).subscribe();
        this.ngOnInit();
      }
    );
  }

}
