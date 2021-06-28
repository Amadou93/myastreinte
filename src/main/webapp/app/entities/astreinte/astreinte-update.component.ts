import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAstreinte, Astreinte } from 'app/shared/model/astreinte.model';
import { AstreinteService } from './astreinte.service';
import { IEmploye } from 'app/shared/model/employe.model';
import { EmployeService } from 'app/entities/employe';
import { FileUploader } from 'ng2-file-upload';

@Component({
  selector: 'jhi-astreinte-update',
  templateUrl: './astreinte-update.component.html'
})
export class AstreinteUpdateComponent implements OnInit {
  isSaving: boolean;

  employes: IEmploye[];

  editForm = this.fb.group({
    id: [],
    matricul: [null, [Validators.required]],
    year: [],
    numberWeek: [null, [Validators.required]],
    employeId: []
  });
  public uploader: FileUploader = new FileUploader({ url: '', itemAlias: 'csvFile' });
  private file: File;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected astreinteService: AstreinteService,
    protected employeService: EmployeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ astreinte }) => {
      this.updateForm(astreinte);
    });
    this.employeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEmploye[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEmploye[]>) => response.body)
      )
      .subscribe((res: IEmploye[]) => (this.employes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(astreinte: IAstreinte) {
    this.editForm.patchValue({
      id: astreinte.id,
      matricul: astreinte.matricul,
      year: astreinte.year,
      numberWeek: astreinte.numberWeek,
      employeId: astreinte.employeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const astreinte = this.createFromForm();
    if (astreinte.id !== undefined) {
      this.subscribeToSaveResponse(this.astreinteService.update(astreinte));
    } else {
      this.subscribeToSaveResponse(this.astreinteService.create(astreinte));
    }
  }

  private createFromForm(): IAstreinte {
    return {
      ...new Astreinte(),
      id: this.editForm.get(['id']).value,
      matricul: this.editForm.get(['matricul']).value,
      year: this.editForm.get(['year']).value,
      numberWeek: this.editForm.get(['numberWeek']).value,
      employeId: this.editForm.get(['employeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAstreinte>>) {
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

  onFileChange(event) {
    this.file = event.target.files.length > 0 ? event.target.files[0] : null;
  }

  uploadFile() {
    const formData = new FormData();
    formData.append('file', this.file);
    this.subscribeToSaveResponse(this.astreinteService.upload(formData));
  }
}
