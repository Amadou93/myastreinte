import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EquipementReseau } from 'app/shared/model/equipement-reseau.model';
import { EquipementReseauService } from './equipement-reseau.service';
import { EquipementReseauComponent } from './equipement-reseau.component';
import { EquipementReseauDetailComponent } from './equipement-reseau-detail.component';
import { EquipementReseauUpdateComponent } from './equipement-reseau-update.component';
import { EquipementReseauDeletePopupComponent } from './equipement-reseau-delete-dialog.component';
import { IEquipementReseau } from 'app/shared/model/equipement-reseau.model';

@Injectable({ providedIn: 'root' })
export class EquipementReseauResolve implements Resolve<IEquipementReseau> {
  constructor(private service: EquipementReseauService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEquipementReseau> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<EquipementReseau>) => response.ok),
        map((equipementReseau: HttpResponse<EquipementReseau>) => equipementReseau.body)
      );
    }
    return of(new EquipementReseau());
  }
}

export const equipementReseauRoute: Routes = [
  {
    path: '',
    component: EquipementReseauComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'EquipementReseaus'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EquipementReseauDetailComponent,
    resolve: {
      equipementReseau: EquipementReseauResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EquipementReseaus'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EquipementReseauUpdateComponent,
    resolve: {
      equipementReseau: EquipementReseauResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EquipementReseaus'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EquipementReseauUpdateComponent,
    resolve: {
      equipementReseau: EquipementReseauResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EquipementReseaus'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const equipementReseauPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: EquipementReseauDeletePopupComponent,
    resolve: {
      equipementReseau: EquipementReseauResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EquipementReseaus'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
