import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IBasededonnee, Basededonnee } from 'app/shared/model/basededonnee.model';
import { BasededonneeService } from './basededonnee.service';
import { IEquipe } from 'app/shared/model/equipe.model';
import { EquipeService } from 'app/entities/equipe';

@Component({
  selector: 'jhi-basededonnee-update',
  templateUrl: './basededonnee-update.component.html'
})
export class BasededonneeUpdateComponent implements OnInit {
  isSaving: boolean;

  equipes: IEquipe[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.minLength(2)]],
    baseType: [null, [Validators.required, Validators.minLength(3)]],
    memory: [null, [Validators.required]],
    equipeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected basededonneeService: BasededonneeService,
    protected equipeService: EquipeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ basededonnee }) => {
      this.updateForm(basededonnee);
    });
    this.equipeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEquipe[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEquipe[]>) => response.body)
      )
      .subscribe((res: IEquipe[]) => (this.equipes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(basededonnee: IBasededonnee) {
    this.editForm.patchValue({
      id: basededonnee.id,
      name: basededonnee.name,
      baseType: basededonnee.baseType,
      memory: basededonnee.memory,
      equipeId: basededonnee.equipeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const basededonnee = this.createFromForm();
    if (basededonnee.id !== undefined) {
      this.subscribeToSaveResponse(this.basededonneeService.update(basededonnee));
    } else {
      this.subscribeToSaveResponse(this.basededonneeService.create(basededonnee));
    }
  }

  private createFromForm(): IBasededonnee {
    return {
      ...new Basededonnee(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      baseType: this.editForm.get(['baseType']).value,
      memory: this.editForm.get(['memory']).value,
      equipeId: this.editForm.get(['equipeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBasededonnee>>) {
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
