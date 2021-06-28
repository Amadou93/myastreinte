import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AstreinteService } from '../astreinte.service';
import { ZoneService } from '../../zone/zone.service';
import { PartnerProfileService } from '../../partner-profile/partner-profile.service';
import { IZone } from 'app/shared/model/zone.model';
import { IPartnerProfile } from 'app/shared/model/partner-profile.model';
import { IPartner } from 'app/shared/model/partner.model';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-advanced-search',
  templateUrl: './advanced-search.component.html',
  styleUrls: ['./advanced-search.component.scss']
})
export class AdvancedSearchComponent implements OnInit {
  @Output() searchReqData: EventEmitter<any> = new EventEmitter();
  partners?: IPartner[];
  partnerprofiles?: IPartnerProfile[];
  zones?: IZone[];
  public isCollapsed = true;
  advancedSearchMsisdn = '';
  advancedSearchZone = '';
  advancedSearchParent = '';
  advancedSearchProfile = '';
  constructor(
    protected partnerService: PartnerService,
    protected partnerProfileService: PartnerProfileService,
    protected zoneService: ZoneService
  ) {}

  ngOnInit() {
    this.partnerProfileService.query().subscribe((res: HttpResponse<IPartnerProfile[]>) => (this.partnerprofiles = res.body || []));

    this.zoneService.query().subscribe((res: HttpResponse<IZone[]>) => (this.zones = res.body || []));
  }

  advancedSearch(msisdn: any, parentMsisdn: any, zone: any, profile: any) {
    this.searchReqData.emit({
      msisdn,
      parentMsisdn,
      zone,
      profile
    });
  }

  advancedInit(): void {
    this.advancedSearchMsisdn = '';
    this.advancedSearchZone = '';
    this.advancedSearchParent = '';
    this.advancedSearchProfile = '';
    this.searchReqData.emit({
      msisdn: '',
      parentMsisdn: '',
      zone: '',
      profile: ''
    });
  }
}
