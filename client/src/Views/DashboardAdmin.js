import * as React from "react";

export default class DashbordAdmin extends React.Component {
   state = {
      creditCards: [],
      error: null,
      isLoading: true
   }

   fetchUsers() {
      fetch('http://localhost:3000/api/v1/account/debitCard/22212345')
        .then(response => response.json())
        .then(data =>
          this.setState({
            creditCards: data.map((x) => ({
               balance: x.balance,
               nbr: x.nbr
             })),
            isLoading: false,
          })
        )
        .catch(error => this.setState({ error, isLoading: false }));
    }

   componentDidMount() {
      this.fetchUsers();
   }
   
   render () {
      const { isLoading, creditCards, error } = this.state;

      return (
         <React.Fragment>
            <h1>Carte</h1>
            {error ? <p>{error.message}</p> : null}
            {!isLoading ? (
            creditCards.map(creditCard => {
               const { nbr, balance, cardLimit, monthExp, yearExp } = creditCard;
               return (
                  <div>
                  <p>Numéro de carte: {nbr}</p>
                  <p>Balance: {balance}</p>
                  <hr />
                  </div>
               );
            })
            ) : (
            <h3>Loading...</h3>
            )}
         </React.Fragment>
      )
   }
}
