import * as React from "react";
import { Card, CardHeader, CardBody, Table } from 'reactstrap';

export const HistoriqueCard  = ({}) => {
  return (
    <Card className="debitCard">
        <CardHeader><b>Historique des transactions : </b></CardHeader>
        <CardBody>  
            <Table striped> {/* size="sm" pour mettre moins d'espacement, à voir quand il y a bcp de transactions */}
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Montant</th>
                        <th>Description</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td scope="row">2019-01-01 08:41:12</td>
                        <td>-40$</td>
                        <td>McDo</td>
                    </tr>
                    <tr>
                        <td scope="row">2019-01-04 10:21:41</td>
                        <td>-38$</td>
                        <td>Steam</td>
                    </tr>
                    <tr>
                        <td scope="row">2019-01-10 22:12:00</td>
                        <td>+800$</td>
                        <td>TaJob</td>
                    </tr>
                </tbody>
            </Table>
        </CardBody>
    </Card>
  );
};
