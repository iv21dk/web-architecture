import { TestsComponent } from "./components/tests/tests.component";
import { CommonModule } from "@angular/common";
import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { FormsModule } from "@angular/forms";
import { BackendsManagerComponent } from "./components/backends-manager/backends-manager.component";
import { BackendComponent } from "./components/backend/backend.component";

@NgModule({
    imports: [
        CommonModule, 
        BrowserModule,
        NgbModule,
        FormsModule
    ],
    declarations: [
        TestsComponent, 
        BackendsManagerComponent, 
        BackendComponent
    ],
    exports: [
        TestsComponent, 
        BackendsManagerComponent]
  })
export class TestsModule {}