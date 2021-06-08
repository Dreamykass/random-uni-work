import logo from './logo.svg';
import './App.css';
import { Component } from 'react';

class App extends Component {

  state = {
    nieruchomosc: true,
    koleje: false,
    szansa: false,
    
    nazwa: '',
    czynsz: 0,
    czynsz_kolor: 0,
    czynsz_jeden: 0,
    czynsz_dwa: 0,
    czynsz_trzy: 0,
    czynsz_cztery: 0,
    czynsz_piec: 0,
    kup_dom: 0,
    kup_hotel: 0,
    zakup: 0,
    zastaw: 0,
  }

  NieruchomoscEvent = (event) => {
    //const text = event.target.value;
    this.setState({
      nieruchomosc: true,
      koleje: false,
      szansa: false,
    });
  }

  KolejeEvent = (event) => {
    //const text = event.target.value;
    this.setState({
      nieruchomosc: false,
      koleje: true,
      szansa: false,
    });
  }

  SzansaEvent = (event) => {
    //const text = event.target.value;
    this.setState({
      nieruchomosc: false,
      koleje: false,
      szansa: true,
    });
  }
  
  nazwaEvent = (event) => {
    const text = event.target.value;
    this.setState({
      nazwa: text,
    });
  }

  czynszEvent = (event) => {
    const text = event.target.value;
    this.setState({
      czynsz: text,
    });
  }

  czynsz_kolorEvent = (event) => {
    const text = event.target.value;
    this.setState({
      czynsz_kolor: text,
    });
  }

  czynsz_jedenEvent = (event) => {
    const text = event.target.value;
    this.setState({
      czynsz_jeden: text,
    });
  }

  czynsz_dwaEvent = (event) => {
    const text = event.target.value;
    this.setState({
      czynsz_dwa: text,
    });
  }

  czynsz_trzyEvent = (event) => {
    const text = event.target.value;
    this.setState({
      czynsz_trzy: text,
    });
  }

  czynsz_czteryEvent = (event) => {
    const text = event.target.value;
    this.setState({
      czynsz_cztery: text,
    });
  }

  czynsz_piecEvent = (event) => {
    const text = event.target.value;
    this.setState({
      czynsz_piec: text,
    });
  }

  kup_domEvent = (event) => {
    const text = event.target.value;
    this.setState({
      kup_dom: text,
    });
  }

  kup_hotelEvent = (event) => {
    const text = event.target.value;
    this.setState({
      kup_hotel: text,
    });
  }

  zakupEvent = (event) => {
    const text = event.target.value;
    this.setState({
      zakup: text,
    });
  }

  zastawEvent = (event) => {
    const text = event.target.value;
    this.setState({
      zastaw: text,
    });
  }

