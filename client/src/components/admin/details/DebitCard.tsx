import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export const DebitCard  = ({balance} : { balance: number}) => {
  return (
    <Card className="debitCard">
        <CardHeader><b>Débit</b></CardHeader>
        <CardBody>
            <CardTitle>Solde actuel : </CardTitle>
            <Input id="soldeDebit" name="soldeDebit" value={balance} disabled />
            <br />
            
            <Link to="/HistoriqueDebit"><Button id="btnHistoTransaction" bsStyle="success">Historique de transactions</Button></Link>
        </CardBody>
    </Card>
  );
};
