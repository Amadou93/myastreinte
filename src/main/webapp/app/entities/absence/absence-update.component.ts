import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IAbsence, Absence } from 'app/shared/model/absence.model';
import { AbsenceService } from './absence.service';
import { IEmploye } from 'app/shared/model/employe.model';
import { EmployeService } from 'app/entities/employe';

@Component({
  selector: 'jhi-absence-update',
  templateUrl: './absence-update.component.html'
})
export class AbsenceUpdateComponent implements OnInit {
  isSaving: boolean;

  employes: IEmploye[];

  editForm = this.fb.group({
    id: [],
    startDate: [null, [Validators.required]],
    endDate: [null, [Validators.required]],
    employeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected absenceService: AbsenceService,
    protected employeService: EmployeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ absence }) => {
      this.updateForm(absence);
    });
    this.employeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEmploye[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEmploye[]>) => response.body)
      )
      .subscribe((res: IEmploye[]) => (this.employes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(absence: IAbsence) {
    this.editForm.patchValue({
      id: absence.id,
      startDate: absence.startDate != null ? absence.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: absence.endDate != null ? absence.endDate.format(DATE_TIME_FORMAT) : null,
      employeId: absence.employeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const absence = this.createFromForm();
    if (absence.id !== undefined) {
      this.subscribeToSaveResponse(this.absenceService.update(absence));
    } else {
      this.subscribeToSaveResponse(this.absenceService.create(absence));
    }
  }

  private createFromForm(): IAbsence {
    return {
      ...new Absence(),
      id: this.editForm.get(['id']).value,
      startDate:
        this.editForm.get(['startDate']).value != null ? moment(this.editForm.get(['startDate']).value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate']).value != null ? moment(this.editForm.get(['endDate']).value, DATE_TIME_FORMAT) : undefined,
      employeId: this.editForm.get(['employeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAbsence>>) {
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

  trackEmployeById(index: number, item: IEmploye) {
    return item.id;
  }
}
