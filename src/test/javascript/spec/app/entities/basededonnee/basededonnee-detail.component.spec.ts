/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyAstreinteTestModule } from '../../../test.module';
import { BasededonneeDetailComponent } from 'app/entities/basededonnee/basededonnee-detail.component';
import { Basededonnee } from 'app/shared/model/basededonnee.model';

describe('Component Tests', () => {
  describe('Basededonnee Management Detail Component', () => {
    let comp: BasededonneeDetailComponent;
    let fixture: ComponentFixture<BasededonneeDetailComponent>;
    const route = ({ data: of({ basededonnee: new Basededonnee(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyAstreinteTestModule],
        declarations: [BasededonneeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BasededonneeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BasededonneeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.basededonnee).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
