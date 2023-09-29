import React from "https://cdn.skypack.dev/react@17.0.1";
import ReactDOM from "https://cdn.skypack.dev/react-dom@17.0.1";

const quotes = [ // array of objects containing quote information
  {
    quote: "Be yourself; everyone else is already taken.",
    author: "Oscar Wilde"
  },
  {
    quote: "Injustice anywhere is a threat to justice everywhere.",
    author: "Martin Luther King"
  },
  {
    quote: "I have not failed. I've just found 10,000 ways that won't work.",
    author: "Thomas A. Edison"
  },
  {
    quote:
      "Weak people revenge. Strong people forgive. Intelligent People Ignore.",
    author: "Albert Einstein"
  },
  {
    quote: "Be the change that you wish to see in the world.",
    author: "Mahatma Gandhi"
  },
  {
    quote:
      "Life isn't about finding yourself. Life is about creating yourself.",
    author: "George Bernard Shaw"
  },
  {
    quote: "No one can make you feel inferior without your consent.",
    author: "Eleanor Roosevelt"
  },
  {
    quote: "If you're going through hell, keep going.",
    author: "Winston Churchill"
  },
  {
    quote: "Peace begins with a smile.",
    author: "Mother Teresa"
  },
  {
    quote: "Tomorrow is always fresh, with no mistakes in it.",
    author: "L.M. Montgomery"
  }
];

const colours = [ // array of colours for changing colour when a new quote is generated
  "blue",
  "red",
  "green",
  "orange",
  "purple",
  "yellow",
  "pink",
  "brown",
  "cyan",
  "maroon"
];

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { // state containing a quote, author and color, which changes on click events
      quote: quotes[0].quote,
      author: quotes[0].author,
      colour: colours[0]
    };
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick(event) { // randomly generates a number between 0-9 to decide on quote, author and colour 
    event.preventDefault();
    this.setState((state) => { // saves the new state
      const index = Math.floor(Math.random() * 9);
      return {
        quote: quotes[index].quote,
        author: quotes[index].author,
        colour: colours[index]
      };
    });
  }

  render() {
    return (
      <body
        style={{ // background colour corresponds to the generated colour
          backgroundColor: this.state.colour
        }}
      >
        <QuoteBox // displays the QuoteBox class, passes the following variables with each value 
          colour={this.state.colour}
          quote={this.state.quote}
          author={this.state.author}
          handleClick={this.handleClick}
        />
      </body>
    );
  }
}

class QuoteBox extends React.Component {
  constructor(props) {
    super(props);
  }
  render() { // retrieves the parent values to assign to different values through props
    return (
      <div
        id="quote-box"
        style={{
          color: this.props.colour
        }}
      >
        <div id="textDiv">
          <h1 id="text">"{this.props.quote}"</h1>
          <p id="author">- {this.props.author}</p>
        </div>
        <div>
          <button
            id="new-quote"
            onClick={this.props.handleClick}
            style={{
              backgroundColor: this.props.colour
            }}
          >
            New Quote
          </button>
          <br />
          <a
            id="tweet-quote"
            href="twitter.com/intent/tweet"
            class="fa fa-twitter"
            style={{
              color: this.props.colour
            }}
          ></a>
        </div>
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("react")); // render the content from App inside the HTML element with an id of 'react'