  render() {
    
    // NIERUCHOMOŚCI
    if(this.state.nieruchomosc) return (
    <div className="App">
      <header className="App-header">
        
        <div className="Buttony">

          <input className="button" type="button" value="Nieruchomość" onClick={this.NieruchomoscEvent}/>
          <input className="button" type="button" value="Koleje" onClick={this.KolejeEvent}/>
          <input className="button" type="button" value="Szansa" onClick={this.SzansaEvent}/>

        </div>
        
        <div className="Karta">

          <div className="w_karta Tytul_karta">
            Karta własności
            <input 
              onChange={this.nazwaEvent}
              className="input_tytul"
              type="text" 
              placeholder="Nazwa"
            />
          </div>

          <div>
          
          <div className="czynsz">
            <div className="left">
              Czynsz:
              <br/><br/>Czynsz przy pełnym kolorze:
              <br/><br/>Czynsz z 1 domem:
              <br/><br/>Czynsz z 2 domami:
              <br/><br/>Czynsz z 3 domami:
              <br/><br/>Czynsz z 4 domami:
              <br/><br/>Czynsz z hotelem:
            </div>

            <div className="right">
            
              <input 
                onChange={this.czynszEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_kolorEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_jedenEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_dwaEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_trzyEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_czteryEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_piecEvent}
                className="input"
                type="number" 
                min="0"
              />
            </div>
          </div>

          <div className="dol">
            
            <div className="left">
              <br/><br/>Koszt domu:
              <br/><br/>Koszt hotelu:
              <br/><br/>Wartość kupna:
              <br/><br/>Wartość zastawu:
            </div>

            <div className="right">
            <br/><br/>
              <input 
                onChange={this.kup_domEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.kup_hotelEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.zakupEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.zastawEvent}
                className="input"
                type="number" 
                min="0"
              />
            </div>
          </div>
        </div>
        </div>

        <div className="Karta">

          <div className="w_karta Tytul_karta">
            Karta własności
            <b>{this.state.nazwa}</b>
          </div>

          <div>
          <div className="czynsz">
            <div className="left">
              Czynsz:
              <br/><br/>Czynsz przy pełnym kolorze:
              <br/><br/>Czynsz z 1 domem:
              <br/><br/>Czynsz z 2 domami:
              <br/><br/>Czynsz z 3 domami:
              <br/><br/>Czynsz z 4 domami:
              <br/><br/>Czynsz z hotelem:
            </div>

            <div className="right">
              {this.state.czynsz}
              <br/><br/>{this.state.czynsz_kolor}
              <br/><br/>{this.state.czynsz_jeden}
              <br/><br/>{this.state.czynsz_dwa}
              <br/><br/>{this.state.czynsz_trzy}
              <br/><br/>{this.state.czynsz_cztery}
              <br/><br/>{this.state.czynsz_piec}
            </div>
          </div>

          <div className="dol">

            <div className="left">
              <br/><br/>Koszt domu:
              <br/><br/>Koszt hotelu:
              <br/><br/>Wartość kupna:
              <br/><br/>Wartość zastawu:
            </div>

            <div className="right">
              <br/><br/>{this.state.kup_dom}
              <br/><br/>{this.state.kup_hotel}
              <br/><br/>{this.state.zakup}
              <br/><br/>{this.state.zastaw}
            </div>
          </div>
        </div>
        </div>
        
      </header>
    </div>
    );

    // KOLEJE
    if(this.state.koleje) return (
      <div className="App">
        <header className="App-header">

          <div className="Buttony">

          <input className="button" type="button" value="Nieruchomość" onClick={this.NieruchomoscEvent}/>
          <input className="button" type="button" value="Koleje" onClick={this.KolejeEvent}/>
          <input className="button" type="button" value="Szansa" onClick={this.SzansaEvent}/>

        </div>
        
        <div className="Karta">

          <div className="w_karta Tytul_karta">
            Karta własności
            <input 
              onChange={this.nazwaEvent}
              className="input_tytul"
              type="text" 
              placeholder="Nazwa"
            />
          </div>

          <div>
          
          <div className="czynsz">
            <div className="left">
              Czynsz:
              <br/><br/>Czynsz z 2 kolejami:
              <br/><br/>Czynsz z 3 kolejami:
              <br/><br/>Czynsz z 4 kolejami:
            </div>

            <div className="right">
            
              <input 
                onChange={this.czynszEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_dwaEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_trzyEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_czteryEvent}
                className="input"
                type="number" 
                min="0"
              />
            </div>
          </div>

          <div className="dol">
            
            <div className="left">
            <br/><br/>
            <br/><br/>
              <br/><br/>Wartość kupna:
              <br/><br/>Wartość zastawu:
            </div>

            <div className="right">
            <br/><br/>
            <br/><br/>
            <br/><br/>
              <input 
                onChange={this.zakupEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.zastawEvent}
                className="input"
                type="number" 
                min="0"
              />
            </div>
          </div>
        </div>
        </div>

        <div className="Karta">

          <div className="w_karta Tytul_karta">
            Karta własności
            <b>{this.state.nazwa}</b>
          </div>

          <div>
          <div className="czynsz">
            <div className="left">
              Czynsz:
              <br/><br/>Czynsz z 2 kolejami:
              <br/><br/>Czynsz z 3 kolejami:
              <br/><br/>Czynsz z 4 kolejami:
            </div>

            <div className="right">
              {this.state.czynsz}
              <br/><br/>{this.state.czynsz_dwa}
              <br/><br/>{this.state.czynsz_trzy}
              <br/><br/>{this.state.czynsz_cztery}
            </div>
          </div>

          <div className="dol">

            <div className="left">
            <br/><br/>
            <br/><br/>
              <br/><br/>Wartość kupna:
              <br/><br/>Wartość zastawu:
            </div>

            <div className="right">
            <br/><br/>
            <br/><br/>
              <br/><br/>{this.state.zakup}
              <br/><br/>{this.state.zastaw}
            </div>
          </div>
        </div>
        </div>

        </header>
      </div>
    );

    // SZANSA
    if(this.state.szansa) return (
      <div className="App">
        <header className="App-header">

          <div className="Buttony">

          <input className="button" type="button" value="Nieruchomość" onClick={this.NieruchomoscEvent}/>
          <input className="button" type="button" value="Koleje" onClick={this.KolejeEvent}/>
          <input className="button" type="button" value="Szansa" onClick={this.SzansaEvent}/>

        </div>
        
        <div className="Karta">

          <div className="w_karta Tytul_karta">
            Karta własności
            <input 
              onChange={this.nazwaEvent}
              className="input_tytul"
              type="text" 
              placeholder="Nazwa"
            />
          </div>

          <div>
          
          <div className="czynsz">
            <div className="left">
              Czynsz:
              <br/><br/>Czynsz z 2 kolejami:
              <br/><br/>Czynsz z 3 kolejami:
              <br/><br/>Czynsz z 4 kolejami:
            </div>

            <div className="right">
            
              <input 
                onChange={this.czynszEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_dwaEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_trzyEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.czynsz_czteryEvent}
                className="input"
                type="number" 
                min="0"
              />
            </div>
          </div>

          <div className="dol">
            
            <div className="left">
            <br/><br/>
            <br/><br/>
              <br/><br/>Wartość kupna:
              <br/><br/>Wartość zastawu:
            </div>

            <div className="right">
            <br/><br/>
            <br/><br/>
            <br/><br/>
              <input 
                onChange={this.zakupEvent}
                className="input"
                type="number" 
                min="0"
              />
              <br/>
              <input 
                onChange={this.zastawEvent}
                className="input"
                type="number" 
                min="0"
              />
            </div>
          </div>
        </div>
        </div>

        <div className="Karta">

          <div className="w_karta Tytul_karta">
            Karta własności
            <b>{this.state.nazwa}</b>
          </div>

          <div>
          <div className="czynsz">
            <div className="left">
              Czynsz:
              <br/><br/>Czynsz z 2 kolejami:
              <br/><br/>Czynsz z 3 kolejami:
              <br/><br/>Czynsz z 4 kolejami:
            </div>

            <div className="right">
              {this.state.czynsz}
              <br/><br/>{this.state.czynsz_dwa}
              <br/><br/>{this.state.czynsz_trzy}
              <br/><br/>{this.state.czynsz_cztery}
            </div>
          </div>

          <div className="dol">

            <div className="left">
            <br/><br/>
            <br/><br/>
              <br/><br/>Wartość kupna:
              <br/><br/>Wartość zastawu:
            </div>

            <div className="right">
            <br/><br/>
            <br/><br/>
              <br/><br/>{this.state.zakup}
              <br/><br/>{this.state.zastaw}
            </div>
          </div>
        </div>
        </div>

        </header>
      </div>
    );
  }
}

export default App;
