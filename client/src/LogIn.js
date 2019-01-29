import * as React from "react";
import { Card, CardHeader, CardFooter, CardBody, CardTitle, CardText, Input } from 'reactstrap';
import Button from 'react-bootstrap/lib/Button';

export default class SignUp extends React.Component { 
  state = { 
  }
  
  render () {                                   
      return (
        <div>
             <div id='connexionContainer'>
               <br />

               <Card class="card">
                <CardHeader><b>Connexion</b></CardHeader>
                  <CardBody>
                    <CardTitle>Veuillez entrer votre numéro de carte : </CardTitle>
                    <Input placeholder="Numéro de carte" />
                    <br />
                    <Button bsStyle="success">Suivant</Button>
                  </CardBody>
               </Card>
             </div>

             <Button id="btnAdministration" bsStyle="info">Administration</Button>
        </div>
      )
   }
}
