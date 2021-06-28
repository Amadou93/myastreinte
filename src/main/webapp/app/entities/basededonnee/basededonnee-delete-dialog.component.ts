import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBasededonnee } from 'app/shared/model/basededonnee.model';
import { BasededonneeService } from './basededonnee.service';

@Component({
  selector: 'jhi-basededonnee-delete-dialog',
  templateUrl: './basededonnee-delete-dialog.component.html'
})
export class BasededonneeDeleteDialogComponent {
  basededonnee: IBasededonnee;

  constructor(
    protected basededonneeService: BasededonneeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.basededonneeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'basededonneeListModification',
        content: 'Deleted an basededonnee'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-basededonnee-delete-popup',
  template: ''
})
export class BasededonneeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ basededonnee }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BasededonneeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.basededonnee = basededonnee;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/basededonnee', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/basededonnee', { outlets: { popup: null } }]);
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
