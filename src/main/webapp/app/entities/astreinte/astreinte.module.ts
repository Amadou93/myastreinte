import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FileSelectDirective } from 'ng2-file-upload';

import { MyAstreinteSharedModule } from 'app/shared';
import {
  AstreinteComponent,
  AstreinteDetailComponent,
  AstreinteUpdateComponent,
  AstreinteDeletePopupComponent,
  AstreinteDeleteDialogComponent,
  astreinteRoute,
  astreintePopupRoute
} from './';

const ENTITY_STATES = [...astreinteRoute, ...astreintePopupRoute];

@NgModule({
  imports: [MyAstreinteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AstreinteComponent,
    AstreinteDetailComponent,
    AstreinteUpdateComponent,
    AstreinteDeleteDialogComponent,
    AstreinteDeletePopupComponent
  ],
  entryComponents: [AstreinteComponent, AstreinteUpdateComponent, AstreinteDeleteDialogComponent, AstreinteDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MyAstreinteAstreinteModule {}
