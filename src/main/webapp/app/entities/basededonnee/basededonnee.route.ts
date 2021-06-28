import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Basededonnee } from 'app/shared/model/basededonnee.model';
import { BasededonneeService } from './basededonnee.service';
import { BasededonneeComponent } from './basededonnee.component';
import { BasededonneeDetailComponent } from './basededonnee-detail.component';
import { BasededonneeUpdateComponent } from './basededonnee-update.component';
import { BasededonneeDeletePopupComponent } from './basededonnee-delete-dialog.component';
import { IBasededonnee } from 'app/shared/model/basededonnee.model';

@Injectable({ providedIn: 'root' })
export class BasededonneeResolve implements Resolve<IBasededonnee> {
  constructor(private service: BasededonneeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBasededonnee> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Basededonnee>) => response.ok),
        map((basededonnee: HttpResponse<Basededonnee>) => basededonnee.body)
      );
    }
    return of(new Basededonnee());
  }
}

export const basededonneeRoute: Routes = [
  {
    path: '',
    component: BasededonneeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Basededonnees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BasededonneeDetailComponent,
    resolve: {
      basededonnee: BasededonneeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Basededonnees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BasededonneeUpdateComponent,
    resolve: {
      basededonnee: BasededonneeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Basededonnees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BasededonneeUpdateComponent,
    resolve: {
      basededonnee: BasededonneeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Basededonnees'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const basededonneePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BasededonneeDeletePopupComponent,
    resolve: {
      basededonnee: BasededonneeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Basededonnees'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
