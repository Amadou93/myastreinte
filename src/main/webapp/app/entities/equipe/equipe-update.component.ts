import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEquipe, Equipe } from 'app/shared/model/equipe.model';
import { EquipeService } from './equipe.service';
import { IDivision } from 'app/shared/model/division.model';
import { DivisionService } from 'app/entities/division';

@Component({
  selector: 'jhi-equipe-update',
  templateUrl: './equipe-update.component.html'
})
export class EquipeUpdateComponent implements OnInit {
  isSaving: boolean;

  divisions: IDivision[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.minLength(3)]],
    divisionId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected equipeService: EquipeService,
    protected divisionService: DivisionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ equipe }) => {
      this.updateForm(equipe);
    });
    this.divisionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IDivision[]>) => mayBeOk.ok),
        map((response: HttpResponse<IDivision[]>) => response.body)
      )
      .subscribe((res: IDivision[]) => (this.divisions = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(equipe: IEquipe) {
    this.editForm.patchValue({
      id: equipe.id,
      name: equipe.name,
      divisionId: equipe.divisionId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const equipe = this.createFromForm();
    if (equipe.id !== undefined) {
      this.subscribeToSaveResponse(this.equipeService.update(equipe));
    } else {
      this.subscribeToSaveResponse(this.equipeService.create(equipe));
    }
  }

  private createFromForm(): IEquipe {
    return {
      ...new Equipe(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      divisionId: this.editForm.get(['divisionId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEquipe>>) {
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

  trackDivisionById(index: number, item: IDivision) {
    return item.id;
  }
}
