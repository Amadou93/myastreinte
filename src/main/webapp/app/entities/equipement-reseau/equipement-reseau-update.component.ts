import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEquipementReseau, EquipementReseau } from 'app/shared/model/equipement-reseau.model';
import { EquipementReseauService } from './equipement-reseau.service';
import { IEquipe } from 'app/shared/model/equipe.model';
import { EquipeService } from 'app/entities/equipe';

@Component({
  selector: 'jhi-equipement-reseau-update',
  templateUrl: './equipement-reseau-update.component.html'
})
export class EquipementReseauUpdateComponent implements OnInit {
  isSaving: boolean;

  equipes: IEquipe[];

  editForm = this.fb.group({
    id: [],
    equipementName: [null, [Validators.minLength(3)]],
    type: [null, [Validators.minLength(3)]],
    ipAddress: [],
    version: [],
    equipeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected equipementReseauService: EquipementReseauService,
    protected equipeService: EquipeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ equipementReseau }) => {
      this.updateForm(equipementReseau);
    });
    this.equipeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEquipe[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEquipe[]>) => response.body)
      )
      .subscribe((res: IEquipe[]) => (this.equipes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(equipementReseau: IEquipementReseau) {
    this.editForm.patchValue({
      id: equipementReseau.id,
      equipementName: equipementReseau.equipementName,
      type: equipementReseau.type,
      ipAddress: equipementReseau.ipAddress,
      version: equipementReseau.version,
      equipeId: equipementReseau.equipeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const equipementReseau = this.createFromForm();
    if (equipementReseau.id !== undefined) {
      this.subscribeToSaveResponse(this.equipementReseauService.update(equipementReseau));
    } else {
      this.subscribeToSaveResponse(this.equipementReseauService.create(equipementReseau));
    }
  }

  private createFromForm(): IEquipementReseau {
    return {
      ...new EquipementReseau(),
      id: this.editForm.get(['id']).value,
      equipementName: this.editForm.get(['equipementName']).value,
      type: this.editForm.get(['type']).value,
      ipAddress: this.editForm.get(['ipAddress']).value,
      version: this.editForm.get(['version']).value,
      equipeId: this.editForm.get(['equipeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEquipementReseau>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackEquipeById(index: number, item: IEquipe) {
    return item.id;
  }
}
