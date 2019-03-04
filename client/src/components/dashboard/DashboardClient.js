import * as React from "react";
import { DebitCard } from './DebitCard';
import { CreditCard } from './CreditCard';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class DashboardClient extends React.Component {
   constructor(props) {
      super(props);
      this.clientLogOut = this.clientLogOut.bind(this);
  }

   state = {
      debitCards: [],
      creditCards: [],
      error: null,
      isLoading: true
   }
   
   verifyLogin(){
      var loginIsSucess = 1;
  
      // Construction du call d'API asynchrone pour permettre le "await"
       const request = async () =>{
        const apiCall = await fetch(this.props.state.URLBackend+"/api/v1/account/debitCard")
         .then(function(response) {
           if(response.status != 200){     // Si le login n'est pas accepté par le backend
             console.log("Dans: PAS 200");
             // On indique que le login N'EST PAS réussi
             loginIsSucess = 0;
           }          
         });

         // Finalement, si le login est un succès, on redirige l'utilisateur a son dashboard
          if(loginIsSucess === 0){
            this.props.history.push("/");
          } 
       } 

       request();

   }

   fetchDebitCards() {
      fetch(this.props.state.URLBackend+"/api/v1/account/debitCard")
         .then(response => response.json())
         .then(data => this.setState({
            debitCards: data,
            isLoading: false,
         }))
        .catch(error => this.setState({ error, isLoading: false }));
   }

   fetchCreditCards() {
      fetch(this.props.state.URLBackend+"/api/v1/account/creditCard")
         .then(response => response.json())
         .then(data => this.setState({
            creditCards: data,
            isLoading: false,
         }))
        .catch(error => this.setState({ error, isLoading: false }));
   }

   clientLogOut() {
      console.log("in logout");
      debugger
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
            <Link to="/"><Button id="btnDeconnexion" bsStyle="danger" onClick={this.clientLogOut}>Déconnexion</Button></Link>
         </div>
      )
   }
}
