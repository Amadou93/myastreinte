import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDomaine, Domaine } from 'app/shared/model/domaine.model';
import { DomaineService } from './domaine.service';

@Component({
  selector: 'jhi-domaine-update',
  templateUrl: './domaine-update.component.html'
})
export class DomaineUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    nomService: [],
    respService: [],
    telRespService: [],
    emailRespService: [],
    nomDivision: [],
    respDivision: [],
    telRespDivision: [],
    emailRespDivision: [],
    nomDepart: [],
    respDepart: [],
    telRespDepart: [],
    emailRespDepart: [],
    nomDirection: [],
    respDirection: [],
    telDirecteur: [],
    emailDirecteur: []
  });

  constructor(protected domaineService: DomaineService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ domaine }) => {
      this.updateForm(domaine);
    });
  }

  updateForm(domaine: IDomaine) {
    this.editForm.patchValue({
      id: domaine.id,
      nom: domaine.nom,
      nomService: domaine.nomService,
      respService: domaine.respService,
      telRespService: domaine.telRespService,
      emailRespService: domaine.emailRespService,
      nomDivision: domaine.nomDivision,
      respDivision: domaine.respDivision,
      telRespDivision: domaine.telRespDivision,
      emailRespDivision: domaine.emailRespDivision,
      nomDepart: domaine.nomDepart,
      respDepart: domaine.respDepart,
      telRespDepart: domaine.telRespDepart,
      emailRespDepart: domaine.emailRespDepart,
      nomDirection: domaine.nomDirection,
      respDirection: domaine.respDirection,
      telDirecteur: domaine.telDirecteur,
      emailDirecteur: domaine.emailDirecteur
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const domaine = this.createFromForm();
    if (domaine.id !== undefined) {
      this.subscribeToSaveResponse(this.domaineService.update(domaine));
    } else {
      this.subscribeToSaveResponse(this.domaineService.create(domaine));
    }
  }

  private createFromForm(): IDomaine {
    return {
      ...new Domaine(),
      id: this.editForm.get(['id']).value,
      nom: this.editForm.get(['nom']).value,
      nomService: this.editForm.get(['nomService']).value,
      respService: this.editForm.get(['respService']).value,
      telRespService: this.editForm.get(['telRespService']).value,
      emailRespService: this.editForm.get(['emailRespService']).value,
      nomDivision: this.editForm.get(['nomDivision']).value,
      respDivision: this.editForm.get(['respDivision']).value,
      telRespDivision: this.editForm.get(['telRespDivision']).value,
      emailRespDivision: this.editForm.get(['emailRespDivision']).value,
      nomDepart: this.editForm.get(['nomDepart']).value,
      respDepart: this.editForm.get(['respDepart']).value,
      telRespDepart: this.editForm.get(['telRespDepart']).value,
      emailRespDepart: this.editForm.get(['emailRespDepart']).value,
      nomDirection: this.editForm.get(['nomDirection']).value,
      respDirection: this.editForm.get(['respDirection']).value,
      telDirecteur: this.editForm.get(['telDirecteur']).value,
      emailDirecteur: this.editForm.get(['emailDirecteur']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDomaine>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
