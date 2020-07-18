import { TestsComponent } from "./components/tests/tests.component";
import { CommonModule } from "@angular/common";
import { BackendsManagerComponent } from "../../backends-manager/backends-manager.component";
import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { BackendComponent } from "../../backend/backend.component";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { FormsModule } from "@angular/forms";

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