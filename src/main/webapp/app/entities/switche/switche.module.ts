import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyAstreinteSharedModule } from 'app/shared';
import {
  SwitcheComponent,
  SwitcheDetailComponent,
  SwitcheUpdateComponent,
  SwitcheDeletePopupComponent,
  SwitcheDeleteDialogComponent,
  switcheRoute,
  switchePopupRoute
} from './';

const ENTITY_STATES = [...switcheRoute, ...switchePopupRoute];

@NgModule({
  imports: [MyAstreinteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SwitcheComponent,
    SwitcheDetailComponent,
    SwitcheUpdateComponent,
    SwitcheDeleteDialogComponent,
    SwitcheDeletePopupComponent
  ],
  entryComponents: [SwitcheComponent, SwitcheUpdateComponent, SwitcheDeleteDialogComponent, SwitcheDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MyAstreinteSwitcheModule {}
