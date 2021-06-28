import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { INotification, Notification } from 'app/shared/model/notification.model';
import { NotificationService } from './notification.service';
import { IEmploye } from 'app/shared/model/employe.model';
import { EmployeService } from 'app/entities/employe';

@Component({
  selector: 'jhi-notification-update',
  templateUrl: './notification-update.component.html'
})
export class NotificationUpdateComponent implements OnInit {
  isSaving: boolean;

  employes: IEmploye[];

  editForm = this.fb.group({
    id: [],
    date: [null, [Validators.required]],
    type: [null, [Validators.required]],
    level: [],
    dispositifName: [null, [Validators.minLength(3)]],
    state: [],
    groupe: [null, [Validators.minLength(3)]],
    astreinteName: [null, [Validators.minLength(3)]],
    availiblity: [],
    employeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected notificationService: NotificationService,
    protected employeService: EmployeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ notification }) => {
      this.updateForm(notification);
    });
    this.employeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEmploye[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEmploye[]>) => response.body)
      )
      .subscribe((res: IEmploye[]) => (this.employes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(notification: INotification) {
    this.editForm.patchValue({
      id: notification.id,
      date: notification.date != null ? notification.date.format(DATE_TIME_FORMAT) : null,
      type: notification.type,
      level: notification.level,
      dispositifName: notification.dispositifName,
      state: notification.state,
      groupe: notification.groupe,
      astreinteName: notification.astreinteName,
      availiblity: notification.availiblity != null ? notification.availiblity.format(DATE_TIME_FORMAT) : null,
      employeId: notification.employeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const notification = this.createFromForm();
    if (notification.id !== undefined) {
      this.subscribeToSaveResponse(this.notificationService.update(notification));
    } else {
      this.subscribeToSaveResponse(this.notificationService.create(notification));
    }
  }

  private createFromForm(): INotification {
    return {
      ...new Notification(),
      id: this.editForm.get(['id']).value,
      date: this.editForm.get(['date']).value != null ? moment(this.editForm.get(['date']).value, DATE_TIME_FORMAT) : undefined,
      type: this.editForm.get(['type']).value,
      level: this.editForm.get(['level']).value,
      dispositifName: this.editForm.get(['dispositifName']).value,
      state: this.editForm.get(['state']).value,
      groupe: this.editForm.get(['groupe']).value,
      astreinteName: this.editForm.get(['astreinteName']).value,
      availiblity:
        this.editForm.get(['availiblity']).value != null ? moment(this.editForm.get(['availiblity']).value, DATE_TIME_FORMAT) : undefined,
      employeId: this.editForm.get(['employeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotification>>) {
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
