import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';

export const NumCard  = ({ /*onSubmitLogin, onChangeLogin*/ }) => {
  return (
    <div id='loginContainer'>
      <form id="numCardContainer">
        <Card className="numCard">
            <CardHeader><b>Connexion</b></CardHeader>
            <CardBody>
                <CardTitle>Veuillez entrer votre numéro de carte : </CardTitle>
                <Input /*onChange={onChangeLogin}*/ id="numCarte" name="NumCarte" placeholder="Numéro de carte" />
                <br />
                
                <Button bsStyle="success" type="submit" /*onClick={onSubmitLogin}*/>Entrer</Button>
            </CardBody>
        </Card>
      </form>
    </div>
  );
};
