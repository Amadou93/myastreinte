import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Astreinte } from 'app/shared/model/astreinte.model';
import { AstreinteService } from './astreinte.service';
import { AstreinteComponent } from './astreinte.component';
import { AstreinteDetailComponent } from './astreinte-detail.component';
import { AstreinteUpdateComponent } from './astreinte-update.component';
import { AstreinteDeletePopupComponent } from './astreinte-delete-dialog.component';
import { IAstreinte } from 'app/shared/model/astreinte.model';

@Injectable({ providedIn: 'root' })
export class AstreinteResolve implements Resolve<IAstreinte> {
  constructor(private service: AstreinteService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAstreinte> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Astreinte>) => response.ok),
        map((astreinte: HttpResponse<Astreinte>) => astreinte.body)
      );
    }
    return of(new Astreinte());
  }
}

export const astreinteRoute: Routes = [
  {
    path: '',
    component: AstreinteComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Astreintes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AstreinteDetailComponent,
    resolve: {
      astreinte: AstreinteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Astreintes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AstreinteUpdateComponent,
    resolve: {
      astreinte: AstreinteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Astreintes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AstreinteUpdateComponent,
    resolve: {
      astreinte: AstreinteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Astreintes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const astreintePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AstreinteDeletePopupComponent,
    resolve: {
      astreinte: AstreinteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Astreintes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
