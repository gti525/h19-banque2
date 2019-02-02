import * as React from "react";
// import Dashboard from './Views/Dashboard';
// import Groups from './Views/Groups';
// import History from './Views/History';
import Login from './Login';
import LoginAdmin from './LoginAdmin';
import DashboardAdmin from './Views/DashboardAdmin';
import MyForm from './MyForm';

{/*import Home from './Views/Home';*/} 
import {BrowserRouter, Route} from 'react-router-dom';

import './App.css';
import logo from './Images/Logo_banque2.png';

class App extends React.Component {
  public render() {
    return (
      <div className="App">
        <header className="App-header">
          <a href="./"><img src={logo} className="App-logo" alt="logo" /></a>
          <br />
        </header>

        <BrowserRouter>
          <div>
            <Route exact={true} path="/" component={Login} />
            <Route path="/LoginAdmin" component={LoginAdmin} />
            <Route path="/MyForm" component={MyForm} />
            {/* <Route path="/Home" component={Home} /> */} 
            <Route path="/DashboardAdmin" component={DashboardAdmin} />
          </div>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
