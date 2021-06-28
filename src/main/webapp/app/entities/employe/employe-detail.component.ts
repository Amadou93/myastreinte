import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IEquipe } from 'app/shared/model/equipe.model';
import { IEmploye } from 'app/shared/model/employe.model';
import { from } from 'rxjs';
import { EquipeService } from '../equipe';
@Component({
  selector: 'jhi-employe-detail',
  templateUrl: './employe-detail.component.html'
})
export class EmployeDetailComponent implements OnInit {
  employe: IEmploye;
  equipe: IEquipe;

  constructor(protected activatedRoute: ActivatedRoute, protected equipeService: EquipeService) {}
  ngOnInit() {
    this.activatedRoute.data.subscribe(({ employe }) => {
      this.employe = employe;
    });
    this.getEquipeById();
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
