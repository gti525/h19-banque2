import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export const NumCard  = ({ /*onSubmitLogin, onChangeLogin*/ }) => {
  return (
    <div className='loginContainer'>
      <form id="numCardContainer">
        <Card className="numCard">
            <CardHeader><b>Connexion</b></CardHeader>
            <CardBody>
                <CardTitle>Veuillez entrer votre numéro de carte : </CardTitle>
                <Input /*onChange={onChangeLogin}*/ id="numCarte" name="NumCarte" placeholder="Numéro de carte" />
                <br />
                
                <Link to="/DashboardClient"><Button bsStyle="success" type="submit" /*onClick={onSubmitLogin}*/>Entrer</Button></Link>
            </CardBody>
        </Card>
      </form>
    </div>
  );
};
