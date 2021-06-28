import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEquipementReseau } from 'app/shared/model/equipement-reseau.model';
import { EquipementReseauService } from './equipement-reseau.service';

@Component({
  selector: 'jhi-equipement-reseau-delete-dialog',
  templateUrl: './equipement-reseau-delete-dialog.component.html'
})
export class EquipementReseauDeleteDialogComponent {
  equipementReseau: IEquipementReseau;

  constructor(
    protected equipementReseauService: EquipementReseauService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.equipementReseauService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'equipementReseauListModification',
        content: 'Deleted an equipementReseau'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-equipement-reseau-delete-popup',
  template: ''
})
export class EquipementReseauDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ equipementReseau }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(EquipementReseauDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.equipementReseau = equipementReseau;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/equipement-reseau', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/equipement-reseau', { outlets: { popup: null } }]);
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
