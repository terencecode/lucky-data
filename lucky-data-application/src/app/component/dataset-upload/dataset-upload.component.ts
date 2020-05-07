import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {MatRadioChange} from "@angular/material/radio";
import {CustomErrorStateMatcher} from "./CustomErrorStateMatcher";
import {DatasetService} from "../../service/dataset.service";

@Component({
  selector: 'app-dataset-upload',
  templateUrl: './dataset-upload.component.html',
  styleUrls: ['./dataset-upload.component.sass']
})
export class DatasetUploadComponent implements OnInit {
  formGroup: FormGroup;
  formArray: FormArray;
  file: File;
  allowedExtensions = ['csv', 'xls', 'xlxs', 'json', 'xml'];
  matcher = new CustomErrorStateMatcher();

  constructor(private fb: FormBuilder, private datasetService: DatasetService) {
    this.formArray = this.fb.array([
      this.fb.group({
        title: [''],
        description: ['', [Validators.required, Validators.max(5000)]],
        source: ['', [Validators.required]],
        dateRadio: ['unique', [Validators.required]],
        datasetDate: [''],
        datasetStartDate: [''],
        datasetEndDate: [''],
        file: [null, [this.fileRequiredValidator, this.fileTypeValidator(this.allowedExtensions)]]
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

  radioButtonChanged(event: MatRadioChange) {
    const radioButtonValue = event.value;
    const datasetDateControl = this.formArray.get([0]).get('datasetDate') as FormControl;
    const datasetStartDateControl = this.formArray.get([0]).get('datasetStartDate') as FormControl;
    const datasetEndDateControl = this.formArray.get([0]).get('datasetEndDate') as FormControl;
    const dateControls = [datasetDateControl, datasetStartDateControl, datasetEndDateControl];

    dateControls.forEach((control => {
      control.clearValidators();
    }));

    switch (radioButtonValue) {
      case('unique'):
        datasetDateControl.setValidators(Validators.required);
        break;
      case('range'):
        datasetStartDateControl.setValidators(Validators.required);
        datasetEndDateControl.setValidators(Validators.required);
        break;
      default:
        break;
    }

    dateControls.forEach((control => {
      control.markAsPristine();
      control.markAsUntouched();
      control.updateValueAndValidity();
    }));
  }

  fileTypeValidator(types: string[]): ValidatorFn {
    return (control: FormControl): {[key: string]: any} | null => {
      const fileName = control.value;
      if (fileName) {
        const dotSplitName = fileName.split('.');
        const extension = dotSplitName[dotSplitName.length - 1].toLowerCase();
        if (!types.includes(extension)) {
          return {
            fileTypeValidator: true
          };
        }
        return null;
      }
      return null;
    }
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

  acceptedExtensions(): string {
    let acceptedExtensions = "";
    for(let index = 0; index < this.allowedExtensions.length; index++) {
      acceptedExtensions += "." + this.allowedExtensions[index];
      if(index != this.allowedExtensions.length - 1)
        acceptedExtensions += ","
    }
    return acceptedExtensions;
  }

  onSubmit() {
    const formData: FormData = new FormData();
    formData.append("file", this.file);
    const mandatoryControls = this.formArray.get([0]);
    const optionalControls = this.formArray.get([1]);
    const params = {
      title: mandatoryControls.get('title').value,
      description: mandatoryControls.get('description').value,
      source: mandatoryControls.get('source').value
    };
    if (mandatoryControls.get('datasetDate').value) {
      params['date'] = mandatoryControls.get('datasetDate').value.getTime();
    } else if (mandatoryControls.get('datasetStartDate').value) {
      params['date'] = mandatoryControls.get('datasetStartDate').value.getTime();
    }
    if (mandatoryControls.get('datasetStartDate').value) {
      params['startDate'] = mandatoryControls.get('datasetStartDate').value.getTime();
    }
    if (mandatoryControls.get('datasetEndDate').value) {
      params['endDate'] = mandatoryControls.get('datasetEndDate').value.getTime();
    }
    if (optionalControls.get('tag').value) {
      params['tag'] = optionalControls.get('tag').value;
    }

    this.datasetService.uploadDataset(formData, params).subscribe(response => console.log(response), (error) => console.log(error));
  }
}
