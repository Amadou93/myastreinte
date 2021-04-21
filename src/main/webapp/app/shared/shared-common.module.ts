import { NgModule } from '@angular/core';

import { MyAstreinteSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [MyAstreinteSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [MyAstreinteSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class MyAstreinteSharedCommonModule {}
