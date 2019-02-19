import * as React from "react";
import { Input } from 'reactstrap';
import { HistoriqueCard } from './HistoriqueCard';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class HistoriqueCredit extends React.Component {
    render () {
        return (
            <div id="historiqueContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h2><u>Compte de crédit</u></h2>

                <div className="row">
                    <div className="column">
                        <h5>Total des transactions facturées : </h5>
                        <Input id="histoTotalTransactionCredit" name="histoTotalTransactionCredit" placeholder="***API 1 000.00$" disabled />
                    </div>
                    <div className="column">
                        <h5>Limite de crédit autorisée : </h5>
                        <Input id="histoLimitCredit" name="histoLimitCredit" placeholder="***API 2 000.00$" disabled />
                    </div>
                </div>

                <br />

                <HistoriqueCard />

                <Button id="btnHistoPayer" bsStyle="info">Payer</Button>
                <Button className="btnImprimer" bsStyle="info">Imprimer</Button>
            </div>
        )
    }
}
