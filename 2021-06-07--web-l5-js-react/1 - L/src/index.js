import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import _ from 'lodash';

class MyForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = { karta: 'Karta własności' };
    this.state = { domki: 1 };
    this.state = { hotel: false };
    this.state = { rysuj: 0 };
  }
  myChangeHandler = (event) => {
    this.setState({ karta: event.target.value });
  }
  do = (event) => {
    this.setState({ domki: event.target.value });
  }
  hot = (event) => {
    this.setState({ hotel: event.target.checked });
  }
  but = (event) => {
    if (this.state.rysuj === 0) {
      this.setState({ rysuj: 1 });
    }
    else {
      this.setState({ rysuj: 0 });
    }

  }
  render() {
    let header = [];
    let buton = "";
    let a = 0;
    let wl = [], wz = [], wi = [], lo = [];
    if (this.state.rysuj === 0) {
      if (this.state.karta === "Karta własności") {

        header.push(<input class="input" type="text" id="nazwa" placeholder="Podaj nazwę własności"></input>);
        header.push(<br></br>);
        header.push(<br></br>);
        header.push(<input class="input" type="number" min="1" step="any" id="dcz" placeholder="Ile czynsz? (€)"></input>);
        header.push(<br></br>);
        header.push(<br></br>);
        header.push(<input class="input" type="number" min="1" max="4" step="any" onChange={this.do} placeholder="Ile domków max?" ></input>);
        _.times(this.state.domki, (i) => {
          var id = "dcz" + (i + 1);
          header.push(<br></br>);
          var tekst = "Czynsz za domek " + (i + 1) + " (€)";
          header.push(<input type="number" min="1" step="any" id={id} placeholder={tekst}></input>);

          if (i !== this.state.domki - 1)
            header.push(<br></br>);


        });
        header.push(<br></br>);
        header.push(<input class="input" class="cb" type="checkbox" id="hotel" onChange={this.hot} name="hotel"></input>);
        header.push(<label for="hotel"> Czy ma hotel?</label>);
        header.push(<br></br>);
        if (this.state.hotel === true) {
          header.push(<input class="input" type="number" id="hczynsz" min="1" step="any" placeholder="Cena za czynsz hotelu (€)" ></input>);
          header.push(<br></br>);
          header.push(<input class="input" type="number" id="hcena" min="1" step="any" placeholder="Koszt hotelu. (€)"></input>);
          header.push(<br></br>);
        }
        header.push(<br></br>);
        header.push(<input class="input" type="number" min="1" id="kdomu" step="any" placeholder="Koszt domu. (€ za każdy)"></input>);
        header.push(<br></br>);
        header.push(<br></br>);


      } else if (this.state.karta === "Karta koleji") {


        header.push(<input class="input" type="text" id="nazwa" placeholder="Podaj nazwę własności"></input>);
        header.push(<br></br>);
        header.push(<br></br>);
        header.push(<input class="input" type="text" id="tekst" placeholder="Podaj cenę własności"></input>);
        header.push(<br></br>);
        header.push(<br></br>);


      } else if (this.state.karta === "Więzienie") {


        header.push(<input class="input" type="text" id="ile" placeholder="Podaj na ile rund idziesz do więzienia"></input>);
        header.push(<br></br>);
        header.push(<br></br>);
        header.push(<input class="input" type="text" id="kaucja" placeholder="Podaj ile kaucja (€)"></input>);
        header.push(<br></br>);
        header.push(<br></br>);

      } else if (this.state.karta === "Loteria") {

        header.push(<input class="input" type="text" id="aa" placeholder="Podaj tytuł karty"></input>);
        header.push(<br></br>);
        header.push(<br></br>);
        header.push(<input class="input" type="text" id="a" placeholder="Podaj opis karty"></input>);
        header.push(<br></br>);
        header.push(<br></br>);
      } else if (this.state.karta === "Start") {

      }

    }
    else {

      if (this.state.karta === "Karta własności") {

        wl.push(<div class="nazwa content" >{document.getElementById('nazwa').value}</div>);
        wl.push(<br></br>);
        wl.push(<div class="dcz" >Czynsz za własność :<span class="tekst">{document.getElementById("dcz").value} €</span></div>);
        wl.push(<br></br>);
        let n = 0;
        _.times(this.state.domki, (i) => {
          var id = "dcz" + (i + 1);
          wl.push(<div class="id" >Cena za domek {(i + 1)} <span class="tekst">{document.getElementById(id).value} €</span> <img src="https://img-premium.flaticon.com/png/512/25/25694.png?token=exp=1622055760~hmac=69c22e2343504430d863be34dbba5db9"></img></div>);
          wl.push();
          if (i !== this.state.domki - 1)
            wl.push(<br></br>);
          n++;
        });
        wl.push(<br></br>);
        wl.push(<h2>Ilość domków: {n}</h2>);
        wl.push(<br></br>);
        wl.push(<div class="line"></div>);
        if (this.state.hotel === true) {
          wl.push(<div class="hczynsz" >Czynsz hotelu: <span class="tekst">{document.getElementById("hczynsz").value} €</span><img src="https://image.flaticon.com/icons/png/512/244/244989.png"></img></div>);
          wl.push(<br></br>);
          wl.push(<div class="hcena" >Cena za hotel: <span class="tekst">{document.getElementById("hcena").value} €</span></div>);
        }

        wl.push(<div class="kdomu" >Cena za domek: <span class="tekst">{document.getElementById("kdomu").value} €</span></div>);


      } else if (this.state.karta === "Karta koleji") {

        wz.push(<div class="nazwa" >{document.getElementById("nazwa").value}</div>);
        wz.push(<div class="line"></div>);
        // wz.push(<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/54/Bahn_aus_Zusatzzeichen_1024-15.svg/120px-Bahn_aus_Zusatzzeichen_1024-15.svg.png"
        //   class="image is-128x128	"
        // />)
        wz.push(
          <iframe width="420" height="315"
            src="https://www.youtube.com/embed/_BKdqWC-Fes">
          </iframe>
        )
        // wz.push(<br></br>);
        wz.push(<div class="tek" >Cena: {document.getElementById("tekst").value} €</div>);

        // wz.push(<br></br>);

      } else if (this.state.karta === "Więzienie") {

        wi.push(<div class="prison"><img src="https://www.pngkey.com/png/full/227-2275140_banner-download-swiss-go-to-monopoly-go-to.png"></img></div>);
        wi.push(<div class="line"></div>);
        wi.push(<br></br>);

        wi.push(<br></br>);
        wi.push(<br></br>);
        wi.push(<div class="ile" >Idziesz do więzienia na rundy: <span class="tekst">{document.getElementById("ile").value} </span>  </div>);
        wi.push(<br></br>);
        wi.push(<div class="kaucja" >Aby się wykupić możesz wpłacić: <span class="tekst">{document.getElementById("kaucja").value} €</span></div>);
        wi.push(<br></br>);
      } else if (this.state.karta === "Loteria") {

        lo.push(<div class="question"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Orange_question_mark.svg/450px-Orange_question_mark.svg.png"></img></div>);
        lo.push(<br></br>);
        lo.push(<div class="line"></div>);
        lo.push(<div class="aa" >{document.getElementById("aa").value}</div>);
        lo.push(<br></br>);
        lo.push(<div class="a" >{document.getElementById("a").value}</div>);

        lo.push(<br></br>);
      } else if (this.state.karta === "Start") {
        lo.push(<div class="question bg-white"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/IK_Start_new_logo.svg/985px-IK_Start_new_logo.svg.png"></img></div>);
        lo.push(<br></br>);
        lo.push(<div class="line"></div>);
        lo.push(<br></br>);

        lo.push(<br></br>);
      }


    }

    if (!this.state.rysuj) {

      buton = <button class="button" type="submit" onClick={this.but} >Wygeneruj</button>

    }
    else {
      buton = <button class="input" type="submit" onClick={this.but} >Wróć</button>

    }

    return (
      <div>
        <div class="form-group">
          <label for="exampleFormControlSelect1">Wybierz rodzaj karty</label>
          <select class="input" class="form-control" id="exampleFormControlSelect1" onChange={this.myChangeHandler}>
            <option disabled selected value> Wybierz jedno </option>
            <option>Karta własności</option>
            <option>Karta koleji</option>
            <option>Więzienie</option>
            <option>Loteria</option>
            <option>Start</option>
          </select>
        </div>
        <br></br>
        {this.state.rysuj !== 0 && this.state.karta === "Karta własności" ? (
          <div class="wl">{wl}</div>
        ) : (
          <div></div>
        )}
        {this.state.rysuj !== 0 && this.state.karta === "Karta koleji" ? (
          <div class="wz">{wz}</div>
        ) : (
          <div></div>
        )}
        {this.state.rysuj !== 0 && this.state.karta === "Więzienie" ? (
          <div class="wi">{wi}</div>
        ) : (
          <div></div>
        )}
        {this.state.rysuj !== 0 && this.state.karta === "Loteria" ? (
          <div class="lo">{lo}</div>
        ) : (
          <div></div>
        )}
        {this.state.rysuj !== 0 && this.state.karta === "Start" ? (
          <div class="lo">{lo}</div>
        ) : (
          <div></div>
        )}

        <div class="divek">{header}{buton}</div>


      </div>
    );
  }
}

ReactDOM.render(<div class='content section' style={{ backgroundColor: "black" }}> <MyForm /></div >, document.getElementById('root'));
