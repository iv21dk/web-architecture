import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
//import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

// import { MatInputModule, MatPaginatorModule, MatProgressSpinnerModule,
//   MatSortModule, MatTableModule } from "@angular/material";

//import { MatTableModule } from "@angular/material";

import { AppComponent } from './app.component';
import { BackendsManagerComponent } from './component/backends-manager/backends-manager.component';
import { JavaServerComponent } from './component/java-server/java-server.component';
import { BackendService } from './service/backend.service';
import { DataTemplatesComponent } from './component/data-templates/data-templates.component';
import {DataTemplateService} from "./service/data-template.service";
import { ModalDialogComponent } from './modal-dialog/modal-dialog.component';
//import {ModalDialogModule} from "./modal-dialog/modal-dialog.module";


@NgModule({
  declarations: [
    AppComponent,
    BackendsManagerComponent,
    JavaServerComponent,
    DataTemplatesComponent,
    ModalDialogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
    //ModalDialogModule
    //BrowserAnimationsModule,
    //MatInputModule,
    //MatTableModule,
    //MatPaginatorModule,
    //MatSortModule,
    //MatProgressSpinnerModule
  ],
  providers: [BackendService, DataTemplateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
