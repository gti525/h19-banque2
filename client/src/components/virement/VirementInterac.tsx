import * as React from "react";
import { Input } from 'reactstrap';
import { PaymentCard } from '../payment/PaymentCard';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class VirementInterac extends React.Component {
    render () {
        return (
            <div id="virementContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h2><u>Virement Interac</u></h2>

                <h5>Entrer le numéro de compte du destinataire : </h5>
                <Input id="numDestinataire" name="numDestinataire" />
                
                <br />

                <PaymentCard />

                <Link to="/DashboardClient"><Button id="btnAnnuler" bsStyle="danger">Annuler</Button></Link>
                <Link to="/DashboardClient"><Button id="btnConfirmer" bsStyle="info">Confirmer</Button></Link>
            </div>
        )
    }
}
