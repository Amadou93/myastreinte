import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Division } from 'app/shared/model/division.model';
import { DivisionService } from './division.service';
import { DivisionComponent } from './division.component';
import { DivisionDetailComponent } from './division-detail.component';
import { DivisionUpdateComponent } from './division-update.component';
import { DivisionDeletePopupComponent } from './division-delete-dialog.component';
import { IDivision } from 'app/shared/model/division.model';

@Injectable({ providedIn: 'root' })
export class DivisionResolve implements Resolve<IDivision> {
  constructor(private service: DivisionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDivision> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Division>) => response.ok),
        map((division: HttpResponse<Division>) => division.body)
      );
    }
    return of(new Division());
  }
}

export const divisionRoute: Routes = [
  {
    path: '',
    component: DivisionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Divisions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DivisionDetailComponent,
    resolve: {
      division: DivisionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Divisions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DivisionUpdateComponent,
    resolve: {
      division: DivisionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Divisions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DivisionUpdateComponent,
    resolve: {
      division: DivisionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Divisions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const divisionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DivisionDeletePopupComponent,
    resolve: {
      division: DivisionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Divisions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
