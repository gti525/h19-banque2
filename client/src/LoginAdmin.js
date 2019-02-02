import * as React from "react";
import { Card, CardHeader, CardFooter, CardBody, CardTitle, CardText, Input } from 'reactstrap';
import Button from 'react-bootstrap/lib/Button';
import { Redirect } from 'react-router-dom'

// Partie du code trouvé sur: https://jsfiddle.net/everdimension/3bo263xj/
const inputParsers = {
    date(input) {
        const split = input.split('/');
        const day = split[1]
        const month = split[0];
        const year = split[2];
        return `${year}-${month}-${day}`;
    },
    uppercase(input) {
        return input.toUpperCase();
    },
    number(input) {
        return parseFloat(input);
    },
};

class ShakingError extends React.Component {
    constructor() { super(); this.state = { key: 0 }; }

    componentWillReceiveProps() {
    // update key to remount the component to rerun the animation
      this.setState({ key: ++this.state.key });
  }
  
  render() {
      return <div key={this.state.key} className="bounce">{this.props.text}</div>;
  }
}



export default class Login extends React.Component { 

    constructor() {
        super();
        this.state = {};
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    // Fait la gestion du bouton "submit"
    handleSubmit(event) {
        event.preventDefault();
        if (!event.target.checkValidity()) {
            this.setState({
            invalid: true,
            displayErrors: true,
          });
          return;
        }
        const form = event.target;
        const data = new FormData(form);
    
        for (let name of data.keys()) {
          const input = form.elements[name];
          const parserName = input.dataset.parse;
          console.log('parser name is', parserName);
          if (parserName) {
            const parsedValue = inputParsers[parserName](data.get(name))
            data.set(name, parsedValue);
          }
        }
        
        this.setState({
            res: stringifyFormData(data),
          invalid: false,
          displayErrors: false,
        });
    
        // fetch('/api/form-submit-url', {
        //   method: 'POST',
        //   body: data,
        // });
      }

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
    
        const { res, invalid, displayErrors } = this.state;                                 
        return (
            <div>
                <div id='adminConnexionContainer' >
                <form
                    onSubmit={this.handleSubmit}
                    noValidate
                    className={displayErrors ? 'displayErrors' : ''}
                >
                    <br />
                    <Card className="card">
                        <CardHeader><b>Connexion Administration</b></CardHeader>
                        <CardBody>
                            <CardTitle>Nom d'utilisateur : </CardTitle>
                            <input 
                                id="username"
                                name="username"
                                type="text" 
                                data-parse="uppercase"
                                placeholder="" 
                            />
                            <br />

                            <CardTitle>Mot de passe : </CardTitle>
                            <input 
                                id="password"
                                type="password" 
                                name="password" 
                                placeholder="" />
                            <br />

                            <button bsStyle="success">Se connecter</button>
                        </CardBody>
                    </Card>
                </form>

                <div className="res-block">
                    {invalid && (
                        <ShakingError text="Form is not valid" />
                    )}
                    {!invalid && res && (
                        <div>
                        <h3>Transformed data to be sent:</h3>
                        <pre>FormData {res}</pre>
                        </div>
                    )}
                </div>
                
                </div>

            
                
                {this.renderRedirect()}
                <Button id="btnAdministration" bsStyle="info" onClick={this.setRedirect}>Client</Button>
            </div>

            
        )
   }
}

function stringifyFormData(fd) {
    const data = {};
      for (let key of fd.keys()) {
        data[key] = fd.get(key);
    }
    return JSON.stringify(data, null, 2);
  }