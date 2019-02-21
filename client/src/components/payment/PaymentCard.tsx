import * as React from "react";
import { Card, CardHeader, CardBody, Input } from 'reactstrap';
import CardTitle from 'reactstrap/lib/CardTitle';

export const PaymentCard  = ({}) => {
  return (
    <div className="row">
        <div className="column">
            <Card className="paymentCard">
                <CardHeader><b>À partir de compte débit : </b></CardHeader>
                <CardBody>
                    <CardTitle>Solde actuel : </CardTitle>
                    <Input id="soldeActuel" name="soldeActuel" placeholder="***API 2 000.00$" disabled />
                </CardBody>
            </Card>
        </div>
        <div className="column">
            <Card className="virementCard">
                <CardHeader><b>Virement : </b></CardHeader>
                <CardBody>
                    <CardTitle>Montant : </CardTitle>
                    <Input id="paymentMontant" name="paymentMontant" />
                </CardBody>
            </Card>
        </div>
    </div>
  );
};
