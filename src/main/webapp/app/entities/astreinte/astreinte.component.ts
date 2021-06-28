import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IAstreinte } from 'app/shared/model/astreinte.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { AstreinteService } from './astreinte.service';
import { Message } from 'app/shared/model/message';

@Component({
  selector: 'jhi-astreinte',
  templateUrl: './astreinte.component.html'
})
export class AstreinteComponent implements OnInit, OnDestroy {
  currentAccount: any;
  astreintes: IAstreinte[];
  astreintesFound: IAstreinte[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;
  chaine: number;
  message: Message = new Message();
  /* @Output() searchReqData: EventEmitter<any> = new EventEmitter();
  partners?: IPartner[];
  partnerprofiles?: IPartnerProfile[];
  zones?: IZone[];
  public isCollapsed = true;
  advancedSearchMsisdn = '';
  advancedSearchZone = '';
  advancedSearchParent = '';
  advancedSearchProfile = '';
 */
  /*  numberWeek = Date(); */
  constructor(
    protected astreinteService: AstreinteService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager /* protected partnerService: PartnerService,
        protected partnerProfileService: PartnerProfileService,
        protected zoneService: ZoneService */
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
    this.chaine =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search'] ? this.activatedRoute.snapshot.params['search'] : '';
  }
  /* search() {
      this.astreintes = undefined;
      this.astreinteService
        .find(this.chaine)
        .pipe(
          filter((res: HttpResponse<IAstreinte>) => res.ok),
          map((res: HttpResponse<IAstreinte>) => res.body)
        )
        .subscribe(
          (res: IAstreinte) => {
            this.astreintes = res;
            this.chaine = 'number';
            this.found = true;
          },

          (res: HttpErrorResponse) => {
            if (res.status ==404) {
               this.found = false;
            }
          }
        );
    } */

  loadAll() {
    this.astreinteService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IAstreinte[]>) => this.paginateAstreintes(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/astreinte'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/astreinte',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInAstreintes();
    this.essaiSend();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAstreinte) {
    return item.id;
  }

  registerChangeInAstreintes() {
    this.eventSubscriber = this.eventManager.subscribe('astreinteListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateAstreintes(data: IAstreinte[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.astreintes = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
  /* advancedSearch(matricul: any, numeroWeek: any, year: any, equipe: any) {
      this.searchReqData.emit ({
        matricul,
       numeroWeek,
        year,
        equipe
      });
    } */
  /*  advancedInit(): void {
        this.advancedSearchMatricule = '';
        this.advancedSearchNumeroWeek = '';
        this.advancedSearchYear= '';
        this.advancedSearchEquipe = '';
        this.searchReqData.emit({
         matricul: '',
         numeroWeek: '',
          year: '',
         equipe: ''
        });
      } */
  /* var curr = new Date; // get current date
      var first = curr.getDate() - curr.getDay(); // First day is the day of the month - the day of the week
      var last = first + 6; // last day is the first day + 6

      var firstday = new Date(curr.setDate(first)).toUTCString();
      var lastday = new Date(curr.setDate(last)).toUTCString();
console.log(firstday) */

  check() {
    this.astreinteService.findByChaine(this.chaine).subscribe(
      (res: HttpResponse<IAstreinte[]>) => {
        this.astreintes = res;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }
  previousState() {
    window.location.reload();
  }

  essaiSend() {
    this.message.messageToSend = 'Envoie sms';
    this.message.receiverMSISDN = '221761995807';
    this.astreinteService.sendMessage(this.message).subscribe(
      data => {
        console.log(data);
      },
      err => {
        console.log(err);
      }
    );
  }
}
