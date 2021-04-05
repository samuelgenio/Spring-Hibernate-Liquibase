import React from 'react';
import { BrowserRouter, Route } from "react-router-dom";

import Home from './pages/Home';
import UserList from './pages/User/index';
import UserCreate from './pages/User/create';

const Routes = () => {
    return (
      <BrowserRouter>
        <Route component={Home} path="/" exact />
        <Route component={UserList} exact path="/user/:email" />
        <Route component={UserCreate} exact path="/user/:email/create" />
      </BrowserRouter>
    );
  }
  
  export default Routes;