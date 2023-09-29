import React from "https://cdn.skypack.dev/react@17.0.1";
import ReactDOM from "https://cdn.skypack.dev/react-dom@17.0.1";

let countDown; // initialised for clock number countdown in the count function
class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { // keep track of the following for clock changes and display
      type: "Session",
      break: 5,
      session: 25,
      timerMinutes: 25,
      timerSeconds: "00",
      running: false,
      paused: false
    };
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick(event) { // if the event's id is one of the following update the state appropriately 
    switch (event.target.id) {
      case "break-increment":
        if (this.state.break < 60) {
          this.setState((state) => {
            return { break: this.state.break + 1 };
          });
        }
        break;
      case "break-decrement":
        if (this.state.break > 1) {
          this.setState((state) => {
            return { break: this.state.break - 1 };
          });
        }
        break;
      case "session-increment":
        if (this.state.session < 60) {
          this.setState((state) => {
            return {
              session: this.state.session + 1,
              timerMinutes: this.state.session + 1
            };
          });
        }
        break;
      case "session-decrement":
        if (this.state.session > 1) {
          this.setState((state) => {
            return {
              session: this.state.session - 1,
              timerMinutes: this.state.session - 1
            };
          });
        }
        break;
      case "reset": // reset state back to its original values 
        clearInterval(countDown); // stop the count function
        this.setState((state) => {
          return {
            type: "Session",
            break: 5,
            session: 25,
            timerMinutes: 25,
            timerSeconds: "00",
            running: false,
            paused: false
          };
        });
        document.getElementById("beep").currentTime = 0; // rewind the audio 
        document.getElementById("beep").pause(); 
        break;
      default:
        return this.state;
        break;
    }
  }

  timer = () => {
    if (this.state.running === false) { // update running to true if it is 'false' as clock countdown should begin
      this.setState({
        running: true
      });
      let secs =
        this.state.timerMinutes * 60 + parseInt(this.state.timerSeconds);
      const now = Date.now();
      const then = now + secs * 1000; 

      countDown = setInterval(() => {
        const secsLeft = Math.round((then - Date.now()) / 1000);
        if (
          this.state.timerMinutes === "00" &&
          this.state.timerSeconds === "00"
        ) {
          document.getElementById("beep").play();
        }
        if (secsLeft < 0) { // end of counting 
          clearInterval(countDown);
          this.break();
          return;
        }
        this.display(secsLeft);
      }, 1000);
    } else {
      clearInterval(countDown);
      let minsToPause = this.state.timerMinutes;
      let secsToPause = this.state.timerSeconds;
      this.setState({
        running: false,
        timerMinutes: minsToPause,
        timerSeconds: secsToPause
      });
    }
  };

  display = (seconds) => {
    const mins = Math.floor(seconds / 60);
    const secs = seconds % 60;
    if (secs >= 10 && mins >= 10) {
      this.setState({
        timerMinutes: mins,
        timerSeconds: secs
      });
    } else if (secs <= 9) {
      this.setState({
        timerMinutes: "0" + mins,
        timerSeconds: "0" + secs
      });
    } else {
      this.setState({
        timerMinutes: "0" + mins,
        timerSeconds: secs
      });
    }
  };

  break = () => { 
    if (this.state.paused === false) {
      if (this.state.break > 9) {
        this.setState({
          type: "Break",
          timerMinutes: this.state.break,
          timerSeconds: "00",
          running: false,
          paused: true
        });
        this.timer();
      } else { // break <= 9
        this.setState({
          type: "Break",
          timerMinutes: "0" + this.state.break,
          timerSeconds: "00",
          running: false,
          paused: true
        });
        this.timer();
      }
    } else { // paused == true (switch from true to false and false to true continuously after the sessions have run there course
      if (this.state.session > 9) {
        this.setState({
          type: "Session",
          timerMinutes: this.state.session,
          timerSeconds: "00",
          running: false,
          paused: false
        });
        this.timer();
      } else {
        this.setState({
          type: "Session",
          timerMinutes: "0" + this.state.session,
          timerSeconds: "00",
          running: false,
          paused: false
        });
        this.timer();
      }
    }
  };

  render() {
    return (
      <body>
        <div className="title">25 + 5 Clock</div>
        <div className="row">
          <div className="break">
            <div id="break-label">Break Length</div>
            <div id="adjustment">
              <div // if a up arrow is clicked increase the number, if a down arrow is decrease the number (using handleClick)
                className="fa fa-arrow-up fa-2x custom-cursor-on-hover"
                id="break-increment"
                onClick={this.handleClick}
              ></div>
              <div id="break-length">{this.state.break}</div>
              <div
                className="fa fa-arrow-down fa-2x custom-cursor-on-hover"
                id="break-decrement"
                onClick={this.handleClick}
              ></div>
            </div>
          </div>

          <div className="timer">
            <div id="timer-label">{this.state.type}</div>
            <div id="time-left">
              <audio
                id="beep"
                src="https://raw.githubusercontent.com/freeCodeCamp/cdn/master/build/testable-projects-fcc/audio/BeepSound.wav"
              ></audio>
              {this.state.timerMinutes}:{this.state.timerSeconds}
            </div>
            <div id="options">
              <div
                className="fa fa-play"
                id="start_stop" // when the play or pause button image is clicked run the 'timer' function
                onClick={() => this.timer()}
              ></div>
              <div
                className="fa fa-pause"
                id="start_stop"
                onClick={() => this.timer()}
              ></div>
              <div
                className="fa fa-refresh" // reset the state using handleClick
                id="reset"
                onClick={this.handleClick}
              ></div>
            </div>
          </div>

          <div className="session">
            <div id="session-label">Session Length</div>
            <div id="adjustment">
              <div
                className="fa fa-arrow-up fa-2x custom-cursor-on-hover"
                id="session-increment"
                onClick={this.handleClick}
              ></div>
              <div id="session-length">{this.state.session}</div>
              <div
                className="fa fa-arrow-down fa-2x custom-cursor-on-hover"
                id="session-decrement"
                onClick={this.handleClick}
              ></div>
            </div>
          </div>
        </div>
      </body>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("react"));
