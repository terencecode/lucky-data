import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {User} from "../../model/user";

@Component({
  selector: 'app-dataset-list',
  templateUrl: './dataset-list.component.html',
  styleUrls: ['./dataset-list.component.sass']
})
export class DatasetListComponent implements OnInit {

  user : User;

  constructor(private userService : UserService) { }

  ngOnInit(): void {
    this.userService.getUserInfo().subscribe(user => this.user = user);
  }

}
