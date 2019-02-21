import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';

export const NumCard  = ({onSubmitLogin} : { onSubmitLogin: any}, {onChangeLogin} : { onChangeLogin: any}) => {
  return (
    <div className='loginContainer'>
      <form id="numCardContainer">
        <Card className="numCard">
            <CardHeader><b>Connexion</b></CardHeader>
            <CardBody>
                <CardTitle>Veuillez entrer votre numéro de carte : </CardTitle>
                <Input onChange={onChangeLogin} id="numCarte" name="NumCarte" placeholder="Numéro de carte" />
                <br />
                
                <Button id="btnLoginNumCarte" bsStyle="success" type="submit" onClick={onSubmitLogin}>Entrer</Button>
            </CardBody>
        </Card>
      </form>
    </div>
  );
};
