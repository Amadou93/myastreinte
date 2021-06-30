import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'switche',
        loadChildren: './switche/switche.module#MyAstreinteSwitcheModule'
      },
      {
        path: 'equipement-reseau',
        loadChildren: './equipement-reseau/equipement-reseau.module#MyAstreinteEquipementReseauModule'
      },
      {
        path: 'departement',
        loadChildren: './departement/departement.module#MyAstreinteDepartementModule'
      },
      {
        path: 'basededonnee',
        loadChildren: './basededonnee/basededonnee.module#MyAstreinteBasededonneeModule'
      },
      {
        path: 'employe',
        loadChildren: './employe/employe.module#MyAstreinteEmployeModule'
      },
      {
        path: 'astreinte',
        loadChildren: './astreinte/astreinte.module#MyAstreinteAstreinteModule'
      },
      {
        path: 'absence',
        loadChildren: './absence/absence.module#MyAstreinteAbsenceModule'
      },
      {
        path: 'notification',
        loadChildren: './notification/notification.module#MyAstreinteNotificationModule'
      },
      {
        path: 'equipe',
        loadChildren: './equipe/equipe.module#MyAstreinteEquipeModule'
      },
      {
        path: 'application',
        loadChildren: './application/application.module#MyAstreinteApplicationModule'
      },
      {
        path: 'serveur',
        loadChildren: './serveur/serveur.module#MyAstreinteServeurModule'
      },
      {
        path: 'division',
        loadChildren: './division/division.module#MyAstreinteDivisionModule'
      },
      {
        path: 'incident',
        loadChildren: './incident/incident.module#MyAstreinteIncidentModule'
      },
      {
        path: 'domaine',
        loadChildren: './domaine/domaine.module#MyAstreinteDomaineModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MyAstreinteEntityModule {}
