import * as React from "react";
import { DebitCard } from './DebitCard';
import { CreditCard } from './CreditCard';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class DashboardClient extends React.Component {
   state = {
      debitCards: [],
      creditCards: [],
      error: null,
      isLoading: true
   }

   fetchDebitCards() {
      fetch("https://banque2-h19.herokuapp.com/api/v1/account/debitCard")
         .then(response => response.json())
         .then(data => this.setState({
            debitCards: data,
            isLoading: false,
         }))
        .catch(error => this.setState({ error, isLoading: false }));
   }

   fetchCreditCards() {
      fetch("https://banque2-h19.herokuapp.com/api/v1/account/creditCard")
         .then(response => response.json())
         .then(data => this.setState({
            creditCards: data,
            isLoading: false,
         }))
        .catch(error => this.setState({ error, isLoading: false }));
   }

   componentDidMount() {
      this.fetchDebitCards();
      this.fetchCreditCards();
   }

   render () {
      return (
         <div id="dashboardClientContainer">
            <Button className="btnAccueil" bsStyle="info" disabled>Accueil</Button>
            <h2><u>Aperçu sur vos comptes</u></h2>

            <div className="row">
               <div className="column">
                  <DebitCard 
                     balance={this.state.debitCards.balance}
                  />
               </div>
               <div className="column">
                  <CreditCard 
                     balance={this.state.creditCards.balance}
                  />
               </div>
            </div>
            
            <Link to="/VirementInterac"><Button id="btnVirementInterac" bsStyle="info">Virement Interac</Button></Link>
            <Link to="/"><Button id="btnDeconnexion" bsStyle="danger">Déconnexion</Button></Link>
         </div>
      )
   }
}
