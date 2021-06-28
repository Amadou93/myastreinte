import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAstreinte } from 'app/shared/model/astreinte.model';
import { AstreinteService } from './astreinte.service';

@Component({
  selector: 'jhi-astreinte-delete-dialog',
  templateUrl: './astreinte-delete-dialog.component.html'
})
export class AstreinteDeleteDialogComponent {
  astreinte: IAstreinte;

  constructor(protected astreinteService: AstreinteService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.astreinteService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'astreinteListModification',
        content: 'Deleted an astreinte'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-astreinte-delete-popup',
  template: ''
})
export class AstreinteDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ astreinte }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AstreinteDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.astreinte = astreinte;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/astreinte', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/astreinte', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
