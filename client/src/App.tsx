import * as React from "react";
import {BrowserRouter, Route} from "react-router-dom";
import Login from "./components/login/Login";
import LoginAdmin from "./components/admin/login/LoginAdmin";
import DashboardAdmin from "./components/admin/DashboardAdmin";
import DashboardClient from "./components/dashboard/DashboardClient";
import HistoriqueDebit from "./components/historique/HistoriqueDebit";
import HistoriqueCredit from "./components/historique/HistoriqueCredit";
import PaymentCarte from "./components/payment/PaymentCarte";
import VirementInterac from "./components/virement/VirementInterac";

import "./styles/App.css";
import logo from "./Images/Logo_banque2.png";

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
        // URLBackend: "https://banque2-h19.herokuapp.com",
        URLBackend: "http://localhost:8080",
    };
}

  
  public render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <br />
        </header>

        <BrowserRouter>
          <div>
            <Route exact={true} path="/" render={props=><Login {...props} state={this.state}/>}  />
            <Route path="/LoginAdmin" render={props=><LoginAdmin {...props} state={this.state}/>} />

            <Route path="/DashboardClient" render={props=><DashboardClient {...props} state={this.state}/>} />
            <Route path="/DashboardAdmin" render={props=><DashboardAdmin {...props} state={this.state}/>} />

            <Route path="/HistoriqueDebit" render={props=><HistoriqueDebit {...props} state={this.state}/>} />
            <Route path="/HistoriqueCredit" render={props=><HistoriqueCredit {...props} state={this.state}/>} />

            <Route path="/PaymentCarte" render={props=><PaymentCarte {...props} state={this.state}/>} />

            <Route path="/VirementInterac" component={VirementInterac} />
          </div>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
