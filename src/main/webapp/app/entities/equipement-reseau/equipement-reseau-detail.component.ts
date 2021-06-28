import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEquipementReseau } from 'app/shared/model/equipement-reseau.model';

@Component({
  selector: 'jhi-equipement-reseau-detail',
  templateUrl: './equipement-reseau-detail.component.html'
})
export class EquipementReseauDetailComponent implements OnInit {
  equipementReseau: IEquipementReseau;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ equipementReseau }) => {
      this.equipementReseau = equipementReseau;
    });
  }

  previousState() {
    window.history.back();
  }
}
