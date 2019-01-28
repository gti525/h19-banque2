import * as React from "react";

export default class SignUp extends React.Component { 
  state = { 
  }
  
  render () {                                   
      return (
        <div>
             <div id='connexionContainer'>
               <h3>Veuillez entrer votre numéro de carte : </h3>
               <br />
                  <form id='form'>       
                      <input className='input' type="text"   
                       placeholder="Numéro"/>
                      
                      <button id='submit'>Se connecter</button>
                  </form>
             </div>
        </div>
      )
   }
}
