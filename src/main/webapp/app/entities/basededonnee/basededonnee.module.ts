import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyAstreinteSharedModule } from 'app/shared';
import {
  BasededonneeComponent,
  BasededonneeDetailComponent,
  BasededonneeUpdateComponent,
  BasededonneeDeletePopupComponent,
  BasededonneeDeleteDialogComponent,
  basededonneeRoute,
  basededonneePopupRoute
} from './';

const ENTITY_STATES = [...basededonneeRoute, ...basededonneePopupRoute];

@NgModule({
  imports: [MyAstreinteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BasededonneeComponent,
    BasededonneeDetailComponent,
    BasededonneeUpdateComponent,
    BasededonneeDeleteDialogComponent,
    BasededonneeDeletePopupComponent
  ],
  entryComponents: [
    BasededonneeComponent,
    BasededonneeUpdateComponent,
    BasededonneeDeleteDialogComponent,
    BasededonneeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MyAstreinteBasededonneeModule {}
