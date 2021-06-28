import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Switche } from 'app/shared/model/switche.model';
import { SwitcheService } from './switche.service';
import { SwitcheComponent } from './switche.component';
import { SwitcheDetailComponent } from './switche-detail.component';
import { SwitcheUpdateComponent } from './switche-update.component';
import { SwitcheDeletePopupComponent } from './switche-delete-dialog.component';
import { ISwitche } from 'app/shared/model/switche.model';

@Injectable({ providedIn: 'root' })
export class SwitcheResolve implements Resolve<ISwitche> {
  constructor(private service: SwitcheService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISwitche> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Switche>) => response.ok),
        map((switche: HttpResponse<Switche>) => switche.body)
      );
    }
    return of(new Switche());
  }
}

export const switcheRoute: Routes = [
  {
    path: '',
    component: SwitcheComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Switches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SwitcheDetailComponent,
    resolve: {
      switche: SwitcheResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Switches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SwitcheUpdateComponent,
    resolve: {
      switche: SwitcheResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Switches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SwitcheUpdateComponent,
    resolve: {
      switche: SwitcheResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Switches'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const switchePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SwitcheDeletePopupComponent,
    resolve: {
      switche: SwitcheResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Switches'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
