import * as React from "react";
import { Input } from 'reactstrap';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { Card, CardHeader, CardBody, Table } from 'reactstrap';
import PrintProvider, { Print, NoPrint } from 'react-easy-print';

export default class HistoriqueDebit extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            debitCardsInfo: [],
            debitCardsTransactions: [],
            error: null,
        };
    }

    printWindow(){
        window.print();
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

    fetchDebitCardsInfo() {
        fetch(this.props.state.URLBackend+"/api/v1/account/debitCard")
        .then(response => response.json())
        .then(data => this.setState({
            debitCardsInfo: data,
        }))
        .catch(error => this.setState({ error }));
    }

    fetchDebitCardsTransaction() {
        fetch(this.props.state.URLBackend+"/api/v1/debitCard/transaction")
        .then(response => response.json())
        .then(data => this.setState({
            debitCardsTransactions: data.transactions,
        }))
        .catch(error => this.setState({ error }));
    }

    componentDidMount() {
        this.verifyLogin();
        this.fetchDebitCardsInfo();
        this.fetchDebitCardsTransaction();
    }

    render () {
        return (
            <PrintProvider>
                <div className="historiqueContainer">
                    <NoPrint><Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link></NoPrint>

                    <h2><u>Compte Débit</u></h2>
                    <h5>Solde actuel : </h5>
                    <Input id="histoSoldeDebit" name="histoSoldeDebit" value={this.state.debitCardsInfo.balanceAsString} disabled />
                    <br />

                    <Card className="debitCard">
                        <CardHeader><b>Historique des transactions : </b></CardHeader>
                        <CardBody>
                            <Table striped> {/* size="sm" pour mettre moins d'espacement, à voir quand il y a bcp de transactions */}
                                <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Montant</th>
                                        <th>Solde</th>
                                        <th>Description</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    {this.state.debitCardsTransactions.map((dynamicData) =>
                                        <tr className="trow">
                                            <td> {dynamicData.timestampAsString}</td>
                                            <td> {dynamicData.amountAsString} </td>
                                            <td> {dynamicData.cumulativeSumAsString} </td>
                                            <td> {dynamicData.description} </td>
                                        </tr>
                                    )}
                                </tbody>
                            </Table>
                        </CardBody>
                    </Card>

                   <NoPrint> <Button className="btnImprimer" bsStyle="info" onClick={this.printWindow.bind(this)}>Imprimer</Button></NoPrint>
                </div>
            </PrintProvider>
        )
    }
}
