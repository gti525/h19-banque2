import * as React from "react";
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { Card, CardHeader, CardBody, CardTitle, Input } from "reactstrap";

export default class PaymentCarte extends React.Component {
    constructor(props) {
        super(props);
        this.creditCardPayment = this.creditCardPayment.bind(this);
    }

    state = {
        debitCards: [],
        creditCards: [],
        error: null,
        isLoading: true
    }

    // Méthode qui valide si l'utilisateur à bel et bien le droit d'accéder à cette page
    verifyLogin(){
        var loginIsSucess = 1;

        const request = async () =>{
        const apiCall = await fetch(this.props.state.URLBackend+"/api/v1/client/ping")
        .then(function(response) {
            if(response.status !== 200){     // Si le login n'est pas accepté par le backend
                console.log("Dans: PAS 200");
                loginIsSucess = 0;
            }          
        });
        
            if(loginIsSucess === 0){
                this.props.history.push("/");
            } 
        } 

        request();
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

     fetchDebitCards() {
        fetch(this.props.state.URLBackend+"/api/v1/account/debitCard")
           .then(response => response.json())
           .then(data => this.setState({
              debitCards: data,
              isLoading: false,
           }))
          .catch(error => this.setState({ error, isLoading: false }));
     }

     componentDidMount() {
        this.verifyLogin();
        this.fetchDebitCards();
        this.fetchCreditCards();
     }

    creditCardPayment(event) {
    event.preventDefault();

    const form = event.target;
    const data = new FormData(form);
    var loginIsSucess = 0;

    this.setState({
        res: stringifyFormData(data),
        infoPhaseFinal: [],
    });
        
    const request = async () =>{
        await fetch(this.props.state.URLBackend+"/api/v1/transaction/creditCardPayment", {
            method: "POST", 
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                },
            body: JSON.stringify({
                sourceDebitCardNumber: this.state.debitCards.nbr,
                targetCreditCardNumber: this.state.creditCards.nbr,
                amount: document.getElementById("amount").value,
                })
            })
            .then(function(response) {
                if (response.status === 200) {
                    loginIsSucess = 1;
                }
                if(response.status !== 200){
                    loginIsSucess = 0;
                    alert("Montant invalide. Réessayez.");
                }          
            });

            if(loginIsSucess === 1){
                this.props.history.push("/PaymentCarte");
                // Pour rafraichir les données dans la page
                this.fetchDebitCards();
                this.fetchCreditCards();
                alert("Votre payment a bien été effectué.");
            }
        } 

        request(); 
    }

    render () {
        return (
            <div id="paymentContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h2><u>Payer votre carte de crédit</u></h2>

                <h5>Total des transactions facturées : </h5>
                <Input id="paymentTotalFacture" name="paymentTotalFacture"  value={this.state.creditCards.balance} disabled />
                
                <br />

                <Card className="paymentCard">
                    <CardHeader><b>À partir de compte débit : </b></CardHeader>
                    <CardBody>
                        <CardTitle>Solde actuel : </CardTitle>
                        <Input id="soldeActuel" name="soldeActuel" value={this.state.debitCards.balance} disabled />
                    </CardBody>
                </Card>
        
                <br />

                <Card className="virementCard">
                    <CardHeader><b>Virement : </b></CardHeader>
                    <form onSubmit={this.creditCardPayment} id="passwordCardContainer">
                        <CardBody>
                            <CardTitle>Montant : </CardTitle>
                            <Input type="hidden" id="sourceDebitCardNumber" name="sourceDebitCardNumber" value={this.state.debitCards.nbr} />
                            <Input type="hidden" id="targetCreditCardNumber" name="targetCreditCardNumber" value={this.state.creditCards.nbr} />
                            <Input id="amount" name="amount" />
                            <br />                                    
                            <Button type="submit" bsStyle="success">Confirmer</Button>
                        </CardBody>
                    </form>
                </Card>

                <Link to="/DashboardClient"><Button id="btnAnnuler" bsStyle="danger">Annuler</Button></Link>
            </div>
        )
    }
}

function stringifyFormData(fd) {
    const data = {};
        for (let key of fd.keys()) {
        data[key] = fd.get(key);
    }

    return JSON.stringify(data, null, 2);
}
