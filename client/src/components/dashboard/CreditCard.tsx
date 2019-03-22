import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export const CreditCard  = ({balance,cardLimit} : { balance: number, cardLimit : number}) => {
  return (
    <Card className="creditCard">
        <CardHeader><b>Crédit | (Limite : {cardLimit})</b></CardHeader>
        <CardBody>
            <CardTitle>Total des transactions facturées : </CardTitle>
            <Input id="soldeCredit" name="soldeCredit" value={balance} disabled />
            <br />
            
            <Link to="/PaymentCarte"><Button id="btnPayerSoldeCredit" bsStyle="success">Payer</Button></Link>
            <Link to="/HistoriqueCredit"><Button id="btnHistoTransaction" bsStyle="success">Historique de transactions</Button></Link>
        </CardBody>
    </Card>
  );
};
