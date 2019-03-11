import * as React from "react";
import { Input } from 'reactstrap';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { Card, CardHeader, CardBody, Table } from 'reactstrap';

export default class HistoriqueCredit extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            creditCardsInfo: [],
            creditCardsTransactions: [],
            error: null,
        };
    }

    // Méthode qui valide si l'utilisateur à bel et bien le droit d'accéder à cette page
    verifyLogin(){
    var loginIsSucess = 1;
        const request = async () =>{
        await fetch(this.props.state.URLBackend+"/api/v1/client/ping")
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

    fetchCreditCardsInfo() {
        fetch(this.props.state.URLBackend+"/api/v1/account/creditCard")
        .then(response => response.json())
        .then(data => this.setState({
            creditCardsInfo: data,
        }))
        .catch(error => this.setState({ error }));
    }

    fetchCreditCardsTransaction() {
        fetch(this.props.state.URLBackend+"/api/v1/creditCard/transaction")
        .then(response => response.json())
        .then(data => this.setState({
            creditCardsTransactions: data.transactions,
        }))
        .catch(error => this.setState({ error }));
    }

    componentDidMount() {
        this.verifyLogin();
        this.fetchCreditCardsInfo();
        this.fetchCreditCardsTransaction();
    }

    render () {
        return (
            <div className="historiqueContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h2><u>Compte Crédit</u></h2>
                <h5>Solde actuel : </h5>
                <Input id="histoSoldeCredit" name="histoSoldeCredit" value={this.state.creditCardsInfo.balanceAsString} disabled />
                <br />


                <Card className="creditCard">
                    <CardHeader><b>Historique des transactions : </b></CardHeader>
                    <CardBody>  
                        <Table striped> {/* size="sm" pour mettre moins d'espacement, à voir quand il y a bcp de transactions */}
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Montant</th>
                                    <th>Description</th>
                                </tr>
                            </thead>

                            <tbody>
                                {this.state.creditCardsTransactions.map((dynamicData) =>
                                    <tr className="trow"> 
                                        <td> {dynamicData.timestampAsString}</td>
                                        <td> {dynamicData.amountAsString} </td>
                                        <td> {dynamicData.description} </td>
                                    </tr>
                                )}
                            </tbody>
                        </Table>
                    </CardBody>
                </Card>

                <Button className="btnImprimer" bsStyle="info">Imprimer</Button>
            </div>
        )
    }
}
