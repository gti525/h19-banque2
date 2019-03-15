import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';

export const CreditCard = ({balance, numCarteCredit} : { balance: number, numCarteCredit: number}) => {
  return (
    <Card className="creditCard">
        <CardHeader><b>Crédit</b></CardHeader>
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
