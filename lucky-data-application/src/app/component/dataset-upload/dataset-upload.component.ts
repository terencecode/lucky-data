import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {MatRadioChange} from "@angular/material/radio";
import {DatasetService} from "../../service/dataset.service";

@Component({
  selector: 'app-dataset-upload',
  templateUrl: './dataset-upload.component.html',
  styleUrls: ['./dataset-upload.component.sass']
})
export class DatasetUploadComponent implements OnInit {
  submitted: boolean;
  formGroup: FormGroup;
  formArray: FormArray;
  file: File;
  allowedExtensions = ['csv', 'xls', 'xlxs', 'xlsx', 'json', 'xml'];
  validityTypes = [{
      value: 'day',
      displayedValue: 'Jour'
    },
    {
      value: 'week',
      displayedValue: 'Semaine'
    },
    {
      value: 'month',
      displayedValue: 'Mois'
    },
    {
      value: 'year',
      displayedValue: 'Année'
    }
  ];
  paramsTypes = [{
      value: 'formData',
      displayedValue: 'Form Data'
    },
    {
      value: 'queryParams',
      displayedValue: 'Query'
    },
    {
      value: 'pathParams',
      displayedValue: 'Path'
    },
    {
      value: 'body',
      displayedValue: 'Body'
    }
  ];
  constructor(private fb: FormBuilder, private datasetService: DatasetService) {
    this.formArray = this.fb.array([
      this.fb.group({
        title: ['', [Validators.required]],
        description: ['', [Validators.required, Validators.max(5000)]],
        source: ['', [Validators.required]],
        dateRadio: ['unique', [Validators.required]],
        datasetDate: [''],
        datasetStartDate: [''],
        datasetEndDate: [''],
        fileRadio: ['file', [Validators.required]],
        file: [null, [this.fileRequiredValidator, this.fileTypeValidator(this.allowedExtensions)]],
        url: [''],
        tokenName: [''],
        tokenValue: [''],
        method: [''],
        contentType: [''],
        validity: this.fb.group({
          amount: [''],
          type: ['']
        }),
        params: this.fb.array([this.initParamRow()])
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

  dateRadioButtonChanged(event: MatRadioChange) {
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

  fileRadioButtonChanged(event: MatRadioChange) {
    const radioButtonValue = event.value;
    const fileControl = this.formArray.get([0]).get('file') as FormControl;
    const urlControl = this.formArray.get([0]).get('url') as FormControl;
    const tokenNameControl = this.formArray.get([0]).get('tokenName') as FormControl;
    const tokenValueControl = this.formArray.get([0]).get('tokenValue') as FormControl;
    const methodControl = this.formArray.get([0]).get('method') as FormControl;
    const contentTypeControl = this.formArray.get([0]).get('contentType') as FormControl;
    const controls: AbstractControl[] = [fileControl, urlControl, tokenNameControl, tokenValueControl, methodControl, contentTypeControl];

    /*const params = this.formArray.get([0]).get('params') as FormArray;
    Array.prototype.push.apply(controls, params.controls);*/

    console.log(controls);
    controls.forEach((control => {
      control.clearValidators();
    }));

    switch (radioButtonValue) {
      case('file'):
        fileControl.setValidators([this.fileRequiredValidator, this.fileTypeValidator(this.allowedExtensions)]);
        break;
      case('api'):
        urlControl.setValidators(Validators.required);
        tokenNameControl.setValidators(Validators.required);
        tokenValueControl.setValidators(Validators.required);
        methodControl.setValidators(Validators.required);
        contentTypeControl.setValidators(Validators.required);
        break;
      default:
        break;
    }

    controls.forEach((control => {
      control.markAsPristine();
      control.markAsUntouched();
      control.updateValueAndValidity();
    }));
  }

  fileTypeValidator(types: string[]): ValidatorFn {
    return (control: FormControl): {[key: string]: any} | null => {
      const files : File[] = control.value;
      if(files?.length) {
        const fileName : string = files[0].name;
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

  initParamRow() {
    return this.fb.group({
      type: [''],
      name: [''],
      value: ['']
    });
  }

  get paramsArray() {
    return this.formArray.get([0]).get('params') as FormArray;
  }

  addNewParam(index: number) {
    this.paramsArray.insert(index + 1, this.initParamRow());
  }

  deleteParam(index: number) {
    this.paramsArray.removeAt(index);
  }

  computeDisplayedValue(displayedValue: string): string {
    if (this.formArray.get([0]).get('validity').get('amount').value > 1 && displayedValue.charAt(displayedValue.length - 1).toLowerCase() != 's') {
      return displayedValue + "s";
    }
    else {
      return displayedValue;
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

    if(mandatoryControls.get('fileRadio').value === 'file') {
      formData.append("file", this.file);
      this.datasetService.uploadDatasetByFile(formData, params).subscribe(response => {
        this.submitted = true;
      }, (error) => {
        console.log(error);
        this.reset();
      });
    } else if(mandatoryControls.get('fileRadio').value === 'api') {
      const datasetAPIInfo = {};
      datasetAPIInfo['url'] = mandatoryControls.get('url').value;
      datasetAPIInfo['tokenName'] = mandatoryControls.get('tokenName').value;
      datasetAPIInfo['tokenValue'] = mandatoryControls.get('tokenValue').value;
      datasetAPIInfo['method'] = mandatoryControls.get('method').value;
      datasetAPIInfo['contentType'] = mandatoryControls.get('contentType').value;

      const validity = {
        amount: mandatoryControls.get('contentType').get('amount')?.value,
        type: mandatoryControls.get('contentType').get('type')?.value
      }
      if (validity.amount && validity.type) {
        const date = new Date();
        const millis = date.getTime();
        switch (validity.type) {
          case 'day':
            date.setDate(date.getDate() + validity.amount);
            break;
          case 'week':
            date.setDate(date.getDate() + validity.amount * 7);
            break;
          case 'month':
            date.setMonth(date.getMonth() + validity.amount);
            break;
          case 'year':
            date.setFullYear(date.getFullYear() + validity.amount);
            break;
          default:
            break;
        }
        datasetAPIInfo['validity'] = date.getTime() - millis;
      }

      let type: string;
      let name: string;
      let value;
      for (let control of this.paramsArray.controls) {
        type = control.get('type').value;
        name = (control.get('name').value as string).trim();
        value = control.get('value').value
        if (isNaN(value) && typeof value === 'string') {
          value = value.trim();
        } else if (!isNaN(value)) {
          value = parseInt(value);
        }
        if (type && name && value) {
          datasetAPIInfo[type] = datasetAPIInfo[type] ? datasetAPIInfo[type] : {};
          datasetAPIInfo[type][name] = value;
        }
      }
      params['datasetAPIInfo'] = datasetAPIInfo;
      console.log(params);
      this.datasetService.uploadDatasetByApi(params).subscribe(response => {
        this.submitted = true;
      }, (error) => {
        console.log(error);
        this.reset();
      });
    }
  }

  reset() {
    this.formGroup.reset();
    this.submitted = false;
  }
}
