import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import Button from 'react-bootstrap/lib/Button';
import { Redirect } from 'react-router-dom'

export default class Login extends React.Component { 
  state = {
    redirect: false
  }

  setRedirect = () => {
    this.setState({
      redirect: true
    })
  }

  renderRedirect = () => {
    if (this.state.redirect) {
      return <Redirect to='/LoginAdmin' />
    }
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
                    <Input name="numCarte" placeholder="Numéro de carte" />
                    <br />
                    <Button bsStyle="success">Suivant</Button>
                </CardBody>
            </Card>
            </div>
             
            {this.renderRedirect()}
            <Button id="btnAdministration" bsStyle="info" onClick={this.setRedirect}>Administration</Button>
        </div>
      )
   }
}
