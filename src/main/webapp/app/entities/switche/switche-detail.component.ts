import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISwitche } from 'app/shared/model/switche.model';

@Component({
  selector: 'jhi-switche-detail',
  templateUrl: './switche-detail.component.html'
})
export class SwitcheDetailComponent implements OnInit {
  switche: ISwitche;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ switche }) => {
      this.switche = switche;
    });
  }

  previousState() {
    window.history.back();
  }
}
