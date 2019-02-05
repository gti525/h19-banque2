import * as React from "react";

export default class DashbordAdmin extends React.Component {
   state = {
      debitCards: [],
      error: null,
      isLoading: true
   }

   fetchDebitCards() {
      fetch("http://localhost:3000/api/v1/account/debitCard/22212345")
         .then(response => response.json())
         .then(data => this.setState({
            debitCards: data,
            isLoading: false,
         }))
        .catch(error => this.setState({ error, isLoading: false }));
   }

   componentDidMount() {
      this.fetchDebitCards();
   }
   
   render () {
      const { isLoading, debitCards, error } = this.state;
      return (
         <React.Fragment>
            <h1>Données API test : </h1>
            
            {error ? <p>{error.message}</p> : null}
          
            {!isLoading ? (
               <p>Numéro : {this.state.debitCards.nbr} <br /> Balance : {this.state.debitCards.balance}</p>
            ):(
               <h3>Loading...</h3>
            )}
         </React.Fragment>
      );
    }
}
