import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {Dataset} from '../../model/dataset';
import {Model} from '../../model/model';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {AdminService} from '../../service/admin.service';
import {DatasetService} from '../../service/dataset.service';
import {ModelService} from '../../service/model.service';

@Component({
  selector: 'app-admin-model',
  templateUrl: './admin-model.component.html',
  styleUrls: ['./admin-model.component.sass']
})
export class AdminModelComponent implements OnInit {

  models: Model[];
  displayedColumns: string[] = ['title', 'description', 'source', 'date', 'manage'];
  dataSource: MatTableDataSource<Model>;
  updateSuccess = false;
  successMess = '';
  updateError = false;
  errorMess = '';

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private modelService: ModelService, private adminService: AdminService) { }

  ngOnInit(): void {
    this.modelService.getModels().subscribe(models => {
      this.dataSource = new MatTableDataSource(models);
      this.models = models;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteModel(model) {
    this.adminService.deleteModel(model.id)
      .subscribe(
        () => {
          this.updateSuccess = true;
          this.successMess = 'Dataset supprimé';
          this.ngOnInit();
        },
        (error) => {
          console.log(error);
          this.updateError = true;
          this.errorMess = 'Une erreur interne est survenue, veuillez réessayer';
        });
  }

}
