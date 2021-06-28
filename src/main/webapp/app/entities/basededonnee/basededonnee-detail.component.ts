import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBasededonnee } from 'app/shared/model/basededonnee.model';

@Component({
  selector: 'jhi-basededonnee-detail',
  templateUrl: './basededonnee-detail.component.html'
})
export class BasededonneeDetailComponent implements OnInit {
  basededonnee: IBasededonnee;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ basededonnee }) => {
      this.basededonnee = basededonnee;
    });
  }

  previousState() {
    window.history.back();
  }
}
