import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/users/register.component';
import { UserDetailComponent } from './components/users/userDetail.component';
import { EditUserComponent } from './components/users/edit-user.component';
import { DeleteUserComponent } from './components/users/delete-user.component';
import { UserListComponent } from './components/users/user-list.component';
import { FamiliaListComponent } from './components/familiasProductoras/familia-list.component';
import { RegisterFamiliaComponent } from './components/familiasProductoras/registerFamilia.component';
import { EditFamiliaComponent } from './components/familiasProductoras/edit-familia.component';
import { DeleteFamiliaComponent } from './components/familiasProductoras/delete-familia.component';
import { LoteListComponent } from './components/lotes/lote-list.compnent';
import { CreateProductComponent } from './components/productoElaborado/create-product.component';
import { ProductListComponent } from './components/productoElaborado/list-products.component';
import { InsumoListComponent } from './components/insumos/insumo-list.component';
import { RegisterInsumoComponent } from './components/insumos/registerInsumo.component';
import { EditInsumoComponent } from './components/insumos/edit-insumo.component';
import { DeleteInsumoComponent } from './components/insumos/delete-insumo.component';
import {CanalListComponent} from './components/canales/canal-list.component';
import {DeleteCanalComponent} from './components/canales/delete-canal.component';
import {EditCanalComponent} from './components/canales/edit-canal.component';
import {CreateCanalComponent} from './components/canales/create-canal.component';
import { RecetaListComponent } from './components/recetas/receta-list.component';
import { RegisterRecetaComponent } from './components/recetas/registerReceta.component';
import { RecetaDetailComponent } from './components/recetas/receta-detail.component';
import { EditRecetaComponent } from './components/recetas/edit-receta.component';
import { DeleteRecetaComponent } from './components/recetas/delete-receta.component';
import {MateriaListComponent} from './components/MateriasPrimas/materia-list.component';


export const routes: Routes = [
  
  //Usuarios
  { path: 'users', component: UserListComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user/:id', component: UserDetailComponent },
  { path: 'edit-user/:id', component: EditUserComponent },
  { path: 'delete-user/:id', component: DeleteUserComponent }, 
  //Familias
  { path: 'familias', component: FamiliaListComponent },
  { path: 'registerFamilia', component: RegisterFamiliaComponent },
  { path: 'edit-familia/:id', component: EditFamiliaComponent },
  { path: 'delete-familia/:id', component: DeleteFamiliaComponent },
  //Insumos
  { path: 'insumos', component: InsumoListComponent },
  { path: 'registerInsumo', component: RegisterInsumoComponent },
  { path: 'edit-insumo/:id', component: EditInsumoComponent },
  { path: 'delete-insumo/:id', component: DeleteInsumoComponent },
  //Lotes
  { path: 'lotes', component: LoteListComponent },
  //Receta
  { path: 'recetas', component: RecetaListComponent },
  { path: 'registerReceta', component: RegisterRecetaComponent },
  { path: 'receta/:id', component: RecetaDetailComponent },
  { path: 'edit-receta/:id', component: EditRecetaComponent },
  { path: 'delete-receta/:id', component: DeleteRecetaComponent }, 
  //Productos
  { path: 'productos', component: ProductListComponent},
  { path: 'producto/:id', component: CreateProductComponent},

  //Canales
  {path: 'canales', component: CanalListComponent},
  {path: 'delete-canal/:id', component: DeleteCanalComponent},
  {path: 'edit-canal/:id', component: EditCanalComponent},
  {path: 'create-canal', component: CreateCanalComponent},

  //Materias Primas
  {path: 'materiasPrimas', component: MateriaListComponent}

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
