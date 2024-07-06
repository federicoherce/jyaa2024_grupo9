import { Component } from "@angular/core";
import { AppRoutingModule } from "../../app.routes";
import { RouterModule } from "@angular/router";


@Component({
    selector: 'app-navbar',
    standalone: true,
    imports: [RouterModule],
    templateUrl: './navbar.component.html'
  })

export class NavbarComponent{}