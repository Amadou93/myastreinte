import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyAstreinteSharedModule } from 'app/shared';
import {
  AbsenceComponent,
  AbsenceDetailComponent,
  AbsenceUpdateComponent,
  AbsenceDeletePopupComponent,
  AbsenceDeleteDialogComponent,
  absenceRoute,
  absencePopupRoute
} from './';

const ENTITY_STATES = [...absenceRoute, ...absencePopupRoute];

@NgModule({
  imports: [MyAstreinteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AbsenceComponent,
    AbsenceDetailComponent,
    AbsenceUpdateComponent,
    AbsenceDeleteDialogComponent,
    AbsenceDeletePopupComponent
  ],
  entryComponents: [AbsenceComponent, AbsenceUpdateComponent, AbsenceDeleteDialogComponent, AbsenceDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MyAstreinteAbsenceModule {}
