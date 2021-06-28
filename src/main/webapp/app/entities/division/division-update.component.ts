import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IDivision, Division } from 'app/shared/model/division.model';
import { DivisionService } from './division.service';
import { IDepartement } from 'app/shared/model/departement.model';
import { DepartementService } from 'app/entities/departement';

@Component({
  selector: 'jhi-division-update',
  templateUrl: './division-update.component.html'
})
export class DivisionUpdateComponent implements OnInit {
  isSaving: boolean;

  departements: IDepartement[];

  editForm = this.fb.group({
    id: [],
    divisionName: [null, [Validators.required, Validators.minLength(3)]],
    divisionChef: [null, [Validators.required]],
    departementId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected divisionService: DivisionService,
    protected departementService: DepartementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ division }) => {
      this.updateForm(division);
    });
    this.departementService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IDepartement[]>) => mayBeOk.ok),
        map((response: HttpResponse<IDepartement[]>) => response.body)
      )
      .subscribe((res: IDepartement[]) => (this.departements = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(division: IDivision) {
    this.editForm.patchValue({
      id: division.id,
      divisionName: division.divisionName,
      divisionChef: division.divisionChef,
      departementId: division.departementId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const division = this.createFromForm();
    if (division.id !== undefined) {
      this.subscribeToSaveResponse(this.divisionService.update(division));
    } else {
      this.subscribeToSaveResponse(this.divisionService.create(division));
    }
  }

  private createFromForm(): IDivision {
    return {
      ...new Division(),
      id: this.editForm.get(['id']).value,
      divisionName: this.editForm.get(['divisionName']).value,
      divisionChef: this.editForm.get(['divisionChef']).value,
      departementId: this.editForm.get(['departementId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDivision>>) {
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

  trackDepartementById(index: number, item: IDepartement) {
    return item.id;
  }
}
