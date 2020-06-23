import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-refresh-api',
  templateUrl: './refresh-api.component.html',
  styleUrls: ['./refresh-api.component.sass']
})
export class RefreshApiComponent implements OnInit {
  form: FormGroup;

  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<RefreshApiComponent>) {
    this.form = this.fb.group({
      token: [''],
    });
  }

  closeDialog() {
    this.dialogRef.close(null);
  }

  ngOnInit(): void {
  }

}
