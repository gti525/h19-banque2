import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';

export const DebitCard  = ({balance, numCarteDebit} : { balance: number, numCarteDebit: number}) => {
  return (
    <Card className="debitCard">
        <CardHeader><b>Débit</b></CardHeader>
        <CardBody>
            <CardTitle>Solde actuel : </CardTitle>
            <Input id="soldeDebit" name="soldeDebit" value={balance} disabled />
            <br />
            
            <a href={"/AdminHistoriqueDebit?debitCardNumber=" + numCarteDebit}>
              <Button id="btnHistoTransaction" bsStyle="success">Historique de transactions</Button>
            </a>
        </CardBody>
    </Card>
  );
};
