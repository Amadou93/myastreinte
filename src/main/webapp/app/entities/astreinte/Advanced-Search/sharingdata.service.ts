import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharingdataService {
  msisdn: any;
  parent: any;
  zone: any;
  profile: any;
  private actionSource = new Subject<boolean>();
  actionSourceObservable = this.actionSource.asObservable();

  constructor() {}

  setData(msisdn: any, parent: any, zone: any, profile: any) {
    this.actionSource.next(true);
    this.msisdn = msisdn;
    this.parent = parent;
    this.zone = zone;
    this.profile = profile;
  }
  getData(): any {
    return [this.msisdn, this.parent, this.zone, this.profile];
  }
}
