/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MyAstreinteTestModule } from '../../../test.module';
import { AstreinteDeleteDialogComponent } from 'app/entities/astreinte/astreinte-delete-dialog.component';
import { AstreinteService } from 'app/entities/astreinte/astreinte.service';

describe('Component Tests', () => {
  describe('Astreinte Management Delete Component', () => {
    let comp: AstreinteDeleteDialogComponent;
    let fixture: ComponentFixture<AstreinteDeleteDialogComponent>;
    let service: AstreinteService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyAstreinteTestModule],
        declarations: [AstreinteDeleteDialogComponent]
      })
        .overrideTemplate(AstreinteDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AstreinteDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AstreinteService);
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
