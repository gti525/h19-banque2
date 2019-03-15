import * as React from "react";
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

document.addEventListener("DOMContentLoaded",function() {
    const e="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTU4LCJpYXQiOjE1NTI0NDI1NTB9._MG5bmn2IZEyRzUyBSx0YIQ68_m0igdPgXOfvWjBAoM";
    const t=function() {
        if("undefined" != typeof Storage && localStorage.getItem("gti525analytic")) {
            const e=JSON.parse(localStorage.getItem("gti525analytic"));

            if(new Date(e.expiration).getTime()>(new Date).getTime())
                return e.clientId
        } return
    }(); t
    ?function(t) {
        let n = new XMLHttpRequest;
        /* eslint-disable no-unused-expressions */
        n.open("GET", "https://gti525-analitycs.herokuapp.com/api/v1/banners/code", !0),
        n.onload=function(o){
            4 === n.readyState && 200 === n.status && Function(`return (${n.responseText})`)()(t,e)},
        n.setRequestHeader("x-access-token",e),n.send()
    }(t)
    :function() { 
        let t = new XMLHttpRequest;
        /* eslint-disable no-unused-expressions */
        t.open("GET", "https://gti525-analitycs.herokuapp.com/api/v1/analytics/code", !0),
        t.onload=function(n){ 
            4 === t.readyState && 200 === t.status && Function(`return (${t.responseText})`)()(e)},
        t.setRequestHeader("x-access-token", e),
        t.send()
    }()
}, !1);

export default class LoginAdmin extends React.Component { 
    constructor(props) {
        super(props);

        this.state = {
            error: false,
        };

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    // Fait la gestion du bouton "submit"
    handleSubmit(event) {
        event.preventDefault();

        const form = event.target;
        const data = new FormData(form);

        // Variable qui va déterminer si l'utilisateur peut être rediriger à la prochaine page.
        var loginIsSucess = 0;

        console.log("Context: "+ this.props.state.URLBackend);
    
        // Construction du call d'API asynchrone pour permettre le "await"
        const request = async () =>{
            await fetch(this.props.state.URLBackend+'/login', {
                method: 'POST', 
                body: data
            })
            .then(function(response) {
                if (response.status === 200) {  // Si la login est valider par le backend
                    console.log("Dans: 200");
                    // On indique que le login EST réussi
                    loginIsSucess = 1;
                }

                if(response.status !== 200){     // Si le login n'est pas accepté par le backend
                    console.log("Dans: PAS 200");
                    // On indique que le login N'EST PAS réussi
                    loginIsSucess = 0;
                }          
            });

            // Finalement, si le login est un succès, on redirige l'utilisateur a son dashboard
            if(loginIsSucess === 1){
                this.props.history.push("/DashboardAdmin");
            } 
            
            if(loginIsSucess === 0){
                this.setState({ error: true });

                document.getElementById("username").value = "";
                document.getElementById("password").value = "";
            } 
        } 

        // à la fin du submit, on appel à requête déclaré plus haut.
        request(); 
    }
  
    render () {
        if(this.state.error === true) {
            return (
                <div className='loginContainer' >
                    <form onSubmit={this.handleSubmit} noValidate>
                        <br />
                        <Card className="card">
                            <CardHeader><b>Connexion Administration</b></CardHeader>
                            <CardBody>
                                <h3 class="redAlerts" >L'identifiant que vous avez entré n'est pas valide. Réessayez.</h3>
                                <CardTitle>Nom d'utilisateur : </CardTitle>
                                <Input id="username" name="username" type="text" />
                                <br />
                                <CardTitle>Mot de passe : </CardTitle>
                                <Input id="password" name="password" type="password" />
                                <br />

                                <Button type="submit" bsStyle="success">Se connecter</Button>
                            </CardBody>

                            <br />
                            <Link to="/"><Button id="btnClientAdmin" bsStyle="info">Client</Button></Link>
                        </Card>
                    </form>
                </div>
            )
        } else {
            return (
                <div className='loginContainer' >
                    <form onSubmit={this.handleSubmit} noValidate>
                        <br />
                        <Card className="card">
                            <CardHeader><b>Connexion Administration</b></CardHeader>
                            <CardBody>
                                <CardTitle>Nom d'utilisateur : </CardTitle>
                                <Input id="username" name="username" type="text" />
                                <br />
                                <CardTitle>Mot de passe : </CardTitle>
                                <Input id="password" name="password" type="password" />
                                <br />

                                <Button type="submit" bsStyle="success">Se connecter</Button>
                            </CardBody>

                            <br />
                            <Link to="/"><Button id="btnClientAdmin" bsStyle="info">Client</Button></Link>
                        </Card>
                    </form>
                </div>
            )
        }
    }
}
