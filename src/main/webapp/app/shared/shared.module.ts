import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MyAstreinteSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [MyAstreinteSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [MyAstreinteSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MyAstreinteSharedModule {
  static forRoot() {
    return {
      ngModule: MyAstreinteSharedModule
    };
  }
}
