import * as React from "react";
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { Card, CardHeader, CardBody, CardTitle, Input } from "reactstrap";

export default class PaymentCarte extends React.Component {
    constructor(props) {
        super(props);
        this.submitPhaseUn = this.submitPhaseUn.bind(this);
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
        const apiCall = await fetch(this.props.state.URLBackend+"/api/v1/account/debitCard")
        .then(function(response) {
            if(response.status != 200){     // Si le login n'est pas accepté par le backend
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

     submitPhaseUn(event) {
        event.preventDefault();

        this.setState({
            infoPhaseUn: [],
            numCarte: "",
        });
        
        fetch(this.props.state.URLBackend+"/api/v1/challenge/" + document.getElementById("numCarte").value)
         .then(response => response.json())
         .then(data => this.setState({
            infoPhaseUn: data,
            numCarte: document.getElementById("numCarte").value,
            phaseEnCours: 2,   /* Le GET a fonctionné, alors on passe à la phase 2 pour la question secrète */
         }))
        .catch(error => this.setState({ error }));
    }
  


    render () {
        return (
            <div id="paymentContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h2><u>Payer votre carte de crédit</u></h2>

                <h5>Total des transactions facturées : </h5>
                <Input id="paymentTotalFacture" name="paymentTotalFacture"  value={this.state.creditCards.balance} disabled />
                
                <br />

                <div className="row">
                    <div className="column">
                        <Card className="paymentCard">
                            <CardHeader><b>À partir de compte débit : </b></CardHeader>
                            <CardBody>
                                <CardTitle>Solde actuel : </CardTitle>
                                <Input id="soldeActuel" name="soldeActuel" value={this.state.debitCards.balance} disabled />
                            </CardBody>
                        </Card>
                    </div>
                    <div className="column">
                    <form onSubmit={this.submitPhaseFinal} id="passwordCardContainer">
                        <Card className="virementCard">
                            <CardHeader><b>Virement : </b></CardHeader>
                            <CardBody>
                                <CardTitle>Montant : </CardTitle>
                                <Input id="paymentMontant" name="paymentMontant" />
                                <br />                                    
                                <Button type="submit" bsStyle="success">Entrer</Button>
                            </CardBody>
                        </Card>
                    </form>
                    </div>
                </div>

                <Link to="/DashboardClient"><Button id="btnAnnuler" bsStyle="danger">Annuler</Button></Link>
                <Link to="/DashboardClient"><Button id="btnConfirmer" bsStyle="info">Confirmer</Button></Link>
            </div>
        )
    }
}
