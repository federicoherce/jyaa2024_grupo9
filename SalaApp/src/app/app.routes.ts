import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/users/register.component';
import { UserDetailComponent } from './components/users/userDetail.component';
import { EditUserComponent } from './components/users/edit-user.component';
import { DeleteUserComponent } from './components/users/delete-user.component';
import { UserListComponent } from './components/users/user-list.component';
import { LoteListComponent } from './components/lotes/lote-list.compnent';
import { CreateProductComponent } from './components/productoElaborado/create-product.component';
import { ProductListComponent } from './components/productoElaborado/list-products.component';

export const routes: Routes = [
  
  //Usuarios
  { path: 'users', component: UserListComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user/:id', component: UserDetailComponent },
  { path: 'edit-user/:id', component: EditUserComponent },
  { path: 'delete-user/:id', component: DeleteUserComponent }, 

  //Lotes
  { path: 'lotes', component: LoteListComponent },

  //Productos
  { path: 'productos', component: ProductListComponent},
  { path: 'producto/:id', component: CreateProductComponent}
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
