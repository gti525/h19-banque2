import * as React from "react";
import { Card, CardHeader, CardText, CardBody, CardTitle, Input } from 'reactstrap';
import Button from 'react-bootstrap/lib/Button';
import { Redirect } from 'react-router-dom';
import CreditCardInput from 'react-credit-card-input';

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

  renderRedirectTestAPI = () => {
    if (this.state.redirect) {
      return <Redirect to='/DashboardAdmin' />
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
                        <CreditCardInput
                            cardCVCInputRenderer={({ handleCardCVCChange, props }) => (
                                <input
                                {...props}
                                onChange={handleCardCVCChange(e => console.log('cvc change', e))}
                                />
                            )}
                            cardExpiryInputRenderer={({ handleCardExpiryChange, props }) => (
                                <input
                                {...props}
                                onChange={handleCardExpiryChange(e =>
                                    console.log('expiry change', e)
                                )}
                                />
                            )}
                            cardNumberInputRenderer={({ handleCardNumberChange, props }) => (
                                <input
                                {...props}
                                onChange={handleCardNumberChange(e =>
                                    console.log('number change', e)
                                )}
                                />
                            )}
                            />
                        <br />
                        
                        {this.renderRedirectTestAPI()}
                        <Button bsStyle="success" onClick={this.setRedirect}>Entrer</Button>
                    </CardBody>
                </Card>
            </div>
            {/*  
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
            */}

            {this.renderRedirect()}
            <Button id="btnAdministration" bsStyle="info" onClick={ this.setRedirect}>Administration</Button>
        </div>
      )
   }
}
