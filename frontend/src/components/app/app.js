import React, {Component} from 'react';
import Home from "../home/home";
import NavBar from "../parts/navbar/navbar";

class App extends Component {
    render() {
        return (
            <>
                <NavBar/>
                <Home/>
            </>
        );
    }
}

export default App;