import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IServeur, Serveur } from 'app/shared/model/serveur.model';
import { ServeurService } from './serveur.service';
import { IEquipe } from 'app/shared/model/equipe.model';
import { EquipeService } from 'app/entities/equipe';

@Component({
  selector: 'jhi-serveur-update',
  templateUrl: './serveur-update.component.html'
})
export class ServeurUpdateComponent implements OnInit {
  isSaving: boolean;

  equipes: IEquipe[];

  editForm = this.fb.group({
    id: [],
    nameServeur: [null, [Validators.required, Validators.minLength(3)]],
    cpu: [null, [Validators.minLength(15)]],
    memory: [null, [Validators.required]],
    disque: [null, [Validators.required]],
    avalibity: [null, [Validators.required]],
    equipeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected serveurService: ServeurService,
    protected equipeService: EquipeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ serveur }) => {
      this.updateForm(serveur);
    });
    this.equipeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEquipe[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEquipe[]>) => response.body)
      )
      .subscribe((res: IEquipe[]) => (this.equipes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(serveur: IServeur) {
    this.editForm.patchValue({
      id: serveur.id,
      nameServeur: serveur.nameServeur,
      cpu: serveur.cpu,
      memory: serveur.memory,
      disque: serveur.disque,
      avalibity: serveur.avalibity,
      equipeId: serveur.equipeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const serveur = this.createFromForm();
    if (serveur.id !== undefined) {
      this.subscribeToSaveResponse(this.serveurService.update(serveur));
    } else {
      this.subscribeToSaveResponse(this.serveurService.create(serveur));
    }
  }

  private createFromForm(): IServeur {
    return {
      ...new Serveur(),
      id: this.editForm.get(['id']).value,
      nameServeur: this.editForm.get(['nameServeur']).value,
      cpu: this.editForm.get(['cpu']).value,
      memory: this.editForm.get(['memory']).value,
      disque: this.editForm.get(['disque']).value,
      avalibity: this.editForm.get(['avalibity']).value,
      equipeId: this.editForm.get(['equipeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServeur>>) {
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
