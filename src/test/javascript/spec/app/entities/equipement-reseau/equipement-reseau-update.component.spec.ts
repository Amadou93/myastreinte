/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { MyAstreinteTestModule } from '../../../test.module';
import { EquipementReseauUpdateComponent } from 'app/entities/equipement-reseau/equipement-reseau-update.component';
import { EquipementReseauService } from 'app/entities/equipement-reseau/equipement-reseau.service';
import { EquipementReseau } from 'app/shared/model/equipement-reseau.model';

describe('Component Tests', () => {
  describe('EquipementReseau Management Update Component', () => {
    let comp: EquipementReseauUpdateComponent;
    let fixture: ComponentFixture<EquipementReseauUpdateComponent>;
    let service: EquipementReseauService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyAstreinteTestModule],
        declarations: [EquipementReseauUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EquipementReseauUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EquipementReseauUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EquipementReseauService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EquipementReseau(123);
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
        const entity = new EquipementReseau();
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
