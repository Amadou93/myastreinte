import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEmploye, Employe } from 'app/shared/model/employe.model';
import { EmployeService } from './employe.service';
import { IEquipe } from 'app/shared/model/equipe.model';
import { EquipeService } from 'app/entities/equipe';
import { IDomaine } from 'app/shared/model/domaine.model';
import { DomaineService } from 'app/entities/domaine';

@Component({
  selector: 'jhi-employe-update',
  templateUrl: './employe-update.component.html'
})
export class EmployeUpdateComponent implements OnInit {
  isSaving: boolean;

  equipes: IEquipe[];

  domaines: IDomaine[];

  editForm = this.fb.group({
    id: [],
    matricul: [null, [Validators.required]],
    firstName: [null, [Validators.minLength(3)]],
    lastName: [null, [Validators.minLength(3)]],
    email: [null, [Validators.minLength(3)]],
    phoneNumber: [],
    equipeId: [],
    domaineId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected employeService: EmployeService,
    protected equipeService: EquipeService,
    protected domaineService: DomaineService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ employe }) => {
      this.updateForm(employe);
    });
    this.equipeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEquipe[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEquipe[]>) => response.body)
      )
      .subscribe((res: IEquipe[]) => (this.equipes = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.domaineService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IDomaine[]>) => mayBeOk.ok),
        map((response: HttpResponse<IDomaine[]>) => response.body)
      )
      .subscribe((res: IDomaine[]) => (this.domaines = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(employe: IEmploye) {
    this.editForm.patchValue({
      id: employe.id,
      matricul: employe.matricul,
      firstName: employe.firstName,
      lastName: employe.lastName,
      email: employe.email,
      phoneNumber: employe.phoneNumber,
      equipeId: employe.equipeId,
      domaineId: employe.domaineId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const employe = this.createFromForm();
    if (employe.id !== undefined) {
      this.subscribeToSaveResponse(this.employeService.update(employe));
    } else {
      this.subscribeToSaveResponse(this.employeService.create(employe));
    }
  }

  private createFromForm(): IEmploye {
    return {
      ...new Employe(),
      id: this.editForm.get(['id']).value,
      matricul: this.editForm.get(['matricul']).value,
      firstName: this.editForm.get(['firstName']).value,
      lastName: this.editForm.get(['lastName']).value,
      email: this.editForm.get(['email']).value,
      phoneNumber: this.editForm.get(['phoneNumber']).value,
      equipeId: this.editForm.get(['equipeId']).value,
      domaineId: this.editForm.get(['domaineId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmploye>>) {
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

  trackDomaineById(index: number, item: IDomaine) {
    return item.id;
  }
}
