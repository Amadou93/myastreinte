import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Equipe } from 'app/shared/model/equipe.model';
import { EquipeService } from './equipe.service';
import { EquipeComponent } from './equipe.component';
import { EquipeDetailComponent } from './equipe-detail.component';
import { EquipeUpdateComponent } from './equipe-update.component';
import { EquipeDeletePopupComponent } from './equipe-delete-dialog.component';
import { IEquipe } from 'app/shared/model/equipe.model';

@Injectable({ providedIn: 'root' })
export class EquipeResolve implements Resolve<IEquipe> {
  constructor(private service: EquipeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEquipe> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Equipe>) => response.ok),
        map((equipe: HttpResponse<Equipe>) => equipe.body)
      );
    }
    return of(new Equipe());
  }
}

export const equipeRoute: Routes = [
  {
    path: '',
    component: EquipeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Equipes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EquipeDetailComponent,
    resolve: {
      equipe: EquipeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Equipes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EquipeUpdateComponent,
    resolve: {
      equipe: EquipeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Equipes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EquipeUpdateComponent,
    resolve: {
      equipe: EquipeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Equipes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const equipePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: EquipeDeletePopupComponent,
    resolve: {
      equipe: EquipeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Equipes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
