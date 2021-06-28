/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { MyAstreinteTestModule } from '../../../test.module';
import { AstreinteUpdateComponent } from 'app/entities/astreinte/astreinte-update.component';
import { AstreinteService } from 'app/entities/astreinte/astreinte.service';
import { Astreinte } from 'app/shared/model/astreinte.model';

describe('Component Tests', () => {
  describe('Astreinte Management Update Component', () => {
    let comp: AstreinteUpdateComponent;
    let fixture: ComponentFixture<AstreinteUpdateComponent>;
    let service: AstreinteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyAstreinteTestModule],
        declarations: [AstreinteUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AstreinteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AstreinteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AstreinteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Astreinte(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Astreinte();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
