import React from "https://cdn.skypack.dev/react@17.0.1";
import ReactDOM from "https://cdn.skypack.dev/react-dom@17.0.1";
// Version needs cleaning 
const zeroToNineRegex = /[0-9.]/; // regex for finding value between 0-9
const hasEqualsRegex = /[=]/; // regex for finding a (=) sign
class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { // store the input and total to display on the calculator screen
      input: "0",
      total: ""
    };
    this.handleClick = this.handleClick.bind(this);
  }
  handleClick(event) { // when a calculator button is clicked perform the correct task
    switch (event.target.innerText) {
      case "AC": // resets the calculator
        this.setState((state) => {
          return {
            input: "0",
            total: ""
          };
        });
        break;
      case "/":
        if (hasEqualsRegex.test(this.state.total)) { // if '=' is found inside total when this button is clicked, input shows the operator only and total shows the answer and the specific operator
          let equal = this.state.total.indexOf("=");
          this.setState((state) => {
            return {
              input: "/",
              total: this.state.total.slice(equal + 1) + event.target.innerText
            };
          });
        } else if (zeroToNineRegex.test(this.state.total.slice(-1))) { // if the previous element in total is a number do the following
          this.setState((state) => {
            return {
              input: "/",
              total: this.state.total.concat(event.target.innerText)
            };
          });
        } else if ( // if total is not empty and the second from last total element is not a number do the following (replace last two elements)
          this.state.total !== "" &&
          zeroToNineRegex.test(this.state.total.slice(-2, -1)) === false
        ) {
          this.setState((state) => {
            return {
              input: "/",
              total: this.state.total.replace(/..$/, "/")
            };
          });
        } else if (this.state.total !== "") { // if second from last is a number then replace the last element
          this.setState((state) => {
            return {
              input: "/",
              total: this.state.total.replace(/.$/, "/")
            };
          });
        }
        break;
      case "x":
        if (hasEqualsRegex.test(this.state.total)) {
          let equal = this.state.total.indexOf("=");
          this.setState((state) => {
            return {
              input: "x",
              total: this.state.total.slice(equal + 1) + "*"
            };
          });
        } else if (zeroToNineRegex.test(this.state.total.slice(-1))) {
          this.setState((state) => {
            return {
              input: "x",
              total: this.state.total.concat("*")
            };
          });
        } else if (
          this.state.total !== "" &&
          zeroToNineRegex.test(this.state.total.slice(-2, -1)) === false
        ) {
          this.setState((state) => {
            return {
              input: "x",
              total: this.state.total.replace(/..$/, "*")
            };
          });
        } else if (this.state.total !== "") {
          this.setState((state) => {
            return {
              input: "x",
              total: this.state.total.replace(/.$/, "*")
            };
          });
        }
        break;
      case "-":
        if (hasEqualsRegex.test(this.state.total)) { 
          let equal = this.state.total.indexOf("=");
          this.setState((state) => {
            return {
              input: "-",
              total: this.state.total.slice(equal + 1) + event.target.innerText
            };
          });
        } else if ( // ensure three operators can not be inputted into the calculator
          this.state.total.slice(-2) !== "--" &&
          this.state.total.slice(-2) !== "+-" &&
          this.state.total.slice(-2) !== "/-" &&
          this.state.total.slice(-2) !== "*-" &&
          this.state.total !== "-"
        ) {
          if (this.state.total.slice(-1) == "-") {
            this.setState((state) => {
              return {
                input: "-",
                total: this.state.total.concat(" " + event.target.innerText)
              };
            });
          } else {
            this.setState((state) => {
              return {
                input: "-",
                total: this.state.total.concat(event.target.innerText)
              };
            });
          }
        }
        break;
      case "+":
        if (hasEqualsRegex.test(this.state.total)) {
          let equal = this.state.total.indexOf("=");
          this.setState((state) => {
            return {
              input: "+",
              total: this.state.total.slice(equal + 1) + event.target.innerText
            };
          });
        } else if (zeroToNineRegex.test(this.state.total.slice(-1))) {
          this.setState((state) => {
            return {
              input: "+",
              total: this.state.total.concat(event.target.innerText)
            };
          });
        } else if (
          this.state.total !== "" &&
          zeroToNineRegex.test(this.state.total.slice(-2, -1)) === false
        ) {
          this.setState((state) => {
            return {
              input: "+",
              total: this.state.total.replace(/..$/, "+")
            };
          });
        } else if (this.state.total !== "") {
          this.setState((state) => {
            return {
              input: "+",
              total: this.state.total.replace(/.$/, "+")
            };
          });
        }
        break;
      case ".": // ensures a 0 is placed before each decimal if it does not follow a number
        if (hasEqualsRegex.test(this.state.total)) {
          let equal = this.state.total.indexOf("=");
          this.setState((state) => {
            return {
              input: "0.",
              total: "0."
            };
          });
        } else if (this.state.total === "") {
          this.setState((state) => {
            return {
              input: this.state.input.concat(event.target.innerText),
              total: this.state.total.concat("0" + event.target.innerText)
            };
          });
        } else if (zeroToNineRegex.test(this.state.total.slice(-1)) === false) {
          this.setState((state) => {
            return {
              input: "0" + event.target.innerText,
              total: this.state.total.concat("0" + event.target.innerText)
            };
          });
        } else if (
          zeroToNineRegex.test(this.state.total.slice(-1)) &&
          this.state.input.indexOf(".") == "-1"
        ) {
          this.setState((state) => {
            return {
              input: this.state.input.concat(event.target.innerText),
              total: this.state.total.concat(event.target.innerText)
            };
          });
        }
        break;
      case "=": // use eval to calculate the total string and display it correctly
        this.setState((state) => {
          return {
            input: eval(this.state.total),
            total: this.state.total.concat(
              event.target.innerText + eval(this.state.total)
            )
          };
        });
        break;
      default: // if a operator is not inputted 
        if (hasEqualsRegex.test(this.state.total)) { // if a number is clicked after an equals is within the total string, assume it is a new equation   
          this.setState((state) => {
            return {
              input: event.target.innerText,
              total: event.target.innerText
            };
          });
        } else if ( // append if a number is the previous element
          this.state.total !== "" &&
          zeroToNineRegex.test(this.state.total.slice(-1))
        ) {
          this.setState((state) => {
            return {
              input: this.state.input.concat(event.target.innerText),
              total: this.state.total.concat(event.target.innerText)
            };
          });
        } else if (
          this.state.total !== "" && // append to total if the previous element is an operator, but replace the input only to show the number
          zeroToNineRegex.test(this.state.total.slice(-1)) == false
        ) {
          this.setState((state) => {
            return {
              input: event.target.innerText,
              total: this.state.total.concat(event.target.innerText)
            };
          });
        } else if (event.target.innerText !== "0") { // if the total string is empty, allow for all numbers except 0 to be entered!
          this.setState((state) => {
            return {
              input: event.target.innerText,
              total: this.state.total.concat(event.target.innerText)
            };
          });
        }
    }
  }
  render() {
    return (
      <body>
        <div id="calculator"> // display the 'Screen' and 'Pad' class, whilst passing handleClick as a variable to 'Pad'
          <Screen total={this.state.total} input={this.state.input} /> 
          <Pad handleClick={this.handleClick} />
        </div>
      </body>
    );
  }
}

