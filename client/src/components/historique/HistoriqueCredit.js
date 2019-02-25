import * as React from "react";
import { Input } from 'reactstrap';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { Card, CardHeader, CardBody, Table } from 'reactstrap';

export default class HistoriqueCredit extends React.Component {
    constructor() {
        super();
        this.state = {
            creditCardsInfo: [],
            creditCardsTransactions: [],
            error: null,
        };
    }

    fetchCreditCardsInfo() {
        fetch("https://banque2-h19.herokuapp.com/api/v1/account/creditCard")
        .then(response => response.json())
        .then(data => this.setState({
            creditCardsInfo: data,
        }))
        .catch(error => this.setState({ error }));
    }

    fetchCreditCardsTransaction() {
        fetch("https://banque2-h19.herokuapp.com/api/v1/creditCard/transaction")
        .then(response => response.json())
        .then(data => this.setState({
            creditCardsTransactions: data.transactions,
        }))
        .catch(error => this.setState({ error }));
    }

    componentDidMount() {
        this.fetchCreditCardsInfo();
        this.fetchCreditCardsTransaction();
    }

    render () {

        return (
            <div className="historiqueContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h2><u>Compte Crédit</u></h2>
                <h5>Solde actuel : </h5>
                <Input id="histoSoldeCredit" name="histoSoldeCredit" value={this.state.creditCardsInfo.balance} disabled />
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
                            {   
                                this.state.creditCardsTransactions.map((dynamicData) =>
                                <tr className="trow"> 
                                    <td> {dynamicData.timestamp}</td>
                                    <td> {dynamicData.amount} </td>
                                    <td> {dynamicData.description} </td>
                                    </tr>
                                ) }
                            </tbody>
                        </Table>
                    </CardBody>
                </Card>

                <Button className="btnImprimer" bsStyle="info">Imprimer</Button>
            </div>
        )
    }
}
