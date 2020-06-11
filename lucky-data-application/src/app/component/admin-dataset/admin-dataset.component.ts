import {Component, OnInit, ViewChild} from '@angular/core';
import {DatasetService} from "../../service/dataset.service";
import {Dataset} from "../../model/dataset";
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {User} from '../../model/user';

@Component({
  selector: 'app-admin-dataset',
  templateUrl: './admin-dataset.component.html',
  styleUrls: ['./admin-dataset.component.sass']
})
export class AdminDatasetComponent implements OnInit {

  datasets: Dataset[];
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'department', 'role', 'manage'];
  dataSource: MatTableDataSource<Dataset>;
  updateSuccess = false;
  successMess = '';
  updateError = false;
  errorMess = '';

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private datasetService: DatasetService) { }

  ngOnInit(): void {
    this.datasetService.getDatasets().subscribe(datasets => {
      this.dataSource = new MatTableDataSource(datasets);
      this.datasets = datasets;
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

}
