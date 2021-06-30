import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyAstreinteSharedModule } from 'app/shared';
import {
  DomaineComponent,
  DomaineDetailComponent,
  DomaineUpdateComponent,
  DomaineDeletePopupComponent,
  DomaineDeleteDialogComponent,
  domaineRoute,
  domainePopupRoute
} from './';

const ENTITY_STATES = [...domaineRoute, ...domainePopupRoute];

@NgModule({
  imports: [MyAstreinteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DomaineComponent,
    DomaineDetailComponent,
    DomaineUpdateComponent,
    DomaineDeleteDialogComponent,
    DomaineDeletePopupComponent
  ],
  entryComponents: [DomaineComponent, DomaineUpdateComponent, DomaineDeleteDialogComponent, DomaineDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MyAstreinteDomaineModule {}
