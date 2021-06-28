/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { MyAstreinteTestModule } from '../../../test.module';
import { BasededonneeUpdateComponent } from 'app/entities/basededonnee/basededonnee-update.component';
import { BasededonneeService } from 'app/entities/basededonnee/basededonnee.service';
import { Basededonnee } from 'app/shared/model/basededonnee.model';

describe('Component Tests', () => {
  describe('Basededonnee Management Update Component', () => {
    let comp: BasededonneeUpdateComponent;
    let fixture: ComponentFixture<BasededonneeUpdateComponent>;
    let service: BasededonneeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyAstreinteTestModule],
        declarations: [BasededonneeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BasededonneeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BasededonneeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BasededonneeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Basededonnee(123);
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
        const entity = new Basededonnee();
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
