import React from "https://cdn.skypack.dev/react@17.0.1";
import ReactDOM from "https://cdn.skypack.dev/react-dom@17.0.1";

document.addEventListener("keydown", (event) => { // listen for when keys are pressed
  const letter = event.key.toUpperCase(); // parse them as uppercase letters
  const audio = document.getElementById(letter); // assign 'audio' to the id of the letter pressed

  switch (letter) { // if a specific letter was pressed assign the inner text to the corresponding name of the sound
    case "Q":
      document.querySelector("#sound").innerText = "Heater-1";
      break;
    case "W":
      document.querySelector("#sound").innerText = "Heater-2";
      break;
    case "E":
      document.querySelector("#sound").innerText = "Heater-3";
      break;
    case "A":
      document.querySelector("#sound").innerText = "Heater-4";
      break;
    case "S":
      document.querySelector("#sound").innerText = "Clap";
      break;
    case "D":
      document.querySelector("#sound").innerText = "Open-HH";
      break;
    case "Z":
      document.querySelector("#sound").innerText = "Kick-n-Hat";
      break;
    case "X":
      document.querySelector("#sound").innerText = "Kick";
      break;
    case "C":
      document.querySelector("#sound").innerText = "Closed-HH";
      break;
    default: // triggered when the user provides an invalid input
      document.querySelector("#sound").innerText = "invalid-key";
      break;
  }
  audio.play(); // play the sound of the audio clip stored within 'audio'
});

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick(event) { // when a click occurs find the inner HTML (the letter) and play the audio stored within the audio tag within the div
    event.preventDefault();
    const letter = event.target.innerHTML;
    const audio = document.getElementById(letter[0]);
    audio.play();

    document.querySelector("#sound").innerText = event.target.id; // update the screen to the appropriate sound name 
  }

  render() { // pass handleClick to the Drum class 
    return <Drum handleClick={this.handleClick} />;
  }
}

class Drum extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return ( // display the drum screen and button pad
      <div id="drum-machine">
        <div id="display">
          <div id="name">| Drum Machine 🎧 |</div>
          <div id="sound">
            <p></p>
          </div>
          <div id="drum">
            <div
              className="drum-pad"
              id="Heater-1"
              onClick={this.props.handleClick} // when this button is clicked play the corresponding mp3 audio stored in the 'audio' tag
            >
              Q
              <audio
                className="clip"
                id="Q"
                src="https://s3.amazonaws.com/freecodecamp/drums/Heater-1.mp3"
                type="audio/mp3"
              ></audio>
            </div>
            <div
              className="drum-pad"
              id="Heater-2"
              onClick={this.props.handleClick}
            >
              W
              <audio
                className="clip"
                id="W"
                src="https://s3.amazonaws.com/freecodecamp/drums/Heater-2.mp3"
              ></audio>
            </div>
            <div
              className="drum-pad"
              id="Heater-3"
              onClick={this.props.handleClick}
            >
              E
              <audio
                className="clip"
                id="E"
                src="https://s3.amazonaws.com/freecodecamp/drums/Heater-3.mp3"
              ></audio>
            </div>
            <div
              className="drum-pad"
              id="Heater-4"
              onClick={this.props.handleClick}
            >
              A
              <audio
                className="clip"
                id="A"
                src="https://s3.amazonaws.com/freecodecamp/drums/Heater-4_1.mp3"
              ></audio>
            </div>
            <div
              className="drum-pad"
              id="Clap"
              onClick={this.props.handleClick}
            >
              S
              <audio
                className="clip"
                id="S"
                src="https://s3.amazonaws.com/freecodecamp/drums/Heater-6.mp3"
              ></audio>
            </div>
            <div
              className="drum-pad"
              id="Open-HH"
              onClick={this.props.handleClick}
            >
              D
              <audio
                className="clip"
                id="D"
                src="https://s3.amazonaws.com/freecodecamp/drums/Dsc_Oh.mp3"
              ></audio>
            </div>
            <div
              className="drum-pad"
              id="Kick-n-Hat"
              onClick={this.props.handleClick}
            >
              Z
              <audio
                className="clip"
                id="Z"
                src="https://s3.amazonaws.com/freecodecamp/drums/Kick_n_Hat.mp3"
              ></audio>
            </div>
            <div
              className="drum-pad"
              id="Kick"
              onClick={this.props.handleClick}
            >
              X
              <audio
                className="clip"
                id="X"
                src="https://s3.amazonaws.com/freecodecamp/drums/RP4_KICK_1.mp3"
              ></audio>
            </div>
            <div
              className="drum-pad"
              id="Closed-HH"
              onClick={this.props.handleClick}
            >
              C
              <audio
                className="clip"
                id="C"
                src="https://s3.amazonaws.com/freecodecamp/drums/Cev_H2.mp3"
              ></audio>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("react"));
