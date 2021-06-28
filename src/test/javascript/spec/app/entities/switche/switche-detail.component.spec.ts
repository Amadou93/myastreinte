/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyAstreinteTestModule } from '../../../test.module';
import { SwitcheDetailComponent } from 'app/entities/switche/switche-detail.component';
import { Switche } from 'app/shared/model/switche.model';

describe('Component Tests', () => {
  describe('Switche Management Detail Component', () => {
    let comp: SwitcheDetailComponent;
    let fixture: ComponentFixture<SwitcheDetailComponent>;
    const route = ({ data: of({ switche: new Switche(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyAstreinteTestModule],
        declarations: [SwitcheDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SwitcheDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SwitcheDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.switche).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
