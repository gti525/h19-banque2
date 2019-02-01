import * as React from "react";
import { Card, CardHeader, CardText, CardBody, CardTitle, Input } from 'reactstrap';
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
            <br />

            <div id='numCarteContainer'>
                <Card className="card">
                    <CardHeader><b>Connexion</b></CardHeader>
                    <CardBody>
                        <CardTitle>Veuillez entrer votre numéro de carte : </CardTitle>
                        <Input name="numCarte" placeholder="Numéro de carte" />
                        <br />
                        
                        <Button bsStyle="success">Entrer</Button>
                        <Button bsStyle="primary">Test good</Button>
                        <Button bsStyle="danger">Test bad</Button>
                    </CardBody>
                </Card>
            </div>

            <br />

            <div id='questionsContainer'>
                <Card className="card">
                    <CardHeader><b>Connexion</b></CardHeader>
                        <CardBody>
                        <CardTitle>Veuillez répondre à la question suivante : </CardTitle>
                        <CardText>Question ... ... ... </CardText>
                        <Input name="questionSecrete" placeholder="Réponse" />
                        <br />

                        <Button bsStyle="success">Entrer</Button>
                        <Button bsStyle="primary">Test good</Button>
                        <Button bsStyle="danger">Test bad</Button>
                    </CardBody>
                </Card>
            </div>

            <br />

            <div id='passwordContainer'>
                <Card className="card">
                    <CardHeader><b>Connexion</b></CardHeader>
                        <CardBody>
                        <CardTitle>Veuillez entrer votre mot de passe : </CardTitle>
                        <Input type="password" name="password" placeholder="" />
                        <br />

                        <Button bsStyle="success">Entrer</Button>
                        <Button bsStyle="primary">Test good</Button>
                        <Button bsStyle="danger">Test bad</Button>
                    </CardBody>
                </Card>
            </div>

            {this.renderRedirect()}
            <Button id="btnAdministration" bsStyle="info" onClick={this.setRedirect}>Administration</Button>
        </div>
      )
   }
}
