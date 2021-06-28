import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAstreinte } from 'app/shared/model/astreinte.model';
import { IEmploye } from 'app/shared/model/employe.model';
import { IEquipe } from 'app/shared/model/equipe.model';
import { EmployeService } from '../employe';
import { EquipeService } from '../equipe';
import { from } from 'rxjs';
@Component({
  selector: 'jhi-astreinte-detail',
  templateUrl: './astreinte-detail.component.html'
})
export class AstreinteDetailComponent implements OnInit {
  astreinte: IAstreinte;
  employe: IEmploye;
  equipe: IEquipe;

  constructor(protected activatedRoute: ActivatedRoute, protected employeService: EmployeService, protected equipeService: EquipeService) {}
  ngOnInit() {
    this.activatedRoute.data.subscribe(({ astreinte }) => {
      this.astreinte = astreinte;
      console.log(this.astreinte);
    });
    this.getEmployeById();
    this.getEquipeById();
  }

  getEmployeById() {
    this.employeService.find(this.astreinte.employeId).subscribe(data => {
      this.employe = data.body;
      console.log(this.employe);
    });
  }
  getEquipeById() {
    this.equipeService.find(this.employe.equipeId).subscribe(data => {
      this.equipe = data.body;
      console.log(this.equipe);
    });
  }

  previousState() {
    window.history.back();
  }
}
