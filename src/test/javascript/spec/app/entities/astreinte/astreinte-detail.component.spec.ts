/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyAstreinteTestModule } from '../../../test.module';
import { AstreinteDetailComponent } from 'app/entities/astreinte/astreinte-detail.component';
import { Astreinte } from 'app/shared/model/astreinte.model';

describe('Component Tests', () => {
  describe('Astreinte Management Detail Component', () => {
    let comp: AstreinteDetailComponent;
    let fixture: ComponentFixture<AstreinteDetailComponent>;
    const route = ({ data: of({ astreinte: new Astreinte(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyAstreinteTestModule],
        declarations: [AstreinteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AstreinteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AstreinteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.astreinte).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
