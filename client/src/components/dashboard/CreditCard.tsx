import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export const CreditCard  = ({balance} : { balance: number}) => {
  return (
    <Card className="creditCard">
        <CardHeader><b>Crédit</b></CardHeader>
        <CardBody>
            <CardTitle>Total des transactions dacturées : </CardTitle>
            <Input id="soldeCredit" name="soldeCredit" value={balance} disabled />
            <br />
            
            
            <Link to="/DashboardClient"><Button id="btnPayerSoldeCredit" bsStyle="success">Payer</Button></Link>
            <Link to="/HistoriqueCredit"><Button id="btnHistoTransaction" bsStyle="success">Historique de transactions</Button></Link>
        </CardBody>
    </Card>
  );
};
