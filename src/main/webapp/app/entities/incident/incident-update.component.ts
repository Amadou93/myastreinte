import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IIncident, Incident } from 'app/shared/model/incident.model';
import { IncidentService } from './incident.service';
import { IEquipe } from 'app/shared/model/equipe.model';
import { EquipeService } from 'app/entities/equipe';

@Component({
  selector: 'jhi-incident-update',
  templateUrl: './incident-update.component.html'
})
export class IncidentUpdateComponent implements OnInit {
  isSaving: boolean;

  equipes: IEquipe[];

  editForm = this.fb.group({
    id: [],
    date: [null, [Validators.required]],
    type: [null, [Validators.required, Validators.minLength(3)]],
    criticite: [null, [Validators.minLength(3)]],
    sla: [],
    description: [null, [Validators.minLength(3)]],
    equipeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected incidentService: IncidentService,
    protected equipeService: EquipeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ incident }) => {
      this.updateForm(incident);
    });
    this.equipeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEquipe[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEquipe[]>) => response.body)
      )
      .subscribe((res: IEquipe[]) => (this.equipes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(incident: IIncident) {
    this.editForm.patchValue({
      id: incident.id,
      date: incident.date != null ? incident.date.format(DATE_TIME_FORMAT) : null,
      type: incident.type,
      criticite: incident.criticite,
      sla: incident.sla,
      description: incident.description,
      equipeId: incident.equipeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const incident = this.createFromForm();
    if (incident.id !== undefined) {
      this.subscribeToSaveResponse(this.incidentService.update(incident));
    } else {
      this.subscribeToSaveResponse(this.incidentService.create(incident));
    }
  }

  private createFromForm(): IIncident {
    return {
      ...new Incident(),
      id: this.editForm.get(['id']).value,
      date: this.editForm.get(['date']).value != null ? moment(this.editForm.get(['date']).value, DATE_TIME_FORMAT) : undefined,
      type: this.editForm.get(['type']).value,
      criticite: this.editForm.get(['criticite']).value,
      sla: this.editForm.get(['sla']).value,
      description: this.editForm.get(['description']).value,
      equipeId: this.editForm.get(['equipeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIncident>>) {
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
