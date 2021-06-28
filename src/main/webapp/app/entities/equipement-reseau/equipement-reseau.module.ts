import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyAstreinteSharedModule } from 'app/shared';
import {
  EquipementReseauComponent,
  EquipementReseauDetailComponent,
  EquipementReseauUpdateComponent,
  EquipementReseauDeletePopupComponent,
  EquipementReseauDeleteDialogComponent,
  equipementReseauRoute,
  equipementReseauPopupRoute
} from './';

const ENTITY_STATES = [...equipementReseauRoute, ...equipementReseauPopupRoute];

@NgModule({
  imports: [MyAstreinteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    EquipementReseauComponent,
    EquipementReseauDetailComponent,
    EquipementReseauUpdateComponent,
    EquipementReseauDeleteDialogComponent,
    EquipementReseauDeletePopupComponent
  ],
  entryComponents: [
    EquipementReseauComponent,
    EquipementReseauUpdateComponent,
    EquipementReseauDeleteDialogComponent,
    EquipementReseauDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MyAstreinteEquipementReseauModule {}
