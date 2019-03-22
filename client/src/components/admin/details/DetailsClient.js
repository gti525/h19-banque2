import * as React from "react";
import { DebitCard } from './DebitCard';
import { CreditCard } from './CreditCard';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
const queryString = require('query-string');

export default class DétailsClient extends React.Component {
   constructor(props) {
      super(props);

      this.adminLogOut = this.adminLogOut.bind(this);
   }

   state = {
      debitCards: [],
      creditCards: [],
      error: null,
      isLoading: true
   }

   verifyLogin(){
      var loginIsSucess = 1;
  
      const request = async () =>{
         await fetch(this.props.state.URLBackend+"/api/v1/admin/ping")
         .then(function(response) {
            if(response.status !== 200){     // Si le login n'est pas accepté par le backend
               console.log("Dans: PAS 200");
               loginIsSucess = 0;
            }
         });
         
         if(loginIsSucess === 0){
            this.props.history.push("/LoginAdmin");
         } 
      } 

      request();
   }

   getClientName(){
      let search = new URLSearchParams(this.props.location.search);
      let name = search.get("name");

      return name;
   }

   getDebitCardNumber(){
      let search = new URLSearchParams(this.props.location.search);
      let debitCardNumber = search.get("debitCardNumber");

      return debitCardNumber;
   }

   getCreditCardNumber(){
      let search = new URLSearchParams(this.props.location.search);
      let creditCardNumber = search.get("creditCardNumber");

      return creditCardNumber;
   }

   fetchDebitCards() {
      let search = new URLSearchParams(this.props.location.search);
      let debitCardNumber = search.get("debitCardNumber");
      
      fetch(this.props.state.URLBackend+"/api/v1/account/debitCard/"+debitCardNumber)
         .then(response => response.json())
         .then(data => this.setState({
            debitCards: data,
            isLoading: false,
         }))
        .catch(error => this.setState({ error, isLoading: false }));
   }

   fetchCreditCards() {
      let search = new URLSearchParams(this.props.location.search);
      let creditCardNumber = search.get("creditCardNumber");

      fetch(this.props.state.URLBackend+"/api/v1/account/creditCard/"+creditCardNumber)
         .then(response => response.json())
         .then(data => this.setState({
            creditCards: data,
            isLoading: false,
         }))
        .catch(error => this.setState({ error, isLoading: false }));
   }

   adminLogOut() {
      console.log("in logout");
      fetch(this.props.state.URLBackend+"/logout")
       .then(response => response.json())
      .catch(error => this.setState({ error }));
   }

   componentDidMount() {
      this.verifyLogin();
      this.fetchDebitCards();
      this.fetchCreditCards();
   }

   render () {
      return (
         <div id="dashboardClientContainer">
            <Link to="/DashboardAdmin"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

            <h4>Détail du client : {this.getClientName()}</h4>
               <DebitCard 
                  balance={this.state.debitCards.balanceAsString}
                  numCarteDebit={this.getDebitCardNumber()}
               />
               
               <br />

               <CreditCard 
                  balance={this.state.creditCards.balanceAsString}
                  numCarteCredit={this.getCreditCardNumber()}
                  cardLimit={this.state.creditCards.cardLimitAsString}
               />
            
            <Link to="/"><Button id="btnDeconnexion" bsStyle="danger" onClick={this.adminLogOut}>Déconnexion</Button></Link>
         </div>
      )
   }
}
