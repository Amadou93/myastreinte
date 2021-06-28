/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { MyAstreinteTestModule } from '../../../test.module';
import { SwitcheUpdateComponent } from 'app/entities/switche/switche-update.component';
import { SwitcheService } from 'app/entities/switche/switche.service';
import { Switche } from 'app/shared/model/switche.model';

describe('Component Tests', () => {
  describe('Switche Management Update Component', () => {
    let comp: SwitcheUpdateComponent;
    let fixture: ComponentFixture<SwitcheUpdateComponent>;
    let service: SwitcheService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyAstreinteTestModule],
        declarations: [SwitcheUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SwitcheUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SwitcheUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SwitcheService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Switche(123);
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
        const entity = new Switche();
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
