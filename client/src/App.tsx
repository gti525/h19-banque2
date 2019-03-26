import * as React from "react";
import {BrowserRouter, Route} from "react-router-dom";
import Login from "./components/login/Login";
import LoginAdmin from "./components/admin/login/LoginAdmin";
import DashboardAdmin from "./components/admin/DashboardAdmin";
import DashboardClient from "./components/dashboard/DashboardClient";
import HistoriqueDebit from "./components/historique/HistoriqueDebit";
import HistoriqueCredit from "./components/historique/HistoriqueCredit";
import AdminHistoriqueDebit from "./components/admin/historique/AdminHistoriqueDebit";
import AdminHistoriqueCredit from "./components/admin/historique/AdminHistoriqueCredit";
import PaymentCarte from "./components/payment/PaymentCarte";
import VirementInterac from "./components/virement/VirementInterac";
import NouveauCompte from "./components/admin/NouveauCompte";
import DetailsClient from "./components/admin/details/DetailsClient";
import ChangementMotDePasse from "./components/changementMotDePasse/ChangementMotDePasse";
import { Switch } from 'react-router-dom';
import PrintProvider, { NoPrint} from 'react-easy-print';

import "./styles/App.css";
import logo from "./Images/Logo_banque2.png";

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
        URLBackend: "https://banque2-h19.herokuapp.com",
        //URLBackend: "http://localhost:8080",
    };
  }

  public render() {
    return (
      <PrintProvider>
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <br />
          </header>

          <BrowserRouter>
            <Switch>
              
                <Route exact={true} path="/" render={props=><Login {...props} state={this.state}/>}  />
                <Route path="/LoginAdmin" render={props=><LoginAdmin {...props} state={this.state}/>} />

                <Route path="/DashboardClient" render={props=><DashboardClient {...props} state={this.state}/>} />
                <Route path="/DashboardAdmin" render={props=><DashboardAdmin {...props} state={this.state}/>} />

                <Route path="/HistoriqueDebit" render={props=><HistoriqueDebit {...props} state={this.state}/>} />
                <Route path="/HistoriqueCredit" render={props=><HistoriqueCredit {...props} state={this.state}/>} />

                <Route path="/AdminHistoriqueDebit" render={props=><AdminHistoriqueDebit {...props} state={this.state}/>} />
                <Route path="/AdminHistoriqueCredit" render={props=><AdminHistoriqueCredit {...props} state={this.state}/>} />

                <Route path="/PaymentCarte" render={props=><PaymentCarte {...props} state={this.state}/>} />

                <Route path="/VirementInterac" render={props=><VirementInterac {...props} state={this.state}/>} />

                <Route path="/NouveauCompte" render={props=><NouveauCompte {...props} state={this.state}/>} />
                <Route path="/DetailsClient" render={props=><DetailsClient {...props} state={this.state}/>} />

                <Route path="/ChangementMotDePasse" render={props=><ChangementMotDePasse {...props} state={this.state}/>} />

            </Switch>
          </BrowserRouter>
          <NoPrint>
            <div id="horizontal-analytic-banner"></div>
          </NoPrint>
        </div>
      </PrintProvider>
    );
  }
}

export default App;
