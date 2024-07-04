import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './components/register.component';
import { AppRoutingModule } from './app.routes';
import { UserDetailComponent } from './components/userDetail.component';
import { CommonModule } from '@angular/common';


@NgModule({
  imports:      [ 
    BrowserModule,
    FormsModule,
    CommonModule,
    AppRoutingModule
 ],
  providers:    [], 
  declarations: [
  ],
  exports:      [],
  bootstrap:    []
})
export class AppModule { }