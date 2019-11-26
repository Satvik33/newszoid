import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Subscription} from 'rxjs';
import {HttpClient, HttpEventType, HttpHeaders, HttpRequest} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {environment} from '../../environments/environment';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {
  /** Name used in form which will be sent in HTTP request. */
  @Input() param = 'file';

  /** Target URL for file uploading. */
  @Input() target = environment.postTargetUrl;
  @Input() accept = '*';
  /** Allow you to add handler after its completion. Bubble up response text from remote. */
  @Output() complete = new EventEmitter<string>();
  private files: Array<FileUploadModel> = [];

  response: any;
  post: any;
  locations = environment.locations;
  currentProgress : string;
  progressNumber: number;
  categories = ['National', 'International',
    'Business', 'Technology', 'Entertainment',
    'Sports', 'Science', 'Health'];

  constructor(private http: HttpClient,
              private router: Router) { }

  ngOnInit() {
  }

  onClick() {
    const fileUpload = document.getElementById('fileUpload') as HTMLInputElement;
    fileUpload.onchange = () => {
      for (let index = 0; index < fileUpload.files.length; index++) {
        const file = fileUpload.files[index];
        this.files.push({
          data: file, state: 'in',
          inProgress: false, progress: 0, canRetry: false, canCancel: true
        });
      }
      this.uploadFiles();
    };
    fileUpload.click();
  }

  cancelFile(file: FileUploadModel) {
    file.sub.unsubscribe();
    this.removeFileFromArray(file);
  }

  retryFile(file: FileUploadModel) {
    this.uploadFile(file);
    file.canRetry = false;
  }

  private uploadFile(file: FileUploadModel) {
    const fd = new FormData();
    fd.append(this.param, file.data);
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });
    /*const request = new HttpRequest('POST', this.target, fd, { headers, responseType: 'text' });
    file.inProgress = true;
    const fds = new FormData();
    this.http.request(request).subscribe(data => {
      this.response = data['body'];
      console.log('abc' + this.response);

    });*/

    this.http.post(this.target, fd, {headers, responseType: 'text', reportProgress: true, observe: 'events'})
      .subscribe(event => {
        if(event.type === HttpEventType.UploadProgress){
          console.log('Upload Progress: ' + Math.round(event.loaded / event.total * 100) + '%');
          this.progressNumber = Math.round(event.loaded / event.total * 100);
          this.currentProgress = 'Upload Progress: ' + Math.round(event.loaded / event.total * 100) + '%';
        }else if(event.type === HttpEventType.Response){
          console.log(event);
          this.response = event.body;
        }
      });
  }

  private uploadFiles() {
    const fileUpload = document.getElementById('fileUpload') as HTMLInputElement;
    fileUpload.value = '';

    this.files.forEach(file => {
      this.uploadFile(file);
    });
  }

  private removeFileFromArray(file: FileUploadModel) {
    const index = this.files.indexOf(file);
    if (index > -1) {
      this.files.splice(index, 1);
    }
  }

  addPost(input: NgForm) {
    const username = localStorage.getItem('username');
    this.post = {
      id: Math.floor(Math.random() * (10000000 - 0 + 1)) + 0,
      title: input.value.title,
      category: input.value.category,
      location: input.value.location,
      // tags: tags,
      videoUrl: this.response,
      postedBy: {
        username: username
      }
    };
    console.log(this.post);
    const httpOptions = {
      headers: new HttpHeaders(
        {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        })
    };
    this.http.post(environment.uploadPostUrl, this.post, httpOptions).subscribe(data => {
      this.router.navigateByUrl('/404').then((dat) => {
        this.router.navigateByUrl('/posted');
      });
    });
  }

}
export class FileUploadModel {
  data: File;
  state: string;
  inProgress: boolean;
  progress: number;
  canRetry: boolean;
  canCancel: boolean;
  sub?: Subscription;
}
