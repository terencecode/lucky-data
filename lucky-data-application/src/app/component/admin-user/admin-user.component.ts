import { Component, OnInit, ViewChild } from '@angular/core';
import {User} from '../../model/user';
import {AdminService} from '../../service/admin.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.sass']
})
export class AdminUserComponent implements OnInit {

  users : User[];
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'department', 'role', 'manage'];
  dataSource: MatTableDataSource<User>;
  updateSuccess = false;
  successMess = '';
  updateError = false;
  errorMess = '';

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.adminService.getUsers().subscribe(users => {
      this.dataSource = new MatTableDataSource(users);
      this.users = users;
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

  isExpertRole(roleList: Array<string>) { return roleList.indexOf('ROLE_DATA_EXPERT') !== -1; }

  isAdminRole(roleList: Array<string>) { return roleList.indexOf('ROLE_ADMIN') !== -1; }

  deleteUser(user: User){
    this.adminService.deleteUser(user.email)
      .subscribe(
        () => {
          this.updateSuccess = true;
          this.successMess = 'Utilisateur supprimé';
          this.ngOnInit();
        },
        (error) => {
          console.log(error);
          this.updateError = true;
          this.errorMess = 'Une erreur interne est survenue, veuillez réessayer';
        });
  }

  EditRole(addRole: boolean, role: string, user: User){
    this.adminService.editUserRole(addRole, role, user.email)
      .subscribe(
        () => {
          this.updateSuccess = true;
          this.successMess = 'Le rôle de cet utilisateur a été modifié';
          this.ngOnInit();
        },
        (error) => {
          console.log(error);
          this.updateError = true;
          this.errorMess = 'Une erreur interne est survenue, veuillez réessayer';
        });
  }

}
