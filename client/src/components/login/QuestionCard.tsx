import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';

export const QuestionCard  = ({ /*onSubmitLogin, onChangeLogin*/ }) => {
  return (
    <div id='loginContainer'>
      <form id="questionCardContainer">
        <Card className="questionCard">
            <CardHeader><b>Connexion</b></CardHeader>
            <CardBody>
                <CardTitle>Veuillez répondre à la question suivante : </CardTitle>
                <br />

                <CardTitle>Question ... ... ... </CardTitle>
                <Input /*onChange={onChangeLogin}*/ id="questionSecrete" name="questionSecrete" placeholder="Réponse" />
                <br />
                
                <Button bsStyle="success" type="submit" /*onClick={onSubmitLogin}*/>Valider</Button>
            </CardBody>
        </Card>
      </form>
    </div>
  );
};
