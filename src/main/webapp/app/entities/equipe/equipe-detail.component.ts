import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEquipe } from 'app/shared/model/equipe.model';

@Component({
  selector: 'jhi-equipe-detail',
  templateUrl: './equipe-detail.component.html'
})
export class EquipeDetailComponent implements OnInit {
  equipe: IEquipe;
  /* division:IDivision
  Departement:IDepartement */

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ equipe }) => {
      this.equipe = equipe;
    });
  }

  previousState() {
    window.history.back();
  }
}
