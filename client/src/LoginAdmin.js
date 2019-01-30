import * as React from "react";
import { Card, CardHeader, CardFooter, CardBody, CardTitle, CardText, Input } from 'reactstrap';
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
            return <Redirect to='/' />
        }
    }
  
    render () {                                   
      return (
        <div>
             <div id='adminConnexionContainer'>
               <br />

               <Card class="card">
                <CardHeader><b>Connexion Administration</b></CardHeader>
                  <CardBody>
                    <CardTitle>Nom d'utilisateur : </CardTitle>
                    <Input name="username" placeholder="" />
                    <br />

                    <CardTitle>Mot de passe : </CardTitle>
                    <Input type="password" name="password" placeholder="" />
                    <br />

                    <Button bsStyle="success">Suivant</Button>
                  </CardBody>
               </Card>
             </div>
             
             {this.renderRedirect()}
             <Button id="btnAdministration" bsStyle="info" onClick={this.setRedirect}>Client</Button>
        </div>
      )
   }
}
