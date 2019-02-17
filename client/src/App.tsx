import * as React from "react";
import {BrowserRouter, Route} from 'react-router-dom';
import Login from './components/login/Login';
import LoginAdmin from './components/login/admin/LoginAdmin';
import DashboardAdmin from './components/DashboardAdmin';
import DashboardClient from './components/DashboardClient';

import './styles/App.css';
import logo from './Images/Logo_banque2.png';

class App extends React.Component {
  public render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <br />
        </header>

        <BrowserRouter>
          <div>
            <Route exact={true} path="/" component={Login} />
            <Route path="/LoginAdmin" component={LoginAdmin} />
            <Route path="/DashboardClient" component={DashboardClient} />
            <Route path="/DashboardAdmin" component={DashboardAdmin} />
          </div>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
