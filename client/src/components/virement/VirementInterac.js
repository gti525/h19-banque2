import * as React from "react";
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { Card, CardHeader, CardBody, CardTitle, Input } from "reactstrap";

export default class VirementInterac extends React.Component {
    constructor(props) {
        super(props);
        this.bankTransefert = this.bankTransefert.bind(this);
    }

    state = {
        debitCards: [],
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
    }

    bankTransefert(event) {
        event.preventDefault();
 
         const form = event.target;
         const data = new FormData(form);
         var loginIsSucess = 0;
 
         this.setState({
             res: stringifyFormData(data),
         });
         
         
         const request = async () =>{
             await fetch(this.props.state.URLBackend+"/api/v1/transaction/bankTransfer", {
                 method: "POST", 
                 headers: {
                     'Accept': 'application/json',
                     'Content-Type': 'application/json',
                   },
                 body: JSON.stringify({
                     sourceAccountNumber: this.state.debitCards.nbr,
                     targetAccountNumber: document.getElementById("targetAccountNumber").value, 
                     amount: document.getElementById("amount").value,
                   })
             })
             .then(function(response) {
                 if (response.status === 200) {
                     loginIsSucess = 1;
                 }
                 if(response.status !== 200){
                     loginIsSucess = 0;
                     alert("Information invalide. Réessayez.");
                 }          
            });
 
             if(loginIsSucess === 1){
                 this.props.history.push("/VirementInterac");
                 // Pour rafraichir les données dans la page
                 this.fetchDebitCards();
                 alert("Votre transfert a bien été effectué.");
            }
             
        } 
 
        request(); 
    }

    render () {
        return (
            <div id="virementContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h2><u>Virement Interac</u></h2>

                <h5>Entrer le numéro de compte du destinataire : </h5>
                <Input id="targetAccountNumber" name="targetAccountNumber" />
                
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
                    <form onSubmit={this.bankTransefert} id="passwordCardContainer">
                        <CardBody>
                            <CardTitle>Montant : </CardTitle>
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
