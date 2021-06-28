import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyAstreinteSharedModule } from 'app/shared';
import {
  ServeurComponent,
  ServeurDetailComponent,
  ServeurUpdateComponent,
  ServeurDeletePopupComponent,
  ServeurDeleteDialogComponent,
  serveurRoute,
  serveurPopupRoute
} from './';

const ENTITY_STATES = [...serveurRoute, ...serveurPopupRoute];

@NgModule({
  imports: [MyAstreinteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ServeurComponent,
    ServeurDetailComponent,
    ServeurUpdateComponent,
    ServeurDeleteDialogComponent,
    ServeurDeletePopupComponent
  ],
  entryComponents: [ServeurComponent, ServeurUpdateComponent, ServeurDeleteDialogComponent, ServeurDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MyAstreinteServeurModule {}
