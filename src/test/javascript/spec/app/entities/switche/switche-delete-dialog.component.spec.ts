/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MyAstreinteTestModule } from '../../../test.module';
import { SwitcheDeleteDialogComponent } from 'app/entities/switche/switche-delete-dialog.component';
import { SwitcheService } from 'app/entities/switche/switche.service';

describe('Component Tests', () => {
  describe('Switche Management Delete Component', () => {
    let comp: SwitcheDeleteDialogComponent;
    let fixture: ComponentFixture<SwitcheDeleteDialogComponent>;
    let service: SwitcheService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyAstreinteTestModule],
        declarations: [SwitcheDeleteDialogComponent]
      })
        .overrideTemplate(SwitcheDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SwitcheDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SwitcheService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
