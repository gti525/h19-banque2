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

import "./styles/App.css";
import logo from "./Images/Logo_banque2.png";

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
        URLBackend: "https://banque2-h19.herokuapp.com",
        // URLBackend: "http://localhost:8080",
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

            <Route path="/AdminHistoriqueDebit" render={props=><AdminHistoriqueDebit {...props} state={this.state}/>} />
            <Route path="/AdminHistoriqueCredit" render={props=><AdminHistoriqueCredit {...props} state={this.state}/>} />

            <Route path="/PaymentCarte" render={props=><PaymentCarte {...props} state={this.state}/>} />

            <Route path="/VirementInterac" render={props=><VirementInterac {...props} state={this.state}/>} />

            <Route path="/NouveauCompte" render={props=><NouveauCompte {...props} state={this.state}/>} />
            <Route path="/DetailsClient" render={props=><DetailsClient {...props} state={this.state}/>} />
          </div>
        </BrowserRouter>
       
         
<footer className="footer">

<div className="bottom_border">
<div className="row footerRow" >
<div id="horizontal-analytic-banner"></div>
<div className=" col-sm-4 col-md col-sm-4  col-12 col">
<h5 className="headin5_amrc col_white_amrc pt2"> DETAIL SUR DESPÉPINIÈRES </h5>

<p className="mb10">Vous pouvez venir à nos bureau ouvert du lundi au vendredi de 8h30am à 17h.</p>
<p><i className="fa fa-location-arrow"></i> Situé au 8080 localhost </p>
<p><i className="fa fa-phone"></i>  +91-9999878398  </p>
<p><i className="fa fa fa-envelope"></i> Contacter nous via courriel : support@Despépinières.com  </p>
</div>
<div className=" col-sm-4 col-md  col-6 col">
<h5 className="headin5_amrc col_white_amrc pt2">À PROPOS DE NOUS</h5>

<ul className="footer_ul_amrc">
<li>Despépinières est une banque qui fait affaire avec des clients au niveau mondial. On possède des branches principalement aux États-Unis, en Europe et en Asie.</li>
</ul>
</div>
<div className=" col-sm-4 col-md  col-6 col">
<h5 className="headin5_amrc col_white_amrc pt2">NOUVELLES DES DESPÉPINIÈRES</h5>

<ul className="footer_ul_amrc">
<li>Venez profitez de notre promotions! Faites trois transactions et payer les frais pour qu'une seule! Ce forfait sera valide jusqu'au 5 Avril 2019</li>

</ul>
<ul className="footer_ul_amrc">
</ul>
</div>
</div>
</div>
<div className="container">

<p className="text-center">© 2019, http://localhost:8080/, Mouvement Despépinières, Tous droits réservé. Despépinières</p>
</div>

</footer>
      </div>
    );
  }
}

export default App;
