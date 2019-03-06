import * as React from "react";
import { Card, CardHeader, CardTitle, CardBody, Input, Table } from 'reactstrap';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

export default class DashbordAdmin extends React.Component {
   constructor(props) {
      super(props);
   }
   
   state = {
      debitCards: [],
      error: null,
      isLoading: true
   }

   fetchDebitCards() {
      fetch(this.props.state.URLBackend+"/api/v1/account/debitCard/")
         .then(response => response.json())
         .then(data => this.setState({
            debitCards: data,
            isLoading: false,
         }))
        .catch(error => this.setState({ error, isLoading: false }));
   }

   componentDidMount() {
      this.fetchDebitCards();
   }

   clientLogOut() {
      console.log("in logout");
      fetch(this.props.state.URLBackend+"/logout")
       .then(response => response.json())
      .catch(error => this.setState({ error }));
   }
   
   render () {
      const { isLoading, debitCards, error } = this.state;
      return (
         <div id="dashboardAdminContainer">
            <Button className="btnAccueil" bsStyle="info" disabled>Accueil</Button>
            <h4>Rechercher un client</h4>

            <form onSubmit={this.submitPhaseUn} noValidate>
               <Card className="rechercheCard">
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
                                 <CardTitle>Numéro de compte : </CardTitle>
                                 <Input id="numCompte" name="numCompte" /> 
                              </td>
                              <td>
                                 <CardTitle>Numéro de carte : </CardTitle>
                                 <Input id="numCarte" name="numCarte" /> 
                              </td>
                              <td>
                                 <Button type="submit" bsStyle="success">Recherche</Button>
                              </td>
                           </tr>
                        </tbody>
                     </Table>   
                  </CardBody>
               </Card>
            </form>
               
            <br />

            <Card className="resultatRecherche">
               <CardHeader><b>Clients correspondants aux critères : </b></CardHeader>
               <CardBody>  
                     <Table striped>
                        <thead>
                           <tr>
                              <th>Prenom</th>
                              <th>Nom</th>
                              <th>Numéro de compte</th>
                              <th>Numéro de carte</th>
                           </tr>
                        </thead>

                        <tbody>
                           <p>Data</p>
                        </tbody>
                     </Table>
               </CardBody>

               <br />
               <Link to="/DetailClient"><Button bsStyle="info">Détail</Button></Link>
            </Card>
            
            <Link to="/NouveauClient"><Button id="btnNouveauClient" bsStyle="info">Nouveau client</Button></Link>

            <Link to="/LoginAdmin"><Button id="btnDeconnexion" bsStyle="danger" onClick={this.clientLogOut}>Déconnexion</Button></Link>
         </div>
      );
   }
}
