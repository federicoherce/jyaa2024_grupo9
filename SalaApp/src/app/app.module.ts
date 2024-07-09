import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app.routes';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';


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