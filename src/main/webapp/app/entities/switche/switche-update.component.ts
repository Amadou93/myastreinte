import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISwitche, Switche } from 'app/shared/model/switche.model';
import { SwitcheService } from './switche.service';
import { IEquipe } from 'app/shared/model/equipe.model';
import { EquipeService } from 'app/entities/equipe';

@Component({
  selector: 'jhi-switche-update',
  templateUrl: './switche-update.component.html'
})
export class SwitcheUpdateComponent implements OnInit {
  isSaving: boolean;

  equipes: IEquipe[];

  editForm = this.fb.group({
    id: [],
    ipAddress: [null, [Validators.required]],
    equipeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected switcheService: SwitcheService,
    protected equipeService: EquipeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ switche }) => {
      this.updateForm(switche);
    });
    this.equipeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEquipe[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEquipe[]>) => response.body)
      )
      .subscribe((res: IEquipe[]) => (this.equipes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(switche: ISwitche) {
    this.editForm.patchValue({
      id: switche.id,
      ipAddress: switche.ipAddress,
      equipeId: switche.equipeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const switche = this.createFromForm();
    if (switche.id !== undefined) {
      this.subscribeToSaveResponse(this.switcheService.update(switche));
    } else {
      this.subscribeToSaveResponse(this.switcheService.create(switche));
    }
  }

  private createFromForm(): ISwitche {
    return {
      ...new Switche(),
      id: this.editForm.get(['id']).value,
      ipAddress: this.editForm.get(['ipAddress']).value,
      equipeId: this.editForm.get(['equipeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISwitche>>) {
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
