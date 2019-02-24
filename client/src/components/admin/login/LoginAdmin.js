import * as React from "react";
import { Card, CardHeader, CardFooter, CardBody, CardTitle, CardText, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { async } from 'q';



// Source : https://jsfiddle.net/everdimension/3bo263xj/
const inputParsers = {
    uppercase(input) {
      return input.toUpperCase();
    }
};

class ShakingError extends React.Component {
  constructor() { super(); this.state = { key: 0 }; }

    componentWillReceiveProps() {
    // Update key to remount the component to rerun the animation
    this.setState({ key: ++this.state.key });
  }
  
  render() {
    return <div key={this.state.key} className="bounce">{this.props.text}</div>;
  }
}

export default class LoginAdmin extends React.Component { 
    constructor() {
      super();
      this.state = {
    };
      this.handleSubmit = this.handleSubmit.bind(this);
    }    

    // Fait la gestion du bouton "submit"
    handleSubmit(event) {
      event.preventDefault();

      if (!event.target.checkValidity()) { this.setState({ invalid: true, displayErrors: true });
        return;
      }

      const form = event.target;
      const data = new FormData(form);

      
      this.setState({
        res: stringifyFormData(data),
        invalid: false,
        displayErrors: false,
      });

      // Variable qui va déterminer si l'utilisateur peut être rediriger à la prochaine page.
      var loginIsSucess = 0;
  
      // Construction du call d'API asynchrone pour permettre le "await"
       const request = async () =>{
        const allo = await fetch('http://localhost:8080/login', {
          method: 'POST', 
          body: data
         })
         .then(function(response) {
           if (response.status === 200) {  // Si la login est valider par le backend
             console.log("Dans: 200");
             // On indique que le login EST réussi
             loginIsSucess = 1;
           }
           if(response.status != 200){     // Si le login n'est pas accepté par le backend
             console.log("Dans: PAS 200");
             // On indique que le login N'EST PAS réussi
             loginIsSucess = 0;
           }          
         });

         // Finalement, si le login est un succès, on redirige l'utilisateur a son dashboard
          if(loginIsSucess === 1){
            this.props.history.push("/DashboardAdmin");
          } 
       } 

       // à la fin du submit, on appel à requête déclaré plus haut.
       request(); 
    }
    
  
    render () {  
      const { res, invalid, displayErrors } = this.state;

      return (
        <div>
            <div className='loginContainer' >
              <form onSubmit={this.handleSubmit} noValidate className={displayErrors ? 'displayErrors' : ''}>
                <br />
                <Card className="card">
                  <CardHeader><b>Connexion Administration</b></CardHeader>
                  <CardBody>
                      <CardTitle>Nom d'utilisateur : </CardTitle>
                      <Input id="username" name="username" type="text" data-parse="uppercase" placeholder="" />
                      <br />
                      <CardTitle>Mot de passe : </CardTitle>
                      <Input id="password" name="password" type="text"  placeholder="" />
                      <br />

                      <Button type="submit" bsStyle="success">Se connecter</Button>
                  </CardBody>
                </Card>
              </form>

              <br />
              <div className="res-block">
                {invalid && (
                  <ShakingError text="Form is not valid" />
                )}
                
              </div>
            </div>
            
            <Link to="/"><Button id="btnClientAdmin" bsStyle="info">Client</Button></Link>
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


