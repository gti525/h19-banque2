import * as React from "react";
import { Card, CardHeader, CardTitle, CardBody, Input, Table } from 'reactstrap';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

export default class NouveauClient extends React.Component {
   constructor(props) {
      super(props);
      
      this.adminLogOut = this.adminLogOut.bind(this);
   }

   verifyLogin(){
      var loginIsSucess = 1;
  
      const request = async () =>{
         const apiCall = await fetch(this.props.state.URLBackend+"/api/v1/admin/ping")
         .then(function(response) {
            if(response.status !== 200){     // Si le login n'est pas accepté par le backend
               console.log("Dans: PAS 200");
               loginIsSucess = 0;
            }
         });
         
         if(loginIsSucess === 0){
            this.props.history.push("/LoginAdmin");
         } 
      } 

      request();
   }

   componentDidMount() {
      this.verifyLogin();
   }

   adminLogOut() {
      console.log("in logout");
      fetch(this.props.state.URLBackend+"/logout")
       .then(response => response.json())
      .catch(error => this.setState({ error }));
   }
   
   render () {
      return (
         <div id="nouveauClientContainer">
            <Link to="/DashboardAdmin"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>
            <h4>Création d'un client</h4>

            <form onSubmit={this.submitNouveauClient} noValidate>
               <Card className="nouveauClientCard">
                  <CardBody>
                     <Table>
                        <tbody>
                           <tr>
                              <td>
                                 <CardTitle>Prenom : </CardTitle>
                                 <Input id="prenom" name="prenom" />
                              </td>
                              <td>
                                 <CardTitle>Nom : </CardTitle>
                                 <Input id="nom" name="nom" />
                              </td>
                              <td>
                                 <CardTitle>Courriel : </CardTitle>
                                 <Input id="numCompte" name="numCompte" /> 
                              </td>
                           </tr>
                        </tbody>
                     </Table>   
                  </CardBody>
               </Card>
            </form>
               
            <br />

            <h4>Où</h4>

            <form onSubmit={this.submitNouveauEnt} noValidate>
               <Card className="nouveauEntCard">
                  <CardBody>
                     <Table>
                        <tbody>
                           <tr>
                              <td>
                                 <CardTitle>Nom d'entreprise : </CardTitle>
                                 <Input id="prenom" name="prenom" />
                              </td>
                              <td>
                                 <CardTitle>Courriel : </CardTitle>
                                 <Input id="numCompte" name="numCompte" /> 
                              </td>
                           </tr>
                        </tbody>
                     </Table>   
                  </CardBody>
               </Card>

                <br />
               <Link to="/DashboardAdmin"><Button bsStyle="danger">Annuler</Button></Link>
               <Button type="submit" bsStyle="success">Confirmer</Button>

               <Link to="/LoginAdmin"><Button id="btnDeconnexion" bsStyle="danger" onClick={this.adminLogOut}>Déconnexion</Button></Link>
            </form>
         </div>
      );
   }
}
