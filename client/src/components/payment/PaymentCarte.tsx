import * as React from "react";
import { Input } from 'reactstrap';
import { PaymentCard } from './PaymentCard';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class PaymentCarte extends React.Component {
    render () {
        return (
            <div id="paymentContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h2><u>Payer votre carte de crédit</u></h2>

                <h5>Total des transactions facturées : </h5>
                <Input id="paymentTotalFacture" name="paymentTotalFacture" placeholder="***API 1 000.00$" disabled />
                
                <br />

                <PaymentCard />

                <Link to="/DashboardClient"><Button id="btnAnnuler" bsStyle="danger">Annuler</Button></Link>
                <Link to="/DashboardClient"><Button id="btnConfirmer" bsStyle="info">Confirmer</Button></Link>
            </div>
        )
    }
}
