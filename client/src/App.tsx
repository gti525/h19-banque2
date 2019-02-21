import * as React from "react";
import {BrowserRouter, Route} from 'react-router-dom';
import Login from './components/login/Login';
import LoginAdmin from './components/admin/login/LoginAdmin';
import DashboardAdmin from './components/admin/DashboardAdmin';
import DashboardClient from './components/dashboard/DashboardClient';
import HistoriqueDebit from './components/historique/HistoriqueDebit';
import HistoriqueCredit from './components/historique/HistoriqueCredit';
import PaymentCarte from './components/payment/PaymentCarte';
import VirementInterac from './components/virement/VirementInterac';

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

            <Route path="/HistoriqueDebit" component={HistoriqueDebit} />
            <Route path="/HistoriqueCredit" component={HistoriqueCredit} />

            <Route path="/PaymentCarte" component={PaymentCarte} />

            <Route path="/VirementInterac" component={VirementInterac} />
          </div>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
