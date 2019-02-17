import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';

export const PasswordCard  = ({ /*onSubmitLogin, onChangeLogin*/ }) => {
  return (
    <div id='loginContainer'>
      <form id="passwordCardContainer">
        <Card className="passwordCard">
            <CardHeader><b>Connexion</b></CardHeader>
            <CardBody>
                <CardTitle>Veuillez entrer votre mot de passe : </CardTitle>
                <Input /*onChange={onChangeLogin}*/ type="password" id="password" name="password" />
                <br />
                
                <Button bsStyle="success" type="submit" /*onClick={onSubmitLogin}*/>Valider</Button>
            </CardBody>
        </Card>
      </form>
    </div>
  );
};
