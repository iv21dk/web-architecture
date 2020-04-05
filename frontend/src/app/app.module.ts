import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';
import { FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
//import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

// import { MatInputModule, MatPaginatorModule, MatProgressSpinnerModule,
//   MatSortModule, MatTableModule } from "@angular/material";

//import { MatTableModule } from "@angular/material";

import { AppComponent } from './app.component';
import { BackendsManagerComponent } from './backends-manager/backends-manager.component';
import { BackendComponent } from './backend/backend.component';
import { BackendService } from './backends-manager/backend.service';
import { DataTemplatesComponent } from './data-templates/data-templates.component';
import {DataTemplateService} from './data-templates/data-template.service';;
import {ModalDialogModule} from './modal-dialog/modal-dialog.module';
import { DataTemplateComponent } from './data-template/data-template.component';
import {ApiInterceptor} from "./interceptors/api-interceptor.service";
import {AppConfig} from "./app.config";

export function initializeApp(appConfig: AppConfig) {
  return () => appConfig.load();
}

@NgModule({
  declarations: [
    AppComponent,
    BackendsManagerComponent,
    BackendComponent,
    DataTemplatesComponent,
    DataTemplateComponent
    //ModalDialogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ModalDialogModule
    //BrowserAnimationsModule,
    //MatInputModule,
    //MatTableModule,
    //MatPaginatorModule,
    //MatSortModule,
    //MatProgressSpinnerModule
  ],
  providers: [
    BackendService,
    DataTemplateService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ApiInterceptor,
      multi: true
    },
    AppConfig,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [AppConfig],
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
