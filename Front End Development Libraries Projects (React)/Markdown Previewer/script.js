import React from "https://cdn.skypack.dev/react@17.0.1";
import ReactDOM from "https://cdn.skypack.dev/react-dom@17.0.1";

const defaultText = `# Welcome to my React Markdown Previewer!

## This is a sub-heading...
### And here's some other cool stuff:
  
Heres some code, \`<div></div>\`, between 2 backticks.

\`\`\`
// this is multi-line code:

function anotherExample(firstLine, lastLine) {
  if (firstLine == '\`\`\`' && lastLine == '\`\`\`') {
    return multiLineCode;
  }
}
\`\`\`
  
You can also make text **bold**... whoa!
Or _italic_.
Or... wait for it... **_both!_**
And feel free to go crazy ~~crossing stuff out~~.

There's also [links](https://www.freecodecamp.com), and
> Block Quotes!

And if you want to get really crazy, even tables:

Wild Header | Crazy Header | Another Header?
------------ | ------------- | ------------- 
Your content can | be here, and it | can be here....
And here. | Okay. | I think we get it.

- And of course there are lists.
  - Some are bulleted.
     - With different indentation levels.
        - That look like this.


1. And there are numbererd lists too.
1. Use just 1s if you want! 
1. But the list goes on...
- Even if you use dashes or asterisks.
* And last but not least, let's not forget embedded images:

![React Logo w/ Text](https://goo.gl/Umyytc)
`;

marked.setOptions({
  breaks: true
});

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      input: defaultText
    };
    this.handleChange = this.handleChange.bind(this);
  }
  handleChange(event) {
    this.setState((state) => {
      return {
        input: event.target.value
      };
    });
  }

  render() {
    return (
      <body>
        <Editor input={this.state.input} handleChange={this.handleChange} />
        <Preview input={this.state.input} />
      </body>
    );
  }
}

class Editor extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <div id="editorDiv">
        <fieldset id="editorField">Editor</fieldset>
        <textarea
          id="editor"
          value={this.props.input}
          onChange={this.props.handleChange}
        >
          # hello
        </textarea>
      </div>
    );
  }
}

class Preview extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <div id="previewDiv">
        <fieldset id="previewField">Preview</fieldset>
        <div
          id="preview"
          dangerouslySetInnerHTML={{
            __html: marked(this.props.input)
          }}
        ></div>
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("react"));
