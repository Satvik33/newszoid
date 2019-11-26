import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
    MatButtonModule, MatCardModule, MatDatepickerModule,
    MatDialogModule,
    MatFormFieldModule, MatGridListModule,
    MatIconModule,
    MatInputModule, MatMenuModule, MatNativeDateModule, MatProgressBarModule, MatSelectModule,
    MatStepperModule,
    MatToolbarModule
} from '@angular/material';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatVideoModule } from 'mat-video';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './navbar/navbar.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import {DatePipe} from '@angular/common';
import { UploadComponent } from './upload/upload.component';
import { TrendingComponent } from './trending/trending.component';
import { RecommendationComponent } from './recommendation/recommendation.component';
import { LikedComponent } from './liked/liked.component';
import { PostedComponent } from './posted/posted.component';
import { PostComponent } from './post/post.component';
import { CategoryComponent } from './category/category.component';
import { EditProfileComponent} from './edit-profile/edit-profile.component';
import { SearchComponent } from './search/search.component';
import { SearchbarComponent } from './searchbar/searchbar.component';
import {ViewProfileComponent} from './view-profile/view-profile.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { PostDetailComponent } from './post-detail/post-detail.component';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    UploadComponent,
    TrendingComponent,
    RecommendationComponent,
    LikedComponent,
    PostedComponent,
    PostComponent,
    CategoryComponent,
    EditProfileComponent,
    SearchComponent,
    SearchbarComponent,
    ViewProfileComponent,
    NotfoundComponent,
    PostDetailComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatIconModule,
        HttpClientModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        LayoutModule,
        MatSidenavModule,
        MatListModule,
        MatDialogModule,
        FormsModule,
        MatStepperModule,
        ReactiveFormsModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatMenuModule,
        MatProgressBarModule,
        MatCardModule,
        MatGridListModule,
        MatVideoModule,
        MatCheckboxModule,
        MatSelectModule
    ],
  entryComponents: [
    LoginComponent,
    UploadComponent
  ],
  providers: [DatePipe, CategoryComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
