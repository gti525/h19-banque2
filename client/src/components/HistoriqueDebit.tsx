import * as React from "react";
import { Input } from 'reactstrap';
import { HistoriqueCard } from './HistoriqueCard';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class HistoriqueDebit extends React.Component {
    render () {
        return (
            <div id="historiqueContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h2><u>Compte Débit</u></h2>
                <h5>Solde actuel : </h5>
                <Input id="histoSoldeDebit" name="histoSoldeDebit" placeholder="***API 1 000.00$" disabled />
                <br />

                <HistoriqueCard />

                <Button className="btnImprimer" bsStyle="info">Imprimer</Button>
            </div>
        )
    }
}
