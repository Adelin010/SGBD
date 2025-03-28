import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UserDashBoardComponent } from './user-dash-board/user-dash-board.component';
import { UsersDashBoardComponent } from './users-dash-board/users-dash-board.component';

export const routes: Routes = [
    {path:"", redirectTo:"/home", pathMatch:"full"},
    {path: "/home", component: HomeComponent},
    {path: "/users", component: UsersDashBoardComponent},
    {path: "/users/:id", component: UserDashBoardComponent}
];
