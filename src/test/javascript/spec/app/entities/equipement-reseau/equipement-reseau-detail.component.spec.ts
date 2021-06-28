/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyAstreinteTestModule } from '../../../test.module';
import { EquipementReseauDetailComponent } from 'app/entities/equipement-reseau/equipement-reseau-detail.component';
import { EquipementReseau } from 'app/shared/model/equipement-reseau.model';

describe('Component Tests', () => {
  describe('EquipementReseau Management Detail Component', () => {
    let comp: EquipementReseauDetailComponent;
    let fixture: ComponentFixture<EquipementReseauDetailComponent>;
    const route = ({ data: of({ equipementReseau: new EquipementReseau(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyAstreinteTestModule],
        declarations: [EquipementReseauDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EquipementReseauDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EquipementReseauDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.equipementReseau).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
