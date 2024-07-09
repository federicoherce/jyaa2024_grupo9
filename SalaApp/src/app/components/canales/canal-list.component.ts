import { Component, OnInit } from '@angular/core';
import { CanalService, Canal } from '../../services/canal.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';


@Component({
  imports: [CommonModule, RouterModule],
  standalone: true,
  selector: 'app-canal-list',
  templateUrl: './canal-detail.component.html'
})
export class CanalListComponent implements OnInit {
  canales: Canal[] = [];
  errorMessage: string = '';

  constructor(private canalService: CanalService) {}

  ngOnInit() {
    this.canalService.getCanales().subscribe(
      canales => {
        this.canales = canales;
      },
      error => {
        console.error('Error: ', error);
      }
    );
  }
}



