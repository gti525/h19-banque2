import * as React from "react";
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { Card, CardHeader, CardBody, CardTitle, Input } from "reactstrap";

export default class VirementInterac extends React.Component {
    render () {
        return (
            <div id="virementContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h2><u>Virement Interac</u></h2>

                <h5>Entrer le numéro de compte du destinataire : </h5>
                <Input id="numDestinataire" name="numDestinataire" />
                
                <br />

                <div className="row">
                    <div className="column">
                        <Card className="paymentCard">
                            <CardHeader><b>À partir de compte débit : </b></CardHeader>
                            <CardBody>
                                <CardTitle>Solde actuel : </CardTitle>
                                <Input id="soldeActuel" name="soldeActuel" placeholder="***API 2 000.00$" disabled />
                            </CardBody>
                        </Card>
                    </div>
                    <div className="column">
                        <Card className="virementCard">
                            <CardHeader><b>Virement : </b></CardHeader>
                            <CardBody>
                                <CardTitle>Montant : </CardTitle>
                                <Input id="paymentMontant" name="paymentMontant" />
                            </CardBody>
                        </Card>
                    </div>
                </div>

                <Link to="/DashboardClient"><Button id="btnAnnuler" bsStyle="danger">Annuler</Button></Link>
                <Link to="/DashboardClient"><Button id="btnConfirmer" bsStyle="info">Confirmer</Button></Link>
            </div>
        )
    }
}
