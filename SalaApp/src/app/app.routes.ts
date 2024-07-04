import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/register.component';
import { UserDetailComponent } from './components/userDetail.component';

export const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'user/:id', component: UserDetailComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
