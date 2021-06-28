import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDepartement, Departement } from 'app/shared/model/departement.model';
import { DepartementService } from './departement.service';

@Component({
  selector: 'jhi-departement-update',
  templateUrl: './departement-update.component.html'
})
export class DepartementUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    departementName: [null, [Validators.required]]
  });

  constructor(protected departementService: DepartementService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ departement }) => {
      this.updateForm(departement);
    });
  }

  updateForm(departement: IDepartement) {
    this.editForm.patchValue({
      id: departement.id,
      departementName: departement.departementName
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const departement = this.createFromForm();
    if (departement.id !== undefined) {
      this.subscribeToSaveResponse(this.departementService.update(departement));
    } else {
      this.subscribeToSaveResponse(this.departementService.create(departement));
    }
  }

  private createFromForm(): IDepartement {
    return {
      ...new Departement(),
      id: this.editForm.get(['id']).value,
      departementName: this.editForm.get(['departementName']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepartement>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
