const API_KEY = "" // Add your unique API Key from OpenAI to run the code!!!

const submitButton = document.querySelector('#submit') // variable to manipulate the Submit button
const answer = document.querySelector('#answer')
const userInput = document.querySelector('input')
const history = document.querySelector('.history')
const button = document.querySelector('button')

function switchUserInput(question) { // activates when a history question is clicked, which ends up replacing the current question
    const theUserInput = document.querySelector('input')
    return theUserInput.value = question
} 

async function messageRetrieval() { // function to send a message to chatGPT and receive a response!
    try {
    const response = await fetch("https://api.openai.com/v1/chat/completions", {
        method: "POST",
        headers: {
            Authorization: `Bearer ${API_KEY}`,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            model: "gpt-3.5-turbo",
            messages: [{
                role: "user",
                content: userInput.value // submit value
            }],
            max_tokens: 75
        })
    })
    const data = await response.json()
    console.log(data)
    answer.textContent = data.choices[0].message.content // retrieval of chatGPT response

    if (data.choices[0].message.content && userInput.value) {
        const newParagraph = document.createElement('p')
        newParagraph.textContent = userInput.value
        newParagraph.addEventListener('click', () => switchUserInput(newParagraph.textContent))
        history.append(newParagraph)
        userInput.value = ""
    }
    }catch(error){
        console.error(error)
    }
}

function clearHistory() {
    window.location.reload();
}

submitButton.addEventListener('click', messageRetrieval) // add the post function to the submit button
button.addEventListener('click', clearHistory)