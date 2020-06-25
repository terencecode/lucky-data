import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {ModelService} from "../../service/model.service";

@Component({
  selector: 'app-model-upload',
  templateUrl: './model-upload.component.html',
  styleUrls: ['./model-upload.component.sass']
})
export class ModelUploadComponent implements OnInit {

  submitted: boolean;
  formGroup: FormGroup;
  formArray: FormArray;
  file: File;

  constructor(private fb: FormBuilder, private modelService: ModelService) {
    this.formArray = this.fb.array([
      this.fb.group({
        title: ['', [Validators.required]],
        description: ['', [Validators.required, Validators.max(5000)]],
        source: ['', [Validators.required]],
        file: [null, [this.fileRequiredValidator]]
      }),
      this.fb.group({
        tag: ['']
      })
    ]);

    this.formGroup = this.fb.group({
      formArray: this.formArray
    });
  }

  ngOnInit(): void {
  }

  fileChanged(event) {
    const files = event.target.files as FileList;
    this.file = files[0];
  }

  fileRequiredValidator(): ValidatorFn {
    return (control: FormControl): {[key: string]: any} | null => {
      const fileName = control.value;
      if (fileName) {
        return null;
      }
      return {
        fileRequiredValidator: true
      };
    }
  }

  onSubmit() {
    const formData: FormData = new FormData();
    const mandatoryControls = this.formArray.get([0]);
    const optionalControls = this.formArray.get([1]);
    const params = {
      title: mandatoryControls.get('title').value,
      description: mandatoryControls.get('description').value,
      source: mandatoryControls.get('source').value
    };
    if (optionalControls.get('tag').value) {
      params['tag'] = optionalControls.get('tag').value;
    }

    formData.append("file", this.file);
    this.modelService.uploadModelByFile(formData, params).subscribe(response => {
      this.submitted = true;
    }, (error) => {
      console.log(error);
      this.reset();
    });
  }

  reset() {
    this.formGroup.reset();
    this.submitted = false;
  }

}
