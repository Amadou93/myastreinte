/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MyAstreinteTestModule } from '../../../test.module';
import { BasededonneeDeleteDialogComponent } from 'app/entities/basededonnee/basededonnee-delete-dialog.component';
import { BasededonneeService } from 'app/entities/basededonnee/basededonnee.service';

describe('Component Tests', () => {
  describe('Basededonnee Management Delete Component', () => {
    let comp: BasededonneeDeleteDialogComponent;
    let fixture: ComponentFixture<BasededonneeDeleteDialogComponent>;
    let service: BasededonneeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyAstreinteTestModule],
        declarations: [BasededonneeDeleteDialogComponent]
      })
        .overrideTemplate(BasededonneeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BasededonneeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BasededonneeService);
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
