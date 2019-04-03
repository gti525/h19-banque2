import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';

export const CreditCard = ({balance, numCarteCredit, cardLimit} : { balance: number, numCarteCredit: number, cardLimit: number}) => {
  return (
    <Card className="creditCard">
        <CardHeader><b>Crédit | (Limite : {cardLimit})</b></CardHeader>
        <CardBody>
            <CardTitle>Total des transactions facturées : </CardTitle>
            <Input id="soldeCredit" name="soldeCredit" value={balance} disabled />
            <br />
            
            <a href={"/AdminHistoriqueCredit?creditCardNumber=" + numCarteCredit}>
              <Button id="btnHistoTransaction" bsStyle="success">Historique de transactions</Button>
            </a>
        </CardBody>
    </Card>
  );
};