class Screen extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <div id="screen"> // uses the App's total and input states to display the total and input
        <div id="total">{this.props.total}</div> 
        <div id="display">{this.props.input}</div>
      </div>
    );
  }
}

class Pad extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <div id="pad">
        <div
          id="clear"
          className="button-ac button" //If a button is clicked, trigger handleClick and perform the appropriate action
          onClick={this.props.handleClick}
        >
          AC
        </div>
        <div
          id="divide"
          className="operator button"
          onClick={this.props.handleClick}
        >
          /
        </div>
        <div
          id="multiply"
          className="operator button"
          onClick={this.props.handleClick}
        >
          x
        </div>
        <div id="seven" className="button" onClick={this.props.handleClick}>
          7
        </div>
        <div id="eight" className="button" onClick={this.props.handleClick}>
          8
        </div>
        <div id="nine" className="button" onClick={this.props.handleClick}>
          9
        </div>
        <div
          id="subtract"
          className="operator button"
          onClick={this.props.handleClick}
        >
          -
        </div>
        <div id="four" className="button" onClick={this.props.handleClick}>
          4
        </div>
        <div id="five" className="button" onClick={this.props.handleClick}>
          5
        </div>
        <div id="six" className="button" onClick={this.props.handleClick}>
          6
        </div>
        <div
          id="add"
          className="operator button"
          onClick={this.props.handleClick}
        >
          +
        </div>
        <div id="one" className="button" onClick={this.props.handleClick}>
          1
        </div>
        <div id="two" className="button" onClick={this.props.handleClick}>
          2
        </div>
        <div id="three" className="button" onClick={this.props.handleClick}>
          3
        </div>
        <div
          id="zero"
          className="button-0 button"
          onClick={this.props.handleClick}
        >
          0
        </div>
        <div id="decimal" className="button" onClick={this.props.handleClick}>
          .
        </div>
        <div
          id="equals"
          className="button-equals button"
          onClick={this.props.handleClick}
        >
          =
        </div>
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("react"));
