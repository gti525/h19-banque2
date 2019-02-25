import * as React from "react";
import { Input } from 'reactstrap';
import { HistoriqueCard } from './HistoriqueCard';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { Card, CardHeader, CardBody, Table } from 'reactstrap';

export default class HistoriqueDebit extends React.Component {
    constructor() {
        super();
        this.state = {
            debitCardsInfo: [],
            debitCardsTransactions: [],
            error: null,
        };
    }

    fetchDebitCardsInfo() {
        fetch("http://localhost:8080/api/v1/account/debitCard")
        .then(response => response.json())
        .then(data => this.setState({
            debitCardsInfo: data,
        }))
        .catch(error => this.setState({ error }));
    }

    fetchDebitCardsTransaction() {
        fetch("http://localhost:8080/api/v1/debitCard/transaction")
        .then(response => response.json())
        .then(data => this.setState({
            debitCardsTransactions: data.transactions,
        }))
        .catch(error => this.setState({ error }));
    }

    componentDidMount() {
        this.fetchDebitCardsInfo();
        this.fetchDebitCardsTransaction();
    }

    render () {

        var myBooks = this.state.debitCardsTransactions;
        console.log("Test de mybook: " +  myBooks);
        return (
            <div className="historiqueContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h2><u>Compte Débit</u></h2>
                <h5>Solde actuel : </h5>
                <Input id="histoSoldeDebit" name="histoSoldeDebit" value={this.state.debitCardsInfo.balance} disabled />
                <br />


                <Card className="debitCard">
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
                                this.state.debitCardsTransactions.map((dynamicData) =>
                                <tr className="trow"> 
                                    <td> {dynamicData.timestamp}</td>
                                    <td> {dynamicData.amount} </td>
                                    <td> {dynamicData.description} </td>
                                    </tr>
                                ) }
                            </tbody>

                            {/*
                            <tbody>
                                <tr>
                                    <td scope="row">{this.state.debitCardsTransactions.timestamp}</td>
                                    <td>{this.state.debitCardsTransactions.amount}</td>
                                    <td>{this.state.debitCardsTransactions.description}</td>
                                </tr>
                            </tbody>
                            {this.state.debitCardsTransactions.map((item, key) =>
                                <li key={item.id}>{item.amount}</li>
                            )}
                            */}
                            

                            
                        </Table>
                    </CardBody>
                </Card>

                <Button className="btnImprimer" bsStyle="info">Imprimer</Button>
            </div>
        )
    }
}
