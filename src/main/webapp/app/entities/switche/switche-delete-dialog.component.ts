import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISwitche } from 'app/shared/model/switche.model';
import { SwitcheService } from './switche.service';

@Component({
  selector: 'jhi-switche-delete-dialog',
  templateUrl: './switche-delete-dialog.component.html'
})
export class SwitcheDeleteDialogComponent {
  switche: ISwitche;

  constructor(protected switcheService: SwitcheService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.switcheService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'switcheListModification',
        content: 'Deleted an switche'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-switche-delete-popup',
  template: ''
})
export class SwitcheDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ switche }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SwitcheDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.switche = switche;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/switche', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/switche', { outlets: { popup: null } }]);
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
