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
    nom: [],
    service: [],
    responsableService: [],
    numTelResponsableService: [],
    emailResponsableService: [],
    division: [],
    responsableDivision: [],
    numResponsableDivision: [],
    emailResponsableDivision: [],
    departement: [],
    responsableDepartement: [],
    numeroTelResponsableDepartement: [],
    emailResponsableDepartement: [],
    direction: [],
    responsableDirection: [],
    numeroTelDirecteur: [],
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
      service: domaine.service,
      responsableService: domaine.responsableService,
      numTelResponsableService: domaine.numTelResponsableService,
      emailResponsableService: domaine.emailResponsableService,
      division: domaine.division,
      responsableDivision: domaine.responsableDivision,
      numResponsableDivision: domaine.numResponsableDivision,
      emailResponsableDivision: domaine.emailResponsableDivision,
      departement: domaine.departement,
      responsableDepartement: domaine.responsableDepartement,
      numeroTelResponsableDepartement: domaine.numeroTelResponsableDepartement,
      emailResponsableDepartement: domaine.emailResponsableDepartement,
      direction: domaine.direction,
      responsableDirection: domaine.responsableDirection,
      numeroTelDirecteur: domaine.numeroTelDirecteur,
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
      service: this.editForm.get(['service']).value,
      responsableService: this.editForm.get(['responsableService']).value,
      numTelResponsableService: this.editForm.get(['numTelResponsableService']).value,
      emailResponsableService: this.editForm.get(['emailResponsableService']).value,
      division: this.editForm.get(['division']).value,
      responsableDivision: this.editForm.get(['responsableDivision']).value,
      numResponsableDivision: this.editForm.get(['numResponsableDivision']).value,
      emailResponsableDivision: this.editForm.get(['emailResponsableDivision']).value,
      departement: this.editForm.get(['departement']).value,
      responsableDepartement: this.editForm.get(['responsableDepartement']).value,
      numeroTelResponsableDepartement: this.editForm.get(['numeroTelResponsableDepartement']).value,
      emailResponsableDepartement: this.editForm.get(['emailResponsableDepartement']).value,
      direction: this.editForm.get(['direction']).value,
      responsableDirection: this.editForm.get(['responsableDirection']).value,
      numeroTelDirecteur: this.editForm.get(['numeroTelDirecteur']).value,
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
